//package ascii_art;
//
//public class Shell {
//    private String userString;
//    private String[] commands = {"exit", "chars", "add", "remove", "res", "image", "output", "asciiArt"};
//
//    public void start() {
//        while (true) {
//            System.out.print("Enter a command: ");
//            userString = KeyboardInput.readLine().toLowerCase();
//
//            switch (userString) {
//                case "exit":
//                    System.out.println("Exiting the software.");
//                    System.exit(0);
//                    break;
//                case "chars":
//                    viewCharacterPool();
//                    break;
//                case "add":
//                    addCharacters();
//                    break;
//                case "remove":
//                    removeCharacters();
//                    break;
//                case "res":
//                    controlResolution();
//                    break;
//                case "image":
//                    selectImageFile();
//                    break;
//                case "output":
//                    chooseOutput();
//                    break;
//                case "asciiart":
//                    runAsciiArtAlgorithm();
//                    break;
//                default:
//                    System.out.println("Invalid command. Please try again.");
//            }
//        }
//    }
//
//    // ... (rest of the methods remain unchanged)
//
//}
