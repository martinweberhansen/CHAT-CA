package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.text.DefaultCaret;

public class EchoClientGui extends javax.swing.JFrame implements ActionListener
{
    GuiController con = new GuiController(this);
    private ArrayList<String> chatHistory = new ArrayList<>();
    private ArrayList<String> userList = new ArrayList<>();
    
    private boolean online = false;
    
    /**
     * Creates new form EchoClientGui
     */
    public EchoClientGui()
    {
        initComponents();
        jTextFieldMessage.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogNewConnection = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        newConnectionServerAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        newConnectionPort = new javax.swing.JTextField();
        newConnectionConnect = new javax.swing.JButton();
        newConnectionCancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        newConnectionUserName = new javax.swing.JTextField();
        newConnectionInfoMessage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonLoginLogout = new javax.swing.JButton();
        jTextFieldMessage = new javax.swing.JTextField();
        jButtonMessageSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListUsers = new javax.swing.JList();
        jToggleButtonSelectUsers = new javax.swing.JToggleButton();

        jDialogNewConnection.setTitle("New Connection");
        jDialogNewConnection.setMinimumSize(new java.awt.Dimension(405, 325));
        jDialogNewConnection.setResizable(false);

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newConnectionServerAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        newConnectionServerAddress.setText("localhost");
        newConnectionServerAddress.setPreferredSize(new java.awt.Dimension(220, 30));
        jPanel2.add(newConnectionServerAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        jLabel1.setText("Serveraddress:");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 30));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel2.setText("Port:");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        newConnectionPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        newConnectionPort.setText("9090");
        newConnectionPort.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel2.add(newConnectionPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        newConnectionConnect.setText("Connect");
        newConnectionConnect.setPreferredSize(new java.awt.Dimension(120, 30));
        newConnectionConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newConnectionConnectActionPerformed(evt);
            }
        });
        jPanel2.add(newConnectionConnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        newConnectionCancel.setText("Cancel");
        newConnectionCancel.setPreferredSize(new java.awt.Dimension(120, 30));
        newConnectionCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newConnectionCancelActionPerformed(evt);
            }
        });
        jPanel2.add(newConnectionCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jLabel3.setText("Username:");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 30));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        newConnectionUserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        newConnectionUserName.setPreferredSize(new java.awt.Dimension(220, 30));
        jPanel2.add(newConnectionUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        newConnectionInfoMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newConnectionInfoMessage.setPreferredSize(new java.awt.Dimension(360, 25));
        jPanel2.add(newConnectionInfoMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        javax.swing.GroupLayout jDialogNewConnectionLayout = new javax.swing.GroupLayout(jDialogNewConnection.getContentPane());
        jDialogNewConnection.getContentPane().setLayout(jDialogNewConnectionLayout);
        jDialogNewConnectionLayout.setHorizontalGroup(
            jDialogNewConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogNewConnectionLayout.setVerticalGroup(
            jDialogNewConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(605, 625));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonLoginLogout.setText("Login");
        jButtonLoginLogout.setPreferredSize(new java.awt.Dimension(120, 30));
        jButtonLoginLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLoginLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jTextFieldMessage.setPreferredSize(new java.awt.Dimension(490, 30));
        jPanel1.add(jTextFieldMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, -1, -1));

        jButtonMessageSend.setText("Send message");
        jButtonMessageSend.setPreferredSize(new java.awt.Dimension(150, 30));
        jButtonMessageSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMessageSendActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonMessageSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 550, -1, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("Chat");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(490, 460));

        jTextAreaChat.setEditable(false);
        jTextAreaChat.setColumns(20);
        jTextAreaChat.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextAreaChat.setLineWrap(true);
        jTextAreaChat.setRows(5);
        jTextAreaChat.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaChat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(150, 425));

        jListUsers.setPreferredSize(new java.awt.Dimension(150, 450));
        jScrollPane2.setViewportView(jListUsers);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 105, -1, -1));

        jToggleButtonSelectUsers.setText("Select receivers:");
        jToggleButtonSelectUsers.setPreferredSize(new java.awt.Dimension(150, 30));
        jToggleButtonSelectUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonSelectUsersActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonSelectUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMessageSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMessageSendActionPerformed
        String text = jTextFieldMessage.getText();
        if(!text.equalsIgnoreCase(""))
        {
            sendMessage();
        }
    }//GEN-LAST:event_jButtonMessageSendActionPerformed

    private void jButtonLoginLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginLogoutActionPerformed
        if(online)
        {
            // Disconnect - close connection to server
            con.disconnect();
            disconnect();
        } else
        {
            jDialogNewConnection.setVisible(true);
            jDialogNewConnection.setLocationRelativeTo(jTextAreaChat);
        }
    }//GEN-LAST:event_jButtonLoginLogoutActionPerformed

    private void newConnectionCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newConnectionCancelActionPerformed
        jDialogNewConnection.setVisible(false);
    }//GEN-LAST:event_newConnectionCancelActionPerformed

    private void newConnectionConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newConnectionConnectActionPerformed
        String userName = newConnectionUserName.getText().trim();
