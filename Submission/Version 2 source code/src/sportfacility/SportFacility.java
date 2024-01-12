package sportfacility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import exceptions.ExWrongDate;

public abstract class SportFacility {
	private int bookingFee;
	private Map<String, Boolean> timeTable;
	private int closingHours;
	private int openingHours;
	// private DatabaseController DBController;
	private List<Review> allReviews;

	public SportFacility(int openingHours, int closingHours, int bookingFee){
		this.openingHours = openingHours;
		this.closingHours = closingHours;
		this.timeTable = new HashMap<>();
		this.allReviews = new ArrayList<>();
		this.bookingFee = bookingFee;
	}

	public void book(String dateHour) {
		//13-02-2000 13
		//13-15 
		String[] parts = dateHour.split(" ");
		int hour = Integer.parseInt(parts[1]);
	
		if (hour < 0 || hour > 23) {
			System.out.print("Please put time in hours only (0-23).\n");
			return;
		}
	
		if (hour >= openingHours) {
			if (hour < closingHours) {
				if (timeTable.containsKey(dateHour) && timeTable.get(dateHour)) {
					System.out.print("Sorry, this time slot is already booked.\n");
					return;
				}
				else{
				timeTable.put(dateHour, true);
				System.out.print("Booking Successful for facility " + dateHour + "\n");
				}

			} else {
				System.out.print("Sorry, the facility is closed during this time\n");
			}
		} else {
			System.out.print("Sorry, the facility is not open at this time\n");
		}
		
	}
	


	public void cancelBooking(String dateHour) {
		if (!timeTable.containsKey(dateHour)) {
			System.out.print("No booking found for " + dateHour + ". Unable to cancel.\n");
			return;
		}
	
		if (!timeTable.get(dateHour)) {
			System.out.print("The time slot for " + dateHour + " is already free.\n");
			return;
		}
	
		timeTable.put(dateHour, false);
		System.out.print("Booking cancelled for " + dateHour +"\n");
	}

	public void addReview(Review review) {
        allReviews.add(review);
    }

	public List<Review> getAllReviews() {
        return new ArrayList<>(allReviews); // Return a copy to prevent external modification
    }

	public int getBookingFee(){
		return this.bookingFee;
	}

	public void writeReview(Review review){
		this.addReview(review);
	}

	public boolean isBooked(String dateHour){
		//changes made by taswar
		String[] parts = dateHour.split(" ");
		int hour = Integer.parseInt(parts[1]);


		if (timeTable.containsKey(dateHour) || hour >= closingHours ||hour < openingHours) {
			return true;
		}
		return false;
	}

	public void showAvailableSlots(String date) throws ExWrongDate {
		//check if date is in correct format else throw exception Wrong Date
		isDateFormatCorrect(date);

		//if not show available time slots
		System.out.print("Available time slots for " + date + ":\n");
	
		for (int hour = openingHours; hour < closingHours; hour++) {
			String dateHour = date + " " + hour;
			if (!timeTable.containsKey(dateHour) || !timeTable.get(dateHour)) {
				System.out.print("Time slot " + hour + ":00 is available.\n");
			}
		}
	}

	public void isDateFormatCorrect(String date) throws ExWrongDate{
		if (date.length() != 10) {
			throw new ExWrongDate(); // Throw exception if the length is not as expected
		}
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date); // Attempt to parse the date
		} catch (ParseException e) {
			throw new ExWrongDate(); 
		}


	}
	
	
}