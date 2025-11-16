package service;

import entity.Order;
import entity.OrderStatus;
import entity.PaymentStatus;
import notif.EventType;
import notif.Notification;
import notif.NotificationBus;
import service.strategy.PaymentFactory;
import service.strategy.PaymentStrategy;

public class CheckoutService {

    private final NotificationBus notificationBus;


    public CheckoutService(NotificationBus notificationBus){
        this.notificationBus=notificationBus;
    }

    public boolean pay(Order order) {
        PaymentStrategy strategy = PaymentFactory.getPaymentStrategy(order.paymentMethod);
        PaymentStatus ps = strategy.pay(order.getCustomer(), order.total);

        order.lock.lock();
        try{
            order.paymentStatus = ps;
            if(ps==PaymentStatus.SUCCESS){
                notificationBus.publish(new Notification(EventType.PAYMENT_SUCCESS, "Payment success ₹"+order.total, order.getId()));
                return true;
            } else {
                order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
                notificationBus.publish(new Notification(EventType.PAYMENT_FAILED, "Payment failed", order.getId()));
                return false;
            }
        } finally { order.lock.unlock(); }
    }
}
