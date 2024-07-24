import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
/**
 * @author Dhanush Venkataramu
 * Class for building indexes using BST.
 */
public class Index {
    /**
     * Instance flag variable to check need for conversion.
     */
    private boolean convertLower = false;
    /**
     * Instance method for building an index BST with  file, natural ordering.
     * @param fileName specifies the name of the file.
     * @return the index tree built.
     */
    public BST<Word> buildIndex(String fileName) {
        BST<Word> myTree = new BST<Word>();
        return buildTree(myTree, fileName);
    }
    /**
     * Instance Method to build an index BST with alternative ordering.
     * @param fileName specifies the name of file.
     * @param comparator specifies the alternative ordering comparator.
     * @return the index tree built.
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> myTree = new BST<Word>(comparator);
        if (comparator instanceof IgnoreCase) {
            convertLower = true;
        }
        return buildTree(myTree, fileName);
    }
    /**
     * Instance method to re-build an index tree with arrayList elements.
     * @param list specifies the list with elements.
     * @param comparator specifies the alternative ordering for BST.
     * @return the re-built index tree.
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        BST<Word> res = new BST<Word>(comparator);
        for (Word wr: list) {
            res.insert(wr);
        }
        return res;
    }
    /**
     * Instance method to sort in alphabetical order.
     * @param tree specifies the root of tree to be sorted.
     * @return array list with elements in sorted order.
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> res = populateList(tree);
        Collections.sort(res, new AlphaFreq());
        return res;
    }
    /**
     * Instance method to sort in frequency order.
     * @param tree specifies the root of tree to be sorted.
     * @return array list with elements in sorted order.
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> res = populateList(tree);
        Collections.sort(res, new Frequency());
        return res;
    }
    /**
     * Instance method to get list with highest frequency elements.
     * @param tree specifies the tree with elements.
     * @return array list with elements with highest frequency.
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> res = new ArrayList<Word>();
        ArrayList<Word> resList = populateList(tree);
        Collections.sort(resList, new Frequency());
        Word topEle = resList.remove(0);
        res.add(topEle);
        while (resList.size() != 0) {
            Word cur = resList.remove(0);
            if (cur.getFrequency() != topEle.getFrequency()) {
                break;
            }
            res.add(cur);
        }
        return res;
    }
    /**
     * Helper method to build tree based on natural or alternative ordering.
     * @param myTree specifies the root of the index tree to be built.
     * @param fileName specifies the name of file.
     * @return the index tree built.
     */
    private BST<Word> buildTree(BST<Word> myTree, String fileName) {
        if (fileName == null) {
            return myTree;
        }
        int numOfLines = 0;
        Scanner scanner = null;
        try {
            File file = new File(fileName);
            scanner = new Scanner(file, "latin1");
            while (scanner.hasNextLine()) {
                numOfLines++;
                String line = scanner.nextLine();
                for (String word : line.split("\\W")) {
                    if (!isWord(word)) {
                        continue;
                    }
                    if (convertLower) {
                        word = word.toLowerCase();
                    }
                    Word wr = new Word(word);
                    Word check = myTree.search(wr);
                    if (check != null) {
                        check.addToIndex(numOfLines);
                        check.setFrequency(check.getFrequency() + 1);
                        continue;
                    }
                    wr.addToIndex(numOfLines);
                    myTree.insert(wr);
                }
            }
            convertLower = false;
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return myTree;
    }
    /**
     * Helper method used to populate Array list with elements from
     * BST using iterator.
     * @param tree specifies the tree to be traversed.
     * @return the array list with all elements from tree.
     */
    private ArrayList<Word> populateList(BST<Word> tree) {
        ArrayList<Word> res = new ArrayList<Word>();
        Iterator<Word> iterator = tree.iterator();
        while (iterator.hasNext()) {
            res.add(iterator.next());
        }
        return res;
    }
    /**
     * Helper method to check if its a valid word.
     * @param text specifies the word to be checked.
     * @return true if valid word, false otherwise.
     */
    private boolean isWord(String text) {
        if (text == null) {
            return false;
        }
        return text.matches("[a-zA-Z]+");
    }

}
