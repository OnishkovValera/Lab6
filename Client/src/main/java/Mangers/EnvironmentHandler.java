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

    public static void setEnvironment() throws IOException, ClassNotFoundException, InterruptedException {
        String env;
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.print("Enter name of environment variable\n$");

            env = scn.nextLine().trim();

            Container container = new Container(env);
            containerHandler.sendContainer(container);
            container = containerHandler.readContainer();

            if(container.error){
                System.err.println(container.getArgument());
                Thread.sleep(50);
                System.out.print("Do you want to create new environment variable?[Y/N]\n$");

                String answer = scn.nextLine().trim().toLowerCase();

                if(answer.equals("y")){
                    System.out.print("Enter path for this variable\n$");
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