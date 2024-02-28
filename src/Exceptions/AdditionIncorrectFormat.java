package Exceptions;

/**
 * Exception thrown when an incorrect format is detected during addition operations.
 * This exception is a subclass of RuntimeException and is used to indicate issues related to adding elements.
 */
public class AdditionIncorrectFormat extends RuntimeException {

    // Error message for incorrect addition format
    private static final String INCORRECT_ADD_INPUT = "Did not add due to incorrect format.";

    /**
     * Constructs an instance of AdditionIncorrectFormat with the predefined error message.
     */
    public AdditionIncorrectFormat() {
        super(INCORRECT_ADD_INPUT);
    }
}