//        userName = userName.replaceAll(",", "");
        String serverAddress = newConnectionServerAddress.getText().trim();
        String portNumber = newConnectionPort.getText().trim();
        int port = 9090;
        
        if(userName.equalsIgnoreCase(""))
        {
            newConnectionInfoMessage.setText("Please enter a username...");
        } else if(userName.contains(","))
        {
            newConnectionInfoMessage.setText("No commas (,) allowed in username...");
        } else if(userName.contains("#"))
        {
            newConnectionInfoMessage.setText("No hashtags (#) allowed in username...");
        } else
        {
            if (serverAddress.equalsIgnoreCase(""))
            {
                serverAddress = "localhost";
            }
            try
            {
                port = Integer.parseInt(portNumber);
            } catch (Exception en)
            {
                newConnectionInfoMessage.setText("Wrong port...");
                return;
            }
            
            jTextAreaChat.setText("");
            if(con.connect(serverAddress, port, userName))
            {
                connect();
                jDialogNewConnection.setVisible(false);
            } else
            {
                disconnect();
                newConnectionInfoMessage.setText("Could not connect to server...");
            }
        }
    }//GEN-LAST:event_newConnectionConnectActionPerformed

    private void jToggleButtonSelectUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSelectUsersActionPerformed
        if(jToggleButtonSelectUsers.isSelected())
        {
            jListUsers.setSelectedIndex(-1);
            jListUsers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            jListUsers.setSelectionForeground(Color.WHITE);
            jListUsers.setSelectionBackground(Color.LIGHT_GRAY);
        } else
        {
            jListUsers.setSelectedIndex(-1);
            jListUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jListUsers.setSelectionForeground(Color.BLACK);
            jListUsers.setSelectionBackground(Color.WHITE);
        }
    }//GEN-LAST:event_jToggleButtonSelectUsersActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        con.disconnect();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        con.disconnect();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EchoClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EchoClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EchoClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EchoClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EchoClientGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLoginLogout;
    private javax.swing.JButton jButtonMessageSend;
    private javax.swing.JDialog jDialogNewConnection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListUsers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaChat;
    private javax.swing.JTextField jTextFieldMessage;
    private javax.swing.JToggleButton jToggleButtonSelectUsers;
    private javax.swing.JButton newConnectionCancel;
    private javax.swing.JButton newConnectionConnect;
    private javax.swing.JLabel newConnectionInfoMessage;
    private javax.swing.JTextField newConnectionPort;
    private javax.swing.JTextField newConnectionServerAddress;
    private javax.swing.JTextField newConnectionUserName;
    // End of variables declaration//GEN-END:variables
    
    public void updateChat(String msg)
    {
        jTextAreaChat.setRows(jTextAreaChat.getRows()+1);
        jTextAreaChat.append(msg + "\n");
        jTextAreaChat.setCaretPosition(jTextAreaChat.getDocument().getLength());
    }
    
    public void updateUserList(DefaultListModel<String> userlist)
    {
        jListUsers.setModel(userlist);
    }
    
    private void clearUserList()
    {
        DefaultListModel<String> users = new DefaultListModel<>();
        jListUsers.setModel(users);
    }
    
    private void sendMessage()
    {
        String text = jTextFieldMessage.getText();
        String msg = text.trim();
        jTextFieldMessage.setText("");
        
        // Find selected users to send a personal message
        List<String> users = new ArrayList<>();
        if(jToggleButtonSelectUsers.isSelected())
        {
            users = jListUsers.getSelectedValuesList();
        }
        
        con.sendMessage(msg, users);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        sendMessage();
    }
    
    private void connect()
    {
        online = true;
        jButtonLoginLogout.setText("Logout");
        jListUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jToggleButtonSelectUsers.setSelected(false);
        jListUsers.setSelectionForeground(Color.BLACK);
        jListUsers.setSelectionBackground(Color.WHITE);
        DefaultCaret caret = (DefaultCaret)jTextAreaChat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }
    
    private void disconnect()
    {
        clearUserList();
        online = false;
        jButtonLoginLogout.setText("Login");
    }
}