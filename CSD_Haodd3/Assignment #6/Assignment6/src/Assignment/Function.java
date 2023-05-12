/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

public class Function {

    //code here!!!!
    int numEdge = 0;
    int numVer = 0;
    String[] vertex = new String[8];

    
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
    
    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");

        for (int i = 1; i < numVer; i++) {
            System.out.println(vertex[parent[i]] + " - " + vertex[i] + "\t" + graph[i][parent[i]]);
        }
    }

        void primMST(int graph[][]) {
        int parent[] = new int[numVer];
        int key[] = new int[numVer];
        Boolean mstSet[] = new Boolean[numVer];
        for (int i = 0; i < numVer; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < numVer - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < numVer; v++) { // graph[u][v] is non zero only for adjacent
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        printMST(parent, graph);
    }
    }
    
