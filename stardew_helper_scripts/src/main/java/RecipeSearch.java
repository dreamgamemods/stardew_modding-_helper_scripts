import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RecipeSearch {

    public static void main(String[] args) {
        String inputFolderPath = "F:\\Mods";  // Specify your folder path here
        String outputFilePath = "F:\\Mods\\machines_extracted.txt";  // Output file path

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            // Traverse every subfolder inside the specified folder
            Files.walk(Paths.get(inputFolderPath))
            .filter(Files::isRegularFile)
            .filter(path -> path.getFileName().toString().endsWith(".json"))
            .forEach(path -> {
                try {
                    readAndWriteLines(path, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
            System.out.println("Search completed. Results written to " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAndWriteLines(Path filePath, BufferedWriter writer) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
        String line;
        boolean writeMode = false;

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {  
                continue;
            }

            if (line.contains("spacechase0") && !line.contains("ExcludeFromRandomSale")) {
                writeMode = true;
                writer.write("Path: " + filePath.toString());
                writer.newLine();
            }

            if (writeMode) {
            	
                writer.write(line);
                writer.newLine();
            	
                if (line.contains("]")) {
                    writeMode = false;
                }
            }
        }

        reader.close();
    }
}