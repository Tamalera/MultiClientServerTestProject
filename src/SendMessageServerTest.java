import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SendMessageServerTest {

    private TalkToClient client = new TalkToClient();

    @Before
    public void init() throws IOException {
        client.startConnection("127.0.0.1", 4444);
    }

    @After
    public void tearDown() throws IOException {
        client.stopConnection();
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        String resp3 = client.sendMessage("!");
        String resp4 = client.sendMessage(".");
        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("!", resp3);
        assertEquals("good bye", resp4);
    }

}