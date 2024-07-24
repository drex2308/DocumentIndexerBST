import java.util.Comparator;
/**
 * @author Dhanush Venkataramu
 * Comparator class for frequency.
 */
public class Frequency implements Comparator<Word> {
    /**
     * Implementation of compare() method.
     * Comparison according to descending frequency.
     */
    @Override
    public int compare(Word o1, Word o2) {
        return Integer.compare(o2.getFrequency(), o1.getFrequency());
    }

}
