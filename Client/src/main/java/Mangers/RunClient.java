package Mangers;

import Managers.Container;
import Managers.ContainerHandler;

import java.io.*;
import java.util.Scanner;

public class RunClient {
    Scanner scn = new Scanner(System.in);
    String env;


    public void run(ContainerHandler containerHandler) throws IOException, ClassNotFoundException {

        EnvironmentHandler.setEnvironment();

        while (true){

            System.out.println("Write command");
            System.out.print("$");
            Container container = null;

            try {
                container = Validator.validateData(scn.nextLine());
            }catch (IllegalArgumentException exception){
                System.err.println("No such command");
                continue;
            }

            containerHandler.sendContainer(container);

            if(container.getCommand().toString().equals("exit")){
                System.out.println("App is closing");
                containerHandler.sendContainer(new Container(true));
                System.exit(1);

            }

            container = containerHandler.readContainer();

            if(container.error){
                System.err.println(container.getCommand());
                continue;

            }

            if(container.getHashMap().equals(null)){
                System.out.println(container.getCommand());
            }else {
                for (Integer key : container.getHashMap().keySet()) {
                    System.out.println(container.getHashMap().get(key).toString());
                }
                System.out.println(container.getArgument());
            }
        }
    }


}
