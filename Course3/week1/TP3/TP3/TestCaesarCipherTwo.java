
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @Orached 
 * @21/11/2015
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    //halfOfString method
    public String halfOfString(String message, int start){
        StringBuilder br = new StringBuilder();
        for(int i=start; i < message.length(); i+=2){
            br.append(message.substring(i, i+1));
        }
        return br.toString();
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
    //Tester
    public void simpleTests(){
        FileResource f = new FileResource();
        CaesarCipherTwo cc = new CaesarCipherTwo(14, 24);
        String contents = f.asString();
        String encrMsg = cc.encrypt(contents);
        String decrMsg = cc.decrypt(encrMsg);
        System.out.println("The encrypted message is "+encrMsg);
        System.out.println("The decrypted message is "+decrMsg);
        System.out.println("The message decrypted automatically is "+breakCaesarCipherTwo(contents));
    }
    //breakCaesarCipher method
    public String breakCaesarCipherTwo(String input){
        String halfInput1 = halfOfString(input, 0);
        String halfInput2 = halfOfString(input, 1);
        int[] letterOccurences1 = countLetters(halfInput1);
        int[] letterOccurences2 = countLetters(halfInput2);
        int indexE1 = maxIndex(letterOccurences1);
        int indexE2 = maxIndex(letterOccurences2);
        int dKey1 = indexE1 - 4;
        int dKey2 = indexE2 - 4;
        if (indexE1 < 4)
           dKey1 = 26 - (4-indexE1);
        if (indexE2 < 4)
           dKey2 = 26 - (4-indexE2);
        CaesarCipherTwo cc = new CaesarCipherTwo(dKey1, dKey2);
        return cc.decrypt(input);
    }
}
