package image_char_matching;

import image.Image;
import image.ImageOperator;

import java.awt.Color;
import java.util.*;


/**
 * A class that handles matching characters based on their brightness levels.
 */
public class SubImgCharMatcher {
    private Map<Character, Double> charBrightnessMapBeforeStratch = new HashMap<>();
    private Map<Character, Double> FinalcharBrightness = new HashMap<>();
    private double minCharValue;
    private double maxCharValue;
    private char minChar;
    private char maxChar;

    private void createFinalMap() {
        for (Character c : charBrightnessMapBeforeStratch.keySet()) {
            FinalcharBrightness.put(c, charToDouble(c));

        }
    }

    /*
    Gets a char checks if it's min and if so updates the attributes
     */
    private boolean updateMin(char c) {
        if (minCharValue >= charBrightness(c)) {
            if(minCharValue == charBrightness(c)){
                maxChar = (char)Math.min((int)minChar, (int)c);
            }
            minCharValue = charBrightness(c);
            minChar = c;
            return true;
        }
        return false;
    }

    /*
    Gets a char checks if it's max and if so updates the attributes
     */
    private boolean updateMax(char c) {
        if (maxCharValue <= charBrightness(c)) {
            if(maxCharValue == charBrightness(c)){
                maxChar = (char)Math.max((int)maxChar, (int)c);
            }
            maxCharValue = charBrightness(c);
            maxChar = c;
            return true;
        }
        return false;
    }

    /*
    Gets a char checks if it's min or max and if so updates the attributes
     */
    private boolean updateMinMax(char c) {
        boolean changeMin, changeMax;
        changeMin = updateMin(c);
        changeMax = updateMax(c);
        return changeMax || changeMin;
    }


    /**
     * Constructor for SubImgCharMatcher.
     *
     * @param charset an array of characters to initialize the character set.
     */
    public SubImgCharMatcher(char[] charset) {
        minCharValue = charBrightness(charset[0]);
        maxCharValue = 0;
        boolean changed;
        for (char c : charset) {
            charBrightnessMapBeforeStratch.put(c, charBrightness(c));
            changed = updateMinMax(c);
        }
        createFinalMap();
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
        return minChar;
    }

    /**
     * Finds the character with the maximum brightness in the character set.
     *
     * @return the character with the maximum brightness.
     */
    private char getCharWithMaxBrightness() {
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
        charBrightnessMapBeforeStratch.put(c, charBrightness(c));
        boolean changed = updateMinMax(c);
        if (changed) {
            createFinalMap();
        }
        FinalcharBrightness.put(c, charToDouble(c));
    }

    /**
     * Removes a character from the character set.
     *
     * @param c the character to remove.
     */
    public void removeChar(char c) {
        FinalcharBrightness.remove(c);
        charBrightnessMapBeforeStratch.remove(c, charBrightness(c));
        if (c == minChar) {
            minCharValue = 1; // so minChar will work
            for (char c1 : charBrightnessMapBeforeStratch.keySet()) {
                updateMin(c1);
            }

        }

        if (c == maxChar) {
            maxCharValue = -1; // so updateMax will work
            for (char c1 : charBrightnessMapBeforeStratch.keySet()) {
                updateMax(c1);

            }
        }
        createFinalMap();
    }

    /**
     * Finds the character in the character set that matches the given brightness level.
     *
     * @param brightness the brightness level to match.
     * @return the character that matches the brightness level.
     */
    public char getCharByImageBrightness(double brightness) {
        char closestChar = 'Z';
        double minDis = Double.POSITIVE_INFINITY;
        for (Map.Entry<Character, Double> entry : FinalcharBrightness.entrySet()) {
            double currentDis = Math.abs(brightness - entry.getValue());
            if (currentDis <= minDis) {
                if (currentDis == minDis) {
                    closestChar = (char) Math.min((int) closestChar, (int) entry.getKey());
                } else {
                    closestChar = entry.getKey();
                    minDis = currentDis;

                }
            }


        }
        return closestChar;

    }
}
