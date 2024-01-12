package main;
import sportfacility.SportFacility;
import transaction.PaymentStrategy;
import user.Customer;

public class Customer_MainStub extends Customer{

    public Customer_MainStub(String username, String password) {
        super(username, password);
    }


    @Override
    public boolean createBooking(SportFacility facility, String bookingDate, int startTime, String paymentStrategy){
        return true;
    }
}
