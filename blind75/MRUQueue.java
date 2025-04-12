package blind75;

import java.util.Set;
import java.util.TreeSet;

import java.util.*;

public class MRUQueue {
  private List<LinkedList<Integer>> blocks;
  private int blockSize;

  public MRUQueue(int n) {
    blockSize = (int) Math.sqrt(n) + 1;
    blocks = new ArrayList<>();

    for (int i = 0; i < blockSize; i++) {
      blocks.add(new LinkedList<>());
    }

    // Fill initial queue
    for (int i = 1; i <= n; i++) {
      blocks.get((i - 1) / blockSize).add(i);
    }
  }

  public int fetch(int k) {
    int count = 0;

    // Locate which block contains the k-th element
    for (int i = 0; i < blocks.size(); i++) {
      LinkedList<Integer> block = blocks.get(i);
      if (count + block.size() >= k) {
        int indexInBlock = k - count - 1;
        int val = block.remove(indexInBlock);

        // Move to the end of the last block
        blocks.get(blocks.size() - 1).addLast(val);

        // Rebalance blocks if needed
        rebalance(i);
        return val;
      }
      count += block.size();
    }

    return -1; // Shouldn't happen due to constraints
  }

  // Rebalances blocks after movement
  private void rebalance(int startBlock) {
    for (int i = startBlock; i < blocks.size() - 1; i++) {
      LinkedList<Integer> current = blocks.get(i);
      LinkedList<Integer> next = blocks.get(i + 1);

      // If current block is short, steal from next
      while (current.size() < blockSize && !next.isEmpty()) {
        current.addLast(next.removeFirst());
      }
    }
  }

  public static void main(String[] args) {
    MRUQueue mRUQueue = new MRUQueue(8); // [1,2,3,4,5,6,7,8]
    System.out.println(mRUQueue.fetch(3)); // 3
    System.out.println(mRUQueue.fetch(5)); // 6
    System.out.println(mRUQueue.fetch(2)); // 2
    System.out.println(mRUQueue.fetch(8)); // 2
  }
}
