import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MachineOutputModifier {

    public static void main(String[] args) {
        String inputFile = "F:\\Mods\\machine_input.txt";  // Specify your folder path here
        String outputFile = "F:\\Mods\\machine_output.txt";  // Output file path

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String id = line.trim();
                String output = convertID(id);
                writer.write(output);
                writer.newLine();
            }
            
            reader.close();
            writer.close();
            
            System.out.println("Conversion complete. Output written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String convertID(String id) {
    	String[] parts = id.split("_");
    	String displayName = "";
    	for (int i = 1; i < parts.length; i++) {
    	    displayName += parts[i];
    	    if (i < parts.length - 1) {
    	        displayName += "_"; // Add underscore between parts if not the last part
    	    }
    	}
      //  String firstLetter = parts[0].substring(0, 1); // Extract the first letter of the first part of the ID
        String modId = "{{ModId}}";
        String product = "Dried";
        String revisedId = modId + "_"+ product + "_"  + displayName; 
       // String myGoodString = "\"zzzCustom.florist";
         String myGoodString = ""+id;
        String outupGoodsId = modId + "_"+ product + "_"  + displayName;
        
        return "\"" + revisedId + "\": {\n" +
            "\"Id\": \"" + revisedId + "\",\n" +
            "\"Triggers\": [\n" +
            "{\n" +
            "\"Id\": \"ItemPlacedInMachine\",\n" +
            "\"Trigger\": \"ItemPlacedInMachine\",\n" +
            "\"RequiredItemId\": \"(O)" + id + "\",\n" +
            "\"RequiredCount\": 5\n" +
            "}\n" +
            "],\n" +
            "\"OutputItem\": [\n" +
            "{\n" +
            "\"Id\": \"" + outupGoodsId + "\",\n" +
            "\"ItemId\": \""+ outupGoodsId + "\",\n" +
            "\"CustomData\": { \"ExtraMachineConfig.CopyColor\": \"true\" },\n" +
     //       "\"ObjectDisplayName\": " +"\"" + displayName + " Bouquet" + "\",\n" +
            "\"CopyQuality\": true,\n" +
            "\"CopyColor\": true,\n" +
            "\"CopyPrice\": true,\n" +
            "\"PriceModifiers\": [\n" +
            "{\n" +
            "\"Modification\": \"Multiply\",\n" +
            "\"Amount\": 1.5\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "],\n" +
            "\"MinutesUntilReady\": 1000\n" +
            "},";
    }
}