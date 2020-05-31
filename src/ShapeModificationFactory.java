public class ShapeModificationFactory {

    public static void performLineOperation(Util.MenuOption menuOption, Line line, Window window) {
        switch (menuOption) {
            case INCREMENT_SIZE:
                line.increaseSize(window);
                break;
            case DECREMENT_SIZE:
                line.decreaseSize(window);
                break;
            case UP:
                line.decrementRowBase(window);
                break;
            case DOWN:
                line.incrementRowBase(window);
                break;
            case RIGHT:
                line.incrementColBase(window);
                break;
            case LEFT:
                line.decrementColBase(window);
                break;
        }
    }
}
