package socket;

public interface ISocketConnection {

    void sendMessage(ISocketMessage message);

    void receiveMessage();

    void setUsername(String username);

    String getUsername();

}
