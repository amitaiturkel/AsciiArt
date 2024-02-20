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
    public static Color[][] ImageToColor(Image image) {
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

    public static Image padding(Image image) {
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
        return new Image(image_colors,new_width,new_height);
    }

    private static int findNextTwoPower(int num){
        int power = 2;
        while (power < num){
            power *= 2;
        }
        return power;
    }
}

