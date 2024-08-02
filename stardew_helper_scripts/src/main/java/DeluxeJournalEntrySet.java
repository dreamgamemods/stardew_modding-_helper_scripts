import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeluxeJournalEntrySet {
    public static void main(String[] args) {
        String inputFile = "F:\\Mods\\AllRecipes.txt";
        String outputFile = "F:\\Mods\\output.txt";


        List<String> lines = readLinesFromFile(inputFile);

        if (lines != null) {
            List<String> modifiedLines = modifyLines(lines);
            writeLinesToFile(outputFile, modifiedLines);
            System.out.println("File modification completed successfully.");
        } else {
            System.out.println("Failed to read input file.");
        }
    }

    private static List<String> readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return lines;
    }

    private static List<String> modifyLines(List<String> lines) {
        List<String> modifiedLines = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("_");
            StringBuilder modifiedLine = new StringBuilder(line);
            modifiedLine.append("!");
            for (int i = 1; i < parts.length; i++) {
                String modifiedPart = parts[i].trim();
                StringBuilder partWithSpaces = new StringBuilder();
                for (int j = 0; j < modifiedPart.length(); j++) {
                    char c = modifiedPart.charAt(j);
                    if (Character.isUpperCase(c) && j != 0) {
                        partWithSpaces.append(" ");
                    }
                    partWithSpaces.append(c);
                }
                modifiedLine.append(partWithSpaces).append(" ");
            }
            modifiedLines.add(modifiedLine.toString().trim());
        }
        return modifiedLines;
    }

    private static void writeLinesToFile(String fileName, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}