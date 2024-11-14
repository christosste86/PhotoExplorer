package org.example.connections;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class IpScanner {

    private final List<String> activeIpAddresses = new ArrayList<>();

    public IpScanner() {
    }

    public void scanIpAddresses() {
        for (int y = 0; y <= 1; y++) {
            for (int i = 2; i < 255; i++) {
                String ipAddress = "192.168." + y + "." + i; // Replace with the IP you want to ping
                int timeout = 1000; // Timeout in milliseconds (5 seconds)
                try {
                    InetAddress inet = InetAddress.getByName(ipAddress);
                    System.out.println(ipAddress);
                    if (inet.isReachable(timeout)) {
                        this.activeIpAddresses.add(ipAddress);
                        System.out.println("active: " + ipAddress);
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        IpScanner ip = new IpScanner();
        ip.scanIpAddresses();
        System.out.println(ip.activeIpAddresses);
    }
}
