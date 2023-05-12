//
//
//
//public class Menu {
//
//    public static void main(String[] args) {
//        AVLTree list = new AVLTree();
//        Node node = new Node();
//        Phone phone ;
//        phone = list.inputPhone();
//        list.insert(phone);
//        phone = list.find_Max_Value();
//        //list.find_Max_Value();
//        System.out.println(phone.toString());
//        //list.breadth();
//    }
//}


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author f1nn
 */
public class Menu {
    public static void main(String[] args) {
        int choice = 0;
        int value = 0;
        Node x = new Node();
        Phone p = new Phone();
        AVLTree a = new AVLTree();
        Scanner sc = new Scanner(System.in);
        do{
        System.out.println("Assignment #4");
        System.out.println("1. Insert a phone");
        System.out.println("2. Information phone");
        System.out.println("3. Height of tree");
        System.out.println("4. Balance Factor");
        System.out.println("5. Breadth");
        System.out.println("6. Sort by PreOrder");
        System.out.println("7. Sort by InOrder");
        System.out.println("8. Sort by PostOrder");
        System.out.println("9. Search");
        System.out.println("10. Find Min Price");
        System.out.println("11. Find Newest Price");
        System.out.println("12. Find Max Value");
        System.out.println("13. Delete By Merging");
        System.out.println("14. Detele By Copying");
        System.out.println("15. Delete Node");
        System.out.println("16. Quit");
        do{
            try{
                choice = sc.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Not invailed!!!");
            }
        }while(true);
        switch(choice){
            case 1:
                p = a.inputPhone();
                a.insert(p);
                break;
            case 2: 
                a.visit(x);
                break;
            case 3: 
                a.height(x);
                break;
            case 4:
                a.balanceFactor(x);
                break;
            case 5:
                a.breadth();
                break;
            case 6:
                a.preOrder(x);
                break;
            case 7:
                a.inOrder(x);
                break;
            case 8: 
                a.postOrder(x);
                break;
            case 9:
                a.search(p);
                break;
            case 10:
                a.find_Min_price();
                break;
            case 11:
                a.find_Newest_Phone();
                break;
            case 12:
                a.find_Max_Value();
                break;
            case 13:
                a.deletebyMerging(value);
                break;
            case 14: 
                a.deletebyCopying(value);
                break;
            case 15:
                a.deleteNode(value);
                break;
            case 16:
                System.exit(0);
                break;
        }
    }while(choice > 0 && choice < 17);
}
}
        

