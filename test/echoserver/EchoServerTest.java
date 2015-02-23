/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Janus
 */
public class EchoServerTest {
    
    public EchoServerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of stopServer method, of class EchoServer.
     */
    @Test
    public void testStopServer() {
        System.out.println("stopServer");
        EchoServer.stopServer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUserToClient method, of class EchoServer.
     */
    @Test
    public void testAddUserToClient() {
        System.out.println("addUserToClient");
        String user = "";
        ClientHandler ch = null;
        EchoServer instance = new EchoServer();
        boolean expResult = false;
        boolean result = instance.addUserToClient(user, ch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClient method, of class EchoServer.
     */
    @Test
    public void testRemoveClient() {
        System.out.println("removeClient");
        String user = "";
        ClientHandler ch = null;
        EchoServer instance = new EchoServer();
        instance.removeClient(user, ch);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsersOnlineString method, of class EchoServer.
     */
    @Test
    public void testGetUsersOnlineString() {
        System.out.println("getUsersOnlineString");
        EchoServer instance = new EchoServer();
        String expResult = "";
        String result = instance.getUsersOnlineString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientsOnline method, of class EchoServer.
     */
    @Test
    public void testGetClientsOnline() {
        System.out.println("getClientsOnline");
        EchoServer instance = new EchoServer();
        ArrayList<ClientHandler> expResult = null;
        ArrayList<ClientHandler> result = instance.getClientsOnline();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientsFromUserNames method, of class EchoServer.
     */
    @Test
    public void testGetClientsFromUserNames() {
        System.out.println("getClientsFromUserNames");
        ArrayList<String> userNames = null;
        EchoServer instance = new EchoServer();
        ArrayList<ClientHandler> expResult = null;
        ArrayList<ClientHandler> result = instance.getClientsFromUserNames(userNames);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
