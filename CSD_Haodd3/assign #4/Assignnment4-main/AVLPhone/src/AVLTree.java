
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmnhn
 */
 class AVLTree implements I_List{
    Node root;
    Scanner sc = new Scanner (System.in);
    public AVLTree(){}
    public AVLTree(Node root) {
        root =null;
    }


    @Override
    public void insert(Phone x) {
        if (root == null) {
            root = new Node(x);
            return;
        }
        Node f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info.getId() == x.getId()) {
                System.out.println(" The key " + x + " already exists, no insertion");
                return;
            }
            inputPhone();
            f = p;
            if (p.info.getId() < x.getId()) {
                p = p.right;
            } else {
                p = p.left;
            }
            if (f.info.getId() < x.getId()) {
                f.right = new Node(x);
            } else {
                f.left = new Node(x);
            }

        }
    }

    @Override
    public void visit(Node p) {
        System.out.println(p.info.toString());
    }

    @Override
    public int height(Node p) {
         if (p == null) {
            return 0;
        } else {
            int lDepth = height(p.left);
            int rDepth = height(p.right);
            if (lDepth > rDepth) {
                return (lDepth + 1);
            } else {
                return (rDepth + 1);
            }
        }
    }

    @Override
    public int balanceFactor(Node p) {
        if (p == null) {
            return 0;
        }
        return height(p.left) - height(p.right);
    }

    @Override
    public void breadth() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            System.out.print(tempNode.info + " ");

            /*add left child to the queue */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*add right right child to the queue */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    @Override
    public void preOrder(Node p) {
        if(p==null) return;
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }
    public void preOrder() {
        preOrder(root);
    }

    @Override
    public void inOrder(Node p) {
        if(p==null) return;
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }
    
    public void inOrder(){
        inOrder(root);
    }

    @Override
    public void postOrder(Node p) {
        if(p==null) return;
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }
    
    public void postOrder(){
        postOrder(root);
    }
    @Override
    public Node search(Phone x) {
        return search(root, x);
    }
    
    Node search(Node p, Phone x) {
        if (p == null) {
            return null;
        } else if (x.getId() == p.info.getId()) {
            return p;
        } else if (x.getId() < p.info.getId()) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }
    

    @Override
    public void deletebyMerging(int x) {
        root = deleteByMergingRec(root, x);
    }
    Node deleteByMergingRec(Node root, int x){
        if(root == null){
            return null;
        }
        if(x < root.info.getId()){
            root.left = deleteByMergingRec(root.left, x);
        } else if (x > root.info.getId()){
            root.right = deleteByMergingRec(root.right, x);
        } else{
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            root.info.setId(minValue(root.right));
            root.right = deleteByMergingRec(root.right, root.info.getId());
        }
        return root;
    }
    int minValue(Node root) {
        int minv = root.info.getId();
        while (root.left != null) {
            minv = root.left.info.getId();
            root = root.left;
        }
        return minv;
    }

    @Override
    public void deletebyCopying(int x) {
        root = deleteByCopyingRec(root, x);
    }

    Node deleteByCopyingRec(Node root, int x){
        if(root == null){
            return null;
        }
        if(x < root.info.getId()){
            root.left = deleteByCopyingRec(root.right, x);
        }
        else if(x > root.info.getId()){
            root.right = deleteByCopyingRec(root.right, x);
        }
        else{
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            }
            Node temp = root.right;
            while(temp.left != null){
                temp = temp.left;
            }
            root.info.setId(temp.info.getId());
            root.right = deleteByCopyingRec(root.right, root.info.getId());
        }
        return root;
    }
    @Override
    public void deleteNode(int x) {
        root = deleteNodeF(root, x);
    }

    Node deleteNodeF(Node root, int x){
        if(root == null){
            return null;
        }
        
        if(x < root.info.getId()){
            root.left = deleteNodeF(root.left, x);
        }
        else if(x > root.info.getId()){
            root.right = deleteNodeF(root.right, x);
        }
        else{
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            }
            Node temp = root.right;
            while(temp.left != null){
                temp = temp.left;
            }
            root.info.setId(temp.info.getId());
            root.right = deleteNodeF(root.right, temp.info.getId());
        }
    return root;
    }
    
    @Override
    public Phone find_Min_price() {
        Node nodeL = root;
         int min = 0;
         int minPriceL = root.info.getPrice();
         while (nodeL.left != null) {
             if (nodeL.info.getPrice() < minPriceL) {
                 minPriceL = nodeL.info.getPrice();
             }
             nodeL = nodeL.left;
         }

         int minPriceR = root.info.getPrice();
         Node nodeR = root;
         while (nodeR.right != null) {
             if (nodeR.info.getPrice() < minPriceR) {
                 minPriceR = nodeR.info.getPrice();
             }
             nodeR = nodeR.right;
         }
         if (minPriceL < minPriceR) {
             min = minPriceL;
             Node node1 = root;
             while (node1.left != null) {
                 if (node1.info.getPrice() == min) {
                     return node1.info;
                 }
             }
         }

         if (minPriceL >= minPriceR) {
             min = minPriceR;
             Node node2 = root;
             while (node2.right != null) {
                 if (node2.info.getPrice() == min) {
                     return node2.info;
                 }
             }
         }
         return null;
    }

    @Override
    public Phone find_Newest_Phone() {
        Node nodeL = root;
         int newest = 0;
         int newL = root.info.getYear();
         while (nodeL.left != null) {
             if (nodeL.info.getYear() > newL) {
                 newL = nodeL.info.getYear();
             }
             nodeL = nodeL.left;
         }

         int newR = root.info.getYear();
         Node nodeR = root;
         while (nodeR.right != null) {
             if (nodeR.info.getYear() < newR) {
                 newR = nodeR.info.getYear();
             }
             nodeR = nodeR.right;
         }
         if (newL > newR) {
             newest = newL;
             Node node1 = root;
             while (node1.left != null) {
                 if (node1.info.getYear() == newest) {
                     return node1.info;
                 }
             }
         }

         if (newR >= newL) {
             newest = newR;
             Node node2 = root;
             while (node2.right != null) {
                 if (node2.info.getYear() == newest) {
                     return node2.info;
                 }
             }
         }
         return null;
     
    }
