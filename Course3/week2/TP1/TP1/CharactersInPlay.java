
/**
 * Write a description of CharactersInPlay here.
 * 
 * @Orached
 * @22/11/2015
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> myWords = new ArrayList<String>();
    private ArrayList<Integer> myFreqs = new ArrayList<Integer>();
    
    public void update(String person){
        if (! myWords.contains(person)){
            myWords.add(person);
            myFreqs.add(1);
        }else {
            int idx = myWords.indexOf(person);
            int freq = myFreqs.get(idx);
            myFreqs.set(idx, freq+1);
        }
    }
    
    public void findAllCharacters(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        int idx = -1;
        
        for(String s : resource.lines()){
            //s = s.toLowerCase();
            idx = s.indexOf(".");
            if(idx != -1)
                update(s.substring(0, idx));
        }
    }
    //Tester
    public void tester(){
        findAllCharacters();
        for(int i=0; i < myWords.size(); i++){
            if(myFreqs.get(i) > 1)
                System.out.println("Character "+i+" is "+myWords.get(i)+" it appears "+myFreqs.get(i)+" times");;
        }
        charactersWithNumParts(10, 15);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("Characters that appears more than "+num1+" and less than "+num2);
        for(int i=0; i < myWords.size(); i++){
            if(myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2){
                System.out.println(myWords.get(i)+"\t"+myFreqs.get(i)+" times");
            }
        }
    }
}
