package ru.job4j.socket.bot;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * TODO Description
 * Created by roman.pogorelov on 10.09.2019
 */
public class ServerTest {

    @Test
    public void exit() throws IOException {
        String expected = "";
        String input = new StringJoiner(System.lineSeparator())
                .add("exit")
                .toString();
        testServer(input, expected);
    }

    @Test
    public void hello() throws IOException {
        String expected = "Hello, dear friend, I'm a oracle.";
        String input = new StringJoiner(System.lineSeparator())
                .add("Hello man")
                .add("exit")
                .toString();
        testServer(input, expected);
    }

    @Test
    public void anotherMsg() throws IOException {
        String expected = "Another cmd from Oracle.";
        String input = new StringJoiner(System.lineSeparator())
                .add("man")
                .add("exit")
                .toString();
        testServer(input, expected);
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        Assert.assertThat(out.toString().strip(), is(expected));
    }
}