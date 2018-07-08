package schoolchat.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class ChatClient {
    static final String DEFAULT_HOST = "localhost";
    static final int DEFAULT_PORT = 2019;

    public static void main(String args[]) throws IOException {
        try (Socket socket = new Socket(DEFAULT_HOST, 2018)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
            Thread thread = new Thread(new LocalThread(in));
            thread.start();
            String name = "client" + new Random().nextInt(100);
            out.println(name);
            String fuser, fserver;
            while ((fuser = inu.readLine()) != null) {
                out.println(fuser);
                if (fuser.equalsIgnoreCase("close")) break;
                if (fuser.equalsIgnoreCase("exit")) break;
            }
        }
    }

    static class LocalThread implements Runnable {
        private BufferedReader in;
        public LocalThread(BufferedReader in) {
            this.in = in;
        }
        @Override
        public void run() {
            while (true) {
                String fserver = null;
                try {
                    fserver = in.readLine();
                    System.out.println(fserver);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
