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

    public void addChar(char c){
        charMatcher.addChar(c);
    }
    public void removeChar(char c){
        charMatcher.removeChar(c);
    }
    public void changeResolution(int res) {
        if (res != resolution) {
            subImagesArray = ImageOperator.createSubImageArray(orignalImage, res);
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