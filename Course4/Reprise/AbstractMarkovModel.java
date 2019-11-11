
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @Orached
 * @24/06/2017
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int numberOfCharacters;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    //protected ArrayList<String> getFollows(String key){
    //    return myMap.get(key);
    //}
    
    public String toString() {
        return("MarkovModel of order" + numberOfCharacters);
    }
}
