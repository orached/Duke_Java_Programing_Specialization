
/**
 * Calclate number of files in which a word appear. Using hash maps
 * 
 * @Orached
 * @28/11/2015
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> myWords;
    
    public WordsInFiles(){
        myWords = new HashMap<String,ArrayList<String>>();
    }
    //A lot of improvisations !!!!
    private void addWordsFromFile(File f){
        Scanner input = null;
        try {
            input = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
        
        while(input.hasNext()) {
            ArrayList<String> sList = new ArrayList<String>();
            String fileName = f.getName();
            String word = input.next();
            if(myWords.containsKey(word)){
                sList = myWords.get(word);
                if(! sList.contains(fileName)){
                    sList.add(fileName);
                }
            } else {
                sList.add(fileName);
            }
            myWords.put(word, sList);
        }
    }

    public void buildWordFileMap(){
        myWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    //Returns the maximum number of files any word appears in
    public int maxNumber(){
        int max = 0;
        for(String word : myWords.keySet()){
            int curr = myWords.get(word).size();
            if(max < curr)
                max = curr;
        }
        return max;
    }
    //Returns an arrayList of words that appear in excatly "number" files
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> sList = new ArrayList<String>();
        for(String word : myWords.keySet()){
            int curr = myWords.get(word).size();
            if(curr == number)
                sList.add(word);
        }
        return sList;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> sList = myWords.get(word);
        System.out.println("The word "+word+" appears in the following files: ");
        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i));
        }
    }
    //This is a tester
    public void testWordsInFiles(){
        buildWordFileMap();
        //int max = maxNumber();
        /*int max = 4;
        ArrayList<String> sList = wordsInNumFiles(max);
        System.out.println("Number of words that appears in "+max+" files is "+sList.size());
        for (int i = 0; i < sList.size(); i++) {
            String word = sList.get(i);
            printFilesIn(sList.get(i));
        }*/
        printFilesIn("tree");
    }
    
    //Tester 2
    //public void tester2(){
    //        ArrayList<String> sList = myWords.get("laid");
    //    }
    //}
}
