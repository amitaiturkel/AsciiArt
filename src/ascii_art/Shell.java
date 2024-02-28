package ascii_art;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;
import image_char_matching.SubImgCharMatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.addAll;

/**
 * The Shell class represents the command-line shell for interacting with the ASCII art generation system.
 * It allows users to input commands to manipulate settings and generate ASCII art from images.
 */
public class Shell {

    // constants

        // used in case of image failure
    private static final String IMAGE_FAILURE = "Did not execute due to problem with image file.";


    // used in runAsciiArtAlgorithm
    private static final String FONT = "Courier New";
    private static final String EMPTY_CHAR_SET = "Did not execute. Charset is empty.";

        // used in run
    private static final String UNIDENTIFIED_COMMAND = "Did not execute due to incorrect command.";

        // used in chooseOutput
    private static final int OUTPUT_PLUS_ANOTHER_WORD = 8;
    private static final int BEGINNING_OF_SECOND_WORD_OF_OUTPUT = 7;
    private static final String INCORRECT_OUTPUT_INPUT =    "Did not change output method " +
                                                            "due to incorrect format.";

        // used in selectImageFile
    private static final int IMAGE_PLUS_ANOTHER_WORD = 7;
    private static final int BEGINNING_OF_SECOND_WORD_OF_IMAGE = 6;
    private static final String ERROR_PRINTING_IMAGE = "Did not execute due to problem with image file.";

        // used in controlResolution
    private static final int RES_PLUS_UP = 6;
    private static final int RES_PLUS_DOWN = 8;
    private static final int UP_DOWN_INDEX = 4;
    private static final String INCORRECT_RES_INPUT = "Did not change resolution due to incorrect format.";

        // used in checkIfAddStringValid
    private static final int ADD_PLUS_CHAR = 5;
    private static final int TO_ADD_INDEX = 4;
    private static final String INCORRECT_ADD_INPUT = "Did not add due to incorrect format.";

        // used in checkIfRemoveStringValid
    private static final int REMOVE_PLUS_CHAR = 8;
    private static final int TO_REMOVE_INDEX = 7;
    private static final String INCORRECT_REMOVE_INPUT = "Did not remove due to incorrect format.";

        // used in IsStringInFormatCharToChar
    private static final int CHAR_TO_CHAR_LENGTH = 3;

    // attributes

    private Set<Character> charSet = new HashSet<>(Arrays.asList(   '0', '1', '2', '3', '4',
                                                                    '5', '6', '7', '8', '9'));
    private int resolution = 128;
    private String input;
    private String outputMethod = "console";
    private String imageName = "cat";
    private AsciiArtAlgorithm asciiArtAlgorithm;
    private SubImgCharMatcher charMatcher;
    private Image image;

    // constructor

    /**
     * Constructor for the Shell class.
     * Initializes necessary components for generating ASCII art from an image.
     */
    public Shell() {
        try {
            image = new Image("cat.jpeg");
            char[] charArray = setToArray(charSet);
            charMatcher = new SubImgCharMatcher(charArray);
            asciiArtAlgorithm = new AsciiArtAlgorithm(image, resolution, charMatcher);
        } catch (IOException e) {
            System.out.println(IMAGE_FAILURE);
        }
    }

    // methods

    /**
     * Runs the command-line shell, allowing users to input commands and interact with the system.
     */
    public void run()  {
        KeyboardInput keyboardInput = KeyboardInput.getObject();
        System.out.print(">>> ");
        input = KeyboardInput.readLine();

        while (!Objects.equals(input, "exit")) {
            try {
                processInput();
            }
            catch (IOException e){
                System.out.println(ERROR_PRINTING_IMAGE);
                continue;

            }
            System.out.print(">>> ");
            input = KeyboardInput.readLine();
        }

        System.exit(0);
    }

