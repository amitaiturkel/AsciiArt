package ascii_art;

import ascii_output.ConsoleAsciiOutput;
import image.Image;
import image.ImageOperator;
import image_char_matching.SubImgCharMatcher;

import java.io.IOException;

public class AsciiArtAlgorithm {
    private Image orignalImage;
    private Image[][] subImagesArray;
    private int subImageRows;
    private int subImageCols;
    private SubImgCharMatcher charMatcher;


    public AsciiArtAlgorithm(Image image, int resolution, char[] charsets) {
        image = ImageOperator.padding(image);
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        // Calculate the size of each sub-image based on the desired resolution
        int subImageSize = imageWidth / resolution;
        // Calculate the number of rows and columns in the resulting array
        subImageRows = imageHeight / subImageSize;
        subImageCols = resolution;
        subImagesArray = ImageOperator.createSubImageArray(image, resolution);
        charMatcher = new SubImgCharMatcher(charsets);

    }

    public char[][] run() {

        char[][] asciiImage = new char[subImageRows][subImageCols];
        for (int row = 0; row < subImageRows; row++) {
            for (int col = 0; col < subImageCols; col++) {

                double subImageBrightness = ImageOperator.ImageBrightness(subImagesArray[row][col]);

                asciiImage[row][col] = charMatcher.getCharByImageBrightness(subImageBrightness);

            }
        }


        return asciiImage;
    }


}