/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgraph;

/**
 *
 * @author ACER
 */
public class Edges {
    int weigthed; //Độ dài từ đỉnh này đến đỉnh kia (độ dài cạnh)
    String ver1;
    String ver2;

    public Edges() {
    }

    public Edges(int weigthed, String ver1, String ver2) {
        this.weigthed = weigthed;
        this.ver1 = ver1;
        this.ver2 = ver2;
    }

    public int getWeigthed() {
        return weigthed;
    }

    public void setWeigthed(int weigthed) {
        this.weigthed = weigthed;
    }

    public String getVer1() {
        return ver1;
    }

    public void setVer1(String ver1) {
        this.ver1 = ver1;
    }

    public String getVer2() {
        return ver2;
    }

    public void setVer2(String ver2) {
        this.ver2 = ver2;
    }

    @Override
    public String toString() {
        return ver1 + ver2;
    }
}
