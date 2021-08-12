package fr.epita.kesKonAVu.domain.common;

public class AlreadyExistingException extends BusinessException{
    private static final long serialVersionUID = 1L;

    public AlreadyExistingException() {
        super();
    }

    public AlreadyExistingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AlreadyExistingException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistingException(String message) {
        super(message);
    }

    public AlreadyExistingException(Throwable cause) {
        super(cause);
    }

}
