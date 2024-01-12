package exceptions;

public class ExAccountExists extends Exception{
    public ExAccountExists(){
        super("Account already exists!\n");
    }
}
