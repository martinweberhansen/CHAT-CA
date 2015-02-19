package echoserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

public class EchoServer
{
    private static boolean keepRunning = true;
    private static ServerSocket serverSocket;
    private static final Properties properties = Utils.initProperties("server.properties");
    Map<String,ClientHandler> userList = new HashMap<String,ClientHandler>();
    ArrayList<ClientHandler> clientList = new ArrayList<>();
    
    public static void stopServer()
    {
        keepRunning = false;
    }
    
    private void runServer()
    {
        int port = Integer.parseInt(properties.getProperty("port"));
        String ip = properties.getProperty("serverIp");
        
        Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Sever started. Listening on: " + port + ", bound to: " + ip);
        try
        {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ip, port));
            do
            {
                Socket socket = serverSocket.accept(); //Important Blocking call
                Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Connected to a client");
                
                ClientHandler ch = new ClientHandler(socket, this);
                clientList.add(ch);
                ch.start();
            } while (keepRunning);
        } catch (IOException ex)
        {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean addUserToClient(String user, ClientHandler ch)
    {
        if(userList.containsKey(user))
        {
            return false;
        } else
        {
            userList.put(user, ch);
            return true;
        }
    }
    
    public void removeClient(String user, ClientHandler ch)
    {
        userList.remove(user, ch);
    }
    
    public String getUsersOnlineString()
    {
        ArrayList<String> list = new ArrayList<>();
        Iterator iterator = userList.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            list.add("" + mapEntry.getKey());
        }
        
        String returnString = "";
        if(list.size() > 0)
        {
            returnString = list.get(0);
            for(int i = 1; i < list.size(); i++)
            {
                returnString = list.get(i);
            }
        }
        return returnString;
    }
    
    public ArrayList<ClientHandler> getClientsOnline()
    {
        ArrayList<ClientHandler> returnList = new ArrayList<>();
        Iterator iterator = userList.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            returnList.add((ClientHandler) mapEntry.getValue());
        }
        return returnList;
    }
    
    public ArrayList<ClientHandler> getClientsFromUserNames(ArrayList<String> userNames)
    {
        ArrayList<ClientHandler> returnList = new ArrayList<>();
        Iterator iterator = userList.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            String user = mapEntry.getKey().toString();
            if(userNames.contains(user))
            {
                returnList.add((ClientHandler) mapEntry.getValue());
            }
        }
        return returnList;
    }
    
    public static void main(String[] args)
    {
        new EchoServer().runServer();
    }
}
