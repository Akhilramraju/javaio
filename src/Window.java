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
    protected char borderDrawinCharacter;
    protected int firstRowBorder = 1;
    protected int firstColumnBorder = 1;
    protected int lastRowBorder;
    protected int lastColumnBorder;


    /*
        rows = number of rows available for drawing, borders excluded
        cols = number of cols available for drawing, borders excluded
        cells = 2D array of char depicting the drawing, its size should be: [rows+2][cols+2]
        shapes = ordered list of shapes (You can use Arrays, or LinkedList as well if you want)
    */
    public Window(int rows, int cols, char border) throws Exception {
        //Initialize everything
        //Make a call to addBorders()
        this.rows = rows;
        this.cols = cols;
        this.borderDrawinCharacter = border;
        this.shapes = new ArrayList<Shape>();
        this.lastRowBorder = rows + 2;
        this.lastColumnBorder = cols + 2;
        this.cells = new char[lastRowBorder + 1][lastColumnBorder + 1];
        addBorder(border);
    }

    public static Window readSpecFile(String fileNameSelected) throws Exception {
        File fileGiven = FileHelper.getFile(fileNameSelected);
        Scanner scan = new Scanner(fileGiven);
        scan.useDelimiter("\\."); // take entire token between    first dot(.)    and next dot(.)

        Window windowShape = getTheWindow(scan);
   
        while (scan.hasNext()) {
            String shapeParam = scan.next();
            StringTokenizer stk = new StringTokenizer(shapeParam);
            String shapeName = stk.nextToken();
            int shapeRow = Integer.parseInt(stk.nextToken());
            int shapeCol = Integer.parseInt(stk.nextToken());
            createShapeAndAddWindow(windowShape, stk, shapeName, shapeRow, shapeCol);
        }
        return windowShape;
    }

    private static void createShapeAndAddWindow(Window windowShape, StringTokenizer stk, String shapeSelected, int shapeOfRow, int shapeofCol) throws Exception {
        Util.ShapeOption optionSelected = ShapeOptionFactory.getShapeOption(shapeSelected);
        switch (optionSelected) {
            case LINE:
                Line line = getTheLine(stk, shapeOfRow, shapeofCol);
                windowShape.addTheShape(line);
                break;
            case CIRCLE:
                Circle circle = getTheCircle(stk, shapeOfRow, shapeofCol);
                windowShape.addTheShape(circle);
                break;
            case RECTANGLE:
                Rectangle rectangle = getTheRectangle(stk, shapeOfRow, shapeofCol);
                windowShape.addTheShape(rectangle);
                break;
            case TRIANGLE:
                Triangle triangle = getTheTriangle(stk, shapeOfRow, shapeofCol);
                windowShape.addTheShape(triangle);
                break;
            case TEXT:
                Text text = getTheText(stk, shapeOfRow, shapeofCol);
                windowShape.addTheShape(text);
                break;
        }
    }

    private static Text getTheText(StringTokenizer stk, int shapeOfRow, int shapeOfCol) {
        String textStrng = stk.nextToken("\n");//since string can be indeterminant length
        int rowIncrement = Integer.parseInt(stk.nextToken("\t\n")); //normal token parsing
        int colIncrement = Integer.parseInt(stk.nextToken("\t\n")); //normal token parsing
        return new Text(shapeOfRow, shapeOfCol, textStrng, rowIncrement, colIncrement);
    }

    private static Triangle getTheTriangle(StringTokenizer stk2, int shapeOfRow, int shapeOfCol) {
        int heightInput = Integer.parseInt(stk2.nextToken());
        int rowIncrement = Integer.parseInt(stk2.nextToken());
        int colIncrement = Integer.parseInt(stk2.nextToken());
        char drawingCharacterInput = stk2.nextToken().charAt(0);
        return new Triangle(shapeOfRow, shapeOfCol, heightInput, rowIncrement, colIncrement, drawingCharacterInput);
    }

    private static Rectangle getTheRectangle(StringTokenizer stk, int shapeOfRow, int shapeOfCol) {
        int heightInput = Integer.parseInt(stk.nextToken());
        int widthInput = Integer.parseInt(stk.nextToken());
        char displayCharacterInput = stk.nextToken().charAt(0);
        return new Rectangle(shapeOfRow, shapeOfCol, heightInput, widthInput, displayCharacterInput);
    }

    private static Circle getTheCircle(StringTokenizer stk, int shapeOfRow, int shapeOfCol) {
        int radiusInput = Integer.parseInt(stk.nextToken());
        char displayCharacterInput = stk.nextToken().charAt(0);
        return new Circle(shapeOfRow, shapeOfCol, radiusInput, displayCharacterInput);
    }

    private static Line getTheLine(StringTokenizer stk, int shapeofRow, int shapeOfCol) {
        int lengthInput = Integer.parseInt(stk.nextToken());
        int rowIncrement = Integer.parseInt(stk.nextToken());
        int colIncrement = Integer.parseInt(stk.nextToken());
        char drawingCharacterInput = stk.nextToken().charAt(0);
        return new Line(shapeofRow, shapeOfCol, lengthInput, rowIncrement, colIncrement, drawingCharacterInput);
    }

    private static Window getTheWindow(Scanner scan) throws Exception {
        String entireWindowParam = scan.next();
        StringTokenizer windowParamsTokenizer = new StringTokenizer(entireWindowParam);
        //window param first
        int windowRowInput = Integer.parseInt(windowParamsTokenizer.nextToken());
        int windowColumnInput = Integer.parseInt(windowParamsTokenizer.nextToken());
        char windowDsplayChar = windowParamsTokenizer.nextToken().charAt(0);

        return new Window(windowRowInput, windowColumnInput, windowDsplayChar);
    }


    public void showDisplay() {
        //display the content of the array to the screen
        for (int currentRowInput = firstRowBorder; currentRowInput <= lastRowBorder; ++currentRowInput) {
            for (int currentColumnInput = firstColumnBorder; currentColumnInput <= lastColumnBorder; ++currentColumnInput) {
                Logger.printChar(cells[currentRowInput][currentColumnInput]);
                Logger.addSeparator(); // space between consecutive pixel
            }
            Logger.addNewLine(); // move to next row for printing
        }

    }

    // This method is needed by the classes of type Shape for method draw()
    // It cannot be private method
    // We choose it to be accessible at package level as the safest
   

    public void addTheShape(Shape shapeInput) throws Exception {
        //add shape to the collection
        this.shapes.add(shapeInput);
        //call  draw() method of the shape to draw itself on the window
        for (Shape item : shapes) {
            item.draw(this);
        }
    }

    void setTheCell(int rowInput, int colInput, char character) throws Exception {
        //setting the character at cells [row][col] to 'ch'
        this.cells[rowInput][colInput] = character;
    }
    //define other methods as needed...

    protected boolean isFirstOrLastRowBorder(int rowInput) {
        return rowInput == firstRowBorder || rowInput == lastRowBorder;
    }

    protected boolean isFirstOrLastColumnBorder(int columnInput) {
        return columnInput == firstColumnBorder || columnInput == lastColumnBorder;
    }

    protected void addBorder(char character) throws Exception {

        for (int currentRow = firstRowBorder; currentRow <= lastRowBorder; ++currentRow) { // outer loop for row
            for (int currentColumn = firstColumnBorder; currentColumn <= lastColumnBorder; ++currentColumn) { //inner loop for column
                if (isFirstOrLastRowBorder(currentRow)) {
                    setTheCell(currentRow, currentColumn, character);// set elements for first and last border row
                    continue;
                }
                //set elements for rows between first and last row
                if (isFirstOrLastColumnBorder(currentColumn)) {
                    setTheCell(currentRow, currentColumn, character); //if the border pixel add char
                } else {
                    setTheCell(currentRow, currentColumn, ' '); // if the non-border pixel add empty char
                }
            }
        }
    }

    public void writeSpecFile(String fileNam) throws IOException {
        File fileInput = FileHelper.createFile(fileNam);
        saveSpecToFile(fileInput);
    }

    private void saveSpecToFile(File fileInput) throws IOException {
        String windowSpec = buildSpecsFormat();
        FileHelper.appendToFile(fileInput, windowSpec);
        for (Shape item : shapes) {
            saveShapeSpecToFile(fileInput, item);
        }
    }

    private void saveShapeSpecToFile(File fileInput, Shape shapeInput) throws IOException {
        String shapeSpecs = shapeInput.getSpecs();
        FileHelper.appendToFile(fileInput, shapeSpecs);
    }

    private String buildSpecsFormat() {
        StringBuilder specif = new StringBuilder();
        specif.append(this.rows);
        specif.append("\t").append(this.cols);
        specif.append("\n").append(this.borderDrawinCharacter);
        specif.append("\n.");
        return specif.toString();
    }

    public void addTheGrid() throws Exception {
        int rowGridNum = 0;
        int columnGridNum = 0;
        for (int currRow = firstRowBorder; currRow <= lastRowBorder; ++currRow) { // outer loop for row
            for (int currColumn = firstColumnBorder; currColumn <= lastColumnBorder; ++currColumn) { //inner loop for column

                //for first row or last row only
                if (isFirstOrLastRowBorder(currRow)) {
                    if (isFirstOrLastColumnBorder(currColumn)) {
                        setTheCell(currRow, currColumn, borderDrawinCharacter);
                    } else {
                        rowGridNum = (rowGridNum >= 9) ? 0 : ++rowGridNum;
                        if (currRow == lastRowBorder && currColumn == firstColumnBorder)
                            rowGridNum = 0; //for last row grid numbers
                        setTheCell(currRow, currColumn, Character.forDigit(rowGridNum, 10));// set elements for first or last border row
                    }
                    continue;
                }

                //set elemets for rows between first and last row
                if (isFirstOrLastColumnBorder(currColumn)) {
                    setTheCell(currRow, currColumn, Character.forDigit(columnGridNum, 10)); //if border pixel add char
                }
            }
            columnGridNum = (columnGridNum >= 9) ? 0 : ++columnGridNum; //only increment on row change
        }
    }


    public void refreshTheImage() throws Exception {
        clearTheWindow();
        for (Shape item : shapes) { //redraw shapes
            item.draw(this);
        }
    }

    private void clearTheWindow() throws Exception {
        for (int currRow = firstRowBorder; currRow <= lastRowBorder; ++currRow) { // outer loop for row
            for (int currColumn = firstColumnBorder; currColumn <= lastColumnBorder; ++currColumn) { //inner loop for column
                if (isFirstOrLastRowBorder(currRow)) {
                    continue;
                }
                //set elemets for rows between first and last row
                if (!isFirstOrLastColumnBorder(currColumn)) {
                    setTheCell(currRow, currColumn, ' '); // if non-border pixel add empty char
                }
            }
        }
    }

}
