package exceptions;

public class ExPasswordIncorrect extends Exception {
    public ExPasswordIncorrect(){
        super("Password incorrect. Please try again.\n");
    }
}
