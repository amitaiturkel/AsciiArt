package ascii_art;

import ascii_output.ConsoleAsciiOutput;
import image.Image;
import image.ImageOperator;
import image_char_matching.SubImgCharMatcher;

import java.io.IOException;
import java.util.HashMap;

/**
 * The AsciiArtAlgorithm class is responsible for generating ASCII art from an input image.
 * It divides the input image into sub-images, calculates their brightness values, and maps
 * each sub-image to a corresponding ASCII character based on its brightness level.
 */
public class AsciiArtAlgorithm {
    private Image orignalImage;
    private Image[][] subImagesArray;
    private HashMap<Image, Double> brightnessMap;
    private int resolution;


    private int subImageRows;
    private int subImageCols;
    private SubImgCharMatcher charMatcher;


    /**
     * Constructs an AsciiArtAlgorithm object with the specified parameters.
     *
     * @param image       The original input image
     * @param res         The resolution parameter for dividing the image into sub-images
     * @param charMatcher The character matcher object for associating brightness levels with ASCII characters
     */
    public AsciiArtAlgorithm(Image image, int res, SubImgCharMatcher charMatcher) {
        orignalImage = ImageOperator.padding(image);
        brightnessMap = new HashMap<>();
        resolution = res;
        int imageWidth = orignalImage.getWidth();
        int imageHeight = orignalImage.getHeight();
        int subImageSize = imageWidth / resolution;
        subImageRows = imageHeight / subImageSize;
        subImageCols = resolution;
        subImagesArray = ImageOperator.createSubImageArray(orignalImage, resolution);
        this.charMatcher = charMatcher;
        updateHashMap();

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