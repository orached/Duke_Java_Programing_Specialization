
/**
 * Write a description of class MarkovRunner here.
 * 
 * @Orached 
 * @30/06/2017
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        //for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        //} 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWord markovWord = new MarkovWord(3); 
        //runModel(markovWord, st, 50, 621);
        MarkovWord markovWord = new MarkovWord(5);
        runModel(markovWord, st, 50, 844);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    public void testHashMap(){
        //String st = ("this is a test yes this is really a test");
        //String st = ("this is a test yes this is really a test yes a test this is wow");
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        EfficientMarkovWord eMarkovWord = new EfficientMarkovWord(2);
        eMarkovWord.setTraining(st);
        eMarkovWord.setRandom(65);
        eMarkovWord.printHashMapInfo();
        //runModel(eMarkovWord, st, 50, 42);
    }
    
    public void testGetFollows(){
        String[] myTestText = {"this","is","a","test","this","is","a","test","this","is","a","test","of","words"};
        String st = ("this is a test this is a test this is a test of words");
        WordGram wg1 = new WordGram(myTestText, 0, 4);
        System.out.println(wg1);
        WordGram wg2 = new WordGram(myTestText, 1, 4);
        System.out.println(wg2);
        WordGram wg3 = new WordGram(myTestText, 10, 4);
        System.out.println(wg3);
        //MarkovWord markovWord = new MarkovWord(4);
        EfficientMarkovWord eMarkovWord = new EfficientMarkovWord(4);
        eMarkovWord.setTraining(st);
        eMarkovWord.setRandom(643);
        //eMarkovWord.testIndexOf();
        System.out.println(eMarkovWord.getFollows(wg1));
        System.out.println(eMarkovWord.getFollows(wg2));
        System.out.println(eMarkovWord.getFollows(wg3));
    }
    
    
}
