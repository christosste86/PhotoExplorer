package org.example.connections;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class FtpConnect {

    private String server;
    private int port;// FTP port
    private String user; // Replace with your username
    private String pass; // Replace with your password

    public FtpConnect(String server, int port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        connect();
    }

    public void connect() {

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
                //System.out.println("Files:");
                for (String file : files) {
                    //System.out.println(file);
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
