package Mangers;

import Managers.Container;
import Managers.ContainerHandler;

import java.io.IOException;
import java.util.Scanner;

public class EnvironmentHandler {
    private static ContainerHandler containerHandler;

    public static void setContainerHandler(ContainerHandler containerHandler) {
        EnvironmentHandler.containerHandler = containerHandler;
    }

    public static void setEnvironment() throws IOException, ClassNotFoundException {
        String env;
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name of environment variable");
            System.out.print("$");

            env = scn.nextLine().trim();

            Container container = new Container(env);
            containerHandler.sendContainer(container);
            container = containerHandler.readContainer();

            if(container.error){

                System.err.println(container.getCommand());
                System.out.println("Do you want to create new environment variable?[Y/N]");
                System.out.print("$");

                String answer = scn.nextLine().trim().toLowerCase();

                if(answer.equals("Y")){
                    System.out.println("Enter path for this variable");
                    System.out.print("$");
                    String path = scn.nextLine();
                    containerHandler.sendContainer(new Container(env, path));
                    container = containerHandler.readContainer();

                    if(container.error){
                        System.out.println(container.getArgument());
                    }else{
                        System.out.println(container.getArgument());
                        break;
                    }
                }
            }else{
                System.out.println(container.getArgument());
                break;
            }
        }

    }
}