import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {

    private List<Elevator> elevators;

    public ElevatorSystem(int numElevators, int capacity) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(i, capacity));
        }
    }

    public void submitRequest(Request request) {

        Elevator bestElevator = findBestElevator(request);
        bestElevator.addRequest(request);

    }

    private Elevator findBestElevator(Request request) {
        Elevator candidate = null;
        int minDistance = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            if (e.isIdle() && e.isAvailable(e)) {
                e.setStatus(ElevatorStatus.OCCUPIED);
                return e;
            };
            int distance = Math.abs(e.getCurrentFloor() - request.getSourceFloor());
            if (distance < minDistance) {
                minDistance = distance;
                candidate = e;
            }
        }
        return candidate;
    }

    public void step() {
        for (Elevator e : elevators) {
            e.step();
        }
    }

}
