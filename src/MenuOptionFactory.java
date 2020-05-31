public class MenuOptionFactory {

    public static Util.MenuOption getMenusOption(char menuKey) {
        Util.MenuOption option = null;
        switch (menuKey) {
            case 'a':
                option = Util.MenuOption.ADD;
                break;
            case 'e':
                option = Util.MenuOption.ERASE;
                break;
            case 's':
                option = Util.MenuOption.SELECT;
                break;
            case 'w':
                option = Util.MenuOption.WRITE;
                break;
            case 'q':
                option = Util.MenuOption.QUIT;
                break;
            case 'u':
                option = Util.MenuOption.UP;
                break;
            case 'd':
                option = Util.MenuOption.DOWN;
                break;
            case 'l':
                option = Util.MenuOption.LEFT;
                break;
            case 'r':
                option = Util.MenuOption.RIGHT;
                break;
            case '+':
                option = Util.MenuOption.INCREMENT_SIZE;
                break;
            case '-':
                option = Util.MenuOption.DECREMENT_SIZE;
                break;
        }
        return option;
    }
}
