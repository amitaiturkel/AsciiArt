package Exceptions;

/**
 * Exception thrown when there is an issue with removing characters, typically due to an incorrect format.
 * This exception is a subclass of RuntimeException and is used to indicate problems with character removal.
 */
public class IncorrectRemoveChar extends RuntimeException {

    // Error message for incorrect character removal format
    private static final String INCORRECT_REMOVE_INPUT = "Did not remove due to an incorrect format.";

    /**
     * Constructs an instance of IncorrectRemoveChar with the predefined error message.
     */
    public IncorrectRemoveChar() {
        super(INCORRECT_REMOVE_INPUT);
    }
}
