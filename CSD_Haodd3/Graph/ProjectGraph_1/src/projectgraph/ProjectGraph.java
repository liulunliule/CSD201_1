/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectgraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class ProjectGraph {

    public int[][] matrix = new int[8][8]; //file matrix
    public int[][] adjMatrix = new int[8][8]; //adjcency matrix
    public int[][] dijMatrix = new int[8][8]; //dijkstra matrix
    public String[] vertex = new String[8];
    int INF = 99;

    public void loadData() {
        int countLine = 0;
        try {
            File file = new File("data_Graph.txt");
            Scanner reader = new Scanner(file);
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
        } catch (Exception e) {
            System.out.println("There aren't any Files");
        }
    }

    public void writeFile(int[][] matrix, String[] vertex) {
        try {
            File file = new File("data_Graph.txt");
            if (!file.exists()) { // if file doesnt exists, then create it
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < vertex.length; i++) { //A B C D E...
                if (i == vertex.length - 1) { //Thằng này đến cuối dòng thì không lưu khoảng trắng nữa
                    bw.write(vertex[i] + "\n");
                } else {
                    bw.write(vertex[i] + " "); //Thằng này lưu  cách bình thường
                }
            }
            /*
             0 1 0 0 2 
             1 0 0 0 3 
             0 0 0 4 1 
             0 0 4 0 7 
             2 3 1 7 0 
             */
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if ((j == matrix[i].length - 1) && (i != matrix.length - 1)) { //Thằng này đến cuối dòng thì không lưu khoảng trắng nữa nhưng phải lưu \n
                        bw.write(matrix[i][j] + "\n");
                    } else if ((j == matrix[i].length - 1) && (i == matrix.length - 1)) { //Còn thằng này là điểm cuối cùng nên không khoảng trắng
                        //bw.write(matrix[i][j]); // Riêng matrix[4][4] không lưu kiểu này dc vì nó ko chịu lưu (do số 0 lưu cuối nó không chịu phải thêm \n mới ok
                        bw.write(matrix[i][j] + "\n");
                    } else { //Thằng này lưu cách bình thường
                        bw.write(matrix[i][j] + " ");
                    }
                }
            }

            System.out.println("Changes have been saved successfully!");
            bw.close();
        } catch (Exception e) {
            System.out.println("The File is empty.");
        }
    }

    public void Menu() {
        int choose;
        Scanner sc = new Scanner(System.in);
        ProjectGraph file = new ProjectGraph();
        file.loadData();
        Graph graph = new Graph(file.matrix.length);
        for (int i = 0; i < file.matrix.length; i++) { //Truyền matrix qua cho graph
            for (int j = 0; j < file.matrix[i].length; j++) {
                graph.matrix[i][j] = file.matrix[i][j];
            }
        }
        for (int i = 0; i < file.vertex.length; i++) { //Truyền vertex qua cho graph
            graph.vertex[i] = file.vertex[i];
        }
        //verKrus[]
        for (int i = 0; i < file.vertex.length; i++) { //Thêm đỉnh theo thứ tự original
            graph.verKrus[i] = file.vertex[i];
        }
        System.out.println("Load file success!");
        System.out.println("File:");
        //  A B C D...
        System.out.print("  ");
        for (int i = 0; i < file.vertex.length; i++) {
            System.out.printf("%3s", file.vertex[i]);
        }
        System.out.println();
        /*
        A 0 1 0 0 2 
        B 1 0 0 0 3 
        C 0 0 0 4 1 
        D 0 0 4 0 7 
        E 2 3 1 7 0 
         */
        for (int i = 0; i < file.matrix.length; i++) {
            System.out.print(file.vertex[i] + " ");
            for (int j = 0; j < file.matrix.length; j++) {
                System.out.printf("%3d", file.matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        //Thêm đỉnh cho adjacency list
        for (int i = 0; i < file.matrix.length; i++) {
            for (int j = 0; j < file.matrix[i].length; j++) {
                if (file.matrix[i][j] != 0) {
                    graph.addEdge(i, j);
                }
            }
        }
        graph.numEdge = graph.numEdge / 2; //Do addEdge phải duyệt hết mảng để lấy adjacency list nên tổng cạnh phải chia 2
        graph.edges = new Edges[graph.numEdge]; //Tạo mảng các cạnh với độ dài mảng bằng số cạnh

        //incident matrix
        for (int i = 0; i < file.matrix.length; i++) {
            for (int j = i + 1; j < file.matrix.length; j++) {
                if (file.matrix[i][j] != 0) {
                    graph.addEdgeToInc(i, j);
                }
            }
        }

        //Adjacency matrix
        for (int i = 0; i < file.matrix.length; i++) { //Biến hết tất cả những cạnh > 0 thành 1
            for (int j = 0; j < file.matrix.length; j++) {
                if (file.matrix[i][j] != 0) {
                    file.adjMatrix[i][j] = 1;
                } else {
                    file.adjMatrix[i][j] = file.matrix[i][j];
                }
            }
        }
        for (int i = 0; i < file.adjMatrix.length; i++) { //truyền giá trị của mảng qua bên mảng bên class graph
            for (int j = 0; j < file.adjMatrix[i].length; j++) {
                graph.adjMatrix[i][j] = file.adjMatrix[i][j];
            }
        }
        //LƯU Ý ko truyền kiểu gán bằng bên dưới
        //graph.adjMatrix = adjMatrix;
        //vì khi thay đổi(sửa, xóa) thì mảng bên đây cũng bị thay đổi theo

        //Dijkstra matrix
        for (int i = 0; i < file.matrix.length; i++) { //Biến hết tất cả những cạnh = 0(bao gồm luôn trùng đỉnh) thành INF
            for (int j = 0; j < file.matrix.length; j++) {
                if (file.matrix[i][j] == 0) {
                    file.dijMatrix[i][j] = INF;
                } else {
                    file.dijMatrix[i][j] = file.matrix[i][j];
                }
            }
        }
        for (int i = 0; i < file.matrix.length; i++) {
            if (file.dijMatrix[i][i] == INF) { //Đổi các giá trị INF tại đường chéo thành 0 vì trùng đỉnh chứ không phải không có cạnh
                file.dijMatrix[i][i] = 0;
            }
        }
        for (int i = 0; i < file.dijMatrix.length; i++) {
            for (int j = 0; j < file.dijMatrix[i].length; j++) {
                graph.dijMatrix[i][j] = file.dijMatrix[i][j];
            }
        }
        graph.dijLength = graph.dijMatrix.length;

        boolean change = true; //to check save file
        String inputVer;
        do {
            System.out.println("1. Print a list of vertice.");
            System.out.println("2. Print a list of edges.");
            System.out.println("3. Print adjacency matrix.");
            System.out.println("4. Print incident matrix.");
            System.out.println("5. Print adjacency list.");
            System.out.println("6. bfs().");
            System.out.println("7. dfs().");
            System.out.println("8. Compute degree(vertex).");
            System.out.println("9. Dijkstra().");
            System.out.println("10. Floyd().");
            System.out.println("11. Prim().");
            System.out.println("12. Kruskal().");
            System.out.println("13. Euler_path().");
            System.out.println("14. Write_data(filepath).");
            System.out.println("Others - Quit.");
            System.out.print("Please enter your choice: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    for (int i = 0; i < graph.vertex.length; i++) {
                        System.out.print(graph.vertex[i] + " ");
                    }
                    System.out.println();
                    System.out.println("Print success!\n");
                    break;
                case 2:
                    for (int i = 0; i < graph.edges.length; i++) {
                        System.out.print(graph.edges[i] + " ");
                    }
                    System.out.println();
                    System.out.println("Print success!\n");
                    break;
                case 3:
                    System.out.print("  ");
                    for (int i = 0; i < graph.vertex.length; i++) {
                        System.out.printf("%4s", graph.vertex[i]);
                    }
                    System.out.println("");
                    for (int i = 0; i < graph.adjMatrix.length; i++) {
                        System.out.print(graph.vertex[i] + " ");
                        for (int j = 0; j < graph.adjMatrix.length; j++) {
                            System.out.printf("%4d", graph.adjMatrix[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println("Print success!\n");
                    break;
                case 4:
                    graph.printIncMatrix();
                    System.out.println("Print success!\n");
                    break;
                case 5:
                    graph.printAdjList();
                    System.out.println("Print success!\n");
                    break;
                case 6:
                    System.out.print("Input the beginning vertex for BFS: ");
                    inputVer = sc.next();
                    for (int i = 0; i < file.vertex.length; i++) {
                        if (String.valueOf(file.vertex[i]).compareToIgnoreCase(inputVer) == 0) {
                            System.out.print("Traversal order: ");
                            graph.BFT(i);
                        }
                    }
                    System.out.println("\n");
                    break;
                case 7:
                    System.out.print("Input the beginning vertex for DFS: ");
                    inputVer = sc.next();
                    for (int i = 0; i < file.vertex.length; i++) {
                        if (String.valueOf(file.vertex[i]).compareToIgnoreCase(inputVer) == 0) {
                            System.out.print("Traversal order: ");
                            graph.DFT(i);
                        }
                    }
                    System.out.println("\n");
                    break;
                case 8:
                    System.out.print("Input the vertex that you want to check: ");
                    inputVer = sc.next();
                    for (int i = 0; i < file.vertex.length; i++) { //Vô adjcency list lấy cái size của đỉnh đó <-> degree của đỉnh đó
                        if (String.valueOf(file.vertex[i]).compareToIgnoreCase(inputVer) == 0) {
                            System.out.print("Degree of vertex " + inputVer + ": " + graph.adjList.get(i).size());
                        }
                    }
                    System.out.println();
                    break;
                case 9:
                    String inputVer1,
                     inputVer2;
                    int ver1 = -1,
                     ver2 = -1;
                    System.out.print("Input the beginning vertex: ");
                    inputVer1 = sc.next();
                    System.out.print("Input the ending vertex: ");
                    inputVer2 = sc.next();
                    for (int i = 0; i < file.vertex.length; i++) {
                        if (String.valueOf(file.vertex[i]).compareToIgnoreCase(inputVer1) == 0) {
                            ver1 = i;
                        } else if (String.valueOf(file.vertex[i]).compareToIgnoreCase(inputVer2) == 0) {
                            ver2 = i;
                        }
                    }
                    graph.dijk(ver1, ver2);
                    System.out.println("\n");
                    break;
                case 10:
                    graph.floyd(graph.matrix);
                    System.out.println("Print success\n");
                    break;
                case 11:
                    graph.primMST(graph.matrix);
                    System.out.println("Print success\n");
                    break;
                case 12:
                    graph.kruskal();
                    System.out.println();
                    break;
                case 13:
                    if (graph.checkEulerPath()) {
                        System.out.println("This graph has Euler_Path.");
                        System.out.println("The Euler_Paths are:");
                        for (int i = 0; i < graph.vertex.length; i++) {
                            System.out.print((i + 1) + ") ");
                            graph.eulerPath(i, graph.adjMatrix);
                            for (int j = 0; j < file.adjMatrix.length; j++) { //truyền lại lần nữa do hàm đó trả hết cạnh về 0 rồi
                                for (int k = 0; k < file.adjMatrix[j].length; k++) {
                                    graph.adjMatrix[j][k] = file.adjMatrix[j][k];
                                }
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println("This graph doesn't have Euler_Path.");
                    }
                    break;
                case 14:
                    file.writeFile(graph.matrix, graph.vertex);
                    break;
                default:
                    if (change == true) {
                        System.out.print("Save file?(Y/N): ");
                        String ans = "";
                        boolean check = true;
                        Scanner scan = new Scanner(System.in);
                        do {
                            ans = scan.nextLine().toUpperCase();
                            if (ans.equals("Y")) {
                                file.writeFile(graph.matrix, graph.vertex);
                                System.out.print("Save Successfully!");
                            } else if (ans.equals("N")) {
                                System.out.print("Save Fail. Good Bye!");
                            } else {
                                check = false;
                            }
                        } while (check == false);
                    } else {
                        System.out.println("Good Bye!");
                    }
            }
        } while (choose >= 1 && choose <= 14);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProjectGraph menu = new ProjectGraph();
        menu.Menu();
    }

}
