package blind75;

import java.util.PriorityQueue;

public class SmallestNumberInInfiniteSet {

  PriorityQueue<Integer> pq;

  public SmallestNumberInInfiniteSet() {
    this.pq = new PriorityQueue<>();
    for (int i = 1; i <= 1000; i++) {
      pq.offer(i);
    }
  }

  public int popSmallest() {
    return pq.poll();
  }

  public void addBack(int num) {
    if (!pq.contains(num))
      pq.offer(num);
  }
}
