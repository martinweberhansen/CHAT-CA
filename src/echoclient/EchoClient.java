package echoclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

public class EchoClient extends Thread implements EchoListener, Observer
{
    Socket socket;
    private int port;
    private String username;
    private InetAddress serverAddress;
    private Scanner input;
    private PrintWriter output;
    List<EchoListener> listeners = new ArrayList<>();
    
    public EchoClient(String ip, int port, String userName) throws UnknownHostException
    {
        this.serverAddress = InetAddress.getByName(ip);
        this.port = port;
        this.username = userName;
        
//        try
//        {
//            connect(ip, port);
//        } catch (UnknownHostException ex)
//        {
//            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex)
//        {
//            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void connect(String address, int port) throws UnknownHostException, IOException
    {
        this.port = port;
        serverAddress = InetAddress.getByName(address);
        socket = new Socket(serverAddress, port);
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);  //Set to true, to get auto flush behaviour
        start();
    }
    
    public void send(String msg)
    {
        output.println(msg);
    }
    
    @Override
    public void run()
    {
        System.out.println("client started..");
        String msg = input.nextLine();
        while (!msg.equals(ProtocolStrings.STOP))
        {
            notifyListeners(msg);
            msg = input.nextLine();
        }
        try
        {
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerEchoListener(EchoListener l)
    {
        listeners.add(l);
    }
    
    public void unRegisterEchoListener(EchoListener l)
    {
        listeners.remove(l);
    }
    
    public void notifyListeners(String msg) //ændret fra private til public pga. test.
    {
        for (EchoListener listener : listeners)
        {
            listener.messageArrived(msg);
        }
    }
    
    @Override
    public void messageArrived(String data)
    {
        output.print(data);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String testMethod(String msg)
    {
        return msg;
    }
}
