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
            System.out.print("Do you want to create new environment variable?[Y/N]\n$");
            Container container;

            if(scn.nextLine().trim().equalsIgnoreCase("y")){

                System.out.print("Enter path of new variable\n$");
                String path = scn.nextLine().trim();

                System.out.print("Enter name of new variable\n$");
                env = scn.nextLine().trim();

                containerHandler.sendContainer(new Container(env, path));

                container = containerHandler.readContainer();

                if(container.error){
                    System.out.println(container.getArgument());
                }else{
                    System.out.println(container.getArgument());
                    break;
                }

            }else{

                System.out.println("Enter path of variable");
                env = scn.nextLine().trim();

                containerHandler.sendContainer(new Container(env));
                container = containerHandler.readContainer();

                if(container.error) {
                    System.err.println(container.getArgument());
                    Thread.sleep(50);

                }else{
                    System.out.println(container.getArgument());
                    break;

                }
            }
        }
    }
}
