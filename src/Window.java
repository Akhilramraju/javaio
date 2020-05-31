import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
This is just an initial skeleton of the class to help you get started.
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.
*/

public class Window {

    protected int rows;
    protected int cols;
    protected ArrayList<Shape> shapes;
    protected char[][] cells;
    protected char borderDrawingCharacter;
    protected int firstRowForBorder = 1;
    protected int firstColumnForBorder = 1;
    protected int lastRowForBorder;
    protected int lastColumnForBorder;


    /*
        rows = number of rows available for drawing, borders excluded
        cols = number of cols available for drawing, borders excluded
        cells = 2D array of char depicting the drawing, its size should be: [rows+2][cols+2]
        shapes = ordered list of shapes (You can use Arrays, or LinkedList as well if you want)
    */
    public Window(int rows, int cols, char border) {
        //Initialize everything
        //Make a call to addBorders()
        this.rows = rows;
        this.cols = cols;
        this.borderDrawingCharacter = border;
        this.shapes = new ArrayList<Shape>();
        this.lastRowForBorder = rows + 2;
        this.lastColumnForBorder = cols + 2;
        this.cells = new char[lastRowForBorder + 1][lastColumnForBorder + 1];
        addBorders(border);
    }

    public static Window readSpecFromFile(String fileName) throws IOException {
        File file = FileHelper.getFile(fileName);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\."); // take entire token between first dot(.) and next dot(.)
        //get window
        Window window = getWindow(scanner);
        //get shapes
        while (scanner.hasNext()) {
            String shapeParams = scanner.next();
            StringTokenizer stk2 = new StringTokenizer(shapeParams);
            String shapeName = stk2.nextToken();
            int shapeRow = Integer.parseInt(stk2.nextToken());
            int shapeCol = Integer.parseInt(stk2.nextToken());
            createShapeAndAddToWindow(window, stk2, shapeName, shapeRow, shapeCol);
        }
        return window;
    }

    private static void createShapeAndAddToWindow(Window window, StringTokenizer stk2, String shapeName, int shapeRow, int shapeCol) {
        Utils.ShapeOption option = ShapeOptionFactory.getShapeOption(shapeName);
        switch (option) {
            case LINE:
                Line line = getLine(stk2, shapeRow, shapeCol);
                window.addShape(line);
                break;
            case CIRCLE:
                Circle circle = getCircle(stk2, shapeRow, shapeCol);
                window.addShape(circle);
                break;
            case RECTANGLE:
                Rectangle rectangle = getRectangle(stk2, shapeRow, shapeCol);
                window.addShape(rectangle);
                break;
            case TRIANGLE:
                Triangle triangle = getTriangle(stk2, shapeRow, shapeCol);
                window.addShape(triangle);
                break;
            case TEXT:
                Text text = getText(stk2, shapeRow, shapeCol);
                window.addShape(text);
                break;
        }
    }

    private static Text getText(StringTokenizer stk2, int shapeRow, int shapeCol) {
        String textString = stk2.nextToken("\n");//since string can be indeterminant length
        int rowInc = Integer.parseInt(stk2.nextToken("\t\n")); //normal token parsing
        int colInc = Integer.parseInt(stk2.nextToken("\t\n")); //normal token parsing
        return new Text(shapeRow, shapeCol, textString, rowInc, colInc);
    }

    private static Triangle getTriangle(StringTokenizer stk2, int shapeRow, int shapeCol) {
        int height = Integer.parseInt(stk2.nextToken());
        int rowInc = Integer.parseInt(stk2.nextToken());
        int colInc = Integer.parseInt(stk2.nextToken());
        char drawingCharacter = stk2.nextToken().charAt(0);
        return new Triangle(shapeRow, shapeCol, height, rowInc, colInc, drawingCharacter);
    }

    private static Rectangle getRectangle(StringTokenizer stk2, int shapeRow, int shapeCol) {
        int height = Integer.parseInt(stk2.nextToken());
        int width = Integer.parseInt(stk2.nextToken());
        char displayCharacter = stk2.nextToken().charAt(0);
        return new Rectangle(shapeRow, shapeCol, height, width, displayCharacter);
    }

    private static Circle getCircle(StringTokenizer stk2, int shapeRow, int shapeCol) {
        int radius = Integer.parseInt(stk2.nextToken());
        char displayCharacter = stk2.nextToken().charAt(0);
        return new Circle(shapeRow, shapeCol, radius, displayCharacter);
    }

    private static Line getLine(StringTokenizer stk2, int shapeRow, int shapeCol) {
        int length = Integer.parseInt(stk2.nextToken());
        int rowInc = Integer.parseInt(stk2.nextToken());
        int colInc = Integer.parseInt(stk2.nextToken());
        char drawingCharacter = stk2.nextToken().charAt(0);
        return new Line(shapeRow, shapeCol, length, rowInc, colInc, drawingCharacter);
    }

    private static Window getWindow(Scanner scanner) {
        String entireWindowParams = scanner.next();
        StringTokenizer windowParamTokenizer = new StringTokenizer(entireWindowParams);
        //window param first
        int windowRow = Integer.parseInt(windowParamTokenizer.nextToken());
        int windowColumn = Integer.parseInt(windowParamTokenizer.nextToken());
        char windowDisplayChar = windowParamTokenizer.nextToken().charAt(0);

        return new Window(windowRow, windowColumn, windowDisplayChar);
    }


