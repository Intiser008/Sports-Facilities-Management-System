package transaction;
public class CreditCardPayment implements PaymentStrategy {
    public void processPayment(double amount){
        System.out.printf("Visa processed the payment with amount of %.2f.\n", amount);
    }
}