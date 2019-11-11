
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int k=0; k < myWords.length; k++){
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        for (int k=0; k < myWords.length; k++){
            if(! myWords[k].equals(other.wordAt(k))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        //String strToShift = "";
        for (int k=0; k < myWords.length-1; k++){
            //System.out.println(out.wordAt(k));
            //String strToShift = myWords[k+1];
            //String strshifted = myWords[k];
            out.myWords[k] = out.myWords[k].replace(myWords[k], myWords[k+1]);
            //System.out.println(out.wordAt(k));
            //out.myWords[k]=strToShift;
        }
        //String strToShift = word;
        out.myWords[this.myWords.length-1] = word;
        //System.out.println(out.myWords[this.myWords.length-1]);
        //out.myWords[myWords.length-1]=strToShift;
        //System.out.println(this);
        return out;
    }

    public int hashCode() {
        String sWg = this.toString();
        myHash = sWg.hashCode();
        return myHash;
    }
}