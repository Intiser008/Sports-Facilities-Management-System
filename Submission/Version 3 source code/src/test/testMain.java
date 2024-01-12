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
import user.Admin;

public class testMain {
    // private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String dateNtime = now.format(formatter);

    Admin admin = Admin.getInstance();

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
    public void testMain_00() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Thank you for using our system.";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
        //assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    public void testMain_01() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" +
                "---------------------------------------------------------\n" +
                "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" +
                "Input: " +
                "Thank you for using our system.\n";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testMain_02() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("2\nadmin\nAstralTrapez0idB0dy\n0\n1\nAlice\npassword\n/\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\r\n"
        		+ "---------------------------------------------------------\r\n"
        		+ "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\r\n"
        		+ "Input: "
                + "Input your username: "
                + "Input your password: "
                + admin.printTime()
                + "\n"
                + "Press 1 to add a facility.\n"
                + "Press 2 to change system time.\n"
                + "Press 0 to log out.\n"
                + "Input: "
                + "Shahbagh Sports Complex\r\n"
        		+ "---------------------------------------------------------\r\n"
        		+ "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\r\n"
                + "Input: "
        		+ "Set a username: "
        		+ "Set a password: "
        		+ "Password must not contain any spaces, and must contain at least one upper case letter, one lower case letter and a number.\r\n"
        		+ "Set a username: "
        		+ "Shahbagh Sports Complex\r\n"
        		+ "---------------------------------------------------------\r\n"
        		+ "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\r\n"
        		+ "Input: "
        		+ "Thank you for using our system.";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString().trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        //actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
        assertEquals(expectedOutput, actualOutput);
    } 
 
