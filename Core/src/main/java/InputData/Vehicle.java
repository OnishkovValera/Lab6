package InputData;

import java.time.LocalDate;

public class Vehicle implements Comparable<Vehicle>{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer enginePower; //Поле не может быть null, Значение поля должно быть больше 0
    private double capacity; //Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
    private FuelType fuelType; //Поле не может быть null

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public double getCapacity() {
        return capacity;
    }

    public VehicleType getType() {
        return type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Vehicle(String name, Coordinates coordinates, Integer enginePower, double capacity, VehicleType type, FuelType fuelType) {
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.type = type;
        this.fuelType = fuelType;
    }

    public Vehicle(int id, String name, Coordinates coordinates, LocalDate creationDate, Integer enginePower, double capacity, VehicleType type, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.type = type;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return
                "Vehicle " + id + "\n" +
                        "   name: " + name + "\n" +
                        "   coordinates: " + coordinates + "\n" +
                        "   creationDate: " + creationDate + "\n" +
                        "   enginePower: " + enginePower + "\n" +
                        "   capacity: " + capacity + "\n" +
                        "   type: " + type + "\n" +
                        "   fuelType: " + fuelType + "\n";
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        return id - vehicle.getId();
    }
}
