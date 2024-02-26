package ascii_art;

import ascii_output.ConsoleAsciiOutput;
import image.Image;
import image.ImageOperator;
import image_char_matching.SubImgCharMatcher;

import java.io.IOException;
import java.util.HashMap;

public class AsciiArtAlgorithm {
    private Image orignalImage;
    private Image[][] subImagesArray;
    private HashMap<Image, Double> brightnessMap;
    private int resolution;


    private int subImageRows;
    private int subImageCols;
    private SubImgCharMatcher charMatcher;


    public AsciiArtAlgorithm(Image image, int res, char[] charsets) {
        orignalImage = ImageOperator.padding(image);
        brightnessMap = new HashMap<>();
        resolution = res;
        int imageWidth = orignalImage.getWidth();
        int imageHeight = orignalImage.getHeight();
        // Calculate the size of each sub-image based on the desired resolution
        int subImageSize = imageWidth / resolution;
        // Calculate the number of rows and columns in the resulting array
        subImageRows = imageHeight / subImageSize;
        subImageCols = resolution;
        subImagesArray = ImageOperator.createSubImageArray(orignalImage, resolution);
        charMatcher = new SubImgCharMatcher(charsets);
        updateHashMap();

    }

    /**
     * Adds the specified character to the character matcher.
     *
     * @param c the character to be added
     */
    public void addChar(char c){
        charMatcher.addChar(c);
    }

    /**
     * Removes the specified character from the character matcher.
     *
     * @param c the character to be removed
     */
    public void removeChar(char c){
        charMatcher.removeChar(c);
    }

    /**
     * Changes the resolution of the original image.
     * If the specified resolution is different from the current
     * resolution, it recalculates the sub-images based on the
     * new resolution and updates the internal state.
     *
     * @param res the new resolution to set
     */
    public void changeResolution(int res) {
        if (res != resolution) {
            subImagesArray = ImageOperator.createSubImageArray(orignalImage, res);
            resolution = res;
            int imageWidth = orignalImage.getWidth();
            int imageHeight = orignalImage.getHeight();

            // Calculate the size of each sub-image based on the desired resolution
            int subImageSize = imageWidth / resolution;

            // Calculate the number of rows and columns in the resulting array
            subImageRows = imageHeight / subImageSize;
            subImageCols = resolution;
            updateHashMap();
        }
    }

    /*
     * Updates the HashMap with brightness values for sub-images if they are not already present.
     * The brightness values are computed using the ImageOperator class.
     * This method iterates through the sub-images array and checks if the brightness
     * value for each sub-image is already present in the brightnessMap. If not, it computes
     * the brightness value using the ImageBrightness method from the ImageOperator class
     * and adds it to the brightnessMap with the corresponding sub-image as the key.
     */
    private void updateHashMap() {
        for (int row = 0; row < subImageRows; row++) {
            for (int col = 0; col < subImageCols; col++) {
                if (!brightnessMap.containsKey(subImagesArray[row][col])) {
                    brightnessMap.put(subImagesArray[row][col],
                            ImageOperator.ImageBrightness(subImagesArray[row][col]));
                }
            }
        }
    }

    /**
     * Runs the ASCII image generation process based
     * on the brightness map and the sub-images array.
     *
     * @return a 2D char array representing the generated ASCII image.
     */
    public char[][] run() {
        char[][] asciiImage = new char[subImageRows][subImageCols];
        for (int row = 0; row < subImageRows; row++) {
            for (int col = 0; col < subImageCols; col++) {
                double subImageBrightness = brightnessMap.get(subImagesArray[row][col]);
                asciiImage[row][col] = charMatcher.getCharByImageBrightness(subImageBrightness);
            }
        }
        return asciiImage;
    }
}