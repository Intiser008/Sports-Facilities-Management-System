package transaction;
public class CreditCardRefund implements RefundStrategy {
    public void processRefund(double amount) {
        System.out.printf("Visa processed the refund with amount of %.2f.\n", amount);
    }
}