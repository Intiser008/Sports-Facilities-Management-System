package transaction;
public class Transaction {

	private PaymentStrategy paymentStrategy;
	private RefundStrategy refundStrategy;
	private double amount;
	private boolean paymentProcessed;

	/**
	 * 
	 * @param PaymentStrategy
	 * @param RefundStrategy
	 * @param amount
	 */
	public Transaction(PaymentStrategy paymentStrategy, RefundStrategy refundStrategy, double amount) {
		this.amount = amount;
		this.paymentStrategy = paymentStrategy;
		this.refundStrategy = refundStrategy;
		this.paymentProcessed = false;
	}

	public void processPayment() {
		paymentStrategy.processPayment(amount);
		paymentProcessed = true;
	}

	public void processRefund() {
		if(paymentProcessed) {
			refundStrategy.processRefund(amount);
		} 
		else {
			System.out.print("Payment is not processed.\n");
		}
	}

	public boolean getPaymentProcessed(){
		return this.paymentProcessed;
	}

}