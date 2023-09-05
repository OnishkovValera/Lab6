package Mangers;

import InputData.Coordinates;
import InputData.FuelType;
import InputData.Vehicle;
import InputData.VehicleType;
import Managers.Container;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {

    public static Container validateData(String command) throws IllegalArgumentException, InterruptedException {
        String[] commandAndArgument = command.toLowerCase().trim().split(" ");
        Container container = null;
        if (CommandManager.isArgumentExists(commandAndArgument[0].trim())) {
            if(commandAndArgument.length > 1) {
                if(!commandAndArgument[0].trim().equals("execute_script")){
                    Integer.parseInt(commandAndArgument[1]);
                }
                if (CommandManager.isElementNeeded(commandAndArgument[0].trim())) {
                    HashMap<Integer, Vehicle> hashMap = new HashMap<>();
                    hashMap.put(0, createVehicle());
                    container = new Container(CommandManager.getCommand(commandAndArgument[0]), commandAndArgument[1], hashMap);

                } else {
                    container = new Container(CommandManager.getCommand(commandAndArgument[0]), commandAndArgument[1]);

                }
            }else{
                throw new InvalidParameterException();
            }
        }else{
            if (CommandManager.isElementNeeded(commandAndArgument[0].trim())) {
                HashMap<Integer, Vehicle> hashMap = new HashMap<>();
                hashMap.put(0, createVehicle());
                container = new Container(CommandManager.getCommand(commandAndArgument[0]), null, hashMap);
            }else {
                container = new Container(CommandManager.getCommand(commandAndArgument[0]));
            }
        }
        return container;
    }

    public static Vehicle createVehicle() throws InterruptedException {

        Scanner scn = new Scanner(System.in);
        String name;
        while(true) {
            System.out.print("Enter name\n$" );
            name = scn.nextLine().trim();
            if(!(name.length() <= 0 || name.equals(null))){
                Thread.sleep(100);
                break;
            }else{
                System.err.println("Name mustn't be null or empty");
            }
        }

        float x;
        while(true) {
            System.out.print("Enter coordinate x\n$");
            try {
                x = scn.nextFloat();
                Thread.sleep(100);
                break;

            } catch (InputMismatchException exception) {
                System.err.println("Coordinate x must be float");
                scn.nextLine();
            }
        }

        double y;
        while(true) {
            System.out.print("Enter coordinate y\n$");
            try {
                y = scn.nextDouble();
                Thread.sleep(100);
                break;

            } catch (InputMismatchException exception) {
                System.err.println("Coordinate y must be double");
                scn.nextLine();
            }
        }

        int enginePower;
        while(true) {
            System.out.print("Enter engin power\n$");
            try {
                enginePower = scn.nextInt();
                Thread.sleep(100);
                if(enginePower > 0) {
                    break;
                }else{
                    System.err.println("Engine power must be bigger than zero");
                }
            } catch (InputMismatchException exception) {
                System.err.println("Engine power must be integer");
                scn.nextLine();
            }
        }

        double capacity;
        while(true) {
            System.out.print("Enter capacity \n$");
            try {
                capacity = scn.nextDouble();
                Thread.sleep(100);
                if(capacity > 0) {
                    break;
                }else{
                    System.err.println("Capacity must be bigger that zero");
                }
            } catch (InputMismatchException exception) {
                System.err.println("Capacity must be double");
                scn.nextLine();
            }
        }

        VehicleType type;
        while(true) {
            System.out.print("Enter vehicle type (helicopter / submarine / chopper / spaceship)\n$");
            try {
                scn.nextLine();
                Thread.sleep(100);
                type = VehicleType.valueOf(scn.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.err.println("No such type");
                scn.nextLine();
            }
        }

        FuelType fuelType;
        while(true) {
            System.out.print("Enter fuel type (kerosene / alcohol / plasma)\n$");
            try {
                fuelType = FuelType.valueOf(scn.nextLine().toUpperCase());
                Thread.sleep(100);
                break;
            } catch (IllegalArgumentException exception) {
                System.err.print("No such fuel type\n$");
                scn.nextLine();

            }
        }

        Vehicle vehicle = new Vehicle(name, new Coordinates(x, y), enginePower, capacity, type, fuelType);

        return vehicle;
    }
}
