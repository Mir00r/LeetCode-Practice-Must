package blind75.ArrayAndHashing;

import java.util.*;

//public class TopNUnique {
//    private final int n; // Maximum number of unique largest integers to keep
//    private final TreeSet<Integer> topNSet;
//
//    // Constructor
//    public TopNUnique(int n) {
//        if (n <= 0) {
//            throw new IllegalArgumentException("N must be greater than 0.");
//        }
//        this.n = n;
//        this.topNSet = new TreeSet<>();
//    }
//
//    // Process integers one by one
//    public void push(int val) {
//        // Add the value to the TreeSet
//        topNSet.add(val);
//
//        // If the set size exceeds N, remove the smallest element
//        if (topNSet.size() > n) {
//            topNSet.pollFirst(); // Remove the smallest element to maintain only N elements
//        }
//    }
//
//    // Return at most N unique largest values in sorted order
//    public Collection<Integer> top() {
//        return topNSet.descendingSet(); // Return in descending order
//    }
//
//    public static void main(String[] args) {
//        TopNUnique topNUnique = new TopNUnique(3);
//
//        // Push integers into the stream
//        topNUnique.push(5);
//        topNUnique.push(10);
//        topNUnique.push(3);
//        topNUnique.push(8);
//        topNUnique.push(15);
//
//        // Retrieve top 3 unique largest values
//        System.out.println(topNUnique.top()); // Output: [15, 10, 8]
//
//        topNUnique.push(12);
//        System.out.println(topNUnique.top()); // Output: [15, 12, 10]
//    }
//}
