package image;

import java.awt.*;

public  class ImageOperator {


    private ImageOperator(){}

    public static double getImageAverage(double[][] image, int rows, int cols){
        double sum = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sum += image[row][col] ;
            }
        }
        return (double) sum /(rows+1) *(cols+1);

    }
    public static Color[][] getSubImage(Image image, int startRow, int endRow, int startCol, int endCol) {
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
                subImage[row][col] = image.getPixel(clippedStartRow + row, clippedStartCol + col);
            }
        }

        return subImage;
    }
    public static Color[][][][] createSubImageArray(Image image, int resolution) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int subImageSize = Math.min(imageWidth, imageHeight) / resolution;

        int rows = imageHeight / subImageSize;
        int cols = imageWidth / subImageSize;

        Color[][][][] subImagesArray = new Color[rows][cols][][];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Calculate start and end coordinates for the sub-image
                int startRow = row * subImageSize;
                int endRow = startRow + subImageSize;
                int startCol = col * subImageSize;
                int endCol = startCol + subImageSize;

                // Extract sub-image using the getSubImage function
                Color[][] subImage = getSubImage(image, startRow, endRow, startCol, endCol);

                // Store the sub-image in the 2D array
                subImagesArray[row][col] = subImage;
            }
        }

        return subImagesArray;
    }
    private static double colorToGray(Color color){
        return color.getRed() * 0.2126 + color.getGreen() * 0.7152
                + color.getBlue() * 0.0722;
    }
    public static double ImageBrightness(Image image) {
        Color[][] imageColors = ImageToColorArray(padding(image));// if already padded doent do anything
        double sum = 0;
        for (int row = 0;row < image.getHeight(); row++) {
            for(int col = 0 ;col < image.getWidth();col++){
                sum +=colorToGray(imageColors[row][col]);
            }

        }
        return sum /(image.getHeight() * image.getWidth() *255);
    }

        public static Color[][] ImageToColorArray (Image image){
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

        public static Image padding (Image image){
            int width = image.getWidth();
            int height = image.getHeight();
            int new_width = findNextTwoPower(width);
            int new_height = findNextTwoPower(height);

            Color[][] image_colors = new Color[new_height][new_width];
            // Calculate padding on all sides
            int paddingWidthLeft = (new_width - width) / 2;
            int paddingHeightTop = (new_height - height) / 2;
            // Copy original image to the new array with symmetrical padding
            for (int row = 0; row < height; row++) {
                for (int col = paddingWidthLeft; col < (width + paddingWidthLeft); col++) {
                    image_colors[row + paddingHeightTop][col] = image.getPixel(row, col - paddingWidthLeft);
                }
            }
            // Fill the left and right padded regions with white color
            for (int row = 0; row < new_height; row++) {
                // Fill left side
                for (int col = 0; col < paddingWidthLeft; col++) {
                    image_colors[row][col] = Color.WHITE;
                }
                // Fill right side
                for (int col = (width + paddingWidthLeft); col < (new_width); col++) {
                    image_colors[row][col] = Color.WHITE;
                }
            }
            return new Image(image_colors, new_width, new_height);
        }

        private static int findNextTwoPower ( int num){
            int power = 2;
            while (power < num) {
                power *= 2;
            }
            return power;
        }

    }
