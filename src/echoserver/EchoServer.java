package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.TokenType;
import shared.ProtocolStrings;
import utils.Utils;

public class EchoServer
{
    private static boolean keepRunning = true;
    private static ServerSocket serverSocket;
    private static final Properties properties = Utils.initProperties("server.properties");
    ArrayList<String> userList = new ArrayList<>();
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
                
                ClientHandler ch = new ClientHandler(socket);
                clientList.add(ch);
                ch.start();
            } while (keepRunning);
        } catch (IOException ex)
        {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args)
    {
        new EchoServer().runServer();
    }
    
//    ---------------------------------------------------------------------------------------------
    
    public class ClientHandler extends Thread
    {
        Scanner input;
        PrintWriter writer;
        
        private ClientHandler(Socket socket) throws IOException
        {
            input = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);
        }
        
        public void run()
        {
            String messageLine = input.nextLine();
            String splitter = "[#]";
            String[] tokens = messageLine.split(splitter);
            String command = tokens[0].toUpperCase();
            String user;
            
            if(tokens.length < 2)
            {
                closeConnection();
                return;
            }
            
            if(command.equalsIgnoreCase("CONNECT"))
            {
                user = tokens[1];
                userList.add(user);
                
                String allUsers = userList.get(0);
                for(int i = 1; i < userList.size(); i++)
                {
                     allUsers = allUsers + "," + userList.get(i);
                }
                writer.println("ONLINE#" + allUsers);
            } else
            {
                closeConnection();
                return;
            }
            
            boolean continueClient = true;
            while (continueClient)
            {
                messageLine = input.nextLine();
                splitter = "[#]";
                tokens = messageLine.split(splitter);
                command = tokens[0].toUpperCase();
                switch (command)
                {
                    case "CONNECT":
                        // Send MESSAGE-command to all clients that this client has connected
                        // Send ONLINE-command to all clients with the updated clientList
                        
                        break;
                    case "SEND":
                        // Send MESSAGE-command to all clients or as a personal message, to the specified client:
                        
                        break;
                    case "CLOSE":
                        // Remove this client from clientList.
                        // Send MESSAGE-command too all clients that this client has disconnected
                        // Send ONLINE-command to all clients with the udated clientList
                        // Lastly, close this connection (and thread)
                        
                        removeClient(this, user);
                        writer.println("CLOSE#");
                        closeConnection();
                        continueClient = false;
                        
                        writer.println("MESSAGE#*#" + user + " disconnected.");
                        String listOfUsers = getUserList();
                        writer.println("ONLINE#" + listOfUsers);
                        break;
                    default:
                        removeClient(this, user);
                        closeConnection();
                }
            }
            
            String message = input.nextLine(); //IMPORTANT blocking call
            Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ", message));
            while (!message.equals(ProtocolStrings.STOP))
            {
                writer.println(message.toUpperCase());
                Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ", message.toUpperCase()));
                message = input.nextLine(); //IMPORTANT blocking call
            }
            writer.println(ProtocolStrings.STOP);//Echo the stop message back to the client for a nice closedown
//            socket.close();
            Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Closed a Connection");
        }
        
        private void sendMessage()
        {
            
        }
        
        private void messageToAll(String msg)
        {
            
        }
        
        private void messageToPersons(String msg, String allPersons)
        {
            String splitter = "[,]";
            String[] tokens = allPersons.split(splitter);
            
            for()
        }
        
        private void closeConnection()
        {
            input.close();
            writer.close();
        }
        
        private void removeClient(ClientHandler ch, String user)
        {
            clientList.remove(ch);
            userList.remove(user);
        }
        
        private String getUserList()
        {
            String allUsers = userList.get(0);
            for (int i = 1; i < userList.size(); i++)
            {
                allUsers = allUsers + "," + userList.get(i);
            }
            return allUsers;
        }
    }
    
    
    
    
}
