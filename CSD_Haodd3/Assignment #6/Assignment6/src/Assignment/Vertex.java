/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

public class Vertex {
    String v1,v2;
    public Vertex(){}

    public Vertex(String v1, String v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Vertex{" + "v1=" + v1 + ", v2=" + v2 + '}';
    }
    
    
}
