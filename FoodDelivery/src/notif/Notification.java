package notif;

public class Notification {

    private final EventType eventType;
    private final String message;

    private final String orderId;

    public Notification(EventType eventType, String message, String orderId) {
        this.eventType = eventType;
        this.message = message;
        this.orderId = orderId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "eventType=" + eventType +
                ", message='" + message + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
