package schoolchat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class ChatServer {
    static final int PORT = 2018;

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Collection<ThreadServer> clients = new ArrayList<>();
        while (true) {
            Socket client = serverSocket.accept();
            ThreadServer threadServer = new ThreadServer(client, clients);
            clients.add(threadServer);
            Thread thread = new Thread(threadServer);
            thread.start();
        }
    }

    static class ThreadServer implements Runnable {
        private Socket client;
        private Collection<ThreadServer> clients;
        private PrintWriter out;
        private BufferedReader in;

        public ThreadServer(Socket client, Collection<ThreadServer> clients) {
            this.client = client;
            this.clients = clients;
        }

        @Override
        public void run() {
            try {
                System.out.println(clients.size());
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(),true);
                String input;
                String clientName = in.readLine();
                sendAll("System >> " + clientName + " has connected to chat");
                while ((input = in.readLine()) != null) {
                    if (input.equalsIgnoreCase("exit")) break;
                    sendAll(clientName + " >> " + input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void addClient(ThreadServer threadServer) {
            clients.add(threadServer);
        }

        private void sendAll(String message) {
            for (ThreadServer threadServer : clients) {
                threadServer.out.println(message);
            }
        }
    }
}
