
/**
 * Write a description of TP2 here.
 * 
 * @Orached 
 * @19/11/2015
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class TP2 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        //start with smallestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
		}
		//The largestSoFar is the answer
		return smallestSoFar;
    }
    
	public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If largestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature > largestSoFar’s
			if (currentTemp < smallestTemp && currentTemp != -9999) {
				//If so update largestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}
	
	public CSVRecord coldestInManyFiles() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			// use method to compare two records
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
		}
		//The largestSoFar is the answer
		return smallestSoFar;
	}
	
	void testColdestInManyFiles(){
	    CSVRecord smallest = coldestInManyFiles();
		System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("DateUTC"));
	   }
	
    public void testColdestHourInFile(){
        FileResource file = new FileResource();
        CSVRecord smallest = coldestHourInFile(file.getCSVParser());
		System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("DateUTC"));
    }
    
    	
	public String fileWithColdestTemperature(){
	    CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		String fileName = "";
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			// use method to compare two records
			if (smallestSoFar == null){
			fileName = f.getName();
			//Otherwise
            } 
            else {
               double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
               double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
               //Check if currentRow’s temperature > largestSoFar’s
			   if (currentTemp < smallestTemp) {
			         //If so update largestSoFar to currentRow
			         fileName = f.getName();
			   }
           }
		}
	    return fileName;
	}
	
	public void testFileWithColdestTemperature(){
	    String fileName = fileWithColdestTemperature();
	    FileResource file = new FileResource("nc_weather/"+fileName.substring(8,12)+"/"+fileName);
	    CSVParser parser = file.getCSVParser();
	    CSVRecord smallest = coldestHourInFile(parser);
	    System.out.println("Coldest day was in file "+fileName);
	    System.out.println("Coldest temperature on that day was "+smallest.get("TemperatureF"));
	    System.out.println("All the Temperatures on the coldest day were:");
	    for (CSVRecord currentRow : parser) {
	        System.out.println(currentRow.get("DateUTC")+": "+currentRow.get("TemperatureF"));
	    }
	}
	
	public CSVRecord lowestHumidityInFile(CSVParser parser){
	    CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			if (smallestSoFar == null) {
			smallestSoFar = currentRow;
            }
            //Otherwise
            else {
                String currentSTemp = currentRow.get("Humidity");
                String smallestSTemp = smallestSoFar.get("Humidity");
                if(!currentSTemp.equals("N/A") && !smallestSoFar.equals("N/A")){
                    double currentTemp = Double.parseDouble(currentSTemp);
                    double smallestTemp = Double.parseDouble(smallestSTemp);
                    //Check if currentRow’s temperature > largestSoFar’s
                    if (currentTemp < smallestTemp) {
                        //If so update largestSoFar to currentRow
                        smallestSoFar = currentRow;
                    }
                }
            }
		}
		//The largestSoFar is the answer
		return smallestSoFar;
	}
	   
	public void testLowestHumidityInFile(){
	    FileResource fr = new FileResource();
	    CSVParser parser = fr.getCSVParser();
	    CSVRecord csv = lowestHumidityInFile(parser);
	    System.out.println("Lowest humidity was "+ csv.get("Humidity") +" at "+ csv.get("DateUTC"));
	   }
	   
	public CSVRecord getSmallestOfTwo1(CSVRecord currentRow, CSVRecord smallestSoFar, String info) {
		//If largestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get(info));
			double smallestTemp = Double.parseDouble(smallestSoFar.get(info));
			//Check if currentRow’s temperature > largestSoFar’s
			if (currentTemp < smallestTemp) {
				//If so update largestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}
	
	public CSVRecord lowestHumidityInManyFiles(){
	    CSVRecord lowestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get lowest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			lowestSoFar = getSmallestOfTwo1(currentRow, lowestSoFar, "Humidity");
		}
		//The largestSoFar is the answer
		return lowestSoFar; 
	}
	   
	public void testLowestHumidityInManyFiles(){
	   CSVRecord csv = lowestHumidityInManyFiles();
	   System.out.println("Lowest humidity was "+ csv.get("Humidity") +" at "+ csv.get("DateUTC")); 
	}
	
	public double averageTemperatureInFile(CSVParser parser){
	    double avgTemperatureF = 0;
	    int i=0;
	    for (CSVRecord currentRow : parser) {
	        avgTemperatureF += Double.parseDouble(currentRow.get("TemperatureF"));
	        i++;
	    }
	    return avgTemperatureF/(double)i;
	}
	
	public void testAverageTemperatureInFile(){
	    FileResource fr = new FileResource();
	    CSVParser parser = fr.getCSVParser();
	    double avg = averageTemperatureInFile(parser);
	    System.out.println("Average temperature in file is "+avg);
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
	    double avgTemperatureF = 0;
	    int i=0;
	    for (CSVRecord currentRow : parser) {
	        if(value < Integer.parseInt(currentRow.get("Humidity"))){
	           avgTemperatureF += Double.parseDouble(currentRow.get("TemperatureF"));
	           i++;
	       }
	    }
	    return avgTemperatureF/(double)i;
	}
	   
	public void testAverageTemperatureWithHighHumidityInFile(){
	    FileResource fr = new FileResource();
	    CSVParser parser = fr.getCSVParser();
	    double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
	    if(avg != 0)
	       System.out.println("Average Temp when high Humidity is "+avg);
	    else
	       System.out.println("No temperatures with that humidity");
	   }
}
