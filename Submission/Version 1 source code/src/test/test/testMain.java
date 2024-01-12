package test;
import main.Main;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import sportfacility.*;

public class testMain {
    //private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String dateNtime = now.format(formatter);

    public void setOutput(ByteArrayInputStream testIn, ByteArrayOutputStream outContent) {
        // Set up the output stream to capture the output
        System.setOut(new PrintStream(outContent));
        // Set up the input stream to simulate user input
        System.setIn(testIn);
    }

    public void getOutput() {
        // Restore the original output and input streams
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testMain_01() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("0\n".getBytes()), outContent);
        Main.main(new String[]{}); // Run the main method which should now use the simulated input



        String expectedOutput = "Shahbagh Sports Complex\n" +
                                "---------------------------------------------------------\n" +
                                "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" +
                                "Input: " +
                                "Thank you for using our system.\n";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); //.trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        //actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMain_02() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("1\nintiser\n12345678\n1\n1\n23-12-2023\n18\n1\n0\n0\n".getBytes()), outContent);
        Main.main(new String[]{}); // Run the main method which should now use the simulated input
        String expectedOutput = "Shahbagh Sports Complex\n" +
                                "---------------------------------------------------------\n" +
                                "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" +
                                "Input: " +
                                "Set a username: " +
                                "Set a password: " +
                                dateNtime + "\n" +
                                "Press 1 to make a booking.\n" +
                                "Press 2 to view bookings.\n" +
                                "Press 3 to cancel bookings.\n" +
                                "Press 4 to show available facilities.\n" +
                                "Press 0 to log out.\n" +
                                "Input: " +
                                "Choose a facility (Enter 0 to go to the main menu).\n" +
                                "1. Swimming\n2. Badminton\n3. Basketball\n4. Tennis\n" +
                                "Input: " +
                                "Enter date of booking (DD-MM-YYYY): " +
                                "Available time slots for 23-12-2023:\r\n" + //
                                "Time slot 9:00 is available.\n" + //
                                "Time slot 10:00 is available.\n" + //
                                "Time slot 11:00 is available.\n" + //
                                "Time slot 12:00 is available.\n" + //
                                "Time slot 13:00 is available.\n" + //
                                "Time slot 14:00 is available.\n" + //
                                "Time slot 15:00 is available.\n" + //
                                "Time slot 16:00 is available.\n" + //
                                "Time slot 17:00 is available.\n" + //
                                "Time slot 18:00 is available.\n" + //
                                "Time slot 19:00 is available.\n" +
                                "Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " +
                                "Select a payment method:\n" +
                                "1. Credit Card\n2. PayPal\n" +
                                "Input: " +
                                "Booking successfully create for 23-12-2023 from " + 
                                "18:00 to 19:00. Go to main menu to manage your bookings.\n" +
                                "------------------------------------------------------------------------------------------------------------------------------------------\r\n" + //
                                "Press 1 to make a booking.\n" + //
                                "Press 2 to view bookings.\n" + //
                                "Press 3 to cancel bookings.\n" + //
                                "Press 4 to show available facilities.\n" + //
                                "Press 0 to log out.\n" +
                                "Input: " +
                                "Shahbagh Sports Complex\n" +
                                "---------------------------------------------------------\n" +
                                "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" +
                                "Input: " +
                                "Thank you for using our system.\n";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); //.trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        //actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMain_03() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("1\nintiser\n12345678\n0\n0\n".getBytes()), outContent);
        Main.main(new String[]{}); // Run the main method which should now use the simulated input
        String expectedOutput = "Shahbagh Sports Complex\n" +
                                "---------------------------------------------------------\n" +
                                "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" +
                                "Input: " +
                                "Set a username: " +
                                "Set a password: " +
                                dateNtime + "\n" +
                                "Press 1 to make a booking.\n" +
                                "Press 2 to view bookings.\n" +
                                "Press 3 to cancel bookings.\n" +
                                "Press 4 to show available facilities.\n" +
                                "Press 0 to log out.\n" +
                                "Input: " +                                
                                "Shahbagh Sports Complex\n" +
                                "---------------------------------------------------------\n" +
                                "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" +
                                "Input: " +
                                "Thank you for using our system.\n";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); //.trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        //actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        assertEquals(expectedOutput, actualOutput);
    }
}
