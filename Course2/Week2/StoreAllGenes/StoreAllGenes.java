
/**
 * Algorithm to identify multiple genes in a strand od DNA (CAPITAL Letters only)
 * For lowecase letter use toUpperCase() method
 * 
 * @Orached 
 * @16/11/2015
 */
import edu.duke.*;
import java.io.*;

public class StoreAllGenes {
    //Finds the first occurence of each stop codon to the right of index
    int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("TGA", index);
        if (stop1 == -1 || (stop1-index)%3 !=0){
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("TAA", index);
        if (stop2 == -1 || (stop2-index)%3 !=0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("TAG", index);
        if (stop3 == -1 || (stop3-index)%3 !=0){
            stop3 = dna.length();
        }
        return Math.min(stop1, Math.min(stop2, stop3));
        }
    
    //Store all the genes found in DNA and return the store
    StorageResource storeAll(String dna){
        int start =0;
        StorageResource store = new StorageResource();
        //String lowDna = dna.toLowerCase();
        StringBuilder dnaTxt = new StringBuilder(dna);
        while(true){
            int loc = dna.indexOf("ATG", start);
            if(loc == -1){
                break;
            }
            int stop = findStopIndex(dna, loc+3);
            
            if(stop != dna.length()){
                store.add(dnaTxt.substring(loc, stop+3).toString());
                start = stop +3;
            }
            else{
                start = start +3;
            }
        }
        return store;
    }
    
    //Return the ration of C's and G's (#C + #G) as a fraction of the entire strand of dna
    float cgRatio(String dna){
        int counter = 0;
        for(int i=0; i < dna.length(); i++){
            if((dna.charAt(i)=='c')||(dna.charAt(i)=='g')||(dna.charAt(i)=='C')||(dna.charAt(i)=='G'))
                counter++;
        }
        return ((float) counter)/dna.length();
    }
    
    //Calculate number of occurences that CTG appear
    int ctgAppear(StorageResource sr){
        int ctgT = 0;
        int start = 0;
        for(String texte : sr.data()){
            while(true){
            int loc = texte.indexOf("CTG", start);
            if(loc == -1){
                break;
            }
            ctgT++;
            start += 3;
            }
        }
        return ctgT;
    }
    
    //Return the length of the logest gene in a collection of genes
    int longestGeneLength(StorageResource sr){
        int longestLength = 0;
        
        for(String texte : sr.data()){
            if(texte.length() > longestLength)
            longestLength = texte.length();
        }
        
        return longestLength;
    }
    
    //Personalized printing method: see detail in pdf
    void printGenes(StorageResource sr){
        //StorageResource provisoire = new StorageResource();
        int tUp60=0;
        int tUp035=0;
        for(String texte : sr.data()){
            if(texte.length() > 60){
                //System.out.println(texte);
                tUp60++;
            }
            if(cgRatio(texte) > 0.35){
                //System.out.println(texte);
                tUp035++;
            }
        }
            System.out.println("Number of stores that have more than 60 characters is: "+tUp60);
            System.out.println("Number of stores that have a cgRatio bigger than 0.35 is: "+tUp035);
    }
    
     //Tester method
    void testStorageFinder(){
        //Pas sur du tout
        FileResource f = new FileResource();
        String dnaTxt = f.asString();
        StorageResource s = storeAll(dnaTxt);
        System.out.println("Size = " +s.size());
        printGenes(s);
        System.out.println("CTG appears "+ctgAppear(s)+" times");
        System.out.println("The length of the longest gene is: "+longestGeneLength(s));
    }
}

