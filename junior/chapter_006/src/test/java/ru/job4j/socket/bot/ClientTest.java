package ru.job4j.socket.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    @Test
    public void something() throws IOException {
        Socket socket = mock(Socket.class);

        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        Client client = new Client(socket);
        client.start();

        assertThat(out.toString().trim(), is("Hell"));
    }
}