
import static java.lang.Math.max;
import static java.lang.Math.min;


import java.util.*;
/*
 * I am not the author of this Algorithm. I just implemented it into my program. Author:  Nirav Thaker.
 * ALL comments are mine
 * BK stands for Burkhard and Keller used to define metric spaces. We us it to define relationships between words. 
 * In This case their respective Levenshtein Distance.
 * 
 * 
 */

// 
// 
public class suggestion {

    public static class BKNode { // This first class will be used to build the BK tree

        final String name;
        final Map<Integer, BKNode> children = new HashMap<Integer, BKNode>(); // Hashmap for each word with its corresponding Integer (Levenshtein distance) is necessary in hashmap
        // int dont work in hashmaps

        public BKNode(String name) { // The selected word that needs placing in the tree
            this.name = name;
        }

        protected BKNode childAtDistance(int pos) {
            return children.get(pos);
        }

        private void addChild(int pos, BKNode child) {
            children.put(pos, child);
        }

        
        public List<String> search(String node, int maxDistance) {
            int time = 0;
            int distance = distance(this.name, node); //Calculates the distance between the two words
            List<String> matches = new LinkedList<String>(); // Creates a Linked list (only the address of the previous member of the list is saved)
            if (distance <= maxDistance) { //If the returned distance is less than the imposed, add to the Linked list
                matches.add(this.name);
            }
            if (children.size() == 0) {
                return matches;
            }
            int i = max(1, distance - maxDistance);
            for (; i <= distance + maxDistance; i++) {  // This is for searching further in the tree
                BKNode child = children.get(i);
                if (child == null) {
                    continue;
                }
                matches.addAll(child.search(node, maxDistance));
            }
            return matches;
        }
    }
    private BKNode root;

    public List<String> search(String q, int maxDist) {

        return root.search(q, maxDist);
    }
/*
 * This is the search function used to find suggestions
 * I decided to do 3 searches, with a levenshtein distance of 1, 2, 3.
 * Searches 2 and 3 would only be done is there are no suggestions from the previous query.
 * Anything above a distance of 3 is irrelevant.
 * This function call the search function in the BKNODE class, just above
 */
    public String search(String q) {
        List<String> list = root.search(q, 1);
        if (list.isEmpty()) {
            list = root.search(q, 2);
        }
        if (list.isEmpty()){
            System.out.println("Sorry, we have no Suggestions for this Word\n");
        }
        else if(!list.isEmpty()){
            System.out.print("Here are some suggestions:  ");
            System.out.println(Arrays.toString(list.toArray())+"\n");
        }
        return list.isEmpty() ? "" : list.iterator().next();
    }

    public void add(String node) { 
        if (node == null || node.isEmpty()) { //Used to check if the string is null or not.
            throw new IllegalArgumentException("word can't be null or empty.");
        }
        BKNode newNode = new BKNode(node); // Creates the instance of the 
        if (root == null) { // Creates the first Node
            root = newNode;
        }
        addInternal(root, newNode); // Creates a New node with the input string "node" internallly. All it means is that this word will not be a the top of the tree
    }

    private void addInternal(BKNode src, BKNode newNode) { // addinternal will compare two strings and determine where to place newNode. It uses the distance function to do so
        if (src.equals(newNode)) {
            return;
        }
        int distance = distance(src.name, newNode.name); // Check the distance function lower for details
        BKNode bkNode = src.childAtDistance(distance);
        if (bkNode == null) {
            src.addChild(distance, newNode);
        } else {
            addInternal(bkNode, newNode);
        }
    }

  /*
   * This function is an implementation of the Levenshtein Distance
   * It is a definition of a metric space between two strings
   * String Src will be compared to String tgt with respect to HOW many INSERTION, DELETION, PERMUTATION
   * necessary to go from String src to tgt (source to target)
   * At the bottom we use a minimum function to take to smallest Levenshtein Distance for these two words
   * 
   */
    public static int distance(String src, String tgt) {
        int d[][];
        if (src.length() == 0) {
            return tgt.length();
        }
        if (tgt.length() == 0) {
            return src.length();
        }
        d = new int[$][tgt.length() + 1];
        for (int i = 0; i <= src.length(); i++) { // Setting the values for the 2d int array 
            d[i][0] = i;
        }
        for (int j = 0; j <= tgt.length(); j++) {
            d[0][j] = j;
        }
        for (int i = 1; i <= src.length(); i++) {
            char sch = src.charAt(i - 1); // Using the Char at each position to check for posible permutations
            for (int j = 1; j <= tgt.length(); j++) {
                char tch = tgt.charAt(j - 1);
                int cost = sch == tch ? 0 : 1;
                d[i][j] = minimum(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }
        return d[src.length()][tgt.length()];

    }

    private static int minimum(int a, int b, int c) {
        return min(min(a, b), c);
    }
}
