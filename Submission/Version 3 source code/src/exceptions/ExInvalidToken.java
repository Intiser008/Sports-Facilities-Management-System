package exceptions;

public class ExInvalidToken extends Exception{
    public ExInvalidToken(){
        super("Invalid Token. Try again.\nInput: ");
    }
}
