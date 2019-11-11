
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @Orached
 * @27/12/2015
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int order;
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovModel(int n) {
        order = n;
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        buildMap();
        printHashMapInfo();
        //System.out.println(myText);
        //System.out.println(myMap);
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-order);
        String key = myText.substring(index, index+order);
        sb.append(key);
        
        for(int k=0; k < numChars-order; k++){
            ArrayList<String> followingChar = getFollows(key);
            //System.out.println(followingChar);
            if(followingChar.isEmpty()){
                break;
            }
            int idxFollowingChar = myRandom.nextInt(followingChar.size());
            String next = followingChar.get(idxFollowingChar);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return "EfficientMarkovModel with order "+order;
    }
    
    /*
    public void buildMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        for(int i=0; i<=myText.length()-order; i++){
            String keyWord = myText.substring(i, i+order);
            ArrayList<String> follows = new ArrayList<String>();
            if(! myMap.containsKey(keyWord)){
               if(! myText.substring(i+order).isEmpty()){
                  follows.add(myText.substring(i+order, i+order+1));
               }// else {
               //    follows.add("");
               //}
               myMap.put(keyWord, follows);
            } else{
                if(! myText.substring(i+order).isEmpty()){
                   myMap.get(keyWord).add(myText.substring(i+order, i+order+1));
               }
            }
        }
        //return myMap;
    }
    */
    
   public void buildMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        for(int i=0; i<=myText.length()-order; i++){
            String keyWord = myText.substring(i, i+order);
            ArrayList<String> follows = new ArrayList<String>();
            if(! myMap.containsKey(keyWord)){
               for(int k=i; k<=myText.length()-order; k++){
                    if(myText.substring(k, k+order).equals(keyWord)&& ! myText.substring(k+order).isEmpty()){
                        follows.add(myText.substring(k+order, k+order+1));
                        }
                    myMap.put(keyWord, follows);
               }
            }
        }
   }
   
    public ArrayList<String> getFollows(String key){
        return myMap.get(key);
    }
    
    public void printHashMapInfo(){
        //buildMap();
        if(myMap.size()<50){
            System.out.println(myMap);
        }
        System.out.println("Number of keys in the HashMap :"+myMap.size());
        //ArrayList<ArrayList<String>> values = myMap.values().toArray();
        int maxVSize = 0;
        for(String word : myMap.keySet()){
            if(maxVSize < myMap.get(word).size()){
                maxVSize = myMap.get(word).size();
            }
        }
        System.out.println("Largest value in the Hashmap is :"+maxVSize);
        int maxKSize = 0;
        for(String word : myMap.keySet()){
            if(maxKSize < word.length()){
                maxKSize = word.length();
            }
        }
        System.out.println("The size of the largest word in the HashMap is: "+maxKSize);
        System.out.println("And those words are: ");
        for(String word : myMap.keySet()){
            if(word.length()==maxKSize){
                System.out.println(word);
                }
        }
        
    }
}
