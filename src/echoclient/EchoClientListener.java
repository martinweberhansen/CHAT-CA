
package echoclient;

/**
 * @author martin weber
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoClientListener implements Runnable {

    private static boolean running = true;
    private final BufferedReader input;
    private final PrintWriter output;
    private Collection<EchoClientListenerInterface> listeners;
    private String message;
    private String online;

    public EchoClientListener(Socket socket) throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(System.out, true);
        listeners = new ArrayList<>();
    }

    public void register(EchoClientListenerInterface l) 
    {
        listeners.add(l);
    }

    public void unregister(EchoClientListenerInterface l) 
    {
        listeners.remove(l);
    }

    @Override
    public void run() 
    {
        while (running) 
        {
            try 
            {
                String l = input.readLine();
                String[] parts = l.split("#");
                
                switch (parts[0]) 
                {
                    case "MESSAGE":
                        setMessage(parts[0] + " " + parts[1] + " " + parts[2]);
                        for (EchoClientListenerInterface listener : listeners) 
                        {
                            listener.onMessage(parts[1], parts[2]);
                        }
                        break;
                    case "ONLINE":
                        System.out.println("hejfraonline");
                        setOnline(parts[0] + " " +parts[1]);                     
                           String[] online = parts[1].split(",");
                           for(EchoClientListenerInterface listener : listeners)
                           {
                           listener.onList(online);
                           input.readLine();
                           }
                           break;
                        
                          case "CLOSE":
                            
                            output.close();
                            input.close();
                            running = false;
                            break;
                    default:
                        System.out.println("MESSAGE FROM SERVER IS NOT VALID FOR THIS CLIENT");
                }
                System.out.println(l);
            } catch (IOException ex) {
                Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getMessage() 
    {
        return message;
    }

    public void setMessage(String Message) 
    {
        this.message = Message;
    }

    public String getOnline() 
    {
        return online;
    }

    public void setOnline(String online) 
    {
        this.online = online;
    }

}