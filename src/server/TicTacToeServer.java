package server;

import socket.SocketConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TicTacToeServer {
    private int port = 0;

    private final ArrayList<SocketConnection> clients = new ArrayList<>();
    private ServerSocket server = null;

    public TicTacToeServer(int port) throws IOException {
        this.port = port;

        server = new ServerSocket(port);
        handleConnections();
    }

    private void handleConnections() {
        try {
            System.out.println("Server started! Listening for incoming connections...");

            while (true) {

                Socket s = server.accept();
                System.out.println("New incoming connection: " + s.getInetAddress().getHostAddress());

                SocketConnection client = new SocketConnection(s);

                /**
                 * @ToDo
                 * Add necessary subscribers and handle gameplay
                 */

                clients.add(client);

                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        TicTacToeServer myServer = new TicTacToeServer(2612);
    }

}
