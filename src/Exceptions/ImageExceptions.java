package Exceptions;

/**
 * Exception thrown when an operation involving an image encounters an issue, such as problems with the image file.
 * This exception is a subclass of RuntimeException and is used to indicate issues related to image processing.
 */
public class ImageExceptions extends RuntimeException {

    // Error message for issues with the image file
    private static final String ERROR_PRINTING_IMAGE = "Did not execute due to a problem with the image file.";

    /**
     * Constructs an instance of ImageExceptions with the predefined error message.
     */
    public ImageExceptions() {
        super(ERROR_PRINTING_IMAGE);
    }
}
