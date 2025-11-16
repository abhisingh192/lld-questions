package notif;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

// consumer wagerah kya hai...
public class NotificationBus {

    private final Map<EventType, CopyOnWriteArrayList<Consumer<Notification>>> listeners = new ConcurrentHashMap<>();

    public void subscribe(EventType t, Consumer<Notification> consumer){
        listeners.computeIfAbsent(t, k -> new CopyOnWriteArrayList<>()).add(consumer);
    }
    public void publish(Notification n){
        var list = listeners.getOrDefault(n.getEventType(), new CopyOnWriteArrayList<>());
        list.forEach(l -> l.accept(n));
    }

}
