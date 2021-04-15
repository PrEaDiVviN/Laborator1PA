package compulsory.exceptions;

public class NoMovieException extends Exception{
    public NoMovieException() {
        super("Exception: no movie was set for the current operation");
    }
}
