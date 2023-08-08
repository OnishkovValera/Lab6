package Mangers;

import Managers.Container;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import static Mangers.ContainerHandler.readContainer;
import static Mangers.ContainerHandler.sendContainer;

public class RunClient {

    String env;



    public void run(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        Validator vld = new Validator();
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name of environment variable");
            System.out.print("$");

            env = scn.nextLine();

            Container container = new Container(true, env);
            sendContainer(container, socketChannel);
            container = readContainer(socketChannel);

            if(container.getError()){
                System.err.println(container.getCommand());

            }else{

                System.out.println(container.getCommand());
                break;

            }
        }
        while (true){

            System.out.println("Write command");
            System.out.print("$");
            Container container = null;

            try {
                container = vld.validateData(scn.nextLine());
            }catch (IllegalArgumentException exception){
                System.err.println("No such command");
                continue;
            }

            sendContainer(container, socketChannel);

            if(container.getCommand() == "exit"){
                System.out.println("App is closing");
                System.exit(1);

            }

            container = readContainer(socketChannel);

            if(container.getError()){
                System.err.println(container.getCommand());
                continue;

            }

            if(container.getHashMap() == null){
                System.out.println(container.getCommand());
                continue;
            }

            for(Integer key : container.getHashMap().keySet()){
                System.out.println(container.getHashMap().get(key).toString());
            }
        }
    }


}
