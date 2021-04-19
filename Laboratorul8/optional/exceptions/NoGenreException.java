package compulsory.exceptions;

public class NoGenreException extends Exception{
    public NoGenreException() {
        super("Exception: no genre was set for the current operation");
    }
}
