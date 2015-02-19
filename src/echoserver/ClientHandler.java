/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

/**
 *
 * @author Janus
 */
public class ClientHandler extends Thread
{
    
    Scanner input;
    PrintWriter writer;
    EchoServer es;
    
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
        String user;
        
        if (tokens.length < 2)
        {
            closeConnection();
            return;
        }
        
        if (command.equalsIgnoreCase("CONNECT"))
        {
            user = tokens[1];
            es.addUserToClient(user, this);
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
                    sendOnlineMessage();
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
        ArrayList<ClientHandler> clients = new ArrayList<>();
        
    }
    
    private void messageToPersons(String msg, String allPersons)
    {
        String splitter = "[,]";
        String[] tokens = allPersons.split(splitter);

//            for()
    }
    
    private void sendOnlineMessage()
    {
        String usersOnline = es.getUsersOnlineString();
        
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
