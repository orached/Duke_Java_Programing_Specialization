
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString());
        
        for(int k=0; k < 1; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
        
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        //Adding seed
        int seed = 792;
        /*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
        */
        EfficientMarkovModel mETwo = new EfficientMarkovModel(6);
        runModel(mETwo, st, size, seed);
        
        //mETwo.buildMap();
        
        mETwo.printHashMapInfo();

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
        /*
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        int seed = 42;
        */
        
       /*
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 50;
        
        //Adding seed
        int seed = 615;
        
        EfficientMarkovModel mTwo = new EfficientMarkovModel(5);
        
        runModel(mTwo, st, size, seed);
        mTwo.printHashMapInfo();
        */
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        EfficientMarkovModel eMarkovM = new EfficientMarkovModel(6);
        eMarkovM.setTraining(st);
        eMarkovM.setRandom(792);
        eMarkovM.printHashMapInfo();
       }
   

   public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        System.out.println("Start time MarkovClassic: "+System.nanoTime());
        
        /*
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        System.out.println("Time after running MarkovModel :"+System.nanoTime());
        */
       
        System.out.println("Start time MarkovEfficient: "+System.nanoTime());
        
        EfficientMarkovModel mETwo = new EfficientMarkovModel(2);
        runModel(mETwo, st, size, seed);
        System.out.println("Time after running EfficientMarkovModel :"+System.nanoTime());
       }
}
