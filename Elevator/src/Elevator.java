import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Elevator {

    private int id;
    private int currentFloor;

    private Direction direction;

    private ElevatorStatus status;

    private int capacity;

    private Queue<Request> requests;

    public Elevator(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = 0; // Assuming ground floor as starting point
        this.direction = Direction.IDLE;
        this.requests = new ConcurrentLinkedDeque<>();
        this.status = ElevatorStatus.FREE;

    }

    public synchronized void addRequest(Request request) {
        requests.add(request);
        System.out.println("Request added to Elevator " + id + ": " + request);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isIdle() {
        return direction == Direction.IDLE;
    }

    public boolean isAvailable(Elevator elevator) {
        return elevator.getStatus() == ElevatorStatus.FREE;
    }

    public void step() {
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        Request next = requests.peek();

        // Move towards source floor first
        if (currentFloor < next.getSourceFloor()) {
            currentFloor++;
            direction = Direction.UP;
        } else if (currentFloor > next.getSourceFloor()) {
            currentFloor--;
            direction = Direction.DOWN;
        } else {
            // At source floor → pick passenger and move directly to destination
            requests.poll();
            System.out.println("Elevator " + id + " picked up passenger at floor " + currentFloor);

            // Now travel to destination
            while (currentFloor != next.getDestinationFloor()) {
                if (currentFloor < next.getDestinationFloor()) {
                    currentFloor++;
                    direction = Direction.UP;
                } else {
                    currentFloor--;
                    direction = Direction.DOWN;
                }
                System.out.println("Elevator " + id + " moving to floor " + currentFloor);
            }

            System.out.println("Elevator " + id + " dropped passenger at floor " + currentFloor);
            direction = Direction.IDLE;
        }
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    public ElevatorStatus getStatus() {
        return status;
    }
}
