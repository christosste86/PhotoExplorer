package org.example.connections;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class JsonFile {

    public String getServer(){
        return ftpClientObject().get("server").getAsString();
    }

    public int getPort(){
        return ftpClientObject().get("port").getAsInt();
    }

    public String getUser(){
        return ftpClientObject().get("server").getAsString();
    }

    public String getPass(){
        return ftpClientObject().get("pass").getAsString();
    }

    public String ftpDirPath(){
        return transferObject().get("ftpDirPath").getAsString();
    }

    public String destinationDirPath(){
        return transferObject().get("destinationDirPath").getAsString();
    }

    public boolean removeFileFromFtp(){
        return transferObject().get("removeFileFromFtp").getAsBoolean();
    }

    private JsonObject jsonObject(){
        try (FileReader reader = new FileReader("data.json")) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }

    private JsonObject transferObject(){
        return Objects.requireNonNull(ftpClientObject()).getAsJsonObject("transfer");
    }

    private JsonObject ftpClientObject(){
        return Objects.requireNonNull(jsonObject()).getAsJsonObject("ftpClient");
    }
}
