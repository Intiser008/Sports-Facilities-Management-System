package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import sportfacility.SportFacility;

public class testSportFacility {

    @Test
    public void testSportFacility_01() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23,13);
        facility.book("13-02-2000 10");
        assertEquals("Booking Successful for facility 13-02-2000 10\n", getOutput());
    }

    @Test
    public void testSportFacility_02() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23,12);
        facility.book("13-02-2000 10"); // First booking attempt
        getOutput(); // Discard the output of the first booking attempt
        setOutput(); // Reset output for capturing the second attempt
        facility.book("13-02-2000 10"); // Second booking attempt
        assertEquals("Sorry, this time slot is already booked.\n", getOutput());
    }

    @Test
    public void testSportFacility_03() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23,11);
        facility.book("13-02-2000 8");
        assertEquals("Sorry, the facility is not open at this time\n", getOutput());
    }

    @Test
    public void testSportFacility_04() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23,10);
        facility.book("13-02-2000 23");
        assertEquals("Sorry, the facility is closed during this time\n", getOutput());
    }

        @Test
    public void testSportFacility_05() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23,12);
        facility.book("13-02-2000 10");
        facility.book("13-02-2000 11");
        facility.book("13-02-2000 22"); // First booking attempt
        getOutput(); // Discard the output of the first booking attempt
        setOutput();
        facility.showAvailableSlots("13-02-2000");
        String expected = "Available time slots for 13-02-2000:\n" +
                  "Time slot 9:00 is available.\n" +
                  "Time slot 12:00 is available.\n" +
                  "Time slot 13:00 is available.\n" +
                  "Time slot 14:00 is available.\n" +
                  "Time slot 15:00 is available.\n" +
                  "Time slot 16:00 is available.\n" +
                  "Time slot 17:00 is available.\n" +
                  "Time slot 18:00 is available.\n" +
                  "Time slot 19:00 is available.\n" +
                  "Time slot 20:00 is available.\n" +
                  "Time slot 21:00 is available.\n";

        assertEquals(expected, getOutput());
    }
    @Test
    public void testSportFacility_06() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23,13);
        facility.book("13-02-2000 -1");
        assertEquals("Please put time in hours only (0-23).\n", getOutput());
    }
    
    @Test
    public void testSportFacility_07() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23, 10);
        facility.cancelBooking("13-02-2000 10");
        assertEquals("No booking found for 13-02-2000 10. Unable to cancel.\n", getOutput());
    }

    @Test
    public void testSportFacility_08() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23, 10);
        facility.book("13-02-2000 11"); // Make a booking
        facility.cancelBooking("13-02-2000 11"); // Cancel the booking
        setOutput(); // Reset output
        facility.cancelBooking("13-02-2000 11"); // Try to cancel again
        assertEquals("The time slot for 13-02-2000 11 is already free.\n", getOutput());
    }

    @Test
    public void testSportFacility_09() throws Exception {
        setOutput();
        SportFacility facility = new SportFacility(9, 23, 10);
        facility.book("13-02-2000 12"); // Make a booking
        getOutput();
        setOutput();
        facility.cancelBooking("13-02-2000 12"); // Cancel the booking
        assertEquals("Booking cancelled for \n13-02-2000 12", getOutput());
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
