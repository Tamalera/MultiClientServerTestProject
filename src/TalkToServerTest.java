import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TalkToServerTest {

    TalkToClient client;

    private static int port = 6666;

    @Before
    public void init() throws IOException {
        client = new TalkToClient();
        client.startConnection("127.0.0.1", port);

    }

    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
        String response = client.sendMessage("hello server");
        assertEquals("hello client", response);
    }

    @After
    public void finish() throws IOException {
        client.stopConnection();
    }

}