import java.io.IOException;

public class T4Main {
    public static void main(String[] args) throws IOException {
        Window window = Window.readSpecFile("new.txt");
        window.showDisplay();
    }
}
