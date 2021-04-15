import java.util.Stack;

public class Demo2 {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    Demo2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int n) {
        stack1.push(n);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) return -1;
        return stack2.pop();
    }

    public static void main(String[] args) {
        Demo2 d = new Demo2();
        d.push(1);
        d.push(2);
        d.push(3);
        System.out.println(d.pop());
        System.out.println(d.pop());
        d.push(4);
    }
}
