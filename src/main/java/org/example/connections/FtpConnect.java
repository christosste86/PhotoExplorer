package org.example.connections;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class FtpConnect {

    private String server;
    private int port;// FTP port
    private String user; // Replace with your username
    private String pass; // Replace with your password
    private FTPClient ftpClient = new FTPClient();

    public FtpConnect(String server, int port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    public void connect() {
        try {
            this.ftpClient.connect(server, port);
            this.ftpClient.login(user, pass);
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listFiles(){
        String[] files = null;
        try {
            files = this.ftpClient.listNames();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (files != null && files.length > 0) {
            System.out.println("Files:");
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("No files found.");
        }
    }

    public void disconnect(){
        try {
            this.ftpClient.logout();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean workingDirectory(String path){
        try {
            this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.changeWorkingDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createDirectory(boolean workingDirectory, String directoryName){
        try {
            return this.ftpClient.makeDirectory(directoryName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path ftpPath(String folder) {
        String ftpUrl = "ftp://username:password@ftp.example.com/path/to/folder";
        try {
            URI uri = new URI(ftpUrl);
            return Path.of(uri.getPath());
        } catch (URISyntaxException e) {
            return Path.of("");
        }
    }
}
