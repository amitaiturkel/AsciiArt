package ascii_art;
import image.Image;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Shell {

    private Image image = new Image("cat.jpeg");
    private Set<Character> charSet = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private int resolution = 128;
    private String input;
    private String[] commands = {"exit", "chars", "add", "remove", "res", "image", "output", "asciiArt"};

    private Shell() throws IOException {
    }

    public void run() {

        KeyboardInput keyboardInput = KeyboardInput.getObject();

        while (true) {
            System.out.print(">>> ");
            input = KeyboardInput.readLine().toLowerCase();

            switch (input) {
                case "exit":
                    System.out.println("Exiting the software.");
                    System.exit(0);
                    break;
                case "chars":
                    viewCharSet();
                    break;
                case "add":
                    addCharacters();
                    break;
                case "remove":
                    removeCharacters();
                    break;
                case "res":
                    controlResolution();
                    break;
                case "image":
                    selectImageFile();
                    break;
                case "output":
                    chooseOutput();
                    break;
                case "asciiart":
                    runAsciiArtAlgorithm();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static char[] sortCharSetByAscii(Set<Character> charSet) {
        // Convert the Set<Character> to a char[] array
        char[] charArray = new char[charSet.size()];
        int index = 0;
        for (char c : charSet) {
            charArray[index++] = c;
        }

        // Sort the char[] array based on ASCII values
        Arrays.sort(charArray);

        return charArray;
    }

    private void printCharArray(char[] array) {
        for (char c : array) {
            System.out.print(c + " ");
        }
    }

    private void viewCharSet() {
        char[] sortedCharArray = sortCharSetByAscii(charSet);
        printCharArray(sortedCharArray);
    }




}
