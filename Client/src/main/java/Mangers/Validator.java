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
    public static boolean isCommandExist(String command){
        return false;
    }

    public Container validateData(String command) throws IllegalArgumentException{

        String[] commandAndArgument = command.toLowerCase().trim().split(" ");
        Container container = null;
        if (CommandManager.isArgumentExists(commandAndArgument[0].trim())) {
            if (CommandManager.isElementNeeded(commandAndArgument[0].trim())){
                HashMap<Integer, Vehicle> hashMap = new HashMap<>();
                hashMap.put(0, createVehicle());
                container = new Container(commandAndArgument[0], commandAndArgument[1], hashMap);

            }else{
                container = new Container(commandAndArgument[1], commandAndArgument[0]);

            }
        }else{
            container = new Container(commandAndArgument[0]);

        }
        return container;
    }




    public static Vehicle createVehicle(){

        Scanner scn = new Scanner(System.in);

        String name;
        name = scn.nextLine();

        float x;

        while(true) {
            try {
                x = scn.nextFloat();
                break;

            } catch (InputMismatchException exception) {
                System.err.println("Coordinate x must be float");
                System.out.print("$");

            }
        }

        double y;
        while(true) {
            try {
                y = scn.nextDouble();
                break;

            } catch (InputMismatchException exception) {
                System.err.println("Coordinate y must be double");
                System.out.print("$");
            }
        }

        int enginePower;
        while(true) {
            try {
                enginePower = scn.nextInt();
                break;

            } catch (InputMismatchException exception) {
                System.err.println("Engine power must be integer");
                System.out.print("$");
            }
        }

        double capacity;
        while(true) {
            try {
                capacity = scn.nextDouble();
                break;
            } catch (InputMismatchException exception) {
                System.err.println("Capacity must be double");
                System.out.print("$");
            }
        }

        VehicleType type;
        while(true) {
            try {
                type = VehicleType.valueOf(scn.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.err.println("No such type");
                System.out.print("$");
            }
        }

        FuelType fuelType;
        while(true) {
            try {
                fuelType = FuelType.valueOf(scn.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.err.println("No such fuel type");
                System.out.print("$");

            }
        }

        Vehicle vehicle = new Vehicle(name, new Coordinates(x, y), enginePower, capacity, type, fuelType);

        return vehicle;

    }

}
