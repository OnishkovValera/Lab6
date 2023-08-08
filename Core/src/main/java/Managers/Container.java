package Managers;

import InputData.Vehicle;

import java.io.Serializable;
import java.util.HashMap;

public class Container implements Serializable {
    private boolean chekEnv;

    private boolean error;

    private HashMap<Integer, Vehicle> hashMap;

    private String command;

    private String argument;

    public boolean getChekEnv() {
        return chekEnv;
    }

    public void setChekEnv(boolean chekEnv) {
        this.chekEnv = chekEnv;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public HashMap<Integer, Vehicle> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }


    public Container(boolean chekEnv, boolean error, HashMap<Integer, Vehicle> hashMap, String command, String argument) {
        this.chekEnv = chekEnv;
        this.error = error;
        this.hashMap = hashMap;
        this.command = command;
        this.argument = argument;
    }

    public Container(boolean chekEnv, String command) {
        this.chekEnv = chekEnv;
        this.command = command;
    }

    public Container(String command, boolean error) {
        this.error = error;
        this.command = command;
    }

    public Container(String command){
        this.command = command;
    }

    public Container(String command, String argument){
        this.command = command;
        this.argument = argument;
    }

    public Container(String command, String argument, HashMap<Integer, Vehicle> hashMap){
        this.command = command;
        this.argument = argument;
        this.hashMap = hashMap;
    }

}
