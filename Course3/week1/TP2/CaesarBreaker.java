
/**
 * Algorithm to break a encrypted message with CaesarCipher
 * 
 * @Omar 
 * @16/11/2015
 */
import edu.duke.*;

public class CaesarBreaker {
    //countLetters method
    int[] countLetters(String message){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counter = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        for(int i=0; i < message.length(); i++){
            char curChar = Character.toUpperCase(message.charAt(i));
            for(int k=0; k < alphabet.length(); k++){
                if(curChar== alphabet.charAt(k))
                    counter[k]++;
            }
            
            //Another function to do (with indexOf(char) method
            //int dex = alphabet.indexOf(curChar);
            //if (dex != -1)
            //    counter[dex] ++;
        }
        
        return counter;
    }
    
    //maxIndex method
    int maxIndex(int[] counter){
       int maxValue = 0;
       int maxIdx = 0;
       for(int i=0; i < counter.length; i++){
           if(maxValue < counter[i]){
               maxValue = counter[i];
               maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    //decrypt method
    String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        //as 4 is the index of "e". As "e" is most common character in an ordinary english text
        int dKey = maxDex - 4;
        if (maxDex < 4)
            dKey = 26 - (4-maxDex);
        return cc.encrypt(encrypted, 26 - dKey);
    }
    
    //Tester method
    void testDecrypt(){
        FileResource resource = new FileResource();
        String message = resource.asString();
        String decrypted = decrypt(message);
        System.out.println(decrypted);
    }
    
    //Return half of string from the starting position
    String halfOfString(String message, int start){
        StringBuilder halfMessage = new StringBuilder();
        for(int i = start; i < message.length(); i += 2){
            halfMessage = halfMessage.append(message.charAt(i));
        }
        return halfMessage.toString();
    }
    
    //Get the key of a message "s"
    int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dKey = maxDex - 4;
        if (maxDex < 4)
            dKey = 26 - (4-maxDex);
        return dKey;
    }
    
    String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String msg1 = halfOfString(encrypted, 0);
        String msg2 = halfOfString(encrypted, 1);
        int dKey1 = getKey(msg1);
        int dKey2 = getKey(msg2);
        System.out.println("The first key is: " +dKey1+ "\tThe scond one is: " +dKey2);
        return cc.encryptTwoKeys(encrypted, dKey1, dKey2);
    }
    
    //Tester method
    void test2kDecrypt(){
        FileResource resource = new FileResource();
        String message = resource.asString();
        //String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }
}
