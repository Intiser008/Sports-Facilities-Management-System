package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import sportfacility.BadmintonCourt;
import sportfacility.BasketballCourt;
import sportfacility.FootBallField;
import sportfacility.SportFacility;
import sportfacility.SwimmingPool;
import sportfacility.TennisCourt;
import user.*;
import sportfacility.*;
public class testSportFacility {

    @Test
    public void testSportFacility_01() throws Exception {
        setOutput();
        SportFacility facility = new BadmintonCourt("BadmintonCourt", 9, 23, 13);
        Customer customer = new Customer("customer", "customer");
        facility.book(customer, "13-02-2000 10");
        assertEquals("Booking Successful for facility 13-02-2000 10\n", getOutput());
    }

    @Test
    public void testSportFacility_02() throws Exception {
        setOutput();
        SportFacility facility = new BadmintonCourt("BadmintonCourt", 9, 23, 13);
        Customer customer = new Customer("customer", "customer");
        facility.book(customer, "13-02-2000 10");
        getOutput(); // Discard the output of the first booking attempt
        setOutput(); // Reset output for capturing the second attempt
        facility.book(customer, "13-02-2000 10"); // Second booking attempt
        assertEquals(
                "Sorry, this time slot is already booked.\nYou have been added to the waitlist, if the booker cancels their booking, you would be notified. Thanks.\n",
                getOutput());
    }

    @Test
    public void testSportFacility_03() throws Exception {
        setOutput();
        SportFacility facility = new FootBallField("FootBallField", 9, 23, 11);
        Customer customer = new Customer("customer", "customer");
        facility.book(customer, "13-02-2000 8");
        assertEquals("Sorry, the facility is not open at this time\n", getOutput());
    }

    @Test
    public void testSportFacility_04() throws Exception {
        setOutput();
        SportFacility facility = new SwimmingPool("SwimmingPool", 9, 23, 10);
        Customer customer = new Customer("customer", "customer");
        facility.book(customer, "13-02-2000 23");
        assertEquals("Sorry, the facility is closed during this time\n", getOutput());
    }

    @Test(expected = ExPastDate.class)
    public void testSportFacility_5() throws Exception {
        SportFacility facility = new TennisCourt("TennisCourt", 9, 23, 10);
        try {
            facility.showAvailableSlots("09-12-2000");
        } catch (ExPastDate e) {
            // Assert the exception message
            assertEquals("Date is in the past. Try again.\n", e.getMessage());
            throw e; // Re-throw to satisfy the 'expected' condition
        }
    }

    @Test(expected = ExWrongDate.class)
    public void testSportFacility_10() throws Exception {
        SportFacility facility = new TennisCourt("TennisCourt", 9, 23, 10);
        try {
            facility.showAvailableSlots("01-0f2-2023");
        } catch (ExWrongDate e) {
            // Assert the exception message
            assertEquals("Please Input Correct Date!\n", e.getMessage());
            throw e; // Re-throw to satisfy the 'expected' condition
        }
    }

    @Test
    public void testSportFacility_06() throws Exception {
        setOutput();
        SportFacility facility = new TennisCourt("TennisCourt", 9, 23, 13);
        Customer customer = new Customer("customer", "customer");
        facility.book(customer, "13-02-2000 -1");
        assertEquals("Please put time in hours only (0-23).\n", getOutput());
    }

    @Test
    public void testSportFacility_07() throws Exception {
        setOutput();
        SportFacility facility = new FootBallField("FootBallField", 9, 23, 10);
        facility.cancelBooking("13-02-2000 10");
        assertEquals("No booking found for 13-02-2000 10. Unable to cancel.\n", getOutput());
    }

    @Test
    public void testSportFacility_08() throws Exception {
        setOutput();
        SportFacility facility = new SwimmingPool("SwimmingPool", 9, 23, 10);
        Customer customer = new Customer("customer", "customer");
        facility.book(customer, "13-02-2000 11"); // Make a booking
        facility.cancelBooking("13-02-2000 11"); // Cancel the booking
        setOutput(); // Reset output
        facility.cancelBooking("13-02-2000 11"); // Try to cancel again
        assertEquals("The time slot for 13-02-2000 11 is already free.\n", getOutput());
    }

    @Test
    public void testSportFacility_09() throws Exception {
        setOutput();
        SportFacility facility = new TennisCourt("TennisCourt", 9, 23, 10);
        Customer customer = new Customer("customer", "customer");
        facility.book(customer, "13-02-2000 12"); // Make a booking
        getOutput();
        setOutput();
        facility.cancelBooking("13-02-2000 12"); // Cancel the booking
        assertEquals("Booking cancelled for 13-02-2000 12\n", getOutput());
    }

    @Test
    public void testSportFacility_11() throws Exception {
        SportFacility facility = new TennisCourt("TennisCourt", 9, 23, 10);
        Review review= new Review("Good",4);
        setOutput();
        facility.addReview(review);
        facility.printReviews();
        assertEquals("Review{comment='Good', rate=4}\n", getOutput());
    }
    @Test
    public void testSportFacility_12() throws Exception {
        SportFacility facility = new TennisCourt("TennisCourt", 9, 23, 10);
        Customer customer =new Customer("customer", "customer");
        facility.attach(customer, "06-12-2023");
        facility.notify("06-12-2023");
        setOutput();
        customer.checkNotifications();
        assertEquals("TennisCourt can be booked for time 06-12-2023 on first come first served basis!\n", getOutput());
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