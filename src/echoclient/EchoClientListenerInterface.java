
package echoclient;

/**
 * @author Martin 
 */
public interface EchoClientListenerInterface {
    public void onMessage(String name, String msg);
    public void onList(String[] list);
}
