package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public int[][] matrix = new int[8][8]; //file matrix
    public int[][] adjMatrix = new int[8][8]; //adjcency matrix
    public int[][] dijMatrix = new int[8][8]; //dijkstra matrix
    public String[] vertex = new String[5];
    int INF = 99;
    
    public void loadFile(){
        int countLine = 0;
        
        try{
        File f = new File("Data.txt");
        Scanner reader = new Scanner(f);
        //loop to check nextLine is null or not
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (countLine == 0) {
                    String[] arrData = data.split(" ", 8);
                    for (int i = 0; i < arrData.length; i++) {
                        vertex[i] = arrData[i];
                    }
                } else {
                    String[] arrData = data.split(" ", 8);
                    for (int i = countLine - 1; i < matrix.length; i++) {
                        for (int j = 0; j < matrix[i].length; j++) {
                            matrix[i][j] = Integer.parseInt(arrData[j]);
                        }
                    }
                }
                countLine++;
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("There aren't any Files");
        }
    }

    public static void main(String[] args) {
        int choice;
        do{
        Scanner sc = new Scanner(System.in);
        System.out.println("ASSIGNMENT 6");
        System.out.println("1. Load from file");
        System.out.println("2. Convert 1: from adjacency matrix to incident matrix");
        System.out.println("3. Convert 2: from adjacency matrix to adjacency list");
        System.out.println("4. BFS");
        System.out.println("5. DFS");
        System.out.println("6. Find path usning Dijsktra's Algorithm");
        System.out.println("7. MST1 using Prim-Janik Algorithm");
        System.out.println("8. MST2 using Kruskal algorithm ");
        System.out.println("9. Euler: Euler cycle from vertex X");
        System.out.println("10. Exit!");
        choice = sc.nextInt();
        switch(choice){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                
                break;
            case 8:
                break;
            case 9:
                break;
        }
        }while (choice > 0 && choice < 11);

    }
}
