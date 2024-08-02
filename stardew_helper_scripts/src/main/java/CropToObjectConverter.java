import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CropToObjectConverter {

    public static void main(String[] args) {
        String inputFile = "F:\\Mods\\croptoobject_input.txt";  // Specify your folder path here
        String outputFile = "F:\\Mods\\croptoobject_output.txt";  // Output file path

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String line;
            boolean descriptionPresent = false;
            
            if(descriptionPresent) {
            	String id = null;
            	while ((line = reader.readLine()) != null) {
            	    if (id == null) {
            	        id = line.trim();
            	    } else {
            	        String description = line.trim();
            	        String output = convertID(id, description);
            	        writer.write(output);
            	        writer.newLine();
            	        id = null;
            	    }
            	}
            }else { 
            while ((line = reader.readLine()) != null) {
            	String description = null;
                String id = line.trim();
                String output = convertID(id, description);
                writer.write(output);
                writer.newLine();
            }}
            reader.close();
            writer.close();
            
            System.out.println("Conversion complete. Output written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String convertID(String id, String description) {
        String[] parts = id.split("_");
       // String displayName = parts[parts.length - 1];
    	String displayName = "";
    	for (int i = 1; i < parts.length; i++) {
    	    displayName += parts[i];
    	    if (i < parts.length - 1) {
    	        displayName += "_"; // Add underscore between parts if not the last part
    	    }
    	}
        String modId = "{{ModId}}"; 
 
        String product = "Dried";
        String revisedId = modId + "_"+ product + "_"  + displayName; 
        String fullDisplayName = product + " " + displayName; 
        if (description==null) {	
        	description ="\"A bundle of dried " + displayName + "s\"";
        }
         
        String tag1 = "\"dried_flower_item\"";
        String tag2 = "\"wildflour_floral_item\"";
        String contextTags =  tag1 + ", " + tag2 ; 
        
        return "\"" + revisedId + "\": {\n" +
    		"\"Name\": \"" + revisedId + "\",\n" +
      		"\"DisplayName\": \"" + fullDisplayName + "\",\n" +
            "\"Description\": " + description + ",\n" +
            "\"Type\": \"Basic\",\n" +
            "\"Category\": -25,\n" +
            "\"Edibility\": -300,\n" +
            "\"IsDrink\": false,\n" +
            "\"IsColored\": true,\n" +
            "\"Price\": 100,\n" +
            "\"ExcludeFromShippingCollection\": false,\n" +
            "\"ContextTags\": " + "[ "+ contextTags + " ],\n" +
            "\"Texture\": \"{{InternalAssetKey: assets/colored/"+ product + "_Flower"+ ".png}}\"\n" +
            "},";
    }
}