
/**
 * Tester class for OO CaeserCipher program.
 * 
 * @Orached 
 * @21/11/2015
 */
import edu.duke.*;

public class TestCaesarCipher {
    //countWordLength method
    public int[] countWordLength(FileResource resource){
        int stringLength = 0;
        int[] counts = new int[31];
        for(String s : resource.words()){
            stringLength = s.length();
            if(stringLength != 0){
                if(!Character.isLetter(s.charAt(0))){
                    stringLength --;
                }
                if(!Character.isLetter(s.charAt(s.length()-1))){
                    stringLength --;
                }
                if(stringLength>=counts.length-1){
                    counts[counts.length-1] += 1;
                } else counts[s.length()] += 1;
            }
        }
        return counts;
    }
    //TestWordlength
    void TestWordlength(){
        FileResource fr = new FileResource();
        int[] count = countWordLength(fr);
        for(int i=0; i < count.length ; i++)
        System.out.println("Number of Words of "+i+" characters is "+count[i]);
    }
    //maxIndex method
    public int maxIndex(int[] values){
        int indexPosition = 0;
        int provisoire = values[0];
        for(int i=0; i < values.length; i++){
            if(values[i]>provisoire){
                provisoire = values[i];
                indexPosition = i;
            }
        }
        return indexPosition;
    }
    //countLetters method
    public int[] countLetters(String phrase){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] letterOccurences = new int[alphabet.length()];
        for(int i=0; i<phrase.length(); i++){
            char provisoireC = phrase.charAt(i);
            if(Character.isLetter(provisoireC)){
                for(int k=0; k<alphabet.length(); k++){
                    if(Character.toUpperCase(provisoireC) == alphabet.charAt(k))
                    letterOccurences[k]++;
                }
            }
        }
        return letterOccurences;
    }
    //SimpleTests method
    public void SimpleTests(){
        FileResource f = new FileResource();
        CaesarCipher cc = new CaesarCipher(15);
        String contents = f.asString();
        String encrMsg = cc.encrypt(contents);
        String decrMsg = breakCaesarCipher(encrMsg);
        System.out.println("The encrypted message is "+encrMsg);
        System.out.println("The decrypted message is "+cc.decrypt(encrMsg));
        System.out.println("The message decrypted automatically is "+decrMsg);
    }
    //breakCaeserCipher method
    public String breakCaesarCipher (String input){
        int[] letterOccurences = countLetters(input);
        int indexE = maxIndex(letterOccurences);
        int dKey = indexE - 4;
        if (indexE < 4)
            dKey = 26 - (4-indexE);
        CaesarCipher cc = new CaesarCipher(dKey);
        return cc.decrypt(input);
    }
}
