package ar.edu.itba.pdc.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

class DateClient {
    public static void main(String[] args) {
        String host = "time-c.nist.gov";   // no conectarse nuevamente en menos de 4 segundos o bloquea
        int port = 13;             // puerto de daytime

        if (args.length > 1)
            host = args[1];
        if (args.length > 2)
            try {
                port = Integer.getInteger(args[2]);
            } catch (NumberFormatException e) {

            }

        try {
            // Se abre un socket conectado al servidor y al puerto
            Socket socket = new Socket(host, port);
            System.out.println("Socket Abierto.");

            // Se consigue el canal de entrada
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            System.out.println("Host: " + socket.getInetAddress());
            System.out.println("Hora actual : " );
            String line;
            while ( (line = input.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Hora actual con la clase date:" + new Date());

            // Se cierra el canal de entrada
            input.close();

            // Se cierra el socket
            socket.close();
        } catch (UnknownHostException e) {
            System.out.println(e);
            System.out.println("No se encontró el host " + host);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}