//************************************************
    @Override
    public Phone find_Max_Value() {
         Node nodeL = root;
         int max = 0;
         int valueL = root.info.getValue();
         while (nodeL.left != null) {
             if (nodeL.info.getValue()> valueL) {
                 valueL = nodeL.info.getValue();
             }
             nodeL = nodeL.left;
         }

         int valueR = root.info.getValue();
         Node nodeR = root;
         while (nodeR.right != null) {
             if (nodeR.info.getValue() > valueR) {
                 valueR = nodeR.info.getValue();
             }
             nodeR = nodeR.right;
         }
         if (valueL > valueR) {
             max = valueL;
             Node node1 = root;
             while (node1.left != null) {
                 if (node1.info.getValue() == max) {
                     return node1.info;
                 }
             }
         }

         if (valueL <= valueR) {
             max = valueR;
             Node node2 = root;
             while (node2.right != null) {
                 if (node2.info.getValue() == max) {
                     return node2.info;
                 }
             }
         }
         return null;
    }
//*******************************************************
    @Override
    public Phone inputPhone() {
         int id, price, year, amount;
         String name;

         System.out.print("Input Phone's ID: ");
         id = Integer.parseInt(sc.nextLine());

         System.out.print("Input Phone's name: ");
         name = sc.nextLine();
         
         System.out.print("Input Phone's price: ");
         price = Integer.parseInt(sc.nextLine());
         
         System.out.print("Input Phone's amount: ");
         amount = Integer.parseInt(sc.nextLine());
         
         System.out.print("Input Phone's (production) year: ");
         year = Integer.parseInt(sc.nextLine());
         Phone phone = new Phone(id, price, year, amount, name);
         return phone;
    }
 }

    