package org.example.connections;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.example.services.ModifyPhoto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void connect() {
        try {
            // Connect to the FTP server
            this.ftpClient.connect(server, port);
            System.out.println("Connected to FTP server at " + server + ":" + port);

            // Login to the server
            boolean login = this.ftpClient.login(user, pass);
            if (login) {
                System.out.println("Login successful!");

                // (Optional) Set to passive mode if needed for firewall compatibility
                this.ftpClient.enterLocalPassiveMode();

                // (Optional) Verify connection and perform operations
                System.out.println("Working Directory: " + this.ftpClient.printWorkingDirectory());
            } else {
                System.out.println("Login failed. Check username and password.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void listFiles(){
        connect();
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
        disconnect();
    }

    public void disconnect(){
        try {
            // Logout and disconnect from the FTP server
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("Disconnected from FTP server.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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

    public Path ftpPath(String path) {
        String ftpUrl = String.format("ftp://%s:%s@%s:%s%s", this.user, this.pass, this.server , this.port, path);
        System.out.println(ftpUrl);
        try {
            URI uri = new URI(ftpUrl);
            return Path.of(uri.getPath());
        } catch (URISyntaxException e) {
            return Path.of("");
        }
    }


    public List<String> getPhotoList(String ftpPathDirs) {
        List<String> photos = new ArrayList<>();
        try {
            this.ftpClient.changeWorkingDirectory(ftpPathDirs);
                for(FTPFile e : ftpClient.listFiles()){
                    if(isImageFile(e.getName().toLowerCase())){
                        photos.add(ftpPathDirs+"/"+e.getName());
                    }
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }return photos;
    }

    private boolean isImageFile(String fileName) {
        String[] img = {"jpg", "jpeg", "heic"};
        for (String e : img) {
            if (fileName.toLowerCase().contains(e)) {
                return true;
            }
        }return false;
    }


    private Path tempDirecoty(Path destinationPath){
        Path outputPath = Path.of(destinationPath+File.separator+"Temp");
        try {
            if(Files.notExists(outputPath)){
                Files.createDirectory(outputPath);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }return outputPath;
    }


    public void downloadFiles(String ftpPath, Path destinationPath){
        String fileName = null;
        for (String e : getPhotoList(ftpPath)){
            fileName = e.replace(ftpPath+"/", "");
            try (OutputStream outputStream = new FileOutputStream(tempDirecoty(destinationPath).toFile()+File.separator+fileName)){
                this.ftpClient.retrieveFile(e, outputStream);
                ModifyPhoto modifyPhoto = new ModifyPhoto(Path.of(tempDirecoty(destinationPath).toFile()+File.separator+fileName), destinationPath);
                modifyPhoto.moveFile();
                this.ftpClient.deleteFile(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}

