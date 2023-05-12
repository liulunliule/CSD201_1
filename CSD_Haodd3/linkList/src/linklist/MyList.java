/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linklist;

import java.util.Scanner;

/**
 *
 * @author 840G3
 */
public class MyList {

    Node head, tail;
    
    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }
    
    // SZ
    int Size() {
        Node p = head;
        int count = 0;
        while (p != null) {
            p = p.next;
            count++;
        }
        return count;
    }

    void add(int x) {
        if (isEmpty()) {
            head = tail = new Node(x, null);
        } else {
            Node q = new Node(x, null);
            tail.next = q;
            tail = q;
        }
    }

    // 1
    
        void add_First0(int x) {
        Node q = new Node(x, head);

    }
        //have tail
        
    void add_First(int x) {
        if (isEmpty()) {
            head = tail = new Node(x, null);
        } else {
            head = new Node(x, head);
//          Node q= new Node(x,null);
//          q.next =head;
//          head=q;
        }
    }


        // have prev
    void add_First2(int x) {
        Node p = new Node(x, null,null);
        if(head ==null){
            head=p;
        } else {
            p.next=head;
            head.prev=p;
            head=p;
        }

    }
    
        //have size
    int size;
    void add_First3(int x) {
         
        Node p = new Node(x);
        if(Size()==0){
            head=p;
        } else {
            p.next=head;
            head=p;
        }
        size ++;
    }

    // 2
    void add_Last(int x) {
//      add(x);
        if (isEmpty()) {
            head = tail = new Node(x, null);
        } else {
            Node q = new Node(x, null);
            tail.next = q;
            tail = q;
        }
    }

    

    //3
    void add_index(int x, int index) {
        int count = 1;
        Node p = head;
        Node q = new Node(x, null);
        if (isEmpty()) {
            head = tail = new Node(x, null);
        } else if (index == 1) {
            add_First(x);
        } else if (index > Size()) {
            add_Last(x);
        } else {
            while (p != null) {
                if (count == index - 1) {
                    q.next = p.next;
                    p.next = q;
                }
                p = p.next;
                count++;
            }
        }
    }

//   4
    void delete_First() {
        head = head.next;
    }

    // 5 có tail
    void delete_Last() {
        Node p = head;
        int count = 1;
        while (p.next != null) {
            if (count == Size() - 1) {
                p.next = null;
                tail = p.next;
            } else {
                p = p.next;
                count++;
            }
        }

    }
    //5 delete_Last dùng size

    //6
    void delete_Pos(int index) {
        int count = 1;
        Node p = head;
        if (index == 1) {
            delete_First();
        } else if (index > Size()) {
            System.out.println("doesn't exist");
        } else {
            while (p != null) {
                if (count == index - 1) {
                    p.next = p.next.next;
                }
                p = p.next;
                count++;
            }
        }

    }

    //6*
    void delete_Node(int num) {
        Node p = head;
        while (p.next != null) {
            if (p.next.info == num) {
                p.next = p.next.next;
            }
            p = p.next;

        }

    }

    //7
    int Search(int x) {
        int count = 0;
        Node q = head;
        while (q != null) {
            if (q.info == x) {
                count++;
            }
            q = q.next;
        }
        return count;
    }

    //8
    void traverse() {
        Node p = head;
        while (p != null) {
            System.out.print("  " + p.info);
            p = p.next;
        }
        System.out.println();
    }
    //9

    void update_1_node(int x, int index) {
        int count = 1;
        Node p = head;
        while (p != null) {
            if (count == index) {
                p.info = x;
            }
            p = p.next;
            count++;
        }
    }

    // 9* (add before)
    void update_2_node(int numInsert, int numCorrect) {
        Node p = head;
        while (p.next != null) {
            if (p.next.info == numCorrect) {
                Node q = new Node(numInsert, null);
                q.next = p.next;
                p.next = q;
                p = p.next;
            }
            p = p.next;
        }
    }

    public static void main(String[] args) {
        MyList tmp = new MyList();
        Scanner sc = new Scanner(System.in);

        System.out.println(tmp.Size());
        tmp.add(0);
        tmp.add(3);
        tmp.add(2);
        tmp.add(3);
        tmp.add(4);
        tmp.add(3);
//        System.out.print("Enter the first number: ");
//        tmp.add_First(sc.nextInt());
//        tmp.traverse();
//        System.out.print("Enter the last number: ");
//        tmp.add_Last(sc.nextInt());
//        tmp.traverse();
//        System.out.print("Enter the info and index want to insert : ");
//        tmp.add_index(sc.nextInt(), sc.nextInt());
//        tmp.traverse();
//        System.out.println("Delete first");
//        tmp.delete_First();
//        tmp.traverse();
//        System.out.println("Delete last");
//        tmp.delete_Last();
//        tmp.traverse();
//        System.out.print("Enter index to Delete :");
//        tmp.delete_Node(sc.nextInt());
//        tmp.traverse();
//        System.out.println("Enter the info and index to update");
//        tmp.update_1_node(sc.nextInt(), sc.nextInt());
//        tmp.traverse();
//        System.out.print("Enter number that u want to insert after and the numbe that u want to insert to: ");
//        tmp.update_2_node(sc.nextInt(), sc.nextInt());
        tmp.delete_Node(3);
        tmp.traverse();
    }
}
