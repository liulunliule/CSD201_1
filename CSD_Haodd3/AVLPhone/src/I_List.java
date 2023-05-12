/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmnhn
 */
public interface I_List {
    void insert(Phone x);

    void visit(Node p);

    int height(Node p);

    int balanceFactor(Node p);

    void breadth();

    void preOrder(Node p);

    void inOrder(Node p);

    void postOrder(Node p);

    Node search(Phone x);

    void deletebyMerging(int x);

    void deletebyCopying(int x);

    void deleteNode(int x);
}
