import ascii_art.AsciiArtAlgorithm;
import ascii_art.Shell;
import ascii_output.AsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create an instance of the Shell
//        Shell shell = new Shell();

        // Run the shell
//        shell.run();

        AsciiArtAlgorithm asciiArtAlgorithm = new AsciiArtAlgorithm(new Image("cat.jpeg"), 128, new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'});

        char[][] asciiImage = asciiArtAlgorithm.run();

        AsciiOutput consoleAsciiOutput = new HtmlAsciiOutput("cat.html" ,"Courier New");

        consoleAsciiOutput.out(asciiImage);
    }
}
