import java.util.Comparator;
/**
 * @author Dhanush Venkataramu
 * Comparator class for ignorecase.
 */
public class IgnoreCase implements Comparator<Word> {
    /**
     * Implementation of compare() method.
     * Comparison according to case insensitive string comparison.
     */
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord().compareToIgnoreCase(o2.getWord());
    }

}
