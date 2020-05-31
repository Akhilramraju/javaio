public class ShapeModificationFactory {

    public static void performLineOperation(Util.MenuOption menusOption, Line line, Window windowShape) {
        switch (menusOption) {
        
            case INCREMENT_SIZE:
            	//Used to increase the size of window
                line.increasesSize(windowShape);
                break;
            case DECREMENT_SIZE:
            	//used to decrease the size of window
                line.decreasesSize(windowShape);
                break;
                
                //menu options
            case UP:
                line.decrementsRowBase(windowShape);
                break;
            case DOWN:
                line.incrementsRowBase(windowShape);
                break;
            case RIGHT:
                line.incrementsColBase(windowShape);
                break;
            case LEFT:
                line.decrementsColBase(windowShape);
                break;
        }
    }
}
