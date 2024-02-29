package Exceptions;

/**
 * Exception thrown when an operation requires a non-empty character set, but the set is found to be empty.
 * This exception is a subclass of RuntimeException and is used to indicate issues related to an empty character set.
 */
public class EmptyCharSet extends RuntimeException {

    // Error message for an empty character set
    private static final String EMPTY_CHAR_SET = "Did not execute. Charset is empty.";

    /**
     * Constructs an instance of EmptyCharSet with the predefined error message.
     */
    public EmptyCharSet() {
        super(EMPTY_CHAR_SET);
    }
}
