package org.example.connections;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonFile {

    //fileExplorer
    public String getFileExplorerSourceDirectory(){
        return fileExplorerObject().get("sourceDirectory").getAsString();
    }

    public String getFileExplorerTargetDirectory(){
        return fileExplorerObject().get("targetDirectory").getAsString();
    }

    //ftpClient
    public String getFtpClientServer(){
        return ftpClientObject().get("server").getAsString();
    }

    public int getFtpClientPort(){
        return ftpClientObject().get("port").getAsInt();
    }

    public String getFtpClientUser(){
        return ftpClientObject().get("user").getAsString();
    }

    public String getFtpClientPass(){
        return ftpClientObject().get("pass").getAsString();
    }


    //ftpClient -> transfer
    public String getFtpTransferDirPath(){
        return transferObject().get("ftpDirPath").getAsString();
    }

    public String getFtpTransferDestinationDirPath(){
        return transferObject().get("destinationDirPath").getAsString();
    }

    public boolean getFtpTransferRemoveFileFromFtp(){
        return transferObject().get("removeFileFromFtp").getAsBoolean();
    }

    //models
    private JsonArray scannerModels() { return models().get("scanners").getAsJsonArray();}

    public List<String> getScannerModels(){
        List<String> newList = new ArrayList<>();
        for (JsonElement e: scannerModels()){
            newList.add(e.getAsString());
        }
        return newList;
    }

    private JsonObject jsonObject(){
        try (FileReader reader = new FileReader("data.json")) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }

    private JsonObject models(){
        return Objects.requireNonNull(jsonObject()).getAsJsonObject("models");
    }

    private JsonObject transferObject(){
        return Objects.requireNonNull(ftpClientObject()).getAsJsonObject("transfer");
    }

    private JsonObject ftpClientObject(){
        return Objects.requireNonNull(jsonObject()).getAsJsonObject("ftpClient");
    }

    private JsonObject fileExplorerObject(){
        return Objects.requireNonNull(jsonObject()).getAsJsonObject("fileExplorer");
    }


}
