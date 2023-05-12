/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgraph;

import java.util.*;

/**
 *
 * @author ACER
 */
public class Graph {

    int[][] matrix = new int[8][8]; //matrix file
    int[][] adjMatrix = new int[8][8]; //adjcency matrix
    ArrayList<ArrayList<String>> adjList = new ArrayList<ArrayList<String>>(); // adjcent list
    ArrayList<ArrayList<String>> incMatrix = new ArrayList<ArrayList<String>>(); // incident matrix
    int numVer = 0; //total vertex
    int numEdge = 0; //total edges
    String[] vertex = new String[8]; //A, B, C,...
    Edges[] edges; //e1, e2, e3,...
    int countEdge = 0;
    int[][] dijMatrix = new int[8][8]; //Dijkstra Matrix
    int dijLength; //Dijkstra Matrix Length
    int INF = 99; //Nếu giữa 2 đỉnh không tiếp xúc thì cạnh đó = INF (chỉ dùng trong dijMatrix thôi)
    String[] verKrus = new String[8]; //Cũng là A, B, C,... (verKrus = vertex original chưa sắp xếp)

    public Graph(int numVer) {
        this.numVer = numVer;
        for (int i = 0; i < this.numVer; i++) { //Tạo arraylist lớp ngoài cùng(cột dọc A, B, C, D,...)
            adjList.add(new ArrayList<String>());
        }
        for (int i = 0; i < this.numVer; i++) { //Tạo arraylist lớp ngoài cùng(cột dọc A, B, C, D,...)
            incMatrix.add(new ArrayList<String>());
        }
    }

    //visit vertex i
    public void visit(int i) {
        System.out.print(vertex[i] + "  ");
    }

    //Line 45-69 Breath first traversal from int u
    public void BFT(int u, boolean[] c) {
        MyQueue mq = new MyQueue();
        mq.enqueue(u);
        c[u] = true;
        while (!mq.isEmpty()) {
            int j = (int) mq.dequeue();
            visit(j);
            for (int i = 0; i < numVer; i++) {
                if (!c[i] && adjMatrix[j][i] > 0) {
                    mq.enqueue(i);
                    c[i] = true;
                }
            }
        }
    }

    public void BFT(int u) {
        boolean[] c = new boolean[numVer];
        BFT(u, c);
        for (int i = 0; i < numVer; i++) {
            if (!c[i]) {
                BFT(i, c);
            }
        }
    }

    //Line 72-90 Depth first traversal from int i
    public void DFT(int i, boolean[] c) {
        c[i] = true;
        visit(i);
        for (int j = 0; j < numVer; j++) {
            if (!c[j] && adjMatrix[i][j] > 0) {
                DFT(j, c);
            }
        }
    }

    public void DFT(int i) {
        boolean[] c = new boolean[numVer];
        DFT(i, c);
        for (int j = 0; j < numVer; j++) {
            if (!c[j]) {
                DFT(j, c);
            }
        }
    }

    //Line 93-106 Adjacency List
    public void addEdge(int ver1, int ver2) { //ver1 -> ver2
        adjList.get(ver1).add(vertex[ver2]);
        numEdge++;
    }

