public abstract class Vehicle {

    // a class ccan be abstract without any abstract mathods, the thing we want to communicate here is that this class is not meant to be instantiated directly
    private String licensePlate;
    private VehicleSize size;

    public Vehicle(String licensePlate, VehicleSize size) {
        this.licensePlate = licensePlate;
        this.size = size;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleSize getSize() {
        return size;
    }
}
