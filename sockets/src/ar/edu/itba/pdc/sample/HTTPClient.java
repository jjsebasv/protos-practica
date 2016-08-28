package ar.edu.itba.pdc.sample;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Intento de un cliente HTTP que solo solicita un recurso dada una url
 */
public class HTTPClient {
    private static final int BUFFSIZE = 500;

    public static void main(String[] args) {
        String host = "www.infobae.com";
        int port = 80;             // puerto por defecto para HTTP

        String url ="/";
        //Scanner input = new Scanner(System.in);
        //System.out.print("URL: ");
        //url=input.next();

        if (args.length > 1)
            host = args[1];
        if (args.length > 2)
            try {
                port = Integer.getInteger(args[2]);
            } catch (NumberFormatException e) {

            }

        try {
            Socket socket = new Socket(host,port);

            System.out.println("Connected to: " + socket.getInetAddress());

            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("GET " + url + " HTTP/1.1");
            out.println("Host: " + host + "");
            out.println("");

            char received[] = new char[BUFFSIZE];
            while ( in.read(received ) > 0) {
                System.out.print(received);
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
