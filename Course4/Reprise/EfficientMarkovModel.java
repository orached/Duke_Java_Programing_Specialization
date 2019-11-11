
/**
 * Write a description of MarkovModel here.
 * 
 * @Orached
 * @24/06/2017
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovModel(int n) {
        numberOfCharacters = n;
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public HashMap<String, ArrayList<String>> buildMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        for(int i=0; i<=myText.length()-numberOfCharacters; i++){
            String keyWord = myText.substring(i, i+numberOfCharacters);
            if(! myMap.containsKey(keyWord)){
               ArrayList<String> follows = new ArrayList<String>();
               for(int k=i; k<myText.length()-numberOfCharacters; k++){
                   if(myText.substring(k, k+numberOfCharacters).equals(keyWord)){
                       follows.add(myText.substring(k+numberOfCharacters, k+numberOfCharacters+1));
                    }
               }
               myMap.put(keyWord, follows);
            }
        }
        return myMap;
    }
    
    public ArrayList<String> getFollows(String key){
        buildMap();
        return myMap.get(key);
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
    
    
    public void printHashMapInfo(){
        buildMap();
        
        /*if(myMap.size()<50){
            System.out.println(myMap);
        }
        */
        
        System.out.println("Number of keys in the HashMap :"+myMap.size());
        //ArrayList<ArrayList<String>> values = myMap.values().toArray();
        int maxVSize = 0;
        for(String word : myMap.keySet()){
            if(maxVSize < myMap.get(word).size()){
                maxVSize = myMap.get(word).size();
            }
        }
        System.out.println("Largest value in the Hashmap is :"+maxVSize);
        System.out.println("Keys that have the maximum size value are: ");
        for(String word : myMap.keySet()){
            if(myMap.get(word).size()==maxVSize){
                System.out.println(word);
                }
        }
     
    }
}
