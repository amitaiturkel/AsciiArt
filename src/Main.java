import ascii_art.AsciiArtAlgorithm;
import ascii_art.Shell;
import ascii_output.AsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
         String FONT = "Courier New";
        Image image = new Image("cat.jpeg");        // Create an instance of the Shell
        char[] numericCharsets = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        // Create an instance of AsciiArtAlgorithm
        AsciiArtAlgorithm asciiArtAlgorithm = new AsciiArtAlgorithm(image, 256, numericCharsets);
        char[][] asciiImage = asciiArtAlgorithm.run();
        AsciiOutput consoleAsciiOutput = new HtmlAsciiOutput( "cat.html",FONT);
        consoleAsciiOutput.out(asciiImage);

        // Run the algorithm to generate ASCII art
        Shell shell = new Shell();

        // Run the shell
        shell.run();
    }
}
