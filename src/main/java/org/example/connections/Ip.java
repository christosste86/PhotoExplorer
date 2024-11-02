package org.example.connections;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Ip {

    private List<String> activeIpAddresses = new ArrayList<>();

    public Ip() {
        activeIpAddresses();
    }

    public void activeIpAddresses() {
        for (int y = 0; y < 1; y++) {
            for (int i = 2; i < 255; i++) {
                String ipAddress = "192.168."+y+"."+i; // Replace with the IP you want to ping
                int timeout = 500; // Timeout in milliseconds (5 seconds)

                try {
                    InetAddress inet = InetAddress.getByName(ipAddress);
                    if (inet.isReachable(timeout)) {
                        this.activeIpAddresses.add(ipAddress);
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}