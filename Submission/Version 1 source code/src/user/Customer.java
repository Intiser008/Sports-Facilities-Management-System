package user;

import java.text.SimpleDateFormat;
import java.util.*;
import sportfacility.*;
import transaction.*;

public class Customer extends User {

	private List<Bookings> bookingsList = new ArrayList<>();
	private Member memberType;
	private int loyaltyPoints;

	public Customer(String username, String password) {
		super(username, password);
		this.loyaltyPoints = 0;
		this.memberType = new StandardMember();

	}

	// convert date and time to string
	public static String concatenateStringAndInt(String str, int number) {
		return str + " " + number;
	}

	private PaymentStrategy paymentStrat(String strat) {
		if (strat.equals("CC")) {
			return new CreditCardPayment();
		} else {
			return new PayPalPayment();
		}
	}
	private RefundStrategy refundStrat(String strat) {
		if (strat.equals("CC")) {
			return new CreditCardRefund();
		} else {
			return new PayPalRefund();
		}
	}

	public boolean createBooking(SportFacility facility, String bookingDate, int startTime, String paymentString) {

		PaymentStrategy paymentStrategy = paymentStrat(paymentString);
		RefundStrategy refundStrategy=refundStrat(paymentString);
		if (facility.isBooked(concatenateStringAndInt(bookingDate, startTime))) {
			facility.book(concatenateStringAndInt(bookingDate, startTime));
			return false;
		} else {
			// Booking is added, even though transaction might fail later on

			Bookings NewBooking = new Bookings(facility, bookingDate, startTime);
			System.out.println("Redirecting you to transaction...");
			NewBooking.calculatePrice(this, paymentStrategy, refundStrategy);
			if (NewBooking.paymentProcessFlag()) {
				facility.book(concatenateStringAndInt(bookingDate, startTime));
				bookingsList.add(NewBooking);
				setMemberType();
				loyaltyPoints += 10;
				return true;
			}
		}
		return false;

		/*
		 * call the approve booking request static method over here. If it is approved,
		 * we add the new booking to the bookingsList.
		 */

		// check and change state of customer

	}

	public void viewBookings() {

		// Show Customer's Name(membershipttpe)
		System.out.print("Membership Type: " + memberType.toString() + "\n");
		for (Bookings booking : bookingsList) {

			System.out.print("Booking ID: " + booking.getBookingId() + "\n");
			System.out.print("Booking Date: " + booking.getBookingDate() + "\n");
			System.out.print("Booking Start Time: " + booking.getStartTime() + "\n");
			System.out.print("\n");
		}
	}

	public void cancelBooking(int bookingId) {
		// Remove from the ArrayList of AllBookings stored in main
		for (Bookings booking : bookingsList) {
			if ((booking.getBookingId() == bookingId)) {
				booking.cancel(); // will remove booking from the facility hashmap and transaction object called
									// to process refund
				bookingsList.remove(booking); // will remove booking from own bookinglist
				break;
			}

		}
		// throw exception if bookingid invalid inside else block
	}

	public List<Bookings> getList() {
		return bookingsList;
	}

	public double getMemberOffer() {
		return (100 - memberType.calculate(loyaltyPoints, bookingsList.size())) / 100;
	}

	public String getMemberType() {

		return memberType.toString();
	}

	public void setMemberType() {
		if (loyaltyPoints >= 10) {
			memberType = new GoldMember();
		}
	}

	public void giveRating(String comment, int rate) {
		Review review = new Review(comment, rate);
		// SportFacility.writeReview(review);

	}

	public String getBookingStartTime(int bookingId) {
		for (Bookings booking : bookingsList) {
			if ((booking.getBookingId() == bookingId)) {
				return booking.getBookingInfo();
			}

		}
		return "";
	}

}