import java.util.ArrayDeque;
import java.util.Deque;

public class Demo4 {

    public String print(String s) {
        char[] c = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '0') {
                while (stack.size() >= 2) {
                    char cc = stack.pop();
                    if (stack.peek() != '0') {
                        stack.push(cc);
                        break;
                    }
                }
            }
            stack.push(c[i]);
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollLast());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Demo4().print("010001101"));
        System.out.println(new Demo4().print("010000101"));
    }
}
