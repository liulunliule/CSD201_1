/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mygraph;

/**
 *
 * @author ACER
 */
public class MyGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph g = new Graph();
        g.BFT(1);//A
        System.out.println("");
        /*Session 1_______________________________________________________________*/
        g.pathDFT(0, 6);//A->F
        System.out.println("");
        /*Session 2_______________________________________________________________*/
        System.out.println("Dijkstra: ");
        Dijkstra d = new Dijkstra();
        d.ijk(6, 7);//A->F
        System.out.println("");
        System.out.println("Euler: ");
        Euler e = new Euler();
        e.euler();
        System.out.println("");
    }

}
