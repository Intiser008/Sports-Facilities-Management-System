package test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import sportfacility.*;
import user.*;
import transaction.*;

public class textCustomerClass {
	@Test
	public void testReview_01() {
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt(9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "13-02-2000", 10, "CC");

		assertEquals(false, customer.createBooking(facility, "13-02-2000", 10, "CC")); 
	}



	@Test
	public void testReview_07() throws Exception { //Cancel booking successful
		Bookings.resetBookingID();
		// Get current date and time
		LocalDateTime now = LocalDateTime.now();
		// Subtract one hour from the current time
		LocalDateTime oneHourBefore = now.minusHours(1);
	
		// Format date and time
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String bookingDate = oneHourBefore.format(dateFormatter);
		int bookingTime = oneHourBefore.getHour();
	
		// Your test code
		Customer customer = new Customer("username", "password");
		Admin admin = new Admin("labiba","labiba");
		SportFacility facility = new TennisCourt(9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
	
		// Use dynamic values for the booking date and time
		customer.createBooking(facility, bookingDate, bookingTime, "CC");
		//int bookingID = customer.getList().get(0).getBookingId();
		setOutput();
		admin.receiveCancelRequest(customer,1); // Pass the booking ID
		assertEquals("Sorry, this booking cannot be cancelled and is therefore non-refundable.\n" +
			"A booking can only be cancelled 6 hours or more in advance.\n",
			getOutput());
	}
	
	@Test
	public void testReview_05() throws Exception { // standard
		// for setMemberType can directly check the getMemberOffer amount
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt(9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "22-11-2023", 10, "CC");
		double offer = customer.getMemberOffer();
		assertEquals(0.99, offer, 0);
	}

	@Test
	public void testReview_06() { // gold
		// for setMemberType can directly check the getMemberOffer amount
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt(9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "22-11-2023", 10, "CC");
		customer.createBooking(facility, "22-11-2023", 15, "CC");
		double offer = customer.getMemberOffer();
		assertEquals(0.88, offer, 0);
	}

	@Test
	public void testReview_08() throws Exception { // Check viewbookings function
		Bookings.resetBookingID();
		setOutput();
		Customer customer = new Customer("username", "password");
		Admin admin = new Admin("labiba", "labiba");
		SportFacility facility = new TennisCourt(9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();

		customer.createBooking(facility, "22-11-2023", 16, "CC");
		getOutput();
		setOutput();
		customer.createBooking(facility, "22-11-2023", 10, "CC");

		// int bookingID = customer.getList().get(0).getBookingId();
		getOutput();
		setOutput();

		// admin.receiveCancelRequest(customer,2); //how pass random bookingID
		customer.viewBookings();
		String expected = "Membership Type: Gold Member\nBooking ID: 1\nBooking Date: 22-11-2023\nBooking Start Time: 16\n\nBooking ID: 2\nBooking Date: 22-11-2023\nBooking Start Time: 10\n\n";

		assertEquals(expected, getOutput());
	}
		@Test
	public void testReview_04() throws Exception { // Cancel booking successful
		// check for cancel successful message
		setOutput();
		Bookings.resetBookingID();
		Customer customer = new Customer("username", "password");
		Admin admin = new Admin("labiba", "labiba");
		SportFacility facility = new TennisCourt(9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "22-11-2023", 16, "CC");
		getOutput();
		setOutput();
		admin.receiveCancelRequest(customer, 1); 
		
		assertEquals("Booking cancelled for \n22-11-2023 16", getOutput());
	}


	// cancelBooking() working?
	// getMemberOffer() working?
	// setMemberType() check

	/**************************************
	 * Helper methods for output capture
	 ***************************************/
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;

	private void setOutput() throws Exception {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	private String getOutput() {
		System.setOut(oldPrintStream);
		return bos.toString();
	}

}
