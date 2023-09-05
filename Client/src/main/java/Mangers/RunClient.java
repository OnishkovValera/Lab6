package Mangers;

import Managers.Container;
import Managers.ContainerHandler;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class RunClient {
    Scanner scn = new Scanner(System.in);
    String env;


    public void run(ContainerHandler containerHandler) throws IOException, ClassNotFoundException, InterruptedException {

        EnvironmentHandler.setEnvironment();

        while (true){

            System.out.print("Write command\n$");
            Container container = null;

            try {
                container = Validator.validateData(scn.nextLine());

            } catch (InvalidParameterException exception) {
                System.err.println("This command requires an argument");
                Thread.sleep(100);
                continue;
            }catch (NumberFormatException exception){
                System.err.println("Argument must be number");
                Thread.sleep(100);
                continue;
            } catch (IllegalArgumentException exception){
                System.err.println("No such command");
                Thread.sleep(100);
                continue;
            }
            
            
            afterValidationStage(containerHandler, container);
            
        }
    }
    public static void afterValidationStage(ContainerHandler containerHandler, Container container) throws IOException, ClassNotFoundException, InterruptedException {
        if(container.getCommand().toString().equals("executescript")) {
            ScriptHandler.handle(containerHandler, container.getArgument());

        }else {
            containerHandler.sendContainer(container);
            if (container.getCommand().toString().equals("save")) {
                containerHandler.sendContainer(new Container(true));
                container = containerHandler.readContainer();
                if (container.error) {
                    System.out.println("Something went wrong");
                } else {
                    System.out.println(container.getArgument());
                }
                System.out.println("App is closing");
                System.exit(1);
            }
            if (container.error) {
                System.err.println(container.getArgument());
                Thread.sleep(100);

            }else if(container.getHashMap() == null){

                System.out.println(container.getArgument());

            } else {
                for (Integer key : container.getHashMap().keySet()) {
                    System.out.println("Key:" + key);
                    System.out.println(container.getHashMap().get(key).toString());
                }
                System.out.println(container.getArgument());
            }
        }
    }
}
