import java.io.IOException;
import java.util.Scanner;

public class DrawingBoard {

    private static int selectItemPosition = -1;

    public static void main(String[] args) throws Exception {
        char menuOptionKeyword;
        int response = 100; //some random init value

        Shape selectedShape = null;
        Scanner scan = new Scanner(System.in);
        String userInput = getFileInput(scan);
        Window window = getWindowShape(scan, userInput);
        if (window == null) {
            Logger.println("Oops something went wrong while reading file, closing Program");
            return;
        }
        displayWindowWithMenu(window);
        //display window either new or from file

        do {
            menuOptionKeyword = getUserInputForMenu(scan);
            response = onMenuOptionEnter(menuOptionKeyword, scan, window, selectedShape);
        } while (response != Util.QUIT_OPTION);
        Logger.println("Thank You!");
    }

    private static void displayWindowWithMenu(Window windowShape) throws Exception {
        Logger.addNewLine();
        windowShape.addTheGrid();
        windowShape.showDisplay();
        Logger.addNewLine();
        displayMenu();
    }

    private static Window getWindowShape(Scanner scan, String input)  throws Exception {
        Window window = null;
        if (checkIsFileInputIsNew(input)) {
            window = createNewWindowShape(scan);
        } else {
            
                window = Window.readSpecFile(input);
        
                // some thing went wrong while reading file
            
        }
        return window;
    }

    private static String getNewWindowParams(Scanner scanner) {
    	
    	// user needxs to enter number of rows and columns and required char
        Logger.println("Enter number of rows required, number of columns required and character (separated by space):");
        return scanner.nextLine();
    }

    private static String getFileInput(Scanner scan) {
        Logger.println("Please enter the window file name (or 'NEW'):");
        return scan.nextLine();
    }

    private static boolean checkIsFileInputIsNew(String userFile) {
        return userFile.equals("NEW");
    }

    private static Window createNewWindowShape(Scanner scan) throws Exception {
        String userWidowParamsInput = getNewWindowParams(scan);
        String[] windowParam = userWidowParamsInput.split(" ");
        int windowRow = Integer.parseInt(windowParam[0]);
        int windowCol = Integer.parseInt(windowParam[1]);
        char windowCharacter = windowParam[2].charAt(0);
        return new Window(windowRow, windowCol, windowCharacter);
    }

    private static void displayMenu() {
        Logger.println("Add Erase Select Write Quit :");
        Logger.println("Up Down Left Right + - (type a)");
    }

    private static char getUserInputForMenu(Scanner scan) {
        return scan.nextLine().charAt(0);
    }

    private static int onMenuOptionEnter(char menuOptionsKeyword, Scanner scan, Window windowShape, Shape selectdShape) throws Exception {
        Util.MenuOption option = MenuOptionFactory.getMenuOption(menuOptionsKeyword);
        if (option == null) {
            Logger.println("Incorrect option provided, please provide correct input");
            return -1; // user entered some incorrect option from menu
        }
        switch (option) {
            case ADD:
                onAddSelected(scan, windowShape);
                break;
            case ERASE:
                onEraseOptionsSelected(scan, windowShape);
                break;
            case SELECT:
                onSelectOptionsSelected(scan, windowShape, selectdShape);
                break;
            case WRITE:
                onWriteOptionsSelected(scan, windowShape);
                break;
            case QUIT:
                return Util.QUIT_OPTION; //user has asked to quit
            case UP:
                onShapesModified(scan, windowShape, Util.MenuOption.UP);
                break;
            case DOWN:
                onShapesModified(scan, windowShape, Util.MenuOption.DOWN);
                break;
            case LEFT:
                onShapesModified(scan, windowShape, Util.MenuOption.LEFT);
                break;
            case RIGHT:
                onShapesModified(scan, windowShape, Util.MenuOption.RIGHT);
                break;
            case DECREMENT_SIZE:
                onShapesModified(scan, windowShape, Util.MenuOption.DECREMENT_SIZE);
                break;
            case INCREMENT_SIZE:
                onShapesModified(scan, windowShape, Util.MenuOption.INCREMENT_SIZE);
                break;
        }
        return Util.CORRECT_OPTION; // correct menu option apart from exit
    }

    private static void onWriteOptionsSelected(Scanner scan, Window windowShape) throws Exception {
        Logger.println("File name: ");
        String fileName = scan.nextLine();
        try {
            windowShape.writeSpecFile(fileName);
        } catch (IOException e) {
            Logger.println("Something went wrong while creating the file");
        }
        displayWindowWithMenu(windowShape);
    }

    //todo : I still override the border when shapes are moved or modified, please fixme , I love my borders.
    private static void onShapesModified(Scanner scan, Window windowShape, Util.MenuOption options) throws Exception {
        if (selectItemPosition < windowShape.shapes.size() && selectItemPosition >= 0) {
            Shape shapeSelected = windowShape.shapes.get(selectItemPosition);
            if (shapeSelected instanceof Line) {
                Line line = (Line) shapeSelected;
                ShapeModificationFactory.performLineOperation(options, line, windowShape);
                windowShape.shapes.remove(selectItemPosition);
                windowShape.shapes.add(selectItemPosition, line);
                windowShape.refreshTheImage();
                displayWindowWithMenu(windowShape);
            }
        }
    }

    private static void onSelectOptionsSelected(Scanner scan, Window windowShape, Shape selectedOnShape) throws Exception {
        int windowSize = windowShape.shapes.size();
        printShapesWithSpec(windowShape, windowSize);
        try {
            selectItemPosition = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
        }
        if (!(selectItemPosition < windowSize && selectItemPosition >= 0)) {
            Logger.println("Please enter correct option");
        }
        displayWindowWithMenu(windowShape);
    }

    private static void onEraseOptionsSelected(Scanner scan, Window windowShape) throws Exception {
        int windowSize = windowShape.shapes.size();
        int eraseItemPosition = -1;
        printShapesWithSpec(windowShape, windowSize);
        try {
            eraseItemPosition = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
        }
        if (eraseItemPosition < windowSize && eraseItemPosition >= 0) {
            windowShape.shapes.remove(eraseItemPosition);
            windowShape.refreshTheImage();
        } else {
            Logger.println("Please enter correct option");
        }
        displayWindowWithMenu(windowShape);
    }

    private static void printShapesWithSpec(Window windowShape, int windowSize) {
        for (int i = 0; i < windowSize; ++i) {
            Logger.print(i + ":  ");
            Logger.println(windowShape.shapes.get(i).toString());
        }
    }

    private static void onAddSelected(Scanner scan, Window windowShape) throws Exception {
        Logger.println("line rowBase colBase length rowIncrement colIncrement character");
        String[] lineParam = scan.nextLine().split(" ");
        int baseRow = Integer.parseInt(lineParam[1]);
        int baseCol = Integer.parseInt(lineParam[2]);
        int length = Integer.parseInt(lineParam[3]);
        int rowIncrement = Integer.parseInt(lineParam[4]);
        int colIncrement = Integer.parseInt(lineParam[5]);
        char drawingChar = lineParam[6].charAt(0);
        Line line = new Line(baseRow, baseCol, length, rowIncrement, colIncrement, drawingChar);
        windowShape.addTheShape(line);
        displayWindowWithMenu(windowShape);
    }
}
