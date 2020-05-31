public class ShapeModificationFactory {

    public static void performLineOperation(Util.MenuOption menusOption, Line line, Window windowShape) {
        switch (menusOption) {
            case INCREMENT_SIZE:
                line.increasesSize(windowShape);
                break;
            case DECREMENT_SIZE:
                line.decreasesSize(windowShape);
                break;
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
