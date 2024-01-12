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

public class testCustomerClass {

	@Test
	public void testCustomer_01() { // unit testing for gold customer
		Member gold = new GoldMember();
		int loyaltyPoints = 10;
		int bookingCount = 2;
		assertEquals(7, gold.calculate(loyaltyPoints, bookingCount), 0);
	}

	@Test
	public void testCustomer_02() { // unit testing for standard customer
		Member standard = new StandardMember();
		int loyaltyPoints = 10;
		int bookingCount = 1;
		assertEquals(1, standard.calculate(loyaltyPoints, bookingCount), 0);
	}

	@Test
	public void testCustomer_03() { // createbooking successful
		Bookings.resetBookingID();
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "13-02-2000", 10, "CC");

		assertEquals(false, customer.createBooking(facility, "13-02-2000", 10, "CC"));
	}

	@Test
	public void testCustomer_04() throws Exception { // Cancel booking failed (integration testing: admin, customer,
														// bookings, facility)
		Bookings.resetBookingID();
		// Get current date and time
		LocalDateTime now = LocalDateTime.now();
		// Subtract two hours from the current time
		LocalDateTime twoHoursBefore = now.plusHours(1);

		// Format date and time
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String bookingDate = twoHoursBefore.format(dateFormatter);
		int bookingTime = twoHoursBefore.getHour();

		// Your test code
		Customer customer = new Customer("username", "password");
		Admin admin = Admin.getInstance();
		admin.resetTime();
		SportFacility facility = new TennisCourt("Tennis", 0, 60, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		// Use dynamic values for the booking date and time
		
		customer.createBooking(facility, bookingDate, bookingTime, "CC");
		int cancelled=admin.receiveCancelRequest(customer, 1); // Pass the booking ID
		assertEquals(2,cancelled);
	}

	@Test
	public void testCustomer_05() throws Exception { // check if standard member has correct discount (integration customer + standard)
		Bookings.resetBookingID();
		// for setMemberType can directly check the getMemberOffer amount
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "22-11-2023", 10, "CC");
		double offer = customer.getMemberOffer();
		assertEquals(0.99, offer, 0);
	}

	@Test
	public void testCustomer_06() { // check if gold member has correct discount (integration customer + gold)
		Bookings.resetBookingID();
		// for setMemberType can directly check the getMemberOffer amount
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "22-11-2023", 10, "CC");
		customer.createBooking(facility, "22-11-2023", 15, "CC");
		double offer = customer.getMemberOffer();
		assertEquals(0.88, offer, 0);
	}

	@Test
	public void testCustomer_07() throws Exception { // Check viewbookings function
		Bookings.resetBookingID();
		setOutput();
		Customer customer = new Customer("username", "password");
		Admin admin = Admin.getInstance();
		SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
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
		String expected = "Membership Type: Gold Member\nBooking ID: 1\nBooking Date: 22-11-2023\nBooking Start Time: 16\nFacility: Tennis\nBooking ID: 2\nBooking Date: 22-11-2023\nBooking Start Time: 10\nFacility: Tennis\n";

		assertEquals(expected, getOutput());
	}

	@Test
	public void testCustomer_08() throws Exception { // Cancel booking successful (integration testing: admin, customer,
														// bookings, facility, transaction)
		// check for cancel successful message
		Bookings.resetBookingID();
		LocalDateTime now = LocalDateTime.now();
		// add 8 hours from the current time
		LocalDateTime twoHoursBefore = now.plusHours(8);

		// Format date and time
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String bookingDate = twoHoursBefore.format(dateFormatter);
		int bookingTime = twoHoursBefore.getHour();
		
		
		Bookings.resetBookingID();
		Customer customer = new Customer("username", "password");
		Admin admin = Admin.getInstance();
		SportFacility facility = new TennisCourt("Tennis", 0, 60, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, bookingDate, bookingTime, "CC");
		
		int cancelled=admin.receiveCancelRequest(customer, 1);
		assertEquals(3,cancelled);
	}

	@Test
	public void testCustomer_09() throws Exception { // (integration testing: customer, bookings, facility, review)
		// check for cancel successful message
		Bookings.resetBookingID();
		setOutput();
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
		PaymentStrategy payStrat = new CreditCardPayment();
		customer.createBooking(facility, "22-11-2023", 16, "CC");
		
		boolean status=customer.addReview(1, "oshadharon", 4);
		assertEquals(true, status);
	}

	
	@Test
	public void testCustomer_11() throws Exception { // facility exists
		// check for cancel successful message
		Bookings.resetBookingID();
		setOutput();
		Admin admin =Admin.getInstance();
		admin.createFacility("Swimming");
		admin.addFacility("Swimming Pool");
		assertEquals("The facility already exists!\n",getOutput());
	}
	@Test
	public void testCustomer_12() throws Exception { //compare time past
		LocalDateTime pastDateTime = LocalDateTime.of(2022, 3, 15, 10, 0); // Set a past date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H"); // Specify the desired format
        String formattedDateTime = pastDateTime.format(formatter); // Convert LocalDateTime to formatted string
        Admin admin= Admin.getInstance();

        boolean notPast=admin.compareTime(formattedDateTime);
        assertEquals(false,notPast);
	}
	@Test
	public void testCustomer_13() throws Exception { //compare time future
		LocalDateTime pastDateTime = LocalDateTime.of(2030, 3, 15, 10, 0); // Set a past date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H"); // Specify the desired format
        String formattedDateTime = pastDateTime.format(formatter); // Convert LocalDateTime to formatted string
        Admin admin= Admin.getInstance();

        boolean notPast=admin.compareTime(formattedDateTime);
        assertEquals(true,notPast);
        admin.resetTime();
	}
	@Test
	public void testCustomer_14() throws Exception { //user found (only user)
		User user= new Customer("user","user");
		boolean nullCheck=user.equals(null);
		assertEquals(false,nullCheck);
	}
	@Test
	public void testCustomer_15() throws Exception { //no notifications bookings (only customer)
		Customer customer= new Customer("customer","customer");
		setOutput();
		customer.checkNotifications();
		assertEquals("Sorry, there is no record of any cancelled bookings.\n",getOutput());
	}
	@Test
	public void testCustomer_16() throws Exception { //notifications present (customer +facility)
		Customer customer= new Customer("customer","customer");
		SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
		customer.update(facility, "06-12-2023");
		setOutput();
		customer.checkNotifications();
		assertEquals("Tennis can be booked for time 06-12-2023 on first come first served basis!\n",getOutput());
	}
	@Test
	public void testCustomer_17() throws Exception { //user found (user + customer)
		User customer1= new Customer("customer", "customer");
		Customer customer= new Customer("customer","customer");
		boolean result= customer1.equals(customer);
		assertEquals(true,result);
	}
	@Test
	public void testCustomer_18() throws Exception { //viewing bookings in the past (customer + admin)
		Bookings.resetBookingID();
		Customer customer = new Customer("username", "password");
		SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
		customer.createBooking(facility, "22-11-2024", 16, "CC");
		LocalDateTime futureDateTime = LocalDateTime.of(2025, 9, 30, 15, 30);
		setOutput();
		customer.viewCompletedBookings(futureDateTime);
		assertEquals("Membership Type: Standard Member\n"
				+ "Booking ID: 1\n"
				+ "Booking Date: 22-11-2024\n"
				+ "Booking Start Time: 16\n"
				+ "Facility: Tennis\n"
				+"\n",getOutput());
	}
	@Test
	public void testCustomer_19() throws Exception { //
		LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current date and time

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Specify the desired format
        String formattedDate = currentDateTime.format(formatter); // Convert LocalDateTime to formatted string
        
        Admin admin= Admin.getInstance();
        SportFacility facility = new TennisCourt("Tennis", 9, 23, 40);
        int result=admin.setFacStartHour(25, formattedDate);
        assertEquals(25,result);
	}
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
