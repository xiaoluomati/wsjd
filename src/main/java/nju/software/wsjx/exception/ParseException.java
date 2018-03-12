package nju.software.wsjx.exception;


/**
 * Ω‚Œˆ“Ï≥£¥ÌŒÛ
 * @author lr12
 *
 */
public class ParseException extends RuntimeException{
	 
    private static final long serialVersionUID = -4231054500293680225L;

    public ParseException() {
        super();
    }

    public ParseException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
