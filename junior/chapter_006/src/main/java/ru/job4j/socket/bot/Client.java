package ru.job4j.socket.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * TODO Description
 * Created by roman.pogorelov on 10.09.2019
 */
public class Client {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 1234;
    private Socket socket;

    public Client() throws IOException {
        this.socket = new Socket(InetAddress.getByName(Client.IP), Client.PORT);
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String answer;
        do {
            out.println("Hell");
            answer = in.readLine();
            while (!(answer == null || answer.isEmpty())) {
                System.out.println(String.format("\t%s", answer));
                answer = in.readLine();
            }
        } while (false);
    }

    public static void main(String[] args) throws IOException {
        new Client().start();
    }
}
