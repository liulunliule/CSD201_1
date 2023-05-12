/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linklist;

/**
 *
 * @author 840G3
 */
public class Node {

    int info;
    Node next;
    Node prev;
    int size;

    Node() {
    }
    

    Node(int x) {
        info = x;
    }

    Node(int x, Node p) {
        info = x;
        next = p;
    }
    
    Node(int x, Node p, Node q) {
        info = x;
        next = p;
        prev = q;
    }
    
    Node(int x, Node p, Node q, int size) {
        info = x;
        next = p;
        prev = q;
        size = size++ ;
    }

}
