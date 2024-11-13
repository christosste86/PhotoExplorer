package org.example.connections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;
import java.util.List;


public class PhotoExplorer {

    private Path directory;
    private List<Path> photoFiles = new ArrayList<>();

    public PhotoExplorer(Path directory) {
        this.directory = directory;
        getPhotoPaths();
    }

    public List<Path> getPhotoFilesList() {
        return photoFiles;
    }

    private void getPhotoPaths() {
        try {
            Files.walkFileTree(this.directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (isImageFile(file)) {
                        photoFiles.add(file);
                    }
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
    private static boolean isImageFile(Path file) {
        String[] imageExtensions = {".jpg", ".jpeg"};
        String fileName = file.toString().toLowerCase();
        for (String ext : imageExtensions) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
