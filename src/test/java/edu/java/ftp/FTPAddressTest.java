package edu.java.ftp;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FTPAddressTest {

    @Test
    public void testConstructor() {
        FTPAddress address = new FTPAddress("127.0.0.1", 21);
        assertEquals(address.host, "127.0.0.1");
        assertEquals(address.port, 21);
    }

    @Test
    public void testCreateValidResponse() throws IOException {
        String response = "227 Entering Passive Mode (192,168,1,1,7,138)";
        FTPAddress address = FTPAddress.create(response);
        assertEquals(address.host, "192.168.1.1");
        assertEquals(address.port, 1930);
    }

    @Test
    public void testCreateInvalidResponse() {
        String response = "227 Invalid Response (192,168,1,1,7)";
        assertThrows(IOException.class, () -> FTPAddress.create(response));
    }

    @Test
    public void testToString() {
        FTPAddress address = new FTPAddress("192.168.1.1", 1930);
        assertEquals(address.toString(), "192,168,1,1,7,138");
    }
}
