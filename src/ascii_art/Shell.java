package ascii_art;
import image.Image;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Shell {

    // constants
    private static final int ADD_PLUS_CHAR = 5;
    private static final int CHAR_TO_CHAR = 3;

    private Image image = new Image("cat.jpeg");
    private Set<Character> charSet = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private int resolution = 128;
    private String input;
    private String[] commands = {"exit", "chars", "add", "remove", "res", "image", "output", "asciiArt"};

    private Shell() throws IOException {
    }

    public void run() {
        KeyboardInput keyboardInput = KeyboardInput.getObject();
        System.out.print(">>> ");
        input = KeyboardInput.readLine();

        while (!Objects.equals(input, "exit")) {

            String command = extractFirstWord(input);
            switch (command) {
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
            System.out.print(">>> ");
            input = KeyboardInput.readLine();
        }
        System.exit(0);
    }

    private static String extractFirstWord(String input) {
        // Trim leading and trailing whitespace
        input = input.trim();

        // Find the index of the first space
        int index = input.indexOf(' ');

        // If no space found, return the whole string
        if (index == -1) {
            return input;
        } else {
            // Otherwise, return the substring before the first space
            return input.substring(0, index);
        }
    }

    /*
     * Sorts a Set of characters by their ASCII values and returns them as a char[] array.
     * a sub function of viewCharSet.
     */
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

    /*
     * Prints the elements of a char[] array to the console.
     * a sub function of viewCharSet.
     */
     private void printCharArray(char[] array) {
            for (char c : array) {
                System.out.print(c + " ");
            }
        }

    /*
     * Sorts the characters in the charSet and prints them to the console.
     */
    private void viewCharSet() {
        char[] sortedCharArray = sortCharSetByAscii(charSet);
        printCharArray(sortedCharArray);
    }

    private void addCharacters() {

        // the user entered just "add" or "add "
        if (input.length() < ADD_PLUS_CHAR) {
            System.out.println("Did not add due to incorrect format.");
            return;
        }

        // extract from input the thing the user wants to add
        String toAdd = input.substring(4);

        // toAdd should not contain " "
        if (toAdd.contains(" ")) {
            System.out.println("Did not add due to incorrect format.");
            return;
        }

        // the user entered "add <char>"
        if (toAdd.length() == 1) {
            char c = input.charAt(4);
            // TOD0
            // add the char
        }

        // the user entered "add <char>-<char>"
        if (IsStringInFormatCharToChar(toAdd)) {
            // TODO
        }
    }

    /*
     * Checks if the given string is in the format "<char>-<char>".
     */
    private boolean IsStringInFormatCharToChar(String toAdd) {
        // checks if the string is in the right length
        if (toAdd.length() != CHAR_TO_CHAR) {
            return false;
        }
        // checks if there's a '-' in the second char
        if (toAdd.charAt(1) != '-') {
            return false;
        }

        return true;
    }
}
