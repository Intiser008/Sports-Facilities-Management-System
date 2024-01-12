package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import transaction.*;

public class TestTransaction {

    @Test
    public void testPayPal(){
        PaymentStrategy paymentStrategy = new PayPalPayment();
        RefundStrategy refundStrategy = new PayPalRefund();
        double amount = 10;
        Transaction transaction = new Transaction(paymentStrategy, refundStrategy, amount);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        transaction.processPayment();
        transaction.processRefund();

        String consoleOutput = outputStream.toString();

        System.setOut(System.out);

        String result = String.format("PayPal processed the payment with amount of %.2f.\n" + 
                                        "PayPal processed the refund with amount of %.2f.\n", 
                                        amount, amount);

        assertEquals(result, consoleOutput);
    }

    @Test
    public void testRefundPaymentNotProcessed(){
        PaymentStrategy paymentStrategy = new PayPalPayment();
        RefundStrategy refundStrategy = new PayPalRefund();
        double amount = 10;
        Transaction transaction = new Transaction(paymentStrategy, refundStrategy, amount);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        transaction.processRefund();

        String consoleOutput = outputStream.toString();

        System.setOut(System.out);

        String result = "Payment is not processed.\n";

        assertEquals(result, consoleOutput);
    }

    @Test
    public void testCreditCardPayment(){
        PaymentStrategy paymentStrategy = new CreditCardPayment();
        RefundStrategy refundStrategy = new CreditCardRefund();
        double amount = 10;
        Transaction transaction = new Transaction(paymentStrategy, refundStrategy, amount);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        transaction.processPayment();
        transaction.processRefund();

        String consoleOutput = outputStream.toString();

        System.setOut(System.out);

        String result = String.format("Visa processed the payment with amount of %.2f.\n" + 
                                        "Visa processed the refund with amount of %.2f.\n", 
                                        amount, amount);

        assertEquals(result, consoleOutput);
    }

}
