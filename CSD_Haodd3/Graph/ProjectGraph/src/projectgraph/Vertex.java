/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgraph;

/**
 *
 * @author ACER
 */
public class Vertex {
    String vertex1;
    String vertex2;

    public Vertex() {
    }

    public Vertex(String vertex1, String vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public String getVertex1() {
        return vertex1;
    }

    public void setVertex1(String vertex1) {
        this.vertex1 = vertex1;
    }

    public String getVertex2() {
        return vertex2;
    }

    public void setVertex2(String vertex2) {
        this.vertex2 = vertex2;
    }

    @Override
    public String toString() {
        return "vertex1: " + vertex1 + ", vertex2: " + vertex2;
    }
    
}
