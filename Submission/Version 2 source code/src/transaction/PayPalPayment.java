package transaction;
public class PayPalPayment implements PaymentStrategy {
    public void processPayment(double amount){
        System.out.printf("PayPal processed the payment with amount of %.2f.\n", amount);
    }
}