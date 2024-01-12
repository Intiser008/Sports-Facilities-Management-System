package exceptions;

public class ExNoAccount extends Exception{
    public ExNoAccount(){
        super("Account does not exist.\n");
    }
}
