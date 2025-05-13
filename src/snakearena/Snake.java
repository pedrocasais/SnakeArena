/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakearena;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class Snake {

    private double x;
    private double y;
    private int tam = 0;
    private ArrayList<Point> corpo = new ArrayList<>();

    public Snake(double x, double y, ArrayList<Point> corpo) {
        super();
        this.x = x;
        this.y = y;
        this.corpo = new ArrayList<>(corpo);
        this.tam = corpo.size();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public ArrayList<Point> getCorpo() {
        return corpo;
    }

    public void setCorpo(ArrayList<Point> corpo) {
        this.corpo = new ArrayList<>(corpo);
    }

}
