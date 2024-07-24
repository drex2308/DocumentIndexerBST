import java.util.HashSet;
import java.util.Set;
/**
 * @author Dhanush Venkataramu
 * Class for words.
 */
public class Word implements Comparable<Word> {
    /**
     * Instance variable to store data of word.
     */
    private String word;
    /**
     * Instance variable to store the line indexes of word.
     * Used HashSet from classes implementing Set interface.
     */
    private Set<Integer> index = new HashSet<Integer>();
    /**
     * Instance variable to store frequency of word.
     */
    private int frequency;
    /**
     * Constructor for Word class.
     * @param s specifies string data for the word.
     */
    public Word(String s) {
        word = s;
        frequency = 1;
    }
    /**
     * Instance method to get the data of word.
     * @return string data of the word.
     */
    public String getWord() {
        return word;
    }
    /**
     * Instance method to get the frequency of the word.
     * @return frequency of the word.
     */
    public int getFrequency() {
        return frequency;
    }
    /**
     * Instance method to get the line indexes for a word.
     * @return the set of line indexes.
     * Protecting private data by returning a deep copy of the set.
     */
    public Set<Integer> getIndex() {
        Set<Integer> res = new HashSet<Integer>();
        for (Integer i : index) {
            Integer x = i.intValue();
            res.add(x);
        }
        return  res;
    }
    /**
     * Instance method to set the data of the word.
     * @param newWord specifies the new string data.
     */
    public void setWord(String newWord) {
        word = newWord;
    }
    /**
     * Instance method to set the frequency of the word.
     * @param freq specifies the new frequency for word.
     */
    public void setFrequency(int freq) {
        frequency = freq;
    }
    /**
     * Instance method to add to set of line indexes.
     * @param i new line index to be added.
     */
    public void addToIndex(Integer i) {
        index.add(i);
    }
    /**
     * Implementation of natural ordering for instance of Word.
     */
    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.word);
    }
    /**
     * Implementation of toString() method for a word, in specified format.
     */
    @Override
    public String toString() {
        return new StringBuilder().append(word)
                                  .append(" ")
                                  .append(frequency)
                                  .append(" ")
                                  .append(index.toString())
                                  .toString();
    }
}
