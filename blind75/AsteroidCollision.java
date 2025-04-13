package blind75;

import java.util.Stack;

public class AsteroidCollision {

  public int[] asteroidCollision(int[] asteroids) {
    // Use a stack to simulate the asteroids moving and colliding.
    Stack<Integer> stack = new Stack<>();

    // Iterate through each asteroid in the input array.
    for (int asteroid : asteroids) {
      // If the current asteroid is moving right (positive), simply push it onto the stack.
      // Asteroids moving in the same direction will not collide.
      if (asteroid > 0) {
        stack.push(asteroid);
      } else { // If the current asteroid is moving left (negative), handle collisions.
        // While the stack is not empty and the top asteroid on the stack is moving right,
        // there's a potential collision.
        while (!stack.isEmpty() && stack.peek() > 0) {
          // Get the size of the asteroid on the stack.
          int stackTop = stack.peek();

          // If the asteroid on the stack is larger, the current asteroid explodes.
          if (stackTop > -asteroid) {
            break; // Current asteroid destroyed, no need to push.
          } else if (stackTop == -asteroid) {
            // If they are the same size, both explode.
            stack.pop(); // Remove the asteroid from the stack.
            break;       // Current asteroid also destroyed, no need to push.
          } else {
            // If the current asteroid is larger, the asteroid on the stack explodes.
            stack.pop(); // Remove the smaller asteroid from the stack.
          }
        }
        // If the stack is empty or the top asteroid is moving left, the current asteroid survives.
        // This means there was no larger right-moving asteroid to stop it.
        if (stack.isEmpty() || stack.peek() < 0) {
          stack.push(asteroid);
        }
      }
    }

    // Convert the remaining asteroids in the stack to an array.
    int[] result = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) {
      result[i] = stack.pop();
    }
    return result;
  }
}
