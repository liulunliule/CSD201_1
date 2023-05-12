/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;
public class Edge {
    int w;
    int v1;
    int v2;
    
        public Edge(){}

    public Edge(int w, int v1, int v2) {
        this.w = w;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int getW() {
        return w;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Edge{" + "w=" + w + ", v1=" + v1 + ", v2=" + v2 + '}';
    }
        
        
        
}
