package org.example.connections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;
import java.util.List;

public class FileExplorer implements Verifications{

    JsonFile jsonFile = new JsonFile();

    private Path sourceDirectory = Path.of(jsonFile.getFileExplorerSourceDirectory());
    private List<Path> listOfPhotosFiles = new ArrayList<>();
    private List<Path> listOfVideosFiles = new ArrayList<>();
    private final List<Path> listOfAllFiles = new ArrayList<>();


    public FileExplorer() {
        containListOfAllFiles();
        containAllPhotoFiles();
        containAllVideoFiles();
    }

    public void setSourceDirectory(Path sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    private void containListOfAllFiles() {
        try {
            Files.walkFileTree(this.sourceDirectory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    listOfAllFiles.add(file);
                    return FileVisitResult.CONTINUE; // Continue traversing
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE; // Continue traversing
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE; // Continue traversing even if there's an error
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Path> getListOfPhotosFiles() {
        return listOfPhotosFiles;
    }

    public List<Path> getListOfVideosFiles() {
        return listOfVideosFiles;
    }

    public List<Path> getListOfAllFiles() {
        return listOfAllFiles;
    }

    private void containAllVideoFiles(){
        this.listOfVideosFiles = this.listOfAllFiles.stream().filter(file -> isVideoFile(file.getFileName().toString())).toList();
    }

    private void containAllPhotoFiles(){
        this.listOfPhotosFiles = this.listOfAllFiles.stream().filter(file -> isImgFile(file.getFileName().toString())).toList();
    }

    public static void main(String[] args) {
        FileExplorer fileExplorer = new FileExplorer();
        fileExplorer.getListOfPhotosFiles().forEach(System.out::println);
    }
}
