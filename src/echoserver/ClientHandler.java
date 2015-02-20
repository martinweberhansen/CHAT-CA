
package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Thread
{
    Scanner input;
    PrintWriter writer;
    EchoServer es;
    String userName;
    
    ClientHandler(Socket socket, EchoServer echo) throws IOException
    {
        input = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
        es = echo;
    }
    
    public void run()
    {
        String messageLine = input.nextLine();
        String splitter = "[#]";
        String[] tokens = messageLine.split(splitter);
        String command = tokens[0].toUpperCase();
        
        if (tokens.length < 2)
        {
            closeConnection();
            return;
        }
        
        if (command.equalsIgnoreCase("CONNECT"))
        {
            userName = tokens[1];
            es.addUserToClient(userName, this);
            sendOnlineMessage();
        } else {
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
                case "SEND":
                    // Send MESSAGE-command to all clients or as a personal message, to the specified client:
                    if(tokens.length > 2)
                    {
                        String persons = tokens[1];
                        String content = tokens[2];
                        if (persons.contains("*"))
                        {
                            messageToAll(2, userName, content);
                        } else
                        {
                            messageToPersons(userName, persons, content);
                        }
                    }
                    break;
                case "CLOSE":
                    removeClient(this, userName);
                    sendMessage("CLOSE#");
                    continueClient = false;
                    break;
                default:
                    removeClient(this, userName);
                    sendMessage("CLOSE#");
                    continueClient = false;
                    break;
            }
        }
        closeConnection();
//        String message = input.nextLine(); //IMPORTANT blocking call
//        Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ", message));
//        while (!message.equals(ProtocolStrings.STOP))
//        {
//            writer.println(message.toUpperCase());
//            Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ", message.toUpperCase()));
//            message = input.nextLine(); //IMPORTANT blocking call
//        }
//        writer.println(ProtocolStrings.STOP);//Echo the stop message back to the client for a nice closedown
//        Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Closed a Connection");
    }
    
    private void sendMessage(String msg)
    {
        writer.println(msg);
    }
    
    private void messageToAll(int type, String user, String msg)
    {
        ArrayList<ClientHandler> clients = es.getClientsOnline();
        switch (type)
        {
            case 1: // ONLINE-command
                for(ClientHandler ch : clients)
                {
                    ch.sendMessage("ONLINE#" + msg);
                }
                break;
            case 2: // MESSAGE-command
                for(ClientHandler ch : clients)
                {
                    ch.sendMessage("MESSAGE#" + user + "#" + msg);
                }
                break;
        }
    }
    
    private void messageToPersons(String user, String allPersons, String msg)
    {
        String splitter = "[,]";
        String[] tokens = allPersons.split(splitter);
        ArrayList<String> usersList = new ArrayList<>();
        
        for(int i = 0; i < tokens.length; i++)
        {
            usersList.add(tokens[i]);
        }
        usersList.add(user);
        
        ArrayList<ClientHandler> clientsOnline = es.getClientsFromUserNames(usersList);
        
        for(ClientHandler ch : clientsOnline)
        {
            ch.sendMessage("MESSAGE#" + user + "#" + msg);
        }
    }
    
    private void sendOnlineMessage()
    {
        String usersOnline = es.getUsersOnlineString();
        messageToAll(1, null, usersOnline);
    }
    
    private void closeConnection()
    {
        input.close();
        writer.close();
    }
    
    private void removeClient(ClientHandler ch, String user)
    {
        es.removeClient(user, ch);
        messageToAll(2, userName, (userName + " disconnected."));
        sendOnlineMessage();
    }
}
