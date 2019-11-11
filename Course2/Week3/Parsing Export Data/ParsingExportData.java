
/**
 * Exporting country's informations from CSV files
 * 
 * @author Orached 
 * @version 18/11/2015
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    //The method seen in course
    public void listExporters(CSVParser parser, String exportOfInterest){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    //Method that returns a string of information about the country ("NOT FOUND" if there are not such information
    //Doesn't work correctly !!!!!!!!!!!!    
    public String countryInfo(CSVParser parser, String country){
         for(CSVRecord record : parser){
            String countryC = record.get("Country");
            //Always use equals() instead of '==' for comparing two strings
            if(countryC.equals(country)){
                String infoExport = countryC + ": " +record.get("Exports")+ ": " + record.get("Value (dollars)");
                //System.out.println(infoExport);
                return infoExport;
            }
         }
         return "NOT FOUND";
    }
    
    //Country that export 2 types of exports
    void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1)&&export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }        
    }
    
    //Number of countries that export the item passed in parameter
    //Doesn't work correctly !!!!!!!!!
    int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem))
                counter++;;        
        }
        return counter;
    }
    
    //bigExporters
    
    
    //Tester method
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters(parser, "coffee");
        listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "sugar"));
        //System.out.println(countryInfo(parser, "Nauru"));
    }
}
