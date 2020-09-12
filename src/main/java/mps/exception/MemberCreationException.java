package mps.exception;

public class MemberCreationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public MemberCreationException(String message) {
        super(message);
    }
}
