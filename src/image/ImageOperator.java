package image;

import java.awt.Color;

/**
 * A utility class that provides various operations on images.
 */
public class ImageOperator {

    // Constants
    private static final double RED_TO_GRAY = 0.2126;
    private static final double GREEN_TO_GRAY = 0.7152;
    private static final double BLUE_TO_GRAY = 0.0722;

    private static final int POWER_OF_TWO = 2;
    private static final int BIGGEST_RGB = 255;

    private ImageOperator() {
        // Private constructor to prevent instantiation, as this is a utility class.
    }

    /**
     * Calculates the average value of a 2D array representing an image.
     *
     * @param image 2D array representing the image.
     * @param rows  Number of rows in the image.
     * @param cols  Number of columns in the image.
     * @return Average value of the image.
     */
    public static double getImageAverage(double[][] image, int rows, int cols) {
        double sum = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sum += image[row][col];
            }
        }
        return (double) sum / ((rows + 1) * (cols + 1));
    }

    /**
     * Retrieves a sub-image from the given image based on specified coordinates.
     *
     * @param image      The original image.
     * @param startRow   Starting row index of the sub-image.
     * @param endRow     Ending row index of the sub-image.
     * @param startCol   Starting column index of the sub-image.
     * @param endCol     Ending column index of the sub-image.
     * @return 2D array representing the sub-image.
     */
    public static Color[][] getSubImage(Image image,
                                        int startRow,
                                        int endRow,
                                        int startCol,
                                        int endCol) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        // Ensure the sub-image boundaries do not exceed the original image size
        int clippedStartRow = Math.max(0, Math.min(startRow, imageHeight - 1));
        int clippedEndRow = Math.max(0, Math.min(endRow, imageHeight));
        int clippedStartCol = Math.max(0, Math.min(startCol, imageWidth - 1));
        int clippedEndCol = Math.max(0, Math.min(endCol, imageWidth));

        // Calculate the size of the sub-image
        int subImageHeight = clippedEndRow - clippedStartRow;
        int subImageWidth = clippedEndCol - clippedStartCol;

        Color[][] subImage = new Color[subImageHeight][subImageWidth];

        for (int row = 0; row < subImageHeight; row++) {
            for (int col = 0; col < subImageWidth; col++) {
                // TODO magic number
                subImage[row][col] = image.getPixel(clippedStartRow + row, clippedStartCol + col);
            }
        }
        return subImage;
    }

    /**
     * Creates a 2D array of sub-images from the given image based on a specified resolution.
     *
     * @param image      The original image.
     * @param resolution Desired resolution for sub-images.
     * @return 2D array of sub-images.
     */
    public static Image[][] createSubImageArray(Image image, int resolution) {

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        // Calculate the size of each sub-image based on the desired resolution
        int subImageSize = imageWidth / resolution;
        // Calculate the number of rows and columns in the resulting array
        int rows = imageHeight / subImageSize;
        int cols = resolution;
        Image[][] subImagesArray = new Image[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Calculate start and end coordinates for the sub-image
                int startRow = row * subImageSize;
                int endRow = startRow + subImageSize;
                int startCol = col * subImageSize;
                int endCol = startCol + subImageSize;
                // Extract sub-image using the getSubImage function
                Color[][] subImage = getSubImage(image, startRow, endRow, startCol, endCol);
                // Create an Image object and store it in the 2D array
                subImagesArray[row][col] = new Image(subImage, subImageSize, subImageSize);
            }
        }
        return subImagesArray;
    }

    /**
     * Converts a Color object to its corresponding grayscale value.
     *
     * @param color Color object to convert.
     * @return Grayscale value.
     */
    public static double colorToGray(Color color) {

        return  color.getRed() * RED_TO_GRAY +
                color.getGreen() * GREEN_TO_GRAY +
                color.getBlue() * BLUE_TO_GRAY;
    }

    /**
     * Calculates the brightness of an image.
     *
     * @param image The image to calculate brightness for.
     * @return The brightness of the image.
     */
    public static double ImageBrightness(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();
        double sum = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sum += colorToGray(image.getPixel(row, col));
            }
        }

        return sum/(image.getWidth() *image.getHeight() * BIGGEST_RGB);
    }

    /**
     * Converts an Image object to a 2D array of Color objects.
     *
     * @param image The Image object to convert.
     * @return 2D array of Color objects.
     */
    public static Color[][] ImageToColorArray(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();

        Color[][] imageColors = new Color[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                imageColors[row][col] = image.getPixel(row, col);
            }
        }

        return imageColors;
    }

    /**
     * Pads an image to the next power of two, ensuring symmetry.
     *
     * @param image The original image.
     * @return Padded Image object.
     */
    public static Image padding(Image image) {
        int width = image.getWidth(), height = image.getHeight(),newWidth = findNextTwoPower(width),
        newHeight = findNextTwoPower(height),paddingWidthLeft = (newWidth - width) / 2;
        int paddingHeightTop = (newHeight - height) / 2;
        Color[][] imageColors = new Color[newHeight][newWidth];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width ; col++) {
                imageColors[row + paddingHeightTop][col + paddingWidthLeft] = image.getPixel(row, col);
            }
        }
        for (int row = 0; row < newHeight; row++) {
            for (int col = 0; col < paddingWidthLeft; col++) {
                imageColors[row][col] = Color.WHITE;
            }
            for (int col = (width + paddingWidthLeft); col < newWidth; col++) {
                imageColors[row][col] = Color.WHITE;
            }
        }
        for (int row = 0; row < paddingHeightTop; row++) {
            for (int col = 0; col < newWidth; col++) {
                imageColors[row][col] = Color.WHITE;
            }
        }
        for (int row = (height + paddingHeightTop); row < newHeight; row++) {
            // Fill bottom side
            for (int col = 0; col < newWidth; col++) {
                imageColors[row][col] = Color.WHITE;
            }
        }
        return new Image(imageColors, newWidth, newHeight);
    }

    /**
     * Finds the next power of two for a given number.
     *
     * @param num The input number.
     * @return The next power of two.
     */
    private static int findNextTwoPower(int num) {
        int power = POWER_OF_TWO;
        while (power < num) {
            power <<= 1;
        }
        return power;
    }
}

