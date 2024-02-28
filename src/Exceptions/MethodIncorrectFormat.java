package Exceptions;

/**
 * Exception thrown when there is an issue with changing the output method due to an incorrect format.
 * This exception is a subclass of RuntimeException and is used to indicate problems with changing the output method.
 */
public class MethodIncorrectFormat extends RuntimeException {

    // Error message for incorrect output method format
    private static final String INCORRECT_OUTPUT_INPUT = "Did not change output method due to an incorrect format.";

    /**
     * Constructs an instance of MethodIncorrectFormat with the predefined error message.
     */
    public MethodIncorrectFormat() {
        super(INCORRECT_OUTPUT_INPUT);
    }
}
