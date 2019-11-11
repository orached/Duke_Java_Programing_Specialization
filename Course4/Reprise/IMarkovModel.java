
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @Orached 
 * @24062017
 */



public interface IMarkovModel {
    public void setTraining(String text);
    
    public String getRandomText(int numChars);
    
    public void setRandom(int seed);
    
}
