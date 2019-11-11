
/**
 * Implement CaesarCipher encryption algorithm + enhancements
 * 
 * @Omar
 * @15/11/2015
 */
import edu.duke.*;

public class CaesarCipher {
    
    String encrypt(String input, int key){
         //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            if(Character.isUpperCase(currChar)){
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            //Otherwise: do nothing
            } else {
                currChar = Character.toUpperCase(currChar);
                //Find the index of currChar in the alphabet (call it idx)
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
                //Otherwise: do nothing
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder phraseS = new StringBuilder(encrypt(input, key1));
        String provisoire = encrypt(input, key2);
        for(int k=0; k < input.length(); k++){
                if((k%2) == 1)
                    phraseS.setCharAt(k, provisoire.charAt(k));
            }
        return phraseS.toString();
    }
        
    
    //a test method
    void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23; //Enter a value of the key
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
}
