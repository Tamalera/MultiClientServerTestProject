import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UltimateMultiClientServer {

    private ServerSocket serverSocket;

//    Start server on specific (free) port. Keep connection open.
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true)
                new SendMessageToClientHandlerHandler(serverSocket.accept()).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }

    }

//    Stop connection.
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    For every new client a new thread is made. As long as Client does not send ".",
//    connection stays open and communication is uphold.
    private static class SendMessageToClientHandlerHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public SendMessageToClientHandlerHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        System.out.println("bye");
                        out.println("bye");
                        break;
                    }
//                    Server simply repeats what client has sent.
                    System.out.println(inputLine);
                    out.println(inputLine);
                }
                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UltimateMultiClientServer server = new UltimateMultiClientServer();
        server.start(5555);
    }
}
