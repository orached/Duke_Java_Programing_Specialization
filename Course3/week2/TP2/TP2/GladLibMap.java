
/**
 * Write a description of GladLibMap here.
 * 
 * @Orached
 * @29/11/2015
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedVocabulary = new ArrayList<String>();
    private ArrayList<String> usedCategories = new ArrayList<String>();
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();        
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] arrayCategories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for(String curCategory : arrayCategories){
            ArrayList<String> wordsList = readIt(source+"/"+curCategory+".txt");
            myMap.put(curCategory, wordsList);
        }          
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
                return ""+myRandom.nextInt(50)+5;
        }
        for(String word : myMap.keySet()){
            if(label.equals(word))
                return randomFrom(myMap.get(word));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String category = w.substring(first+1,last);
        String sub = getSubstitute(category);
        if(! usedCategories.contains(category))
            usedCategories.add(category);
        //Check if this word is already used
        if(usedVocabulary.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedVocabulary.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        usedVocabulary.clear();
        usedCategories.clear();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int total = 0;
        for(String word : myMap.keySet()){
            //System.out.println(word);
            total += myMap.get(word).size();
        }
        return total;
    }
    
    public int totalWordsConsidered(){
        int totalWords = 0;
        for(String category : usedCategories){
            //System.out.println(category);
            if (! category.equals("number")){
                if(category.equals("name")){
                    totalWords += myMap.get("noun").size();
                } else {
                    totalWords += myMap.get(category).size();
                }
            }
        }
        return totalWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("The number of words replaced is "+usedVocabulary.size());
        System.out.println("The total substituation words number is : "+totalWordsInMap());
        System.out.println("The total of categories concerned is "+usedCategories.size()+" and the total words is "+totalWordsConsidered());
    }
}
