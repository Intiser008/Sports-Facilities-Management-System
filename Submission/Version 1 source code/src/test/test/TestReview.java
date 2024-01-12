package test;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import sportfacility.*;

public class TestReview {

    @Test
    public void testReview_01() {
        String expectedComment = "Great facility!";
        int expectedRate = 5;

        Review review = new Review(expectedComment, expectedRate);

        assertEquals( "Great facility!",review.getComment());
    }


@Test
public void testReview_02() {
    String expectedComment = "Great facility!";
    int expectedRate = 4;

    Review review = new Review(expectedComment, expectedRate);

    assertEquals( 4,review.getRate());
	}

@Test
public void testReview_03() {
    Review review = new Review("Test comment", 3); // Initialize with a valid rate

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    review.setRate(6); // Set an invalid rate

    String consoleOutput = outputStream.toString().replaceAll("\\s+", "");
    System.setOut(System.out); // Reset the System.out to its original stream

    String expectedOutput = "Rating must be between 1 and 5.\n".replaceAll("\\s+", "");

    assertEquals(expectedOutput, consoleOutput);
}
}

