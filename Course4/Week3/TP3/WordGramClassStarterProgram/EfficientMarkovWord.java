
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @Orached
 * @01/07/2017
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        for(int i = 0; i < kGram.length(); i ++) {
            sb.append(kGram.wordAt(i));
            sb.append(" ");
        }
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            //System.out.println("Words that follows the keyGram '" + kGram + "' are " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
            //System.out.println(kGram);
        }
        return sb.toString().trim();
    }
    
    /* Old method
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText, kGram, pos);
            if(start == -1){
                break;
            }
            
            if(start + kGram.length() > myText.length-1){
                break;
            }
            
            String next = myText[start+kGram.length()];
            follows.add(next);
            
            pos = start+1;
        }
        //System.out.println(follows);
        return follows;
    }
    */
   
    public ArrayList<String> getFollows(WordGram keyG){
        buildMap();
        return myMap.get(keyG);
    }

    private int indexOf(String[] words, WordGram target, int start){
        for(int i = start; i < words.length-myOrder; i++){
            int k = 0;
            while (words[i+k].equals(target.wordAt(k)) && k < target.length()-1){
                //System.out.println("Word in training text at position" +i+ " and " + k + " is " + words[i+k]+ " is it equal to " + target.wordAt(k));
                k ++;
            }
            if(words[i+target.length()-1].equals(target.wordAt(target.length()-1)) && k == target.length()-1){
                return i;
            }
        }
        return -1;
    }
    
    private HashMap<WordGram, ArrayList<String>> buildMap(){
        myMap = new HashMap<WordGram, ArrayList<String>>();
        for(int i=0; i<=myText.length-myOrder; i++){
            WordGram keyGram = new WordGram(myText, i, myOrder);
            if(! myMap.containsKey(keyGram)){
                ArrayList<String> follows = new ArrayList<String>();
                for(int k=i; k<myText.length-myOrder; k++){
                    int j=0;
                    while (j<keyGram.length() && j<myOrder-1){                        
                        j++;
                    }
                    if(myText[k+myOrder-1].equals(keyGram.wordAt(myOrder-1)) && j==myOrder-1){
                        follows.add(myText[k+keyGram.length()]);;
                    }
                }
                myMap.put(keyGram, follows);
            }
        }
        return myMap;
    }
    
    public void printHashMapInfo(){
        buildMap();
        /*
        if(myMap.size()<50){
            System.out.println(myMap);
        }
        */
        
        System.out.println("Number of keys in the HashMap :"+myMap.size());
        
        int maxVSize = 0;
        for(WordGram wordG : myMap.keySet()){
            if(maxVSize < myMap.get(wordG).size()){
                maxVSize = myMap.get(wordG).size();
            }
        }
        System.out.println("Largest value in the Hashmap is :"+maxVSize);
        System.out.println("Keys that have the maximum size value are: ");
        for(WordGram wordG : myMap.keySet()){
            if(myMap.get(wordG).size()==maxVSize){
                System.out.println(wordG);
                }
        }
    }
    
}
