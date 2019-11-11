
/**
 * Find all URLs on a web pas
 * 
 * @Orached
 * @17/11/2015
 */
import edu.duke.*;

public class FindAllURLs {
    //Find all the URLs on a webpage passed in parameter
    StorageResource findURLs(String url){
        StorageResource store = new StorageResource();
        URLResource urlRes = new URLResource(url);
        int index = 0;
        String line = urlRes.asString();
            while(true){
                int start = line.indexOf("href=\"http", index);
                if(start == -1)
                    break;
                    int stopIdx = line.indexOf("\"", start+10);
                    String urrl = line.substring(start+6, stopIdx);
                    store.add(urrl);
                    index = start + urrl.length();
            }
        //System.out.println(store.size());
        return store;
    }
    
    //Method to find Secured URLs
    int NumberSecURLs(StorageResource sr){
        return 1;
    }
    
    //Method that return the number of links which contains ".com"
    int NumberDotComs(StorageResource sr){
        int counter = 0;
        for(String line : sr.data()){
            /*for(int i=0; i<line.length(); i++){
                if(line.substring(i, i+4) == ".com")
                    counter++;
                }*/
            if(line.contains(".com"))
                counter++;
            }
        return counter;
    }
        
    //Method that return the number of links which ends with ".com" or ".com/"
    int NumberEDotComs(StorageResource sr){
        int counter = 0;
        for(String line : sr.data()){
            if(line.endsWith(".com") || line.endsWith(".com/"))
                counter++;
        }
        return counter;
    }
    
    //Method that a number of "." in a collection of strings
    int NumberDot(StorageResource sr){
        int counter =0;
        for(String line : sr.data()){
            for(int i=0; i<line.length(); i++){
                if(line.charAt(i)=='.')
                    counter++;
            }
        }
        return counter;
    }
    
    //Tester method
    void testURLWithStorage(){
        int counter = 0;
        StorageResource sr = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        //Print all the links in the web page
        for(String line : sr.data()){
            System.out.println(line);
            counter++;
        }
        System.out.println("Number of URLs is: " +counter);
        System.out.println("Number of links which contain \".com\" string is: " +NumberDotComs(sr));
        System.out.println("Number of links which ends with \".com\" or \".com/\" string is: " +NumberEDotComs(sr));
        System.out.println("Total number of '.' in all the links is: " +NumberDot(sr));
    }
}
