package ru.job4j.socket.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * TODO Description
 * Created by roman.pogorelov on 10.09.2019
 */
public class Server {
    public static final String EXIT = "exit";
    private Socket socket;

    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port).accept();
    }

    public Server(Socket socket) throws IOException {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String msg;
        do {
            msg = in.readLine();
            if (msg.contains("Hello")) {
                out.println("Hello, dear friend, I'm a oracle.\n");
            } else if (!Objects.equals(Server.EXIT, msg)) {
                out.println("Another cmd from Oracle.\n");
            }
        } while (!Objects.equals(Server.EXIT, msg));
    }

    public static void main(String[] args) throws IOException {
        new Server(1234).start();
    }
}
