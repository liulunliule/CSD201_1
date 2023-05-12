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

    Phone inputPhone();

    void insert(Phone x);

    void visit(Node p);

    int height(Node p);

    int balanceFactor(Node p);

    void breadth();

    void preOrder(Node p);

    void inOrder(Node p);

    void postOrder(Node p);

    Node search(Phone x);
    
    Phone find_Min_price();
    
    Phone find_Newest_Phone(); // compare year
    
    Phone find_Max_Value();

    void deletebyMerging(int x);

    void deletebyCopying(int x);

    void deleteNode(int x);
}
