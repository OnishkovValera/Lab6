package Managers;

import InputData.Vehicle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class CollectionManager {

    public static Integer idGenerator(HashMap<Integer, Vehicle> hashMap){
        Set<Integer> set = new HashSet<>();

        for(Vehicle vehicle : hashMap.values()) {
            set.add(vehicle.getId());
        }

        int[] numbers = IntStream.range(1, 2000000).filter(i -> !set.contains(i)).toArray();
        int randomNumber = new Random().nextInt(numbers.length);

        return numbers[randomNumber];
    }





}
