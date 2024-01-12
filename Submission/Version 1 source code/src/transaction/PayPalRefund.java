package transaction;
public class PayPalRefund implements RefundStrategy {
    public void processRefund(double amount) {
        System.out.printf("PayPal processed the refund with amount of %.2f.\n", amount);
    }
}