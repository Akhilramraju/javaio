import java.io.IOException;
import java.util.Scanner;

public class DrawingBoard {

    private static int selectItemPosition = -1;

    public static void main(String[] args) {
        char menuOptionKeyword;
        int response = 100; //some random init value

        Shape selectedShape = null;
        Scanner scanner = new Scanner(System.in);
        String userInput = getFileInputFromUser(scanner);
        Window window = getWindow(scanner, userInput);
        if (window == null) {
            Logger.println("Oops something went wrong while reading file, closing Program");
            return;
        }
        displayWindowWithMenuOption(window);
        //display window either new or from file

        do {
            menuOptionKeyword = getUserInputForMenuOption(scanner);
            response = onMenuOptionEntered(menuOptionKeyword, scanner, window, selectedShape);
        } while (response != Utils.QUIT_OPTION);
        Logger.println("Thank You!");
    }

    private static void displayWindowWithMenuOption(Window window) {
        Logger.addNewLine();
        window.addGrid();
        window.display();
        Logger.addNewLine();
        displayMenuOption();
    }

    private static Window getWindow(Scanner scanner, String userInput) {
        Window window = null;
        if (checkIfFileInputIsNew(userInput)) {
            window = createNewWindow(scanner);
        } else {
            try {
                window = Window.readSpecFromFile(userInput);
            } catch (IOException e) {
                // some thing went wrong while reading file
            }
        }
        return window;
    }

    private static String getNewWindowParams(Scanner scanner) {
        Logger.println("Enter number of rows, number of columns and character (separated by space):");
        return scanner.nextLine();
    }

    private static String getFileInputFromUser(Scanner scanner) {
        Logger.println("Enter the window file name (or NEW):");
        return scanner.nextLine();
    }

    private static boolean checkIfFileInputIsNew(String userFileInput) {
        return userFileInput.equals("NEW");
    }

    private static Window createNewWindow(Scanner scanner) {
        String userWidowParamInput = getNewWindowParams(scanner);
        String[] windowParams = userWidowParamInput.split(" ");
        int windowRows = Integer.parseInt(windowParams[0]);
        int windowCols = Integer.parseInt(windowParams[1]);
        char windowChar = windowParams[2].charAt(0);
        return new Window(windowRows, windowCols, windowChar);
    }

    private static void displayMenuOption() {
        Logger.println("Add Erase Select Write Quit");
        Logger.println("Up Down Left Right + -");
    }

    private static char getUserInputForMenuOption(Scanner scanner) {
        return scanner.nextLine().charAt(0);
    }

    private static int onMenuOptionEntered(char menuOptionKeyword, Scanner scanner, Window window, Shape selectedShape) {
        Utils.MenuOption option = MenuOptionFactory.getMenuOption(menuOptionKeyword);
        if (option == null) {
            Logger.println("Incorrect option provided, please provide correct input");
            return -1; // user entered some incorrect option from menu
        }
        switch (option) {
            case ADD:
                onAddOptionSelected(scanner, window);
                break;
            case ERASE:
                onEraseOptionSelected(scanner, window);
                break;
            case SELECT:
                onSelectOptionSelected(scanner, window, selectedShape);
                break;
            case WRITE:
                onWriteOptionSelected(scanner, window);
                break;
            case QUIT:
                return Utils.QUIT_OPTION; //user has asked to quit
            case UP:
                onShapeModified(scanner, window, Utils.MenuOption.UP);
                break;
            case DOWN:
                onShapeModified(scanner, window, Utils.MenuOption.DOWN);
                break;
            case LEFT:
                onShapeModified(scanner, window, Utils.MenuOption.LEFT);
                break;
            case RIGHT:
                onShapeModified(scanner, window, Utils.MenuOption.RIGHT);
                break;
            case DECREMENT_SIZE:
                onShapeModified(scanner, window, Utils.MenuOption.DECREMENT_SIZE);
                break;
            case INCREMENT_SIZE:
                onShapeModified(scanner, window, Utils.MenuOption.INCREMENT_SIZE);
                break;
        }
        return Utils.CORRECT_OPTION; // correct menu option apart from exit
    }

    private static void onWriteOptionSelected(Scanner scanner, Window window) {
        Logger.println("File name: ");
        String fileName = scanner.nextLine();
        try {
            window.writeSpecToFile(fileName);
        } catch (IOException e) {
            Logger.println("Something went wrong while creating the file");
        }
        displayWindowWithMenuOption(window);
    }

    //todo : I still override the border when shapes are moved or modified, please fixme , I love my borders.
    private static void onShapeModified(Scanner scanner, Window window, Utils.MenuOption option) {
        if (selectItemPosition < window.shapes.size() && selectItemPosition >= 0) {
            Shape shape = window.shapes.get(selectItemPosition);
            if (shape instanceof Line) {
                Line line = (Line) shape;
                ShapeModificationFactory.performLineOperation(option, line, window);
                window.shapes.remove(selectItemPosition);
                window.shapes.add(selectItemPosition, line);
                window.refreshImage();
                displayWindowWithMenuOption(window);
            }
        }
    }

    private static void onSelectOptionSelected(Scanner scanner, Window window, Shape selectedShape) {
        int windowShapeSize = window.shapes.size();
        printShapesWithSpecs(window, windowShapeSize);
        try {
            selectItemPosition = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
        }
        if (!(selectItemPosition < windowShapeSize && selectItemPosition >= 0)) {
            Logger.println("Please enter correct option");
        }
        displayWindowWithMenuOption(window);
    }

    private static void onEraseOptionSelected(Scanner scanner, Window window) {
        int windowShapeSize = window.shapes.size();
        int erasedItemPosition = -1;
        printShapesWithSpecs(window, windowShapeSize);
        try {
            erasedItemPosition = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
        }
        if (erasedItemPosition < windowShapeSize && erasedItemPosition >= 0) {
            window.shapes.remove(erasedItemPosition);
            window.refreshImage();
        } else {
            Logger.println("Please enter correct option");
        }
        displayWindowWithMenuOption(window);
    }

    private static void printShapesWithSpecs(Window window, int windowShapeSize) {
        for (int i = 0; i < windowShapeSize; ++i) {
            Logger.print(i + ":  ");
            Logger.println(window.shapes.get(i).toString());
        }
    }

    private static void onAddOptionSelected(Scanner scanner, Window window) {
        Logger.println("line rowBase colBase length rowIncrement colIncrement character");
        String[] lineParams = scanner.nextLine().split(" ");
        int baseRow = Integer.parseInt(lineParams[1]);
        int baseCol = Integer.parseInt(lineParams[2]);
        int length = Integer.parseInt(lineParams[3]);
        int rowIncrement = Integer.parseInt(lineParams[4]);
        int colIncrement = Integer.parseInt(lineParams[5]);
        char drawingChar = lineParams[6].charAt(0);
        Line line = new Line(baseRow, baseCol, length, rowIncrement, colIncrement, drawingChar);
        window.addShape(line);
        displayWindowWithMenuOption(window);
    }
}
