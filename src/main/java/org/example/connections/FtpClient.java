package org.example.connections;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FtpClient implements Verifications {

    JsonFile jsonFile = new JsonFile();

    private final String server = jsonFile.getFtpClientServer();
    private final int port = jsonFile.getFtpClientPort();// FTP port
    private final String user = jsonFile.getFtpClientUser(); // Replace with your username
    private final String pass = jsonFile.getFtpClientPass(); // Replace with your password
    private final FTPClient ftpClient = new FTPClient();
    private final String ftpDirPath = jsonFile.getFtpTransferDirPath();
    private final String destinationDirPath = jsonFile.getFtpTransferDestinationDirPath();
    private final boolean removeFileFromFtp = jsonFile.getFtpTransferRemoveFileFromFtp();
    private final List<String> listOfAllFiles = new ArrayList<>();
    private List<String> listOfPhotoFiles = new ArrayList<>();
    private List<String> listOfVideoFiles = new ArrayList<>();

    public FtpClient() {
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public List<String> getListOfAllFiles() {
        return listOfAllFiles;
    }

    public List<String> getListOfPhotoFiles() {
        return listOfPhotoFiles;
    }

    public List<String> getListOfVideoFiles() {
        return listOfVideoFiles;
    }

    //login to the FTP Server
    public void connect() {
        try {
            this.ftpClient.connect(this.server, this.port);
            boolean login = this.ftpClient.login(this.user, this.pass);
            if (login) {
                System.out.println("Login successful!");
                this.ftpClient.enterLocalPassiveMode();
                System.out.println("Working Directory: " + this.ftpClient.printWorkingDirectory());
            } else {
                System.out.println("Login failed. Check username and password.");
            }
        } catch (IOException ignored) {
        }
    }

    // Logout and disconnect from the FTP server
    public void disconnect(){
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("Disconnected from FTP server.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //change ftp working directory
    private void changeWorkingDirectory(){
        try {
            boolean dirExist = ftpClient.changeWorkingDirectory(this.ftpDirPath);
            System.out.println(dirExist ? "cd "+ this.ftpDirPath : "Directory not exist" );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //List of AllFiles in Specific directory
    public void containListOfAllFiles(){
        try {
            changeWorkingDirectory();
            String[] files = this.ftpClient.listNames();
            if(files !=null && files.length > 0){
                this.listOfAllFiles.addAll(Arrays.asList(files));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        };
    }

    //contain Photos files from Specific directory
    public void containListOfPhotoFiles(){
        this.listOfPhotoFiles = this.listOfAllFiles
                .stream()
                .filter(this::isImgFile)
                .toList();
    }

    //List of Videos in Specific directory
    public void containListOfVideoFiles(){
        this.listOfVideoFiles = this.listOfAllFiles
                .stream()
                .filter(this::isVideoFile)
                .toList();
    }

    //set to binary file type to and buffer size for better transferring files;
    private void setToBinaryFileTypeAndBufferSize(int bufferSizeToMb){
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(bufferSizeToMb * 1024 * 1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Template for download files From Specific directory to destination folder/Temp
    private void downloadFiles(List<String> listOfFiles){
        mkdirTemp();
        setToBinaryFileTypeAndBufferSize(1);
        listOfFiles.forEach(ftpFileName -> {
        try(FileOutputStream outputStream = new FileOutputStream(destinationTempFilePath(ftpFileName))){
                    try {
                        if(!isFileExist(ftpFileName)) {
                            boolean isDownload = this.ftpClient.retrieveFile(ftpFileName, outputStream);
                            if (isDownload) {
                                if (this.removeFileFromFtp) {
                                    removeFile(ftpFileName);
                                }
                            }
                            System.out.println(isDownload ? ftpFileName + " downloaded" : ftpFileName + " canceled");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void downloadVideoFiles(){
        containListOfVideoFiles();
        downloadFiles(this.listOfVideoFiles);
    }

    public void downloadPhotoFiles(){
        containListOfPhotoFiles();
        downloadFiles(this.listOfPhotoFiles);
    }

    public void downloadAllFiles(){
        containListOfAllFiles();
        downloadFiles(this.listOfAllFiles);
    }


    //prepare path of folder for destination
    private String destinationTempDirPath(){
        return String.format("%s%s%s",
                this.destinationDirPath,
                File.separator,
                "Temp");
    }

    //prepare path of file for destination
    private String destinationTempFilePath(String ftpFileName){
        return String.format("%s%s%s",
                destinationTempDirPath(),
                File.separator,
                ftpFileName);
    }

    //create Temp file if not exist
    private void mkdirTemp() {
        if (Files.notExists(Path.of(this.destinationDirPath))) {
            try {
                Files.createDirectory(Path.of(this.destinationDirPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //remove file from ftp client
    private void removeFile(String ftpFileName){
        try {
            boolean isRemoved = this.ftpClient.deleteFile(ftpFileName);
            System.out.println(isRemoved ? ftpFileName+" removed from Ftp Server" : "canceled remove file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //check if file exist
    private boolean isFileExist(String ftpFileName) {
        try {
            if (Files.exists(Path.of(destinationTempFilePath(ftpFileName)))) {
                if (this.ftpClient.mlistFile(ftpFileName).getSize() == Files.size(Path.of(destinationTempFilePath(ftpFileName)))) {
                    return true;
                }
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }return false;
    }

    public static void main(String[] args) {
        FtpClient ftpClient = new FtpClient();
        ftpClient.connect();
        ftpClient.downloadPhotoFiles();
        ftpClient.disconnect();
    }
}

