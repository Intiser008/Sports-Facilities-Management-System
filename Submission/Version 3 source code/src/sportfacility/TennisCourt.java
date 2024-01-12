package sportfacility;
public class TennisCourt extends SportFacility {
    public TennisCourt(String name,int openingTime, int closingTime, int bookingFee) {
        super(name, openingTime, closingTime, bookingFee); // Opening time 9:00, closing time 23:00
    }
}