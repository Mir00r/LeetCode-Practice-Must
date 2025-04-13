package blind75;

import java.util.PriorityQueue;

public class TotalCostToHireKWorkers {

  static class Worker {
    int cost;
    int index;
    int side; // 0 for left, 1 for right

    public Worker(int cost, int index, int side) {
      this.cost = cost;
      this.index = index;
      this.side = side;
    }
  }

  public long totalCost(int[] costs, int k, int candidates) {
    PriorityQueue<Worker> heap = new PriorityQueue<>(
      (a, b) -> a.cost == b.cost
        ? Integer.compare(a.index, b.index)
        : Integer.compare(a.cost, b.cost)
    );

    int left = 0, right = costs.length - 1;

    // Fill heap with initial candidates
    for (int i = 0; i < candidates && left <= right; i++, left++) {
      heap.offer(new Worker(costs[left], left, 0));  // From left
    }
    for (int i = 0; i < candidates && left <= right; i++, right--) {
      heap.offer(new Worker(costs[right], right, 1)); // From right
    }

    long totalCost = 0;

    // Hire k workers
    for (int hired = 0; hired < k; hired++) {
      Worker best = heap.poll();  // Choose cheapest available worker
      totalCost += best.cost;

      // Refill from the same side the worker came from
      if (left <= right) {
        if (best.side == 0) {
          heap.offer(new Worker(costs[left], left, 0));
          left++;
        } else {
          heap.offer(new Worker(costs[right], right, 1));
          right--;
        }
      }
    }

    return totalCost;
  }

  public static void main(String[] args) {
    TotalCostToHireKWorkers solver = new TotalCostToHireKWorkers();

    int[] costs1 = {17, 12, 10, 2, 7, 2, 11, 20, 8};
    System.out.println("Total Hiring Cost: " + solver.totalCost(costs1, 3, 4));  // Expected: 11

    int[] costs2 = {1, 2, 4, 1};
    System.out.println("Total Hiring Cost: " + solver.totalCost(costs2, 3, 3));  // Expected: 4
  }
}
