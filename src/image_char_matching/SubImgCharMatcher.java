package image_char_matching;

import image.ImageOperator;

import java.awt.*;
import java.util.*;

public class SubImgCharMatcher{
    private Set<Character> myCharSet = new HashSet<>();
    //private Map<double, Set<char>> doubleToCharMap = new HashMap<>();


    public SubImgCharMatcher(char[] charset){
        for(char c: charset){
            myCharSet.add(c);
        }
    }
    public void addChar(char c){
        myCharSet.add(c);
    }
    public void removeChar(char c){
        myCharSet.remove(c);

    }

    private double char_to_double(char c){
        int sum = 0;
        boolean[][] boolArray = CharConverter.convertToBoolArray(c);
        for (int row =0 ;row <CharConverter.DEFAULT_PIXEL_RESOLUTION;row++){
            for (int col =0 ;row <CharConverter.DEFAULT_PIXEL_RESOLUTION;col++)
                 if(boolArray[row][col]){
                     sum += 1;
                 }
        }
        return (double) sum / (Math.pow(CharConverter.DEFAULT_PIXEL_RESOLUTION, 2));
    }
    public double subImageBrightness(char[][] image ,int rows,int cols ) {
        double max_char = -1;
        double min_char = 17;

        double[][] new_image = new double[rows + 1][cols + 1];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                new_image[row][col] = char_to_double(image[row][col]);
                if (new_image[row][col] < min_char) {
                    min_char = new_image[row][col];
                }
                if (new_image[row][col] > max_char) {
                    max_char = new_image[row][col];
                }
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                new_image[row][col] = (double)(new_image[row][col] - min_char) / (max_char - min_char);

            }
        }
    return ImageOperator.getImageAverage(new_image,rows,cols);
    }
    public double imageToDouble(Image image){


    }


    private double colorToGray(Color color){
        return color.getRed() * 0.2126 + color.getGreen() * 0.7152
                + color.getBlue() * 0.0722;
    }
    private char getMinChar(char[] charArray){
        int minAscii = Integer.MAX_VALUE;

        // Initialize minChar to store the character with the minimum ASCII value
        char minChar = '\0'; // Default value

        // Iterate through the array
        for (char currentChar : charArray) {
            // Check if the ASCII value of the current character is smaller than minAscii
            if ((int) currentChar < minAscii) {
                minAscii = (int) currentChar;
                minChar = currentChar;
            }
        }
        return minChar;
    }
}