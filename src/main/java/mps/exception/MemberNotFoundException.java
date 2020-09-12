package mps.exception;

public class MemberNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public MemberNotFoundException(String message) {
        super(message);
    }
}
