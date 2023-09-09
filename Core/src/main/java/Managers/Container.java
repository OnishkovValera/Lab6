package Managers;

import Commands.Command;
import InputData.Vehicle;

import java.io.Serializable;
import java.util.HashMap;

public class Container implements Serializable {

    public boolean checkEnv = false;
    public boolean setNewVariable = false;
    public boolean endConnection = false;
    public boolean error = false;

    private String env;
    private String nameOfVariable;
    private String pathOfVariable;
    private Command command;
    private String argument;
    private HashMap<Integer, Vehicle> hashMap;



    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getNameOfVariable() {
        return nameOfVariable;
    }

    public void setNameOfVariable(String nameOfVariable) {
        this.nameOfVariable = nameOfVariable;
    }

    public String getPathOfVariable() {
        return pathOfVariable;
    }

    public void setPathOfVariable(String pathOfVariable) {
        this.pathOfVariable = pathOfVariable;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public HashMap<Integer, Vehicle> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }


    public Container(String env) {
        this.checkEnv = true;
        this.setNewVariable = false;
        this.env = env;
    }

    public Container(String nameOfVariable, String pathOfVariable) {
        this.checkEnv = true;
        this.setNewVariable = true;
        this.nameOfVariable = nameOfVariable;
        this.pathOfVariable = pathOfVariable;
    }
    public Container(boolean endConnection){
        this.endConnection = endConnection;
    }

    public Container(Command command){
        this.command = command;
    }

    public Container(Command command, String argument){
        this.command = command;
        this.argument = argument;
    }
    public Container(Command command, String argument, HashMap<Integer, Vehicle> hashMap){
        this.command = command;
        this.argument = argument;
        this.hashMap = hashMap;
    }
    public Container(boolean error, String argument){
        this.error = error;
        this.argument = argument;
    }
    public Container(boolean error, String argument, HashMap<Integer, Vehicle> hashMap){
        this.error = error;
        this.argument = argument;
        this.hashMap = hashMap;

    }

}