    public void printAdjList() {
        for (int i = 0; i < numVer; i++) {
            System.out.print(vertex[i]);
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(" -> " + adjList.get(i).get(j));
            }
            System.out.println("");
        }
    }

    //Line 109-140 Incidency Matrix
    public void addEdgeToInc(int ver1, int ver2) { //ver1 -> ver2 && ver1 <- ver2
        int weigth = matrix[ver1][ver2];
        Vertex ver = new Vertex();
        ver.setVertex1(vertex[ver1]); //String.valueOf(vertex[ver1]) là convert từ char sang string
        ver.setVertex2(vertex[ver2]); //String.valueOf(vertex[ver2]) là convert từ char sang string
        edges[countEdge] = new Edges(weigth, ver.getVertex1(), ver.getVertex2());
        incMatrix.get(ver1).add(edges[countEdge].toString());
        countEdge++;
    }

    public void printIncMatrix() {
        System.out.print(" ");
        for (int i = 0; i < incMatrix.size(); i++) {
            for (int j = 0; j < incMatrix.get(i).size(); j++) {
                System.out.printf("%4s", incMatrix.get(i).get(j));
            }
        }
        System.out.println("");
        for (int i = 0; i < vertex.length; i++) {
            System.out.print(vertex[i]);
            for (int j = 0; j < edges.length; j++) {
                if (vertex[i].compareTo(edges[j].ver1) == 0) {
                    System.out.printf("%4d", edges[j].weigthed);
                } else if (vertex[i].compareTo(edges[j].ver2) == 0) {
                    System.out.printf("%4d", edges[j].weigthed);
                } else {
                    System.out.printf("%4d", 0);
                }
            }
            System.out.println("");
        }
    }

    //Line 143-201 Dijstra()
    public void dijk(int u, int v) {
        boolean[] c = new boolean[dijLength];//c[i] = true -> da chon dinh i
        int[] s = new int[dijLength];//s[i] = j -> dinh truoc i la j
        int[] d = new int[dijLength];//d[i] shortest path tu u den i
        for (int i = 0; i < dijLength; i++) {
            s[i] = u;
            d[i] = dijMatrix[u][i];
        }
        int curr = u;
        while (curr != v) {
            //tim k la dinh nho nhat xuat phat tu curr -> tim min tren mang d
            int k = -1, min = INF;
            for (int i = 0; i < dijLength; i++) {
                if (d[i] < min && !c[i]) {
                    min = d[i];
                    k = i;
                }
            }
            if (min == INF) {
                System.out.println("Shortest path from " + vertex[u] + " to " + vertex[v] + " not found");
                return;
            }
            curr = k;
            c[k] = true;
            //tinh lai mang d
            for (int i = 0; i < dijLength; i++) {
                if (d[i] > d[k] + dijMatrix[k][i]) {
                    d[i] = d[k] + dijMatrix[k][i];
                    s[i] = k;
                }
            }
        }
        //output result
        System.out.println("Shortest path from " + vertex[u] + " to " + vertex[v] + " is " + d[v]);
        int[] h = new int[dijLength];
        int hn = 0;
        h[hn] = v;
        int[] w = new int[dijLength];//w[i] trong so cua dinh i
        int wn = 0;//dem spt cua w
        int x, y = v;
        //tinh cac dinh tren duong di
        while (u != v) {
            v = s[v];
            h[++hn] = v;
        }
        //tinh trong so cac dinh tren duong di
        for (int i = hn; i >= 0; i--) {
            x = y;
            y = h[i];
            w[wn] = dijMatrix[x][y];
            wn++;
        }
        //tinh trong so giua cac dinh tren duong di
        int k = 1;//khong hien thi trong so cua dinh dau tien
        System.out.print(vertex[h[hn]]);
        for (int i = hn - 1; i >= 0; i--) {
            System.out.print("->" + vertex[h[i]] + "(" + w[k++] + ")");
        }
    }

    //Line 204-266 Folyd()
    public void floyd(int graph[][]) {
        int dist[][] = new int[numVer][numVer];
        int i, j, k;

        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
        for (i = 0; i < numVer; i++) {
            for (j = 0; j < numVer; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        /* Add all vertices one by one
           to the set of intermediate
           vertices.
          ---> Before start of an iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of an iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < numVer; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < numVer; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < numVer; j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distance matrix
        printFloyd(dist);
    }

    void printFloyd(int dist[][]) {
        System.out.println("The following matrix shows the shortest distances between every pair of vertices");
        System.out.print(" ");
        for (int i = 0; i < vertex.length; i++) {
            System.out.printf("%4s", vertex[i]);
        }
        System.out.println();
        for (int i = 0; i < numVer; ++i) {
            System.out.print(vertex[i] + "   ");
            for (int j = 0; j < numVer; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    //Line 269-342 Prim() MST(Minimum Spanning Tree)
    // A utility function to find the vertex with minimum key value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < numVer; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // A utility function to print the constructed MST
    // stored in parent[]
    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");

        for (int i = 1; i < numVer; i++) {
            System.out.println(vertex[parent[i]] + " - " + vertex[i] + "\t" + graph[i][parent[i]]);
        }
    }

    // Function to construct and print MST for a graph
    // represented using adjacency matrix representation
    void primMST(int graph[][]) {
        // Array to store constructed MST
        int parent[] = new int[numVer];
        // Key values used to pick minimum weight edge in
        // cut
        int key[] = new int[numVer];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[numVer];

        // Initialize all keys as INFINITE
        for (int i = 0; i < numVer; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < numVer - 1; count++) {
            // Pick thd minimum key vertex from the set of
            // vertices not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the
            // adjacent vertices of the picked vertex.
            // Consider only those vertices which are not
            // yet included in MST
            for (int v = 0; v < numVer; v++) { // graph[u][v] is non zero only for adjacent
                // vertices of m mstSet[v] is false for
                // vertices not yet included in MST Update
                // the key only if graph[u][v] is smaller
                // than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // print the constructed MST
        printMST(parent, graph);
    }

    //Line 345-359 Euler Path()
    public boolean checkEulerPath() {
        boolean check;
        int count = 0;
        for (int i = 0; i < vertex.length; i++) {
            if (adjList.get(i).size() % 2 != 0) { //Đếm số bậc lẻ
                count++;
            }
        }
        if (count <= 2) { //Có tô đa 2 đỉnh bậc lẻ
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    //Line 362-415 Kruskal()
    public int find(String ver) {
        int indexVer = -1;
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].compareTo(ver) == 0) {
                indexVer = i;
            }
        }
        if (verKrus[indexVer].compareTo(ver) == 0) { // Nếu cái đỉnh trong mảng origin khớp với đỉnh của cạnh thì trả về index đó
            return indexVer;
        }
        return find(verKrus[indexVer]); // Nếu không khớp thì tìm cái đỉnh không khớp đó trong mảng origin xong trả về index của đỉnh đó
    }

    public void unite(String ver1, String ver2) {
        int inVer1 = find(ver1);
        int inVer2 = find(ver2);
        verKrus[inVer1] = verKrus[inVer2];
    }

    public void kruskal() {
        String ver1;
        String ver2;
        int weigth;
        int mst_weigth = 0;  //đếm độ cao của cây MST (Kruskal)
        int mst_edges = 0; //đếm số cạnh có trong MST (Krustal)
        int mst_ni = 0; //đếm node index trong MST (Kruskal)
        Edges[] sortEdges = new Edges[numEdge]; //Mảng đỉnh sắp xếp tăng dần
        for (int i = 0; i < sortEdges.length; i++) { //Thêm hết cạnh vào sortEdges trước sau đó mới sắp xếp
            sortEdges[i] = edges[i];
        }
        for (int i = 0; i < sortEdges.length - 1; i++) { //Sắp xếp theo thứ tự tăng dần
            for (int j = i + 1; j < sortEdges.length; j++) {
                if (sortEdges[i].weigthed > sortEdges[j].weigthed) { //swap
                    Edges tmp = sortEdges[i];
                    sortEdges[i] = sortEdges[j];
                    sortEdges[j] = tmp;
                }
            }
        }
        while ((mst_edges < numVer - 1) || (mst_ni < numEdge)) { //số cạnh phải nhỏ hơn số đỉnh - 1 vì n đỉnh có n - 1 cạnh
            ver1 = sortEdges[mst_ni].ver1; //lôi đỉnh thứ i đã sắp xếp ra
            ver2 = sortEdges[mst_ni].ver2;
            weigth = sortEdges[mst_ni].weigthed; //lôi cạnh thứ i đã sắp xếp ra
            if (find(ver1) != find(ver2)) { //Nếu tụi nó không cùng đỉnh (không liên kết), không cùng cây
                unite(ver1, ver2);
                mst_weigth += weigth;
                System.out.println(ver1 + " " + ver2 + " " + weigth);
                mst_edges++;
            }
            mst_ni++; //tăng biến đếm i lên
        }

        System.out.println("\nWeigth of the MST is " + mst_weigth);
    }

    //Line 418-450 Euler_Path()
    public void eulerPath(int ver, int[][] adj) {
        MyStack stack = new MyStack();
        int[] eu = new int[100];
        int i, j, r, m;
        m = 0;
        stack.push(ver); //thêm đỉnh vô stack
        while (!stack.isEmpty()) { //cho đến khi nào stack rỗng thì thoát ra
            r = stack.top();
            for (i = 0; i < numVer; i++) {
                if (adj[r][i] > 0) { //Tìm được đường đi nào đó từ r đến i thì thoát ra để lưu điểm đó vào
                    break;
                }
            }
            if (i == numVer) { //Trường hợp nếu i = numVer có nghĩa là ko có i nối với r nào cả => i bị cô lập
                stack.pop(); //Nếu bị cô lập thì xóa nó đi
                eu[m++] = r;
            } else { //Ngược lại nếu có đường đi từ r đến i thì thêm nó vô stack
                stack.push(i);
                //Sau khi thêm vô thì xóa đường đi đó đi
                //Thì lát duyệt sẽ thấy nó bị cô lập
                //Và sẽ pop nó ra như trên
                //Sau khi cô lập hết các điểm, không còn đường nối nào nữa
                //Thì có nghĩa là mình đã duyệt xong cái đồ thị, sẵn sàng cho chu trình Euler
                adj[r][i]--;
                adj[i][r]--;
            }
        }
        System.out.print(vertex[eu[0]]);
        for (i = 1; i < m; i++) { //Duyệt mảng eu[] tối đa m phần tử
            System.out.print(" -> " + vertex[eu[i]]);
        }
        System.out.println();
    }
}
