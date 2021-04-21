package compulsory.exceptions;

public class NoReportException extends Exception{
    public NoReportException() {
        super("Exception: no employee list was set for the current operation!");
    }
}
