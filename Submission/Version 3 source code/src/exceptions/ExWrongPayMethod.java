package exceptions;

public class ExWrongPayMethod extends Exception{
    public ExWrongPayMethod(){
        super("Payment method does not exist. Enter CC for Credit Card payment, or PL for payment via PayPal.\n");
    }
}
