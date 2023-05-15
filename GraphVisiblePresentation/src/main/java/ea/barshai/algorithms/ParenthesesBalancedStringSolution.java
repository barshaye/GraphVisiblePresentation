package ea.barshai.algorithms;

import java.util.Scanner;
import java.util.Stack;

class ParenthesesBalancedStringSolution {

  public static void main(String[] argh) {

    System.setIn(ParenthesesBalancedStringSolution.class.getClassLoader().getResourceAsStream("HakerRank_testCase27.txt"));
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String input = sc.next();
      Stack<Character> stack = new Stack<>();
      boolean isValid = true;

      for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);
        if (c == '(' || c == '{' || c == '[') {
          stack.push(c);
        } else if (c == ')' || c == '}' || c == ']') {
          if (stack.isEmpty()) {
            isValid = false;
            break;
          }
          char top = stack.pop();
          if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
            isValid = false;
            break;
          }
        }
      }

      if (!stack.isEmpty()) {
        isValid = false;
      }

      if (isValid) {
        System.out.println("true");
      } else {
        System.out.println("false");
      }
    }
  }
}
