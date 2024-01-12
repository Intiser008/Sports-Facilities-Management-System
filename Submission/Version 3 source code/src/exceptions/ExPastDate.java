package exceptions;

public class ExPastDate extends Exception{
    public ExPastDate(){
        super("Date is in the past. Try again.\n");
    }
}
