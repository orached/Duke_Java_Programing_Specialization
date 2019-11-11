
/**
 * Find the most common word length
 * 
 * @Omar
 * @15/11/2015
 */
import edu.duke.*;

public class WordLenghts {
    
    void countWordLengths(FileResource resource, int[] counts){
        //FileResource resource;
        //int[] counts;
        //resource = new FileResource("romeo.txt");
        //counts = new int[31];
        int stringLength = 0;
        for(String s : resource.words()){
            stringLength = s.length();
            if(!Character.isLetter(s.charAt(0))){
                stringLength --;
            }
            if(!Character.isLetter(s.charAt(s.length()-1))){
                stringLength --;
            }
            if(stringLength>=counts.length-1){
            counts[counts.length-1] += 1;
            } else counts[s.length()] += 1;
        }
        
        for (int i=0; i<counts.length; i++){
            System.out.println("Words of " +i+ " letters\t" +counts[i]);
        }
    }
    
    void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        indexOfMax(counts);
        
        
    }
    
    int indexOfMax(int[] values){
        int indexPosition = 0;
        int provisoire = values[0];
        for(int i=0; i<values.length; i++){
            if(values[i]>provisoire){
                provisoire = values[i];
                indexPosition = i;
            }
        }
        return indexPosition;
    }
    
}
