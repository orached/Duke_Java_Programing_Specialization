
/**
 * Write a description of class MarkovOne here.
 * 
 * @Orached
 * @26/12/2015 v2.0
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel{
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> followingChar = getFollows(key);
            if(followingChar.size() == 0){
                break;
            }
            int idxFollowingChar = myRandom.nextInt(followingChar.size());
            String next = followingChar.get(idxFollowingChar);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }
}

