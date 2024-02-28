package Exceptions;

/**
 * Exception thrown when an execution command is incorrect, leading to the inability to perform a specific operation.
 * This exception is a subclass of RuntimeException and is used to indicate issues with command execution.
 */
public class IncorrectExecuteCommend extends RuntimeException {

    // Error message for incorrect command execution
    private static final String UNIDENTIFIED_COMMAND = "Did not execute due to an incorrect command.";

    /**
     * Constructs an instance of IncorrectExecuteCommend with the predefined error message.
     */
    public IncorrectExecuteCommend() {
        super(UNIDENTIFIED_COMMAND);
    }
}
