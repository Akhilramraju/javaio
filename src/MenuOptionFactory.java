public class MenuOptionFactory {

    public static Utils.MenuOption getMenuOption(char menuKeyword) {
        Utils.MenuOption option = null;
        switch (menuKeyword) {
            case 'a':
                option = Utils.MenuOption.ADD;
                break;
            case 'e':
                option = Utils.MenuOption.ERASE;
                break;
            case 's':
                option = Utils.MenuOption.SELECT;
                break;
            case 'w':
                option = Utils.MenuOption.WRITE;
                break;
            case 'q':
                option = Utils.MenuOption.QUIT;
                break;
            case 'u':
                option = Utils.MenuOption.UP;
                break;
            case 'd':
                option = Utils.MenuOption.DOWN;
                break;
            case 'l':
                option = Utils.MenuOption.LEFT;
                break;
            case 'r':
                option = Utils.MenuOption.RIGHT;
                break;
            case '+':
                option = Utils.MenuOption.INCREMENT_SIZE;
                break;
            case '-':
                option = Utils.MenuOption.DECREMENT_SIZE;
                break;
        }
        return option;
    }
}
