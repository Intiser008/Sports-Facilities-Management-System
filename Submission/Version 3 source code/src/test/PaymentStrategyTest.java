package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import transaction.*;

public class PaymentStrategyTest {

    // Tests for CreditCardPayment
    @Test
    public void creditCardPayment_processPayment() throws Exception{
        setOutput();
        CreditCardPayment ccp = new CreditCardPayment();
        ccp.processPayment(100.0);
        assertEquals("Visa processed the payment with amount of 100.00.\n", getOutput());
    }

    // Tests for CreditCardRefund
    @Test
    public void creditCardRefund_processRefund() throws Exception {
        setOutput();
        CreditCardRefund ccr = new CreditCardRefund();
        ccr.processRefund(100.0);
        assertEquals("Visa processed the refund with amount of 100.00.\n", getOutput());
    }

    // Tests for PayPalPayment
    @Test
    public void payPalPayment_processPayment() throws Exception{
        setOutput();
        PayPalPayment ppp = new PayPalPayment();
        ppp.processPayment(100.0);
        assertEquals("PayPal processed the payment with amount of 100.00.\n", getOutput());
    }

    // Tests for PayPalRefund
    @Test
    public void payPalRefund_processRefund() throws Exception {
        setOutput();
        PayPalRefund ppr = new PayPalRefund();
        ppr.processRefund(100.0);
        assertEquals("\nPayPal processed the refund with amount of 100.00.\n", getOutput());
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
