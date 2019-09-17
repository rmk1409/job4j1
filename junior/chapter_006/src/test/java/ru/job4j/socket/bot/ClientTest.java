package ru.job4j.socket.bot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * TODO Description
 * Created by roman.pogorelov on 11.09.2019
 */
public class ClientTest {


    /**
     * Вывод в консоль по дефолту
     */
    private final PrintStream stdout = System.out;
    /**
     * Буфер для результата.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void beforeTest() {
        System.out.println("Starting test...");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void afterTest() {
        System.setOut(this.stdout);
        System.out.println("Ending test...");
    }

    @Test
    public void something() throws IOException {
        Socket socket = mock(Socket.class);

        ByteArrayInputStream in = new ByteArrayInputStream("Hello, dear friend, I'm a oracle.".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        Client client = new Client(socket);
        client.start();

        assertThat(this.out.toString(), is("\tHello, dear friend, I'm a oracle." + System.lineSeparator()));
    }
}