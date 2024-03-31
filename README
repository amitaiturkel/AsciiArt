
# ASCII Art Generator

## Overview
This Java program is an ASCII art generator that allows users to manipulate character sets, handle images, control resolution, and produce ASCII art from input images.

## How to Run
To run the ASCII art generator, follow these steps:

1. **Clone the Repository:** 
   Clone the repository to your local machine using Git:
   ```
   git clone https://github.com/amitaiturkel/ascii-art-generator.git
   ```

2. **Compile the Code:**
   Navigate to the project directory and compile the Java files:
   ```
   javac *.java
   ```

3. **Run the Program:**
   Execute the compiled `Main` class:
   ```
   java Main
   ```

4. **Interact with the Program:**
   Once the program is running, you can interact with it using the command-line interface. Follow the on-screen instructions to perform various tasks such as viewing character sets, adding/removing characters, selecting image files, controlling resolution, and generating ASCII art.

5. **Exiting the Program:**
   To exit the program, type `exit` and press Enter. 

## Command List
Here is a list of commands you can use within the program:

- `chars`: View available character sets.
- `add`: Add characters to the set.
- `remove`: Remove characters from the set.
- `res`: Control resolution settings.
- `image`: Select an image file to convert to ASCII art.
- `output`: Choose the output format.
- `asciiArt`: Generate ASCII art from the selected image.

## Troubleshooting
If you encounter any issues while running the program, ensure that you have Java Development Kit (JDK) installed on your system and that the `javac` and `java` commands are accessible from the command line. Additionally, make sure you have the necessary permissions to read/write files in the project directory.

### Classes and Exceptions

#### 1.1 Image Class:
   - Role: Represents an image and provides methods for reading, processing, and saving images.
   - Connections:
     - Used by the AsciiArtAlgorithm class to obtain the original image for ASCII art generation.
     - Utilized by the HtmlAsciiOutput class for creating HTML representations of images.
   - Throws: IOExceptions

#### 1.2 AsciiOutput Interface:
   - Role: Defines the contract for outputting 2D arrays of characters (ASCII art).
   - Connections:
     - Implemented by ConsoleAsciiOutput and HtmlAsciiOutput classes to specify different output methods.

#### 1.3. ConsoleAsciiOutput Class:
   - Role: Implements the AsciiOutput interface for outputting ASCII art to the console.
   - Connections:
     - Used by the Shell class in the runAsciiArtAlgorithm method to display ASCII art on the console.

#### 1.4. HtmlAsciiOutput Class:
   - Role: Implements the AsciiOutput interface for outputting ASCII art to an HTML file.
   - Connections:
     - Used by the Shell class in the runAsciiArtAlgorithm method to generate and save HTML representations of ASCII art.

#### 1.5 SubImgCharMatcher Class:
   - Role: Matches brightness values of sub-images to corresponding ASCII characters.
   - Connections:
     - Utilized by the AsciiArtAlgorithm class and Shell class, to determine the ASCII character for each sub-image.

#### 1.6 Shell Class:
   - Role: Represents the command-line shell for interacting with the ASCII art generation system.
   - Connections:
     - Collaborates with various classes, such as AsciiArtAlgorithm, SubImageCharMatcher, ConsoleAsciiOutput, and HtmlAsciiOutput, to execute commands and generate ASCII art.
     - Uses the Image class for image-related operations.
     - Utilizes the SubImgCharMatcher class to associate brightness values with ASCII characters.

#### 1.7 AsciiArtAlgorithm Class:
   - Role: Generates ASCII art from an input image using sub-images and brightness mapping.
   - Connections:
     - Utilizes the Image class to obtain the original image.
     - Collaborates with the SubImgCharMatcher class for mapping brightness values to ASCII characters.
     - Interacts with the ConsoleAsciiOutput and HtmlAsciiOutput and ImageOperator classes for displaying or saving the generated ASCII art.

#### 1.8 KeyboardInput Class:
   - Role: Represents a utility class for handling keyboard input in the command-line shell.
   - Connections:
     - Used by the Shell class to read user inputs and manage the command-line interface.

Exceptions:

1. AdditionIncorrectFormat:
   - Thrown by the Shell::processInput when the format for adding characters is incorrect.

2. EmptyCharSet:
   - Thrown by the Shell::processInput when attempting to run the ASCII art algorithm with an empty character set.

3. ImageExceptions:
   - Thrown by the Shell::processInput when there is an issue with the image file or during image processing.

4. IncorrectExecuteCommend:
   - Thrown by the Shell::processInput when an unidentified command is encountered during the execution of the shell.

5. IncorrectRemoveChar:
   - Thrown by the Shell::processInput when the format for removing characters is incorrect.

6. MethodIncorrectFormat:
   - Thrown by the Shell::processInput when the format for choosing the output method is incorrect.

7. ResolutionBoundaryException:
   - Thrown by the Shell::processInput when attempting to set a resolution outside the allowed boundaries.

8. WrongFormatResolution:
   - Thrown by the Shell::processInput when the format for changing the resolution is incorrect.

These custom exceptions provide more specific information about the nature of the error, making it easier to identify and handle issues during the execution of our code.

These classes work together to create a system for generating and displaying ASCII art from images through a command-line interface. The Shell class serves as the entry point for user interactions, coordinating the execution of commands and interactions with other classes. The AsciiArtAlgorithm class performs the actual ASCII art generation, leveraging the SubImgCharMatcher for character mapping. Output is managed by classes implementing the AsciiOutput interface, allowing flexibility in displaying or saving the ASCII art.

### Performance Considerations
1. **SubImgCharMatcher**:
   - Utilizes two hash maps for efficient brightness value management.
   - Pre-stretch and post-stretch brightness values optimize lookup time and space complexity.

2. **AsciiArtAlgorithm**:
   - Pre-calculates brightness values for each sub-image during instantiation.
   - Improves runtime efficiency while enabling dynamic character choices.

3. **Exception Handling**:
   - Enhances user experience and maintains code clarity.
```
