package blind75;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate {

  public String predictPartyVictory(String senate) {
    int n = senate.length();

    Queue<Integer> radiantQueue = new LinkedList<>();
    Queue<Integer> direQueue = new LinkedList<>();

    // Initial setup: queue positions for each party
    for (int i = 0; i < n; i++) {
      if (senate.charAt(i) == 'R') {
        radiantQueue.offer(i);
      } else {
        direQueue.offer(i);
      }
    }

    // Simulate the rounds
    while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
      int radiantIndex = radiantQueue.poll();  // Next Radiant senator
      int direIndex = direQueue.poll();        // Next Dire senator

      // Whoever is earlier gets to ban the other
      if (radiantIndex < direIndex) {
        radiantQueue.offer(radiantIndex + n);  // Put back in line for next round
      } else {
        direQueue.offer(direIndex + n);        // Put back in line for next round
      }
    }

    // Whichever queue still has senators wins
    return radiantQueue.isEmpty() ? "Dire" : "Radiant";
  }

  public static void main(String[] args) {
    Dota2Senate solver = new Dota2Senate();

    System.out.println(solver.predictPartyVictory("RD"));     // Expected: Radiant
    System.out.println(solver.predictPartyVictory("RDD"));    // Expected: Dire
    System.out.println(solver.predictPartyVictory("RDRD"));   // Expected: Radiant
  }
}
