package mps.exception;

public class MemberNotAuthorizedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public MemberNotAuthorizedException(String message) {
        super(message);
    }
}