    @Test
    public void testMain_03() throws Exception {//1\n1\n31-11-2021\n0\n
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("2\nadmin\nAstralTrapez0idB0dy\n1\n1\n1\n2\n1\n3\n1\n4\n1\n5\n0\n1\nAlice\nPassword123\n1\n1\n05-12-2023\n15\nCC\n0\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input
       
        String expectedOutput = "Shahbagh Sports Complex\r\n"
                + "---------------------------------------------------------\r\n"
                + "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\r\n"
                + "Input: "
                + "Input your username: "
                + "Input your password: "
                + admin.printTime()
                + "\n"
                + "Press 1 to add a facility.\n"
                + "Press 2 to change system time.\n"
                + "Press 0 to log out.\n"
                + "Input: "
                + "Choose a facility to add.\r\n" + //
                        "1. Swimming\r\n" + //
                        "2. Badminton\r\n" + //
                        "3. Basketball\r\n" + //
                        "4. Tennis\r\n" + //
                        "5. Football\r\n" + //
                        "Input: "
                + "A facility Swimming has been added successfully.\n"
                + admin.printTime()
                + "\n"
                + "Press 1 to add a facility.\n"
                + "Press 2 to change system time.\n"
                + "Press 0 to log out.\n"
                + "Input: "
                + "Choose a facility to add.\r\n" + //
                        "1. Swimming\r\n" + //
                        "2. Badminton\r\n" + //
                        "3. Basketball\r\n" + //
                        "4. Tennis\r\n" + //
                        "5. Football\r\n" + //
                        "Input: "
                + "A facility Badminton has been added successfully.\n"
                + admin.printTime()
                + "\n"
                + "Press 1 to add a facility.\n"
                + "Press 2 to change system time.\n"
                + "Press 0 to log out.\n"
                + "Input: "
                + "Choose a facility to add.\r\n" + //
                        "1. Swimming\r\n" + //
                        "2. Badminton\r\n" + //
                        "3. Basketball\r\n" + //
                        "4. Tennis\r\n" + //
                        "5. Football\r\n" + //
                        "Input: "
                + "A facility Basketball has been added successfully.\n"
                + admin.printTime()
                + "\n"
                + "Press 1 to add a facility.\n"
                + "Press 2 to change system time.\n"
                + "Press 0 to log out.\n"
                + "Input: "
                + "Choose a facility to add.\r\n" + //
                        "1. Swimming\r\n" + //
                        "2. Badminton\r\n" + //
                        "3. Basketball\r\n" + //
                        "4. Tennis\r\n" + //
                        "5. Football\r\n" + //
                        "Input: "
                + "A facility Tennis has been added successfully.\n"
                + admin.printTime()
                + "\n"
                + "Press 1 to add a facility.\n"
                + "Press 2 to change system time.\n"
                + "Press 0 to log out.\n"
                + "Input: "
                + "Choose a facility to add.\r\n" + //
                        "1. Swimming\r\n" + //
                        "2. Badminton\r\n" + //
                        "3. Basketball\r\n" + //
                        "4. Tennis\r\n" + //
                        "5. Football\r\n" + //
                        "Input: "
                + "A facility Football has been added successfully.\n"
                + admin.printTime()
                + "\n"
                + "Press 1 to add a facility.\n"
                + "Press 2 to change system time.\n"
                + "Press 0 to log out.\n"
                + "Input: "
                + "Shahbagh Sports Complex\r\n"
                + "---------------------------------------------------------\r\n"
                + "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\r\n"
                + "Input: "
                + "Set a username: "
                + "Set a password: "
                + admin.printTime()
                + "\n"
                + "Press 1 to make a booking.\r\n" + //
                    "Press 2 to view bookings.\r\n" + //
                    "Press 3 to cancel bookings.\r\n" + //
                    "Press 4 to check for any cancelled bookings.\r\n" + //
                    "Press 5 to review any past bookings.\r\n" + //
                    "Press 6 to check facility ratings.\r\n" + //
                    "Press 0 to log out.\r\n" + //
                    "Input: "
                + "Choose a facility (Enter 0 to go to the main menu).\r\n" + //
                        "1. Swimming Pool\r\n" + //
                        "2. Badminton Court\r\n" + //
                        "3. Basketball Court\r\n" + //
                        "4. Tennis Court\r\n" + //
                        "5. Football Field\r\n" + //
                        "Input:"
                + "Enter date of booking (DD-MM-YYYY): "
                + "Available time slots for 05-12-2023:\r\n" + //
                        "Time slot 9:00 is available.\r\n" + //
                        "Time slot 10:00 is available.\r\n" + //
                        "Time slot 11:00 is available.\r\n" + //
                        "Time slot 12:00 is available.\r\n" + //
                        "Time slot 13:00 is available.\r\n" + //
                        "Time slot 14:00 is available.\r\n" + //
                        "Time slot 15:00 is available.\r\n" + //
                        "Time slot 16:00 is available.\r\n" + //
                        "Time slot 17:00 is available.\r\n" + //
                        "Time slot 18:00 is available.\r\n" + //
                        "Time slot 19:00 is available.\r\n" + //
                        "Enter preferred time slot (e.g. if you want to book at 20:00, type 20): "
                + "Select a payment method:\r\n" + //
                        "1. Credit Card (Enter CC)\r\n" + //
                        "2. PayPal (Enter PL)\r\n" + //
                        "Input: "
                + "Redirecting you to transaction...\r\n" + //
                        "Your total is: 10.0\r\n" + //
                        "Processing transaction...\r\n" + //
                        "Visa processed the payment with amount of 10.00.\r\n" + //
                        "Booking Successful for facility 05-12-2023 15\r\n" + //
                 admin.printTime() 
                +
                        "\r\n" + //
                        "Press 1 to make a booking.\r\n" + //
                        "Press 2 to view bookings.\r\n" + //
                        "Press 3 to cancel bookings.\r\n" + //
                        "Press 4 to check for any cancelled bookings.\r\n" + //
                        "Press 5 to review any past bookings.\r\n" + //
                        "Press 6 to check facility ratings.\r\n" + //
                        "Press 0 to log out.\r\n" + //
                        "Input: "
                    + "Shahbagh Sports Complex\r\n" + //
                            "---------------------------------------------------------\r\n" + //
                            "Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\r\n"
                + "Input: "
                + "Thank you for using our system."
                        ;

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();
       
        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
}
    
    
 
    
    @Test
    public void testMain_() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
        //assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    public void testMain_11() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("2\nadmin\n1\n1\nadmin\nAstralTrapez0idB0dy\n1\n1\n1\n2\n1\n3\n1\n4\n1\n5\n0\n1\nalice\npassword\nalice\nPassword123\n0\n2\nalice\naa\nalice\nPassword123\n2\n0\n3\n0\n4\n0\n5\n0\ngood\n4\n6\n1\n1\n01-01-2022\n01-01-2024\n1\n1\n10-12-2023\n21\ncc\n1\n1\n10-12-2023\n10\nPL\n1\n2\n10-12-2024\n21\nCC\n2\n0\n3\n2\n3\n2\n0\n0\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: " + 
        		"Input your password: " + 
        		"Password incorrect. Please try again.\n" + 
        		"Input your username: " + 
        		"Account does not exist.\n" + 
        		"Input your username: " + 
        		"Input your password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Swimming has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Badminton has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Basketball has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Tennis has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Football has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Set a username: " + 
        		"Set a password: " + 
        		"Password must not contain any spaces, and must contain at least one upper case letter, one lower case letter and a number.\n" + 
        		"Set a username: " + 
        		"Set a password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: " + 
        		"Input your password: " + 
        		"Password incorrect. Please try again.\n" + 
        		"Input your username: " + 
        		"Input your password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Membership Type: Standard Member\n" + 
        		"You have no bookings currently. \n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"" + /////////////
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Standard Member\n" + 
        		"You have no bookings currently. \n" + 
        		"Input: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Sorry, there is no record of any cancelled bookings.\n" + 
        		"Enter 0 to go back to main menu.\n" + 
        		"" +  ///////////////////
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Membership Type: Standard Member\n" + 
        		"Input booking ID to review a booking: " + 
        		"Enter comment: " + 
        		"Enter rating (a number between 1-5): " + 
        		"Thank you for your feedback.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Swimming Pool: \n" + 
        		"\n" + 
        		"Badminton Court: \n" + 
        		"\n" + 
        		"Basketball Court: \n" + 
        		"\n" + 
        		"Tennis Court: \n" + 
        		"\n" + 
        		"Football Field: \n" + 
        		"\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Date is in the past. Try again.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Input is not a number. Try again.\n" + 
        		//"Input: 2023-12-06 11:42:10\n" + 
        		"Input: "+admin.printTime()+"\n"
        		
        		+"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 10-12-2023:\n" + 
        		"Time slot 9:00 is available.\n" + 
        		"Time slot 10:00 is available.\n" + 
        		"Time slot 11:00 is available.\n" + 
        		"Time slot 12:00 is available.\n" + 
        		"Time slot 13:00 is available.\n" + 
        		"Time slot 14:00 is available.\n" + 
        		"Time slot 15:00 is available.\n" + 
        		"Time slot 16:00 is available.\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Payment method does not exist. Enter CC for Credit Card payment, or PL for payment via PayPal.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): 10-12-2023\n" + 
        		"Available time slots for 10-12-2023:\n" + 
        		"Time slot 9:00 is available.\n" + 
        		"Time slot 10:00 is available.\n" + 
        		"Time slot 11:00 is available.\n" + 
        		"Time slot 12:00 is available.\n" + 
        		"Time slot 13:00 is available.\n" + 
        		"Time slot 14:00 is available.\n" + 
        		"Time slot 15:00 is available.\n" + 
        		"Time slot 16:00 is available.\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): 10\n" + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Redirecting you to transaction...\n" + 
        		"Your total is: 10.0\n" + 
        		"Processing transaction...\n" + 
        		"PayPal processed the payment with amount of 10.00.\n" + 
        		"Booking Successful for facility 10-12-2023 10\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 10-12-2024:\n" + 
        		"Time slot 9:00 is available.\n" + 
        		"Time slot 10:00 is available.\n" + 
        		"Time slot 11:00 is available.\n" + 
        		"Time slot 12:00 is available.\n" + 
        		"Time slot 13:00 is available.\n" + 
        		"Time slot 14:00 is available.\n" + 
        		"Time slot 15:00 is available.\n" + 
        		"Time slot 16:00 is available.\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Time slot 20:00 is available.\n" + 
        		"Time slot 21:00 is available.\n" + 
        		"Time slot 22:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Redirecting you to transaction...\n" + 
        		"Your total is: 19.8\n" + 
        		"Processing transaction...\n" + 
        		"Visa processed the payment with amount of 19.80.\n" + 
        		"Booking Successful for facility 10-12-2024 21\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Membership Type: Gold Member\n" + 
        		"Booking ID: 1\n" + 
        		"Booking Date: 10-12-2023\n" + 
        		"Booking Start Time: 10\n" + 
        		"Facility: Swimming Pool\n" + 
        		"Booking ID: 2\n" + 
        		"Booking Date: 10-12-2024\n" + 
        		"Booking Start Time: 21\n" + 
        		"Facility: Badminton Court\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Gold Member\n" + 
        		"Booking ID: 1\n" + 
        		"Booking Date: 10-12-2023\n" + 
        		"Booking Start Time: 10\n" + 
        		"Facility: Swimming Pool\n" + 
        		"Booking ID: 2\n" + 
        		"Booking Date: 10-12-2024\n" + 
        		"Booking Start Time: 21\n" + 
        		"Facility: Badminton Court\n" + 
        		"Input: " + 
        		"Booking cancelled for 10-12-2024 21\n" + 
        		"Visa processed the refund with amount of 19.80.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Gold Member\n" + 
        		"Booking ID: 1\n" + 
        		"Booking Date: 10-12-2023\n" + 
        		"Booking Start Time: 10\n" + 
        		"Facility: Swimming Pool\n" + 
        		"Input: " + 
        		"Booking does not exist. Try again.\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Gold Member\n" + 
        		"Booking ID: 1\n" + 
        		"Booking Date: 10-12-2023\n" + 
        		"Booking Start Time: 10\n" + 
        		"Facility: Swimming Pool\n" + 
        		"Input: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Thank you for using our system.";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
       
    }
    @Test
    public void testMain_12() throws Exception {//1\n1\n31-11-2021\n0\n
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("1\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input
       
        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"The system is not yet ready to use. Please try again later. Thank you.\n" + 
        		"\n" + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Thank you for using our system.\n";

                // Trim the captured output to avoid issues with trailing newlines
                String actualOutput = outContent.toString(); // .trim();

                // Replace all system-dependent newlines with \n for consistent comparison
                 actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
                getOutput();
              //  expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
              //  actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
                assertEquals(expectedOutput, actualOutput);
               }
    
 
    
    
    
    @Test
    public void testMain_13() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		setOutput(new ByteArrayInputStream("1\n0\n".getBytes()), outContent);        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: " + 
        		"Input your password: " + 
        		"Password incorrect. Please try again.\n" + 
        		"Input your username: " + 
        		"Account does not exist.\n" + 
        		"Input your username: " + 
        		"Input your password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Swimming has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Badminton has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Basketball has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Tennis has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Football has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Set a username: " + 
        		"Set a password: " + 
        		"Password must not contain any spaces, and must contain at least one upper case letter, one lower case letter and a number.\n" + 
        		"Set a username: " + 
        		"Set a password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: " + 
        		"Input your password: " + 
        		"Password incorrect. Please try again.\n" + 
        		"Input your username: " + 
        		"Input your password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Membership Type: Standard Member\n" + 
        		"You have no bookings currently. \n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Standard Member\n" + 
        		"You have no bookings currently. \n" + 
        		"Input: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Sorry, there is no record of any cancelled bookings.\n" + 
        		"Enter 0 to go back to main menu.\n" + 
        		"" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Membership Type: Standard Member\n" + 
        		"Input booking ID to review a booking: " + 
        		"Enter comment: " + 
        		"Enter rating (a number between 1-5): " + 
        		"Thank you for your feedback.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Swimming Pool: \n" + 
        		"\n" + 
        		"Badminton Court: \n" + 
        		"\n" + 
        		"Basketball Court: \n" + 
        		"\n" + 
        		"Tennis Court: \n" + 
        		"\n" + 
        		"Football Field: \n" + 
        		"\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Date is in the past. Try again.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 01-01-2024:\n" + 
        		"Time slot 9:00 is available.\n" + 
        		"Time slot 10:00 is available.\n" + 
        		"Time slot 11:00 is available.\n" + 
        		"Time slot 12:00 is available.\n" + 
        		"Time slot 13:00 is available.\n" + 
        		"Time slot 14:00 is available.\n" + 
        		"Time slot 15:00 is available.\n" + 
        		"Time slot 16:00 is available.\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Payment method does not exist. Enter CC for Credit Card payment, or PL for payment via PayPal.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 10-12-2023:\n" + 
        		"Time slot 9:00 is available.\n" + 
        		"Time slot 10:00 is available.\n" + 
        		"Time slot 11:00 is available.\n" + 
        		"Time slot 12:00 is available.\n" + 
        		"Time slot 13:00 is available.\n" + 
        		"Time slot 14:00 is available.\n" + 
        		"Time slot 15:00 is available.\n" + 
        		"Time slot 16:00 is available.\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Redirecting you to transaction...\n" + 
        		"Your total is: 10.0\n" + 
        		"Processing transaction...\n" + 
        		"PayPal processed the payment with amount of 10.00.\n" + 
        		"Booking Successful for facility 10-12-2023 10\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 10-12-2024:\n" + 
        		"Time slot 9:00 is available.\n" + 
        		"Time slot 10:00 is available.\n" + 
        		"Time slot 11:00 is available.\n" + 
        		"Time slot 12:00 is available.\n" + 
        		"Time slot 13:00 is available.\n" + 
        		"Time slot 14:00 is available.\n" + 
        		"Time slot 15:00 is available.\n" + 
        		"Time slot 16:00 is available.\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Time slot 20:00 is available.\n" + 
        		"Time slot 21:00 is available.\n" + 
        		"Time slot 22:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Redirecting you to transaction...\n" + 
        		"Your total is: 19.8\n" + 
        		"Processing transaction...\n" + 
        		"Visa processed the payment with amount of 19.80.\n" + 
        		"Booking Successful for facility 10-12-2024 21\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 10-12-2024:\n" + 
        		"Time slot 9:00 is available.\n" + 
        		"Time slot 10:00 is available.\n" + 
        		"Time slot 11:00 is available.\n" + 
        		"Time slot 12:00 is available.\n" + 
        		"Time slot 13:00 is available.\n" + 
        		"Time slot 14:00 is available.\n" + 
        		"Time slot 15:00 is available.\n" + 
        		"Time slot 16:00 is available.\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Time slot 20:00 is available.\n" + 
        		"Time slot 22:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Sorry, this time slot is already booked.\n" + 
        		"You have been added to the waitlist, if the booker cancels their booking, you would be notified. Thanks.\n" + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"2. Badminton Court\n" + 
        		"3. Basketball Court\n" + 
        		"4. Tennis Court\n" + 
        		"5. Football Field\n" + 
        		"Input: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Gold Member\n" + 
        		"Booking ID: 1\n" + 
        		"Booking Date: 10-12-2023\n" + 
        		"Booking Start Time: 10\n" + 
        		"Facility: Swimming Pool\n" + 
        		"Booking ID: 2\n" + 
        		"Booking Date: 10-12-2024\n" + 
        		"Booking Start Time: 21\n" + 
        		"Facility: Badminton Court\n" + 
        		"Input: " + 
        		"Booking cancelled for 10-12-2024 21\n" + 
        		"Visa processed the refund with amount of 19.80.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Gold Member\n" + 
        		"Booking ID: 1\n" + 
        		"Booking Date: 10-12-2023\n" + 
        		"Booking Start Time: 10\n" + 
        		"Facility: Swimming Pool\n" + 
        		"Input: " + 
        		"Booking does not exist. Try again.\n" + 
        		"Enter Booking ID to cancel a booking.\n" + 
        		"Enter 0 to go to the main menu.\n" + 
        		"Membership Type: Gold Member\n" + 
        		"Booking ID: 1\n" + 
        		"Booking Date: 10-12-2023\n" + 
        		"Booking Start Time: 10\n" + 
        		"Facility: Swimming Pool\n" + 
        		"Input: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Membership Type: Gold Member\n" + 
        		"Input booking ID to review a booking: " + 
        		"Enter comment: " + 
        		"Enter rating (a number between 1-5): " + 
        		"Thank you for your feedback.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Thank you for using our system."
                ;

// Trim the captured output to avoid issues with trailing newlines
String actualOutput = outContent.toString(); // .trim();

// Replace all system-dependent newlines with \n for consistent comparison
// actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
getOutput();
expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
    }
    
    
    @Test
    public void testMain_14() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("5\na\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: 5\n" + 
        		"Invalid Token. Try again.\n" + 
        		"Input: a\n" + 
        		"Input is not a number. Try again.\n" + 
        		"Input: 0\n" + 
        		"Thank you for using our system.";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
         
    }
    
