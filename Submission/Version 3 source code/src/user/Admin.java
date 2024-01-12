package user;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import sportfacility.*;

public class Admin extends User {
    private static Admin adminInstance = new Admin("admin", "AstralTrapez0idB0dy");
    private LocalDateTime clock;
    private List<SportFacility> facilities;

    private Admin(String username, String password) {
        super(username, password);
        facilities = new ArrayList<SportFacility>();
        clock = LocalDateTime.now();
    }
    
    public int receiveCancelRequest(Customer customer, int bookingId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H");

        // Parsing booking start time to LocalDateTime
        String bookingStartTimeString = customer.getBookingStartTime(bookingId);
        if (bookingStartTimeString.equals(""))
            return 1;

        LocalDateTime bookingStartTime = LocalDateTime.parse(bookingStartTimeString, formatter);

        if (bookingStartTime.isBefore(clock.plusHours(6))){
            //customer.cancelBooking(bookingId);
            return 2;
        }
        customer.cancelBooking(bookingId);
        return 3;
        // long hoursBetween = ChronoUnit.HOURS.between(bookingStartTime, clock);

        // if (hoursBetween >= 6) {
        //     customer.cancelBooking(bookingId);
        //     return true;
        // } else {
        //     return false;
        // }
    }

    public boolean receiveBookingRequest(Customer customer, SportFacility facility, String bookingDate, int startTime,
            String paymentString) {
                                                                // only in future
            return customer.createBooking(facility, bookingDate, startTime, paymentString);

    }

    public boolean compareTime(String dateHour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H");
        LocalDateTime bookingStartTime = LocalDateTime.parse(dateHour, formatter);

        if (bookingStartTime.isBefore(clock)) {
            return false;
        } else {
            return true;
        }
    }

    public LocalDateTime getClock() {
        return clock;
    }

    public void changeTime(String date, String hour) {
        String[] sepDate = date.split("-");
        clock = LocalDateTime.of(Integer.parseInt(sepDate[2]), Integer.parseInt(sepDate[1]),
                Integer.parseInt(sepDate[0]), Integer.parseInt(hour), 0, 0);
    }

    public void resetTime() {
        clock = LocalDateTime.now();
    }

    public String printTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateNtime = clock.format(formatter);
        return (dateNtime + "\n");
    }

    public static Admin getInstance() {
        return adminInstance;
    }

    public static String concatenateStringAndInt(String str, int number) {
        return str + " " + number;
    }

    public List<SportFacility> addFacility(String facName) {
        if (facilities == null){
            createFacility(facName);
            System.out.print("A " + facName + " has been added successfully.\n");
        }
        else {
            boolean exists = false;
            for (SportFacility fac : facilities) {
                if (fac.toString().equals(facName)) {
                    System.out.print("The facility already exists!\n");
                    exists = true;
                    break;
                }
            }
            if (exists == false){
                createFacility(facName);
                System.out.print("A facility " + facName + " has been added successfully.\n");
            }
        }
        return facilities;
    }

    public void createFacility(String facName) {
        if (facName.equals("Swimming"))
            facilities.add(new SwimmingPool("Swimming Pool", 9, 20, 10));
        else if (facName.equals("Badminton"))
            facilities.add(new BadmintonCourt("Badminton Court", 9, 23, 20));
        else if (facName.equals("Basketball"))
            facilities.add(new BasketballCourt("Basketball Court", 9, 22, 120));
        else if (facName.equals("Tennis"))
            facilities.add(new TennisCourt("Tennis Court", 9, 22, 50));
        else if (facName.equals("Football"))
            facilities.add(new FootBallField("Football Field", 9, 23, 200));
    }

    public int setFacStartHour(int openingHours, String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate givenDate = LocalDate.parse(date, dateFormatter);
        LocalDate clockDate = clock.toLocalDate();
        int currHour = clock.getHour();
        if (givenDate.equals(clockDate))
            return Math.max(currHour, openingHours);
        return openingHours;
    }

    public boolean isPast(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate givenDate = LocalDate.parse(date, dateFormatter);
        LocalDate clockDate = clock.toLocalDate();
        if (givenDate.isBefore(clockDate))
            return true;
        return false;
    }
}