package socket;

public class SocketMessage implements ISocketMessage {
    private ISocketConnection source = null;
    private String message = "";

    public SocketMessage(ISocketConnection source, String message) {
        this.source = source;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public ISocketConnection getSource() {
        return source;
    }

    @Override
    public void setSource(ISocketConnection src) {
        this.source = src;
    }
}
