package sportfacility;
public class BadmintonCourt extends SportFacility {
    public BadmintonCourt(String name, int openingTime, int closingTime, int bookingFee) {
        super(name, openingTime, closingTime, bookingFee); // Opening time 9:00, closing time 23:00
    }

    // Additional BadmintonCourt-specific methods can be added here
}