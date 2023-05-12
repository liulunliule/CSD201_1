/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgraph;

import java.util.LinkedList;

/**
 *
 * @author ACER
 */
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
