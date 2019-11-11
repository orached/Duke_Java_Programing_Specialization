
/**
 * Write a description of CodonCount here.
 * 
 * @Orached 
 * @28/11/2015
 */
import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String,Integer> myDNACodons;
    
    public CodonCount(){
        myDNACodons = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        myDNACodons.clear();
        for(int i=start; i<dna.length()-3; i+=3){
            String codon = dna.substring(i,i+3);
            if(! myDNACodons.containsKey(codon)){
                myDNACodons.put(codon,1);
            } else {
                myDNACodons.put(codon,myDNACodons.get(codon)+1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        int count=0;
        String maxCodon = "";
        for(String codon : myDNACodons.keySet()){
            int current = myDNACodons.get(codon);
            if(count<current){
                count = current;
                maxCodon = codon;
            }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end){
        for(String codon : myDNACodons.keySet()){
            int curr = myDNACodons.get(codon);
            if(curr >= start && curr <= end){
                System.out.println("The codon "+codon+" appears "+curr+" times");
            }
        }
    }
    
    //tester
    public void testCodonCount(){
        FileResource f = new FileResource();
        String contents = f.asString().toUpperCase();
        //1st possiblity: Starting with 0
        System.out.println("*********************Start with 0 *************************");
        buildCodonMap(0, contents);
        System.out.println("Total of unique codons is "+myDNACodons.size());
        String maxCodon = getMostCommonCodon();
        System.out.println("The most common codon is "+maxCodon+ " which occur "+myDNACodons.get(maxCodon)+" times");
        System.out.println("Codons that occur between 6 and 7 times");
        printCodonCounts(6,7);
        
        //1st possiblity: Starting with 1
        System.out.println("*********************Start with 1 *************************");
        buildCodonMap(1, contents);
        System.out.println("Total of unique codons is "+myDNACodons.size());
        maxCodon = getMostCommonCodon();
        System.out.println("The most common codon is "+maxCodon+ " which occur "+myDNACodons.get(maxCodon)+" times");
        //System.out.println("Codons that occur between 1 and 5 times");
        //printCodonCounts(1,5);
        
        //1st possiblity: Starting with 2
        System.out.println("*********************Start with 2 *************************");
        buildCodonMap(2, contents);
        System.out.println("Total of unique codons is "+myDNACodons.size());
        maxCodon = getMostCommonCodon();
        System.out.println("The most common codon is "+maxCodon+ " which occur "+myDNACodons.get(maxCodon)+" times");
        //System.out.println("Codons that occur between 1 and 5 times");
        //printCodonCounts(1,5);
        }
}
