package exceptions;

public class ExPasswordInvalid extends Exception{
    public ExPasswordInvalid(){
        super("Password must not contain any spaces, and must contain at least one upper case letter, one lower case letter and a number.\n");
    }
}
