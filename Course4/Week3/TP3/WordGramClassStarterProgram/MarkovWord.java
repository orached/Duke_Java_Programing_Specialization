
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @Orached
 * @30/06/2017
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
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
    
    public ArrayList<String> getFollows(WordGram kGram) {
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
    
    
    public void testIndexOf(){
        String[] myTestText = {"this","is","a","test","this","is","a","test","this","is","a","test","of","words"};
        /*
        WordGram wg1 = new WordGram(myTestText, 0, 3);
        WordGram wg2 = new WordGram(myTestText, 2, 3);
        String[] myTestText1 = {"a","frog","a", "crazy", "frog"};
        WordGram wg3 = new WordGram(myTestText1, 0, 3);
        System.out.println(wg1);
        System.out.println(indexOf(myTestText, wg1, 0));
        System.out.println(indexOf(myTestText, wg1, 1));
        System.out.println(indexOf(myTestText, wg1, 9));
        System.out.println(wg2);
        System.out.println(indexOf(myTestText, wg2, 0));
        System.out.println(wg3);
        System.out.println(indexOf(myTestText, wg3, 0));
        */
        System.out.println("------------------------------------");
        WordGram wg4 = new WordGram(myTestText, 0, 4);
        System.out.println(wg4.length());
        System.out.println(wg4);
        System.out.println(indexOf(myTestText, wg4, 0));
        WordGram wg5 = new WordGram(myTestText, 1, 4);
        System.out.println(wg5);
        //System.out.println(indexOf(myTestText, wg5, 0));
        System.out.println(indexOf(myTestText, wg5, 2));
        System.out.println(indexOf(myTestText, wg5, 5));
        System.out.println(indexOf(myTestText, wg5, 6));
        //WordGram wg6 = new WordGram(myTestText, 10, 4);
        //System.out.println(wg6);
        //System.out.println(indexOf(myTestText, wg6, 0));
        //System.out.println(indexOf(myTestText, "this", 3));
        //System.out.println(indexOf(myTestText, "frog", 0));
        //System.out.println(indexOf(myTestText, "frog", 5));
        //System.out.println(indexOf(myTestText, "simple", 2));
        //System.out.println(indexOf(myTestText, "test", 5));
    }
    
}
