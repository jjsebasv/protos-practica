package ar.edu.itba.pdc.sample;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class IPFinder {
    public static void main(String[] args) {
        String host;
        Scanner input = new Scanner(System.in);
        InetAddress address;
        System.out.print("\n\nEnter host name: ");
        host = input.next();
        try {
            address = InetAddress.getByName(host);
            System.out.println("toString: " + address.toString());
            System.out.println("Host name: " + address.getHostName());
            System.out.println("Canonical name: " + address.getCanonicalHostName());

            System.out.printf("Addres: %d %d %d %d\n", address.getAddress()[0], address.getAddress()[1], address.getAddress()[2], address.getAddress()[3]);
            System.out.println("Is reachable: " + address.isReachable(5000));
        } catch (UnknownHostException uhEx) {
            System.out.println("Could not find " + host);
        } catch (IOException e) {
            System.out.println("not reachable");;
        }
    }
}