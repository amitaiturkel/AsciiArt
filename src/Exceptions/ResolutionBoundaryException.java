package Exceptions;

/**
 * Exception thrown when attempting to change the resolution exceeds predefined boundaries.
 * This exception is a subclass of RuntimeException and is used to indicate problems
 * with resolution changes.
 */
public class ResolutionBoundaryException extends RuntimeException {

    // Error message for exceeding resolution boundaries
    private static final String RES_IS_TWO = "Did not change resolution due to exceeding boundaries.";

    /**
     * Constructs an instance of ResolutionBoundaryException with the predefined error message.
     */
    public ResolutionBoundaryException() {
        super(RES_IS_TWO);
    }
}
