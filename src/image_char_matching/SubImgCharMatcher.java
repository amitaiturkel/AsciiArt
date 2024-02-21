package image_char_matching;

import image.Image;
import image.ImageOperator;

import java.awt.Color;
import java.util.*;

import static java.lang.Math.max;

/**
 * A class that handles matching characters based on their brightness levels.
 */
public class SubImgCharMatcher {
    private Set<Character> myCharSet = new HashSet<>();
    private Map<Character, Double> charBrightnessMapBeforeStratch = new HashMap<>();
    private double minCharValue;
    private double maxCharValue;
    private char minChar;
    private char maxChar;


    /*
    Gets a char checks if it's min and if so updates the attributes
     */
    private void updateMin(char c) {
        if (minCharValue > charBrightness(c)) {
            minCharValue = charBrightness(c);
            minChar = c;
        }
    }

    /*
    Gets a char checks if it's max and if so updates the attributes
     */
    private void updateMax(char c) {
        if (maxCharValue < charBrightness(c)) {
            maxCharValue = charBrightness(c);
            maxChar = c;
        }
    }

    /*
    Gets a char checks if it's min or max and if so updates the attributes
     */
    private void updateMinMax(char c) {
        updateMin(c);
        updateMax(c);
    }



    /**
     * Constructor for SubImgCharMatcher.
     *
     * @param charset an array of characters to initialize the character set.
     */
    public SubImgCharMatcher(char[] charset) {
        minCharValue = charBrightness(charset[0]);
        maxCharValue = 0;
        for (char c : charset) {
            myCharSet.add(c);
            charBrightnessMapBeforeStratch.put(c, charBrightness(c));
            updateMinMax(c);
        }
    }


    /**
     * Calculates the brightness of a character.
     *
     * @param c the character for which brightness is calculated.
     * @return the brightness value of the character.
     */
    private double charBrightness(char c) {
        int sum = 0;
        boolean[][] boolArray = CharConverter.convertToBoolArray(c);
        for (int row = 0; row < CharConverter.DEFAULT_PIXEL_RESOLUTION; row++) {
            for (int col = 0; col < CharConverter.DEFAULT_PIXEL_RESOLUTION; col++) {
                if (boolArray[row][col]) {
                    sum += 1;
                }
            }
        }
        return (double) sum / (Math.pow(CharConverter.DEFAULT_PIXEL_RESOLUTION, 2));
    }

    /**
     * Finds the character with the minimum brightness in the character set.
     *
     * @return the character with the minimum brightness.
     */
    private char getCharWithMinBrightness() {
        char minChar = '\0';
        double minBrightness = Double.MAX_VALUE;

        for (Character c : charBrightnessMapBeforeStratch.keySet()) {
            double currentBrightness = charBrightnessMapBeforeStratch.get(c);
            if (currentBrightness < minBrightness) {
                minBrightness = currentBrightness;
                minChar = c;
            }
        }

        return minChar;
    }

    /**
     * Finds the character with the maximum brightness in the character set.
     *
     * @return the character with the maximum brightness.
     */
    private char getCharWithMaxBrightness() {
        char maxChar = '\0';
        double maxBrightness = Double.MIN_VALUE;

        for (Character c : charBrightnessMapBeforeStratch.keySet()) {
            double currentBrightness = charBrightnessMapBeforeStratch.get(c);
            if (currentBrightness > maxBrightness) {
                maxBrightness = currentBrightness;
                maxChar = c;
            }
        }

        return maxChar;
    }

    /**
     * Converts the brightness of a character to a value between 0 and 1 using linear stretching.
     *
     * @param c the character for which brightness is converted.
     * @return the normalized brightness value of the character.
     */
    private double charToDouble(char c) {
        double minBrightness = charBrightness(getCharWithMinBrightness());
        double maxBrightness = charBrightness(getCharWithMaxBrightness());

        return (charBrightness(c) - minBrightness) / (maxBrightness - minBrightness);
    }

    /**
     * Adds a character to the character set.
     *
     * @param c the character to add.
     */
    public void addChar(char c) {
        myCharSet.add(c);
        charBrightnessMapBeforeStratch.put(c, charBrightness(c));
        updateMinMax(c);
    }

    /**
     * Removes a character from the character set.
     *
     * @param c the character to remove.
     */
    public void removeChar(char c) {
        myCharSet.remove(c);
        charBrightnessMapBeforeStratch.remove(c, charBrightness(c));
        if (c == minChar) {
            for (char c1 : myCharSet) {
                updateMin(c1);
            }
        }

        if (c == maxChar) {
            for (char c1 : myCharSet) {
                updateMax(c1);
            }
        }
    }

    /**
     * Finds the character in the character set that matches the given brightness level.
     *
     * @param brightness the brightness level to match.
     * @return the character that matches the brightness level.
     */
    public char getCharByImageBrightness(double brightness) {
        int min = (int) 'Z';
        double min_dis = 10001;
        for (Character c : charBrightnessMapBeforeStratch.keySet()) {
            double current_dis = Math.abs(brightness - charToDouble(c));
            if (current_dis <= min_dis) {
                if (current_dis == min_dis) {
                    min = Math.min(min, c);
                    min_dis= current_dis ;

                } else {
                    min = c;
                    min_dis= current_dis ;

                }

            }
        }
        return (char) min;
    }

    /**
     * Calculates the average brightness of an image.
     *
     * @param image the image for which average brightness is calculated.
     * @param rows  the number of rows in the image.
     * @param cols  the number of columns in the image.
     * @return the average brightness of the image.
     */
    public double subImageBrightness(Image image, int rows, int cols) {
        Color[][] image_as_color = ImageOperator.ImageToColorArray(image);
        double sum = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sum += ImageOperator.colorToGray(image_as_color[row][col]);
            }
        }
        return sum / ((image.getWidth() * image.getHeight()) * 255);
    }
}
