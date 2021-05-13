package exceptions;

public class AcceptConnectionException extends Exception{
    public AcceptConnectionException() {
        super("Exception: The connection with the current user failed!");
    }
}
