package transaction;
public interface RefundStrategy {

	/**
	 * 
	 * @param amount
	 */
	public void processRefund(double amount);

}