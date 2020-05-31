import java.io.*;
import java.util.Scanner;

public class FileHelper {

    public static File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        try {
            if (file.exists()) {
                //file already exist
                //delete the existing file
                file.delete();
            } else {
                // create new file
                file.createNewFile();
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void appendToFile(File file, String content) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file.getName(), true));
        printWriter.print(content);
        printWriter.flush();
        printWriter.close();
    }

    public static void readFromFile(File file, String delimiterPattern) throws IOException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(delimiterPattern);
        while (scanner.hasNext()) {
            Logger.println(scanner.next());
        }
    }

    public static File getFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (file.exists()) return file;
        else throw new FileNotFoundException();
    }
}