    public void display() {
        //display the content of the array to the screen
        for (int currentRow = firstRowForBorder; currentRow <= lastRowForBorder; ++currentRow) {
            for (int currentColumn = firstColumnForBorder; currentColumn <= lastColumnForBorder; ++currentColumn) {
                Logger.printChar(cells[currentRow][currentColumn]);
                Logger.addSeparator(); // space between consecutive pixel
            }
            Logger.addNewLine(); // move to next row for printing
        }

    }

    // This method is needed by classes of type Shape for method draw()
    // It cannot be private
    // We choose it to be accessible at the package level as the safest
    // choice open to us

    public void addShape(Shape shape) {
        //add a shape to the collection
        this.shapes.add(shape);
        //call the draw() method of the shape to draw itself on this window
        for (Shape item : shapes) {
            item.draw(this);
        }
    }

    void setCell(int row, int col, char ch) {
        //set the character at cells[row][col] to 'ch'
        this.cells[row][col] = ch;
    }
    //define other methods as needed...

    protected boolean isFirstOrLastRowForBorder(int row) {
        return row == firstRowForBorder || row == lastRowForBorder;
    }

    protected boolean isFirstOrLastColumnForBorder(int column) {
        return column == firstColumnForBorder || column == lastColumnForBorder;
    }

    protected void addBorders(char ch) {
        //add the border using ch as the character
        for (int currentRow = firstRowForBorder; currentRow <= lastRowForBorder; ++currentRow) { // outer loop for row
            for (int currentColumn = firstColumnForBorder; currentColumn <= lastColumnForBorder; ++currentColumn) { //inner loop for column
                if (isFirstOrLastRowForBorder(currentRow)) {
                    setCell(currentRow, currentColumn, ch);// set elements for first and last border row
                    continue;
                }
                //set elemets for rows between first and last row
                if (isFirstOrLastColumnForBorder(currentColumn)) {
                    setCell(currentRow, currentColumn, ch); //if border pixel add char
                } else {
                    setCell(currentRow, currentColumn, ' '); // if non-border pixel add empty char
                }
            }
        }
    }

    public void writeSpecToFile(String fileName) throws IOException {
        File file = FileHelper.createFile(fileName);
        saveSpecsToFile(file);
    }

    private void saveSpecsToFile(File file) throws IOException {
        String windowSpecs = buildSpecsInFormat();
        FileHelper.appendToFile(file, windowSpecs);
        for (Shape item : shapes) {
            saveShapeSpecsToFile(file, item);
        }
    }

    private void saveShapeSpecsToFile(File file, Shape shape) throws IOException {
        String shapeSpecs = shape.getSpecs();
        FileHelper.appendToFile(file, shapeSpecs);
    }

    private String buildSpecsInFormat() {
        StringBuilder specs = new StringBuilder();
        specs.append(this.rows);
        specs.append("\t").append(this.cols);
        specs.append("\n").append(this.borderDrawingCharacter);
        specs.append("\n.");
        return specs.toString();
    }

    public void addGrid() {
        int rowGridNumber = 0;
        int columnGridNumber = 0;
        for (int currentRow = firstRowForBorder; currentRow <= lastRowForBorder; ++currentRow) { // outer loop for row
            for (int currentColumn = firstColumnForBorder; currentColumn <= lastColumnForBorder; ++currentColumn) { //inner loop for column

                //for first row or last row only
                if (isFirstOrLastRowForBorder(currentRow)) {
                    if (isFirstOrLastColumnForBorder(currentColumn)) {
                        setCell(currentRow, currentColumn, borderDrawingCharacter);
                    } else {
                        rowGridNumber = (rowGridNumber >= 9) ? 0 : ++rowGridNumber;
                        if (currentRow == lastRowForBorder && currentColumn == firstColumnForBorder)
                            rowGridNumber = 0; //for last row grid numbers
                        setCell(currentRow, currentColumn, Character.forDigit(rowGridNumber, 10));// set elements for first or last border row
                    }
                    continue;
                }

                //set elemets for rows between first and last row
                if (isFirstOrLastColumnForBorder(currentColumn)) {
                    setCell(currentRow, currentColumn, Character.forDigit(columnGridNumber, 10)); //if border pixel add char
                }
            }
            columnGridNumber = (columnGridNumber >= 9) ? 0 : ++columnGridNumber; //only increment on row change
        }
    }


    public void refreshImage() {
        clearWindow();
        for (Shape item : shapes) { //redraw shapes
            item.draw(this);
        }
    }

    private void clearWindow() {
        for (int currentRow = firstRowForBorder; currentRow <= lastRowForBorder; ++currentRow) { // outer loop for row
            for (int currentColumn = firstColumnForBorder; currentColumn <= lastColumnForBorder; ++currentColumn) { //inner loop for column
                if (isFirstOrLastRowForBorder(currentRow)) {
                    continue;
                }
                //set elemets for rows between first and last row
                if (!isFirstOrLastColumnForBorder(currentColumn)) {
                    setCell(currentRow, currentColumn, ' '); // if non-border pixel add empty char
                }
            }
        }
    }

}
