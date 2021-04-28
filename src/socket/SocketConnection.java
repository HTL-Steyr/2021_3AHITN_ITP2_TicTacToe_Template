package socket;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketConnection implements ISocketConnection, ISocketPublisher {
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private int port = 0;
    private String host = "";

    private Socket socket = null;
    private Scanner input = null;
    private PrintWriter output = null;

    private boolean running = false;

    private String username = "";

    private final ArrayList<ISocketSubscriber> subscribers = new ArrayList<>();

    public SocketConnection(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            socket = new Socket(host, port);
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
        }
    }

    public SocketConnection(Socket connection) {
        this.host = connection.getInetAddress().getHostAddress();
        this.port = connection.getPort();

        try {
            socket = connection;
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
        }
    }

    public void start() {
        running = true;

        receiveMessage(); // start new Thread for receiving messages
    }

    public void stop() {
        running = false;
    }

    @Override
    public void sendMessage(ISocketMessage message) {
        executor.execute( // the executor handles the threads in the right order! --> not important for any test!
                new Thread() { // new thread
                    @Override
                    public void run() { // every thread needs a run - method

                        // send message in a new thread!
                        output.println(message.getMessage());
                    }
                }
        ); // start thread immediately
    }

    @Override
    public void receiveMessage() {
        new Thread() { // new thread
            @Override
            public void run() { // every thread needs a run - method
                while (running) { // receive messages in an endless-loop
                    String msg = input.nextLine();

                    // this method works asynchronously
                    notifySubscribers(new SocketMessage(SocketConnection.this, msg)); // access outer class "SocketConnection" and get "this" - reference
                }
            }
        }.start();
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void notifySubscribers(ISocketMessage msg) {
        for (ISocketSubscriber sub : subscribers) {
            sub.messageReceived(msg);
        }
    }

    @Override
    public void addSubscriber(ISocketSubscriber sub) {
        if (!subscribers.contains(sub)) {
            subscribers.add(sub);
        }
    }

    @Override
    public void removeSubscriber(ISocketSubscriber sub) {
        subscribers.remove(sub);
    }
}
