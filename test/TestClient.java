import echoclient.EchoClient;
import echoserver.EchoServer;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Lars Mortensen
 */
public class TestClient {
  
  public TestClient() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    new Thread(new Runnable(){
      @Override
      public void run() {
        EchoServer.main(null);
      }
    }).start();
  }
  
  @AfterClass
  public static void tearDownClass() {
    EchoServer.stopServer();
  }
  
  @Before
  public void setUp() {
  }
  
  @Test
  public void send() throws IOException{
    EchoClient client = new EchoClient("ip", 0, "Username");
    client.registerEchoListener(client);
    client.connect("localhost",9090,"Username");
    client.start();
    client.messageArrived("MESSAGE#Username#Hello");
    client.send("SEND#UserName#Hello");
    
    
    assertEquals("HELLO", client.testMethod("fjiwjf"));
    assertEquals("HELLO", client.messageArrived("MESSAGE#Username#Hello"));
  }
}
