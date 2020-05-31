public class Line extends Shape {
    public static final String TAG_CLASSNAME = "line";
    private int length;    // line would have (length + 1) characters
    private int rInc;   // -1 (go up), 0 or 1 (go down)
    private int cInc;   // -1, 0 or 1
    // if both = 0, then have a point
    private char character;

    //define the constructor following the signature in the specification
    public Line(int rowBase, int colBase, int length, int rowIncrement,
                int colIncrement, char drawingCharacter) {
        this.length = length;
        this.rInc = rowIncrement;
        this.cInc = colIncrement;
        this.rb = rowBase;
        this.cb = colBase;
        this.character = drawingCharacter;
    }

    public void draw(Window window) throws Exception {
        int row = rb + 1;
        int col = cb + 1;
        for (int i = 0; i <= length; i++) {
            //determine appropriate row, col values
            //then make a call to setCell() method of the Window class
            window.setTheCell(row, col, character);
            row += rInc;
            col += cInc;
            //after reading the specification, draw a line in a piece of paper first
            //to visualize the different points in a line
        }
    }

    //define other methods...
    protected void decrementRowBase(Window window) {
        --this.rb;
        isOverlappingWindow(window, Util.MenuOption.UP);
    }

    void isOverlappingWindow(Window window, Util.MenuOption option) {
        if (rInc == 0 && cInc == 1) { //detect type of line
            //this is the line which only grows horizontally in East from basepoint
            switch (option) { //detect user move
                case DOWN: {
                    if ((rb + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + length + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case LEFT: {
                    if (cb < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case UP: {
                    if (rb < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if ((cb + length + 2) > window.lastColumnBorder) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }
        }

        if (rInc == 1 && cInc == 0) {
            // this is the line which only grows vertically in South direction from basepoint
            switch (option) {
                case DOWN: {
                    if ((rb + length + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case LEFT: {
                    if (cb < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case UP: {
                    if (rb < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if ((rb + length + 2) > window.lastRowBorder) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }
        }

        if (rInc == 0 && cInc == -1) {
            // this is the line which only grows horizontally in West direction from basepoint
            switch (option) {
                case DOWN: {
                    if ((rb + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case LEFT: {
                    if ((Math.abs(length - cb)) < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case UP: {
                    if (rb < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if ((Math.abs(length - cb)) < window.firstColumnBorder) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }
        }

        if (rInc == -1 && cInc == 0) {
            // this is the line which only grows vertically in North direction from basepoint
            switch (option) {
                case DOWN: {
                    if ((rb + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case LEFT: {
                    if (cb < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case UP: {
                    if ((Math.abs(length - rb)) < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if ((Math.abs(length - rb)) < window.firstRowBorder) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }
        }

        if (rInc == 1 && cInc == 1) {
            //this is the line which grows horizontally and vertically in South East direction from basepoint
            switch (option) {
                case DOWN: {
                    if ((rb + length + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + length + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case LEFT: {
                    if (cb < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case UP: {
                    if (rb < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if (((rb + length + 2) > window.lastRowBorder) || ((cb + length + 2) > window.lastColumnBorder)) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }

        }

        if (rInc == 1 && cInc == -1) {
            // this is the line which only grows horizontally and vertically in South West direction from basepoint
            switch (option) {
                case UP: {
                    if (rb < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }

                case DOWN: {
                    if ((rb + length + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }

                case LEFT: {
                    if ((Math.abs(length - cb)) < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if (((rb + length + 2) > window.lastRowBorder) || ((Math.abs(length - cb)) < window.firstColumnBorder)) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }
        }

        if (rInc == -1 && cInc == -1) {
            // this is the line which only grows horizontally and vertically in North West direction from basepoint
            switch (option) {
                case UP: {
                    if ((Math.abs(length - rb)) < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }
                case DOWN: {
                    if ((rb + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }
                case LEFT: {
                    if ((Math.abs(length - cb)) < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if (((Math.abs(length - rb)) < window.firstRowBorder) || ((Math.abs(length - cb)) < window.firstColumnBorder)) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }
        }

        if (rInc == -1 && cInc == 1) {
            // this is the line which only grows horizontally and vertically in North East direction from basepoint
            switch (option) {
                case UP: {
                    if ((Math.abs(length - rb)) < window.firstRowBorder) {
                        ++rb;
                    }
                    break;
                }
                case DOWN: {
                    if ((rb + 2) > window.lastRowBorder) {
                        --rb;
                    }
                    break;
                }

                case RIGHT: {
                    if ((cb + length + 2) > window.lastColumnBorder) {
                        --cb;
                    }
                    break;
                }

                case LEFT: {
                    if (cb < window.firstColumnBorder) {
                        ++cb;
                    }
                    break;
                }

                case INCREMENT_SIZE: {
                    if (((Math.abs(length - rb)) < window.firstRowBorder) || ((cb + length + 2) > window.lastColumnBorder)) {
                        --length;
                    }
                    break;
                }

                case DECREMENT_SIZE: {
                    if (length < 0) {
                        ++length;
                    }
                    break;
                }
            }
        }
    }

    protected void incrementRowBase(Window window) {
        ++this.rb;
        isOverlappingWindow(window, Util.MenuOption.DOWN);
    }

    protected void incrementColBase(Window window) {
        ++this.cb;
        isOverlappingWindow(window, Util.MenuOption.RIGHT);
    }

    protected void decrementColBase(Window window) {
        --this.cb;
        isOverlappingWindow(window, Util.MenuOption.LEFT);
    }

    protected void increaseSize(Window window) {
        ++this.length;
        isOverlappingWindow(window, Util.MenuOption.INCREMENT_SIZE);
    }

    protected void decreaseSize(Window window) {
        --this.length;
        isOverlappingWindow(window, Util.MenuOption.DECREMENT_SIZE);
    }

    @Override
    protected StringBuilder buildSpecsInFormat() {
        StringBuilder specs = new StringBuilder();
        specs.append("\n").append(TAG_CLASSNAME);
        specs.append("\n").append(this.rb);
        specs.append("\t").append(this.cb);
        specs.append("\t").append(this.length);
        specs.append("\t").append(this.rInc);
        specs.append("\t").append(this.cInc);
        specs.append("\n").append(character);
        specs.append("\n.");
        return specs;
    }

    @Override
    public String toString() {
        return TAG_CLASSNAME + " (" + rb + ',' + cb + ") " + '(' + length + ") " + '(' + rInc + "," + cInc + ')'
                + " (" + character + ')';
    }
}
