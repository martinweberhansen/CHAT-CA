/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import echoclient.EchoClient;
import echoclient.EchoListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Janus
 */
public class GuiController implements EchoListener
{
    EchoClient client;
    EchoClientGui echoGUI;
    // Azure-ip:   137.117.216.126
    // Port:       8080
    
    public GuiController(EchoClientGui ecg)
    {
        this.echoGUI = ecg;
    }
    
    public boolean connect(String serverAddress, int port, String userName)
    {
        try
        {
            client = new EchoClient("localhost", 9090, "Anonymous");
            client.connect(serverAddress, port, userName);
        } catch (IOException ex)
        {
            Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        client.registerEchoListener(this);
        client.send("CONNECT#" + userName);
        return true;
    }
    
    public void disconnect()
    {
        client.unRegisterEchoListener(this);
        client.send("CLOSE#");
        updateChat("", "--- Disconnected ---");
    }
    
    @Override
    public void messageArrived(String data)
    {
        handleMessage(data);
    }
    
    public void sendMessage(String msg, List<String> users)
    {
        String sendMSG;
        if(users.size() > 0)
        {
            String usersToString = users.get(0);
            for(int i = 1; i < users.size(); i++)
            {
                usersToString = usersToString + "," + users.get(i);
            }
            sendMSG = "SEND#" + usersToString + "#" + msg;
        } else
        {
            sendMSG = "SEND#*#" + msg;
        }
        System.out.println("Sending message to client/server:   " + sendMSG);
        client.send(sendMSG);
    }
    
    private void handleMessage(String data)
    {
        String splitter = "[#]";
        String[] tokens = data.split(splitter);
        String command = tokens[0].toUpperCase();
        
        switch (command)
        {
            case "ONLINE":
                String users = "";
                System.out.println("GuiController - handleMessage: " + tokens[1]);
                try
                {
                    users = tokens[1];
                } catch (Exception a)
                {
                    System.out.println("Error in handleMessage() - converting ONLINE to String");
                }
                updateUserList(users);
                break;
            case "MESSAGE":
                String sender = "";
                String msg = "";
                try
                {
                    sender = tokens[1];
                    msg = tokens[2];
                } catch (Exception a)
                {
                    System.out.println("Error in handleMessage() - converting MESSAGE to String");
                }
                updateChat(sender, msg);
                break;
            case "CLOSE":
                break;
            default:
                break;
        }
    }
    
    private void updateChat(String sender, String msg)
    {
        String updateMessage = "";
        if(sender.equalsIgnoreCase(""))
        {
            updateMessage = msg;
        } else
        {
            updateMessage = "<" + sender + "> " + msg;
        }
        echoGUI.updateChat(updateMessage);
    }
    
    private void updateUserList(String users)
    {
        String splitter = "[,]";
        String[] tokens = users.split(splitter);
        Arrays.sort(tokens);
        System.out.println("Online users: " + tokens[0]);
        
        DefaultListModel userlist = new DefaultListModel();
        for(int i = 0; i < tokens.length; i++)
        {
            userlist.addElement(tokens[i]);
        }
        echoGUI.updateUserList(userlist);
    }
}
