import ascii_art.AsciiArtAlgorithm;
import ascii_output.ConsoleAsciiOutput;
import image.Image;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Example usage within the AsciiArtAlgorithm class
        String imagePath = "board.jpeg";
        int resolution = 2;
        char[] charsets = {'m', 'o'};

        Image image = new Image(imagePath);
        AsciiArtAlgorithm asciiArtAlgorithm = new AsciiArtAlgorithm(image, resolution, charsets);
        char[][] asciiImage = asciiArtAlgorithm.run();

        // Display the generated ASCII art using ConsoleAsciiOutput
        ConsoleAsciiOutput consoleAsciiOutput = new ConsoleAsciiOutput();
        consoleAsciiOutput.out(asciiImage);

    }
}


