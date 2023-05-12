package Assignment;

import java.util.LinkedList;

public class MyStack {
    
    LinkedList<Integer> head;

    public MyStack() {
        head = new LinkedList<>();
    }

    public void push(int x) {
        head.addFirst(x);
    }

    public boolean isEmpty() {
        return head.isEmpty();
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return head.removeFirst();
    }

    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return head.getFirst();
    }
}
