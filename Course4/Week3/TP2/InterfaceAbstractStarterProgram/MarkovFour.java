
/**
 * Write a description of class MarkovOne here.
 * 
 * @Orached
 * @27/12/2015 v2.0
 */

import java.util.*;

public class MarkovFour extends AbstractMarkovModel{
    
    public MarkovFour() {
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
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);
        
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> followingChar = getFollows(key);
            if(followingChar.size() == 0){
                break;
            }
            int idxFollowingChar = myRandom.nextInt(followingChar.size());
            String next = followingChar.get(idxFollowingChar);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        for(int i=0; i<myText.length()-key.length(); i++){
            if(myText.substring(i, i+key.length()).equals(key)){
                follows.add(myText.substring(i+key.length(), i+key.length()+1));
            }
        }
        return follows;
    }
}
