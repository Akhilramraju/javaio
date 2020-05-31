import java.io.*;
import java.util.Scanner;

public class FileHelper {

    public static File createsFile(String filesName) throws IOException {
        File file = new File(filesName);
        try {
            if (file.exists()) {
                //Deleting the file as the file already exists
                file.delete();
            } else {
            	       
            	file.createNewFile();
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void appendsToFile(File filename, String contents) throws IOException {
        PrintWriter printsWriter = new PrintWriter(new FileWriter(filename.getName(), true));
        printsWriter.print(contents);
        printsWriter.flush();
        printsWriter.close();
    }

    
    public static void readsFromFile(File filename, String delimitersPattern) throws IOException {
    //Scanner reads the file from the file name
    	Scanner scanner = new Scanner(filename);
        scanner.useDelimiter(delimitersPattern);
        while (scanner.hasNext()) {
            Logger.println(scanner.next());
        }
    }

    public static File getsFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (file.exists()) return file;
        else throw new FileNotFoundException();
    }
}
