package user;
import transaction.*;
import java.util.*;
import sportfacility.*;
public class Bookings {

	private SportFacility facility;
	private int bookingID;
	private String bookingDate;
	private int startTime;
	private int endTime;
	private User user;
	private Transaction transaction;
	private static int bookingIDcount=1;

	// starttime = 1400 end time 1500 end = start + 100
	public Bookings(SportFacility spFacility, String bookingDate, int startTime) {
		this.facility = spFacility;
		this.bookingDate = bookingDate;
		this.startTime = startTime;
		this.endTime = startTime + 100;
		//bookingID = generateBookingId(); // check this
		setbookingId();
	}
	public void setbookingId(){
		bookingID=bookingIDcount;
		bookingIDcount++;
	}

	// private String generateBookingId() {
	// 	UUID randomUUID = UUID.randomUUID();
	// 	String bookingId = randomUUID.toString().replaceAll("-", "");
	// 	return bookingId;
	// }
	
	//payment start needed here
	public void calculatePrice(Customer customer, PaymentStrategy paymentStrategy, RefundStrategy refundStrategy) {
		// check customer state, assign 0.9 price if gold
		double payWithDiscount = customer.getMemberOffer(); // discount returns a number from 0-1
		double amount = (facility.getBookingFee()) * payWithDiscount;
		System.out.println("Your total is: "+amount+"\nProcessing transaction...");

		
		transaction = new Transaction(paymentStrategy, refundStrategy, amount);
		transaction.processPayment();
		
	}
	


	public int getBookingId() {
		return bookingID;
	}

	public String getBookingDate() {
		return bookingDate.toString();
	}

	public int getStartTime() {
		return startTime;
	}
	public void cancel(){
		String dateHour= concatenateStringAndInt(bookingDate,startTime);
		facility.cancelBooking(dateHour);
		transaction.processRefund();
	}
	public static String concatenateStringAndInt(String str, int number) {
        return str +" "+ number;
    }
	public String getBookingInfo() {return concatenateStringAndInt(bookingDate,startTime);}
	public boolean paymentProcessFlag(){return transaction.getPaymentProcessed();}
	public static void resetBookingID() {
		bookingIDcount = 1;
	}
}