    @Test
    public void testMain_15() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("1\n2\n/\n2\nadmin\n/\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"The system is not yet ready to use. Please try again later. Thank you.\n" + 
        		"\n" + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: \n" + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: " + 
        		"Input your password: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Thank you for using our system.";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
        //assertEquals(expectedOutput, actualOutput);
    }    
    
    @Test
    public void testMain_16() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("2\nadmin\nAstralTrapez0idB0dy\n1\n1\n1\n2\n2\n06-12-2023\n15\n4\n0\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: " + 
        		"Input your password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.    \n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Swimming has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Badminton has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Enter the date you want to change to (DD-MM-YYYY): 06-12-2023\n" + 
        		"Enter the hour you want to change to (If you want to change hour to 13:00, enter 13): 15\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Invalid Token. Try again.\n" + 
        		"Input: 2023-12-06 15:00:00\n" + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Thank you for using our system.";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
         
    }
  
    
    @Test
    public void testMain_19() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        setOutput(new ByteArrayInputStream("2\nadmin\nAstralTrapez0idB0dy\n1\n1\n0\n1\nalice\nPassword123\n1\n/\n1\n1\n06-12-2023\n/\n1\n06-12-2023\n19\n/\n0\n0\n0\n".getBytes()), outContent);
        Main.main(new String[] {}); // Run the main method which should now use the simulated input

        String expectedOutput = "Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Input your username: " + 
        		"Input your password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility to add.\n" + 
        		"1. Swimming\n" + 
        		"2. Badminton\n" + 
        		"3. Basketball\n" + 
        		"4. Tennis\n" + 
        		"5. Football\n" + 
        		"Input: " + 
        		"A facility Swimming has been added successfully.\n" + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to add a facility.\n" + 
        		"Press 2 to change system time.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Set a username: " + 
        		"Set a password: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"Input: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 06-12-2023:\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"Input: " + 
        		"Enter date of booking (DD-MM-YYYY): " + 
        		"Available time slots for 06-12-2023:\n" + 
        		"Time slot 17:00 is available.\n" + 
        		"Time slot 18:00 is available.\n" + 
        		"Time slot 19:00 is available.\n" + 
        		"Enter preferred time slot (e.g. if you want to book at 20:00, type 20): " + 
        		"Select a payment method:\n" + 
        		"1. Credit Card (Enter CC)\n" + 
        		"2. PayPal (Enter PL)\n" + 
        		"Input: " + 
        		"Choose a facility (Enter 0 to go to the main menu).\n" + 
        		"1. Swimming Pool\n" + 
        		"Input: " + 
        		admin.printTime() + 
        		"\n" + 
        		"Press 1 to make a booking.\n" + 
        		"Press 2 to view bookings.\n" + 
        		"Press 3 to cancel bookings.\n" + 
        		"Press 4 to check for any cancelled bookings.\n" + 
        		"Press 5 to review any past bookings.\n" + 
        		"Press 6 to check facility ratings.\n" + 
        		"Press 0 to log out.\n" + 
        		"Input: " + 
        		"Shahbagh Sports Complex\n" + 
        		"---------------------------------------------------------\n" + 
        		"Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n" + 
        		"Input: " + 
        		"Thank you for using our system.\n" + 
        		"";

        // Trim the captured output to avoid issues with trailing newlines
        String actualOutput = outContent.toString(); // .trim();

        // Replace all system-dependent newlines with \n for consistent comparison
        // actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
        getOutput();
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        actualOutput = actualOutput.replaceAll("\\s+", " ").trim();
         
    }
    
	/*
	 * @Test public void testMain_0() throws Exception { ByteArrayOutputStream
	 * outContent = new ByteArrayOutputStream(); setOutput(new
	 * ByteArrayInputStream("0\n".getBytes()), outContent); Main.main(new String[]
	 * {}); // Run the main method which should now use the simulated input
	 * 
	 * String expectedOutput = "";
	 * 
	 * // Trim the captured output to avoid issues with trailing newlines String
	 * actualOutput = outContent.toString(); // .trim();
	 * 
	 * // Replace all system-dependent newlines with \n for consistent comparison //
	 * actualOutput = actualOutput.replace(System.lineSeparator(), "\n");
	 * getOutput(); assertEquals(expectedOutput, actualOutput); }
	 */

    
  
    
  
}
