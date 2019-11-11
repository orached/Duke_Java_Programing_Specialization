
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @Orached
 * @27/12/2015
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        for(int i=0; i<myText.length()-key.length(); i++){
            if(myText.substring(i, i+key.length()).equals(key)){
                follows.add(myText.substring(i+key.length(), i+key.length()+1));
            }
        }
        return follows;
    }
}
