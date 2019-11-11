
/**
 * Write a description of WordFrequencies here.
 * 
 * @Orached
 * @22/11/2015
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        
        for(String s : resource.words()){
             s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    //Tester
    public void testFindUnique(){
        findUnique();
        System.out.println("The total number of unique words is: "+myWords.size());
        for(int i=0; i < myWords.size(); i++){
            System.out.println(myWords.get(i)+ " appears "+myFreqs.get(i)+" times");
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs the most often is the "+maxIndex+"th word: "+myWords.get(maxIndex));
    }
    
    //Method that returns the index location of the largest value in myFreqs list
    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
