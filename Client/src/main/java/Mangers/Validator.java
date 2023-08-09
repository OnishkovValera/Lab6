package Mangers;

import InputData.Coordinates;
import InputData.FuelType;
import InputData.Vehicle;
import InputData.VehicleType;
import Managers.Container;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {

    public static Container validateData(String command) throws IllegalArgumentException{
        String[] commandAndArgument = command.toLowerCase().trim().split(" ");
        Container container = null;
        if (CommandManager.isArgumentExists(commandAndArgument[0].trim())) {
            if (CommandManager.isElementNeeded(commandAndArgument[0].trim())){
                HashMap<Integer, Vehicle> hashMap = new HashMap<>();
                hashMap.put(0, createVehicle());
                container = new Container(CommandManager.getCommand(commandAndArgument[0]), commandAndArgument[1], hashMap);

            }else{
                container = new Container(CommandManager.getCommand(commandAndArgument[1]), commandAndArgument[0]);

            }
        }else{
            container = new Container(CommandManager.getCommand(commandAndArgument[0]));

        }
        return container;
    }

    public static Vehicle createVehicle(){

        Scanner scn = new Scanner(System.in);
        String name;
        while(true) {
            System.out.print("Enter name\n$" );
            name = scn.nextLine().trim();
            if(!(name.length() <= 0 & name.equals(null))){
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
                break;

            } catch (InputMismatchException exception) {
                System.err.println("Coordinate x must be float");

            }
        }

        double y;
        while(true) {
            System.out.print("Enter coordinate y\n$");
            try {
                y = scn.nextDouble();
                break;

            } catch (InputMismatchException exception) {
                System.err.println("Coordinate y must be double");

            }
        }

        int enginePower;
        while(true) {
            System.out.println("Enter engin power\n$");
            try {
                enginePower = scn.nextInt();
                if(enginePower > 0) {
                    break;
                }else{
                    System.err.println("Engine power must be bigger than zero");
                }
            } catch (InputMismatchException exception) {
                System.err.println("Engine power must be integer");

            }
        }

        double capacity;
        while(true) {
            System.out.print("Enter capacity \n$");
            try {
                capacity = scn.nextDouble();
                if(capacity > 0) {
                    break;
                }else{
                    System.err.println("Capacity must be bigger that zero");
                }
            } catch (InputMismatchException exception) {
                System.err.println("Capacity must be double");
            }
        }

        VehicleType type;
        while(true) {
            System.out.println("Enter vehicle type\n$");
            try {
                type = VehicleType.valueOf(scn.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.err.println("No such type");
            }
        }

        FuelType fuelType;
        while(true) {
            try {
                fuelType = FuelType.valueOf(scn.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.err.println("No such fuel type\n$");

            }
        }

        Vehicle vehicle = new Vehicle(name, new Coordinates(x, y), enginePower, capacity, type, fuelType);

        return vehicle;
    }
}
