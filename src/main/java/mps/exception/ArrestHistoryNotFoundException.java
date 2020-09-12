package mps.exception;

public class ArrestHistoryNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ArrestHistoryNotFoundException(String message) {
        super(message);
    }
}
