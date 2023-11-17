import java.util.ArrayList;
import java.util.List;

// Observer
interface ShoppingCartObserver {
    void update(float totalPrice);
}

// Subject
class ShoppingCart {
    private List<ShoppingCartObserver> observers = new ArrayList<>();
    private List<Float> items = new ArrayList<>();

    public void addObserver(ShoppingCartObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ShoppingCartObserver observer) {
        observers.remove(observer);
    }

    public void addItem(float item) {
        items.add(item);
        notifyObservers();
    }

    public void notifyObservers() {
        float totalPrice = calculateTotal();
        for (ShoppingCartObserver observer : observers) {
            observer.update(totalPrice);
        }
    }

    private float calculateTotal() {
        float sum = 0;
        for (Float item : items) {
            sum += item;
        }
        return sum;
    }
}

// Concrete Observer
class EmailNotification implements ShoppingCartObserver {
    @Override
    public void update(float totalPrice) {
        System.out.println("Sending email notification: Total Price is now $" + totalPrice);
    }
}

// Concrete Observer
class SMSPushNotification implements ShoppingCartObserver {
    @Override
    public void update(float totalPrice) {
        System.out.println("Sending SMS notification: Total Price is now $" + totalPrice);
    }
}

// Client
public class ShoppingCartApp {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        EmailNotification emailNotification = new EmailNotification();
        SMSPushNotification smsPushNotification = new SMSPushNotification();

        cart.addObserver(emailNotification);
        cart.addObserver(smsPushNotification);

        cart.addItem(20.0f);  // Both email and SMS notifications should be triggered.
        cart.addItem(25.0f);  // Both email and SMS notifications should be triggered.
    }
}
