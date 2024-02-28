package Exceptions;

/**
 * Exception thrown when the format of the resolution input is incorrect.
 * This exception is a subclass of RuntimeException and is used to indicate problems with resolution input format.
 */
public class WrongFormatResolution extends RuntimeException {

    // Error message for incorrect resolution format
    private static final String INCORRECT_FORMAT = "Did not change resolution due to incorrect format.";

    /**
     * Constructs an instance of WrongFormatResolution with the predefined error message.
     */
    public WrongFormatResolution() {
        super(INCORRECT_FORMAT);
    }
}
