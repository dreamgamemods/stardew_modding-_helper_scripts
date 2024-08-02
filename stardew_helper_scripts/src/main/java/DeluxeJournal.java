import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeluxeJournal {

    public static void main(String[] args) {
        String inputFileName = "F:\\Mods\\AllObjects_strings.txt"; // Replace with the path to your input file
        String outputFileName = "F:\\Mods\\journal_entries_test_output.txt"; // Replace with the path to your output file

        try {
            List<String> itemIds = readItemIds(inputFileName);
            Collections.shuffle(itemIds); 
            if (itemIds.size() > 50) {
                itemIds = itemIds.subList(0, 50);
            }
            List<String> outputLines = generateOutputLines(itemIds);
            writeOutputToFile(outputLines, outputFileName);
            System.out.println("Output has been written to the output file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readItemIds(String fileName) throws IOException {
        List<String> itemIds = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            itemIds.add(line);
        }
        reader.close();
        return itemIds;
    }

    private static List<String> generateOutputLines(List<String> lines) {
        List<String> outputLines = new ArrayList<>();
        for (String item : lines) {
        	String[] parts = item.split("!");
            String name = processName(parts[1].toString(), parts[0]);
            String itemId = parts[0];
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            sb.append("  \"ID\": \"sell\",\n");
            sb.append("  \"Name\": \"" + name + "\",\n");
            sb.append("  \"Active\": true,\n");
            sb.append("  \"Complete\": false,\n");
            sb.append("  \"ItemIds\": [\n");
            sb.append("    \"(O)" + itemId + "\"\n");
            sb.append("  ],\n");
            sb.append("  \"RenewPeriod\": \"Never\",\n");
            sb.append("  \"RenewDate\": {\n");
            sb.append("    \"Season\": \"spring\",\n");
            sb.append("    \"Day\": 1,\n");
            sb.append("    \"Year\": 1\n");
            sb.append("  },\n");
            sb.append("  \"Count\": 0,\n");
            sb.append("  \"MaxCount\": 5,\n");
            sb.append("  \"BasePrice\": 1\n");
            sb.append("},\n");
            outputLines.add(sb.toString());
        }
        return outputLines;
    }
    
    private static String capitalizeFirstLetter(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
    
    private static String processName(String name, String itemid) {

        System.out.println("itemid: "+ itemid);
        
        itemid.trim();
        if(itemid.contains("wildflour")) {
        	name = "Wild "+ name;
        	
        }
        
        if(itemid.contains("QL_")) {
        	name = "Quiet "+ name;
        }
        if(itemid.contains("Mixology")) {
        	name = "Mixed " + name;
        }
        if(itemid.contains("Cornucopia")) {
        	name = "Quality "+ name;
        }
        if(itemid.contains("NeosSucculentFarmRedux")) {
        	name = "Succulent "+ name;
        }
        if(itemid.contains("MtVapius")) {
        	name = "Vapius "+ name;
        }
        if(itemid.contains("MoreNewFish")) {
        	name = "Fish "+ name;
        }
        if(itemid.contains("FOF")) {
        	name = "Foraged "+ name;
        }
        if(itemid.contains("RSVCP")) {
        	name = "Ridge "+ name;
        }
        if(itemid.contains("SereneMeadow")) {
        	name = "Serene "+ name;
        }
        if(itemid.contains("uncleirohapprovedteacp")) {
        	name = "Uncle Iroh's "+ name;
        }
        if(itemid.contains("candycreation")) {
        	name = "Creative Candy "+ name;
        }
        if(itemid.contains("TSP_")) {
        	name = "Sushi "+ name;
        }
        System.out.println("name: "+name);
        return name;
    }

    private static void writeOutputToFile(List<String> outputLines, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String line : outputLines) {
            writer.write(line);
        }
        writer.close();
    }
}