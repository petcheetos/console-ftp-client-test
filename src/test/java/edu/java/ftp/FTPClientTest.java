package edu.java.ftp;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FTPClientTest {
    private FTPClient ftpClient;

    @BeforeMethod
    public void setUp() {
        //Указать адрес FTP-сервера, логин и пароль
        ftpClient = new FTPClient("127.0.0.1", "testUser", "12345");
        assertTrue(ftpClient.connect());
    }

    @AfterMethod
    public void tearDown() {
        ftpClient.disconnect();
    }

    @Test
    public void testConnect() {
        assertTrue(true);
    }

    @Test
    public void testGetDataFromFilePassiveMode() {
        String testData = "Test data for FTP\n";
        String data = ftpClient.getDataFromFile("test.txt", true);
        assertEquals(data, testData);
    }

    @Test
    public void testSaveDataToFilePassiveMode() {
        String testData = "Test data for FTP\n";

        boolean result = ftpClient.saveDataToFile(testData, "test_upload.txt", true);
        assertTrue(result);

        String data = ftpClient.getDataFromFile("test_upload.txt", true);
        assertEquals(data, testData);
    }

    @Test
    public void testDisconnect() {
        ftpClient.disconnect();
        assertTrue(true);
    }
}