
/**
 * Write a description of MarkovModel here.
 * 
 * @Orached
 * @27/12/2015 v2.0
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int numberOfCharacters;
    
    public MarkovModel(int n) {
        numberOfCharacters = n;
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
        int index = myRandom.nextInt(myText.length()-numberOfCharacters);
        String key = myText.substring(index, index+numberOfCharacters);
        sb.append(key);
        
        for(int k=0; k < numChars-numberOfCharacters; k++){
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
}
