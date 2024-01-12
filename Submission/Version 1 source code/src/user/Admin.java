package user;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import sportfacility.*;

public class Admin extends User {

    private List<Bookings> cancelRequests = new ArrayList<>();

    public Admin(String username, String password) {
        super(username, password);
    }

    // where do i add facility )):
    // consider editing facility(booking fee change)

    public boolean receiveCancelRequest(Customer customer, int bookingId) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H");

        // Parsing booking start time to LocalDateTime
        String bookingStartTimeString = customer.getBookingStartTime(bookingId);
        LocalDateTime bookingStartTime = LocalDateTime.parse(bookingStartTimeString, formatter);

        long hoursBetween = ChronoUnit.HOURS.between(bookingStartTime, currentTime);

        if (hoursBetween >= 6) {
            customer.cancelBooking(bookingId);
            return true;
        } else {
            System.out.print("Sorry, this booking cannot be cancelled and is therefore non-refundable.\n" +
                    "A booking can only be cancelled 6 hours or more in advance.\n");
            return false;
        }
    }

}
