
/**
 * Algorithm to identify multiple genes in a strand od DNA
 * 
 * @Orached 
 * @16/11/2015
 */
// import java.io.*;
public class FindAllGenes {
    //Finds the first occurence of each stop codon to the right of index
    int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("tga", index);
        if (stop1 == -1 || (stop1-index)%3 !=0){
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("taa", index);
        if (stop2 == -1 || (stop2-index)%3 !=0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("tag", index);
        if (stop3 == -1 || (stop3-index)%3 !=0){
            stop3 = dna.length();
        }
        return Math.min(stop1, Math.min(stop2, stop3));
        }
    
    //Print all the genes found in DNA
    void printAll(String dna){
        int start =0;
        String lowDna = dna.toLowerCase();
        StringBuilder dnaTxt = new StringBuilder(dna);
        while(true){
            int loc = lowDna.indexOf("atg", start);
            if(loc == -1){
                break;
            }
            int stop = findStopIndex(lowDna, loc+3);
            
            if(stop != dna.length()){
                System.out.println(dnaTxt.substring(loc, stop+3).toString());
                start = stop +3;
            }
            else{
                start = start +3;
            }
        }
    }
    
    void testFinder(){
        String dna1 = "ATCAAATGAAAA";
        String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        
        
    }
}
