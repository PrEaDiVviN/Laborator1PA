package compulsory.exceptions;

public class NoEmployeeException extends Exception{
    public NoEmployeeException() {
        super("Exception: no employee was set for the current operation");
    }
}
