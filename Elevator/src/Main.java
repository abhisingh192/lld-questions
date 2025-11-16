public class Main {
    public static void main(String[] args) {

        ElevatorSystem elevatorSystem = new ElevatorSystem(2, 10); // 3 elevators, 10 floors

        // Simulate some requests
        elevatorSystem.submitRequest(new Request(2, 4));
        elevatorSystem.submitRequest(new Request(2, 7));
        elevatorSystem.submitRequest(new Request(9, 2));

        for (int i = 0; i < 10; i++) {
            System.out.println("--- Step " + (i + 1) + " ---");
            elevatorSystem.step();
        }

    }
}