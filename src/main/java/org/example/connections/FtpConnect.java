package org.example.connections;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class FtpConnect {
    public static void main(String[] args) {
        String server = "192.168.0.198"; // Replace with your server
        int port = 2221; // FTP port
        String user = "christos"; // Replace with your username
        String pass = "android"; // Replace with your password

        FTPClient ftpClient = new FTPClient();
        try {
            // Connect to the FTP server
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // List files in the directory
            String[] files = ftpClient.listNames();
            if (files != null && files.length > 0) {
                System.out.println("Files:");
                for (String file : files) {
                    System.out.println(file);
                }
            } else {
                System.out.println("No files found.");
            }

            // Logout and disconnect
            ftpClient.logout();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
