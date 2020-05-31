import java.io.IOException;

public class T4Main {
    public static void main(String[] args) throws IOException {
        Window window = Window.readSpecFromFile("new.txt");
        window.display();
    }
}
