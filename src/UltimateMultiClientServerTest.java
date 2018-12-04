import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class UltimateMultiClientServerTest {

    private static int port;

    @BeforeClass
    public static void start() throws InterruptedException, IOException {

        // Take an available port
        ServerSocket s = new ServerSocket(0);
        port = s.getLocalPort();
        s.close();

        Executors.newSingleThreadExecutor().submit(() -> new UltimateMultiClientServer().start(port));
        Thread.sleep(500);
    }

    @Test
    public void givenClient1_whenServerResponds_thenCorrect() {
        SendMessageClient client = new SendMessageClient();
        client.startConnection("127.0.0.1", port);
        String msg1 = client.sendMessage("hello");
        String msg2 = client.sendMessage("world");
        String terminate = client.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
        client.stopConnection();
    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() {
        SendMessageClient client = new SendMessageClient();
        client.startConnection("127.0.0.1", port);
        String msg1 = client.sendMessage("hello");
        String msg2 = client.sendMessage("world");
        String terminate = client.sendMessage(".");
        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
        client.stopConnection();
    }

    @Test
    public void givenClient3_whenServerResponds_thenCorrect() {
        SendMessageClient client = new SendMessageClient();
        client.startConnection("127.0.0.1", port);
        String msg1 = client.sendMessage("hello");
        String msg2 = client.sendMessage("world");
        String terminate = client.sendMessage(".");
        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
        client.stopConnection();
    }
}