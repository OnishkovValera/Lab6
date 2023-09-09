package Managers;

import InputData.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Session {

    static JsonHandler jsonHandler = new JsonHandler();



    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    private String path;
    private Date startSession;


    public HashMap<Integer, Vehicle> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Session() {
        this.startSession = new Date();
    }


    public void initializeSession(String path){
        this.path = path;
        hashMap = jsonHandler.toHashmap(path);
    }


    public void saveCollection() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(jsonHandler.HashmapToJson(hashMap));
        bw.close();
    }
}
