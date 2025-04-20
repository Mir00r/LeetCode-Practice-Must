package blind75;

import java.util.*;

public class AssignmentSystem {

  // Inner class representing each operator
  private static class Operator implements Comparable<Operator> {
    String name;                  // Operator identifier
    int currentAssignments;       // Number of active conversations
    int limit;                    // Maximum allowed conversations

    // Constructor initializes with default limit of 2
    Operator(String name) {
      this.name = name;
      this.limit = 2;             // Default assignment limit
      this.currentAssignments = 0; // Starts with no assignments
    }

    // Comparison method for priority queue ordering
    @Override
    public int compareTo(Operator o) {
      // Primary sort by current assignment count (lower first)
      if (this.currentAssignments != o.currentAssignments) {
        return Integer.compare(this.currentAssignments, o.currentAssignments);
      }
      // Secondary sort by name (alphabetical tie-breaker)
      return this.name.compareTo(o.name);
    }

    // Creates a deep copy for simulation purposes
    public Operator clone() {
      Operator copy = new Operator(this.name);
      copy.currentAssignments = this.currentAssignments;
      copy.limit = this.limit;
      return copy;
    }
  }


  // Maps operator names to their objects
  private final Map<String, Operator> operatorMap = new HashMap<>();

  // Priority queue for efficient assignment selection
  private final PriorityQueue<Operator> heap = new PriorityQueue<>();

  // Initializes the system with given operator names
  public AssignmentSystem(List<String> operators) {
    for (String name : operators) {
      Operator op = new Operator(name);    // Create new operator
      operatorMap.put(name, op);          // Add to name mapping
      heap.offer(op);                     // Add to priority queue
    }
  }

  // Updates an operator's conversation limit
  public void set_limit(String operatorName, int n) {
    if (operatorMap.containsKey(operatorName)) {
      Operator op = operatorMap.get(operatorName);
      heap.remove(op);      // Remove from queue to update
      op.limit = n;         // Update the limit
      heap.offer(op);       // Reinsert with new limit
    }
  }

  // Assigns a conversation to an available operator
  public void assign(int conversationId) {
    while (!heap.isEmpty()) {
      Operator op = heap.poll();  // Get most available operator
//      System.out.println("OperatorName -> "+op.name);

      // Check if operator can take more conversations
      if (op.currentAssignments < op.limit) {
        op.currentAssignments++;  // Increment assignment count
        heap.offer(op);           // Return to queue
        System.out.println("Assigned conversation " + conversationId + " to " + op.name);
        return;
      }

      // If operator is at limit, put back and continue
      heap.offer(op);
    }
    // If no operators available
    System.out.println("No operator available for conversation " + conversationId);
  }

  // Predicts next n assignment order without affecting actual state
  public List<String> get_assignment_queue(int n) {
    List<String> result = new ArrayList<>();
    // Temporary structures for simulation
    PriorityQueue<Operator> tempHeap = new PriorityQueue<>();
    Map<String, Operator> tempMap = new HashMap<>();

    // Clone current state for simulation
    for (Operator op : operatorMap.values()) {
      tempMap.put(op.name, op.clone());
      tempHeap.offer(tempMap.get(op.name));
    }

    // Simulate n assignments
    while (result.size() < n && !tempHeap.isEmpty()) {
      Operator op = tempHeap.poll();
      if (op.currentAssignments < op.limit) {
        result.add(op.name);         // Add to prediction list
        op.currentAssignments++;     // Increment in simulation
        tempHeap.offer(op);          // Return to simulated queue
      } else {
        tempHeap.offer(op);          // Put back if at limit
      }
    }
    return result;
  }

  // Example usage
  public static void main(String[] args) {
    // Initialize with 3 operators
    AssignmentSystem system = new AssignmentSystem(Arrays.asList("Alice", "Bob", "Charlie"));

    // Set custom limits
    system.set_limit("Bob", 4);
    system.set_limit("Charlie", 3);

    // Get next 4 expected assignments
    System.out.println(system.get_assignment_queue(4));

    // Make actual assignments
    system.assign(101);  // Alice
    system.assign(102);  // Bob
    system.assign(103);  // Charlie
    system.assign(104);  // Alice

    // Get next 5 expected assignments
    System.out.println(system.get_assignment_queue(5));
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