    private void processInput() throws IOException {
        String command = extractFirstWordOfInput();
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
            case "asciiArt":
                runAsciiArtAlgorithm();
                break;
            default:
                System.out.println(UNIDENTIFIED_COMMAND);
        }
    }


    // the commends methods

    /*
     * Sorts the characters in the charSet and prints them to the console.
     */
    private void viewCharSet() {
        char[] sortedCharArray = sortCharSetByAscii(charSet);
        printCharArray(sortedCharArray);
    }

    private void addCharacters() {

        // Checks if the input is in a format of "add" + space + something
        if (!checkIfStringValid(input, "add")) {
            return;
        }

        // extract from input the thing the user wants to add
        String toAdd = input.substring(TO_ADD_INDEX);

        // the user entered "add <char>"
        if (toAdd.length() == 1) {
            addSingleChar();
            return;
        }

        // the user entered "add <char>-<char>"
        if (IsStringInFormatCharToChar(toAdd)) {
            addOrRemoveFromCharToChar(toAdd, "add");
            return;
        }

        // the user entered "add all"
        if (toAdd.equals("all")) {
            addAll();
            return;
        }

        // the user entered "add space"
        if (toAdd.equals("space")) {
            addSpace();
            return;
        }

        // the user hasn't entered any of the valid options
        System.out.println(INCORRECT_ADD_INPUT);
    }

    private void removeCharacters() {

        // Checks if the input is in a format of "remove" + space + something
        if (!checkIfStringValid(input, "remove")) {
            return;
        }

        // extract from input the thing the user wants to remove
        String toRemove = input.substring(TO_REMOVE_INDEX);

        // the user entered "remove <char>"
        if (toRemove.length() == 1) {
            removeSingleChar();
            return;
        }

        // the user entered "remove <char>-<char>"
        if (IsStringInFormatCharToChar(toRemove)) {
            addOrRemoveFromCharToChar(toRemove, "remove");
            return;
        }

        // the user entered "remove all"
        if (toRemove.equals("all")) {
            removeAll();
            return;
        }

        // the user entered "remove space"
        if (toRemove.equals("space")) {
            removeSpace();
            return;
        }

        // the user hasn't entered any of the valid options
        System.out.println(INCORRECT_REMOVE_INPUT);
    }

    private void controlResolution() {

        // check if the input is at the size "res up"
        if (input.length() == RES_PLUS_UP) {

            // check if the input is "res up"
            if (input.substring(UP_DOWN_INDEX).equals("up")) {
                resolution *= 2;
                asciiArtAlgorithm = new AsciiArtAlgorithm(image,resolution,charMatcher);
                System.out.println("Resolution set to " + resolution + ".");
                return;
            }
            System.out.println(INCORRECT_RES_INPUT);
            return;
        }

        // check if the input is "res down"
        if (input.length() == RES_PLUS_DOWN) {

            // check if the input is "res down"
            if (input.substring(UP_DOWN_INDEX).equals("down")) {
                resolution /= 2;
                asciiArtAlgorithm = new AsciiArtAlgorithm(image,resolution,charMatcher);
                System.out.println("Resolution set to " + resolution + ".");
                return;
            }
            System.out.println(INCORRECT_RES_INPUT);
        }

        // 2.6.5 עם משהו לעשות
    }

    private void selectImageFile() throws IOException {
        if (input.length() < IMAGE_PLUS_ANOTHER_WORD) {
            System.out.println(ERROR_PRINTING_IMAGE);
        }

        String imageString = input.substring(BEGINNING_OF_SECOND_WORD_OF_IMAGE);
        try {
            image = new Image(imageString);
        }
        catch (IOException e){
            System.out.print(ERROR_PRINTING_IMAGE);
        }

    }

    private void chooseOutput() {
        // check if input is too short
        if (input.length() < OUTPUT_PLUS_ANOTHER_WORD) {
            System.out.println(INCORRECT_OUTPUT_INPUT);
        }

        String output = input.substring(BEGINNING_OF_SECOND_WORD_OF_OUTPUT);

        if (output.equals("console") || output.equals("html")) {
            outputMethod = output;
        } else {
            System.out.println(INCORRECT_OUTPUT_INPUT);
        }
    }

    private void runAsciiArtAlgorithm() {

        // if the charSet is empty, an error message will be printed
        if (charSet.isEmpty()) {
            System.out.println(EMPTY_CHAR_SET);
            return;
        }

        char[][] asciiImage = asciiArtAlgorithm.run();

        AsciiOutput consoleAsciiOutput;

        switch (outputMethod) {
            case ("console"):
                consoleAsciiOutput = new ConsoleAsciiOutput();
                break;

            case ("html"):
                consoleAsciiOutput = new HtmlAsciiOutput(imageName + ".html",FONT);
                break;

            default:
                consoleAsciiOutput = new ConsoleAsciiOutput();
        }

        consoleAsciiOutput.out(asciiImage);
    }


    // Auxiliary functions

    /*
     * Removes a space character from the set and invokes the
     * ASCII art algorithm to process the removal of the character.
     * a sub function of removeCharacters.
     */
    private void removeSpace() {
        charSet.remove(' ');
        charMatcher.removeChar(' ');
    }

    /*
     * Removes all characters present in the character set from the ASCII art algorithm.
     * This method iterates through each character in the character set,
     * removes it from the ASCII art algorithm, and then clears the character set.
     */
    private void removeAll() {
        for(char c :charSet){
            charMatcher.removeChar(c);
        }
        charSet.clear();

    }

    /*
     * removes a single character from the input string from the character set
     * and invokes the ASCII art algorithm to process the removal of the character.
     */
    private void removeSingleChar() {
        char c = input.charAt(TO_REMOVE_INDEX);
        charSet.remove(c);
        charMatcher.removeChar(c);
    }

    /*
     * Adds a space character to the set and invokes the
     * ASCII art algorithm to process the character.
     * a sub function of addCharacters.
     */
    private void addSpace() {
        charSet.add(' ');
        charMatcher.addChar(' ');
    }

    /*
     * Adds a single character from the input string to the character
     * set and invokes the ASCII art algorithm to process the character.
     * a sub function of addCharacters.
     */
    private void addSingleChar() {
        char c = input.charAt(TO_ADD_INDEX);
        charSet.add(c);
        charMatcher.addChar(c);
    }

    /*
     * Adds all possible characters from ASCII 32
     * (space) to ASCII 126 (tilde) into the charSet.
     * a sub function of addCharacters.
     */
    private void addAll() {
        for (int i = 32; i <= 126; i++) {
            charSet.add((char) i);
            charMatcher.addChar((char) i);
        }
    }

    /*
     * Converts a Set of Character elements into a char array.
     */
    private char[] setToArray(Set<Character> set) {

        // Create a char array with the size of the set
        char[] charArray = new char[charSet.size()];

        // Convert charSet to charArray
        int i = 0;
        for (char c : charSet) {
            charArray[i++] = c;
        }
        return charArray;
    }

    /*
     * Extracts the first word from the input string.
     */
     private String extractFirstWordOfInput() {

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
    private char[] sortCharSetByAscii(Set<Character> charSet) {
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
     * Checks if the input string is valid for adding or removing an item.
     * a sub function of addCharacters and of removeCharacters.
     *
     * if it is not valid the function will print a message and return false.
     * otherwise it will return true.
     */
    private boolean checkIfStringValid(String input, String addOrRemove) {

        int minLength = 0;
        String invalidMessage = "";
        int AddOrRemoveIndex = 0;

        switch (addOrRemove) {
            case ("add"):
                minLength = ADD_PLUS_CHAR;
                invalidMessage = INCORRECT_ADD_INPUT;
                AddOrRemoveIndex = TO_ADD_INDEX;
                break;

            case ("remove"):
                minLength = REMOVE_PLUS_CHAR;
                invalidMessage = INCORRECT_REMOVE_INPUT;
                AddOrRemoveIndex = TO_REMOVE_INDEX;
                break;
        }

        // the user entered just "add" or "add " / "remove" or "remove "
        if (input.length() < minLength) {
            System.out.println(invalidMessage);
            return false;
        }

        // extract from input the thing the user wants to add / remove
        String toAddOrRemove = input.substring(AddOrRemoveIndex);

        // toAddOrRemove should not contain " "
        if (toAddOrRemove.contains(" ")) {
            System.out.println(invalidMessage);
            return false;
        }
        return true;
    }

    private void addOrRemoveFromCharToChar(String toAddOrToRemove, String addOrRemove) {

        char first = toAddOrToRemove.charAt(0);
        char second = toAddOrToRemove.charAt(2);

        // Ensure first holds the smaller character and second holds the larger character
        if (first > second) {
            char temp = first;
            first = second;
            second = temp;
        }

        while (first < second) {
            if (addOrRemove.equals("add")) {
                charSet.add(first);
                charMatcher.addChar(first);
            } else if (addOrRemove.equals("remove")) {
                charSet.remove(first);
                charMatcher.removeChar(first);
            }
            first++;
        }
    }

    /*
     * Checks if the given string is in the format "<char>-<char>".
     */
    private boolean IsStringInFormatCharToChar(String toAddOrRemove) {
        // checks if the string is in the right length
        if (toAddOrRemove.length() != CHAR_TO_CHAR_LENGTH) {
            return false;
        }
        // checks if there's a '-' in the second char
        if (toAddOrRemove.charAt(1) != '-') {
            return false;
        }

        return true;
    }

    /**
     * The main entry point of the program.
     * Initializes and runs the ASCII art generation shell.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Run the algorithm to generate ASCII art
        Shell shell = new Shell();

        // Run the shell
        shell.run();
    }

}
