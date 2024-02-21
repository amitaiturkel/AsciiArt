import ascii_art.AsciiArtAlgorithm;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        int resolution = 256;
        char[] charsets1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char startChar = ' ';
        char endChar = '~';
        String imagePath = "board.jpeg";
        int resolution = 2;
        char[] charsets = {'m', 'o'};

        int arraySize = endChar - startChar + 1;
        char[] charArray = new char[arraySize];

        for (int i = 0; i < arraySize; i++) {
            charArray[i] = (char) (startChar + i);
        }
        Image image = new Image(imagePath);
        AsciiArtAlgorithm asciiArtAlgorithm = new AsciiArtAlgorithm(image, resolution, charArray);
        char[][] asciiImage = asciiArtAlgorithm.run();

        // Display the generated ASCII art using ConsoleAsciiOutput
        HtmlAsciiOutput consoleAsciiOutput = new HtmlAsciiOutput("cat.html","Courier New");
        consoleAsciiOutput.out(asciiImage);

    }
}


