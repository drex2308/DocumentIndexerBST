import java.util.Comparator;
/**
 * @author Dhanush Venkataramu
 * Comparator class for alphafreq.
 */
public class AlphaFreq implements Comparator<Word> {
    /**
     * Implementation of compare() method.
     * Compares the alphabetical order in ascending first, if a
     * comparison gives equal, then compare according to
     * ascending frequency.
     */
    @Override
    public int compare(Word o1, Word o2) {
        int alphaResult = o1.getWord().compareTo(o2.getWord());
        if (alphaResult == 0) {
            return Integer.compare(o1.getFrequency(), o2.getFrequency());
        }
        return alphaResult;
    }
}
