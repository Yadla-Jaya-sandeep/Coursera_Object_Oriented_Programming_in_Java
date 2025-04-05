import java.io.*;
import java.util.*;

public class FileResource implements Iterable<String> {
    private String myPath;
    private ArrayList<String> myLines;

    public FileResource(String filename) {
        initLinesFromFile(filename);
    }

    public FileResource() {
        // default to pick a file from user input
        myPath = getFilePathFromUser();
        initLinesFromFile(myPath);
    }

    private void initLinesFromFile(String filename) {
        myLines = new ArrayList<>();
        myPath = filename;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myPath));
            String line;
            while ((line = reader.readLine()) != null) {
                myLines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + myPath);
        }
    }

    public String asString() {
        StringBuilder sb = new StringBuilder();
        for (String line : myLines) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    public Iterable<String> lines() {
        return myLines;
    }

    public Iterator<String> iterator() {
        return myLines.iterator();
    }

    public String getFilePath() {
        return myPath;
    }

    private String getFilePathFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        return scanner.nextLine().trim();
    }
}
