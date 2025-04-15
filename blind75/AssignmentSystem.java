package blind75;

import java.util.*;

public class AssignmentSystem {

  private static class Operator implements Comparable<Operator> {
    String name;
    int currentAssignments;
    int limit;
    long lastAssignedTime;

    Operator(String name) {
      this.name = name;
      this.limit = 2; // default limit
      this.currentAssignments = 0;
      this.lastAssignedTime = Long.MIN_VALUE;
    }

    @Override
    public int compareTo(Operator o) {
      if (this.currentAssignments != o.currentAssignments) {
        return Integer.compare(this.currentAssignments, o.currentAssignments);
      }
      if (this.lastAssignedTime != o.lastAssignedTime) {
        return Long.compare(this.lastAssignedTime, o.lastAssignedTime);
      }
      return this.name.compareTo(o.name);  // tie-break by name
    }

    // Make a deep copy for simulation
    public Operator clone() {
      Operator copy = new Operator(this.name);
      copy.currentAssignments = this.currentAssignments;
      copy.lastAssignedTime = this.lastAssignedTime;
      copy.limit = this.limit;
      return copy;
    }
  }

  private final Map<String, Operator> operatorMap = new HashMap<>();
  private final PriorityQueue<Operator> heap = new PriorityQueue<>();
  private long globalClock = 0;

  public AssignmentSystem(List<String> operators) {
    for (String name : operators) {
      Operator op = new Operator(name);
      operatorMap.put(name, op);
      heap.offer(op);
    }
  }

  public void set_limit(String operatorName, int n) {
    if (operatorMap.containsKey(operatorName)) {
      Operator op = operatorMap.get(operatorName);
      heap.remove(op);
      op.limit = n;
      heap.offer(op);
    }
  }

  public void assign(int conversationId) {
    while (!heap.isEmpty()) {
      Operator op = heap.poll();
      if (op.currentAssignments < op.limit) {
        op.currentAssignments++;
        op.lastAssignedTime = globalClock++;
        heap.offer(op);
        System.out.println("Assigned conversation " + conversationId + " to " + op.name);
        return;
      }
      heap.offer(op);  // still put it back
    }
    System.out.println("No operator available for conversation " + conversationId);
  }

  public List<String> get_assignment_queue(int n) {
    List<String> result = new ArrayList<>();
    PriorityQueue<Operator> tempHeap = new PriorityQueue<>();
    Map<String, Operator> tempMap = new HashMap<>();

    for (Operator op : operatorMap.values()) {
      tempMap.put(op.name, op.clone());
      tempHeap.offer(tempMap.get(op.name));
    }

    long virtualTime = globalClock;
    while (result.size() < n && !tempHeap.isEmpty()) {
      Operator op = tempHeap.poll();
      if (op.currentAssignments < op.limit) {
        result.add(op.name);
        op.currentAssignments++;
        op.lastAssignedTime = virtualTime++;
        tempHeap.offer(op);
      } else {
        tempHeap.offer(op);  // Put back and continue
      }
    }
    return result;
  }

  public static void main(String[] args) {
    AssignmentSystem system = new AssignmentSystem(Arrays.asList("Alice", "Bob", "Charlie"));
    system.set_limit("Bob", 4);
    system.set_limit("Charlie", 3);

    System.out.println(system.get_assignment_queue(4));  // Expect: [Alice, Bob, Charlie, Alice]

    system.assign(101);  // Alice
    system.assign(102);  // Bob
    system.assign(103);  // Charlie
    system.assign(104);  // Alice

    System.out.println(system.get_assignment_queue(5));  // Expect: [Bob, Charlie, Bob, Charlie, Bob]
  }
}

//Exercise: Fair Conversation Assignment API
//
//  Context
//  Intercom builds a customer service product that allows businesses to talk to their end users.
//  In this programming exercise, we’d like you to build functionality that determines how conversations
//  should be assigned to the customer support staff (who we will refer to as “operators”).
//
//  Task: Load-balanced assignments
//  Implement a AssignmentSystem class that has the following API:
//  - Initializes with a set of available operators.
//  - set_limit(operator_name, n)
//  Sets an operator’s limit.
//  - assign(conversation_id)
//  Assigns a given conversation to the next available operator.
//  - get_assignment_queue(n)
//  Returns a queue of size n of the next possible assignments, respecting operators’ limits and balancing load fairly.
//
//  Requirements
//  1. Multiple Operators: Multiple operators are handling support conversations.
//  2. Load balanced: Assign conversations to the operator with the least current assignments.
//  Tie-Breaking Rule: If multiple operators have the same number of assignments, priority is given to
//  the operator who received their most recent assignment the earliest.
//  3. Assignment limits: Do not assign more than the specific limit of conversations.
//  The default limit is 2 assignments per operator.
//
//  Example
//  # operators = ["Alice", "Bob", "Charlie"]
//  # system = AssignmentSystem(operators)
//
//  # system.set_limit("Bob", 4)
//  # system.set_limit("Charlie", 3)
//
//  # I want to know who will receive next 4 conversations
//  # system.get_assignment_queue(4)
//  # ["Alice", "Bob", "Charlie", "Alice"]
//
//  # Make some assignments
//  # system.assign(101)  # Assigns to Alice
//  # system.assign(102)  # Assigns to Bob
//  # system.assign(103)  # Assigns to Charlie
//  # system.assign(104)  # Assigns to Alice
//
//  # Now that the above assignments have been made, I want to know who will receive next 5 conversations
//  # system.get_assignment_queue(5)
//  # ['Bob', 'Charlie', 'Bob', 'Charlie', 'Bob']
