/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakearena;

/**
 *
 * @author pedro
 */
public class Dados {
    private int pos;
    private String nome;
    private int pontos;
    private String data;
    private String cor;
    private String mode;
    
    public Dados(int pos,String nome, int pontos,String data,String cor,String mode){
        this.pos = pos;
        this.nome = nome;
        this.pontos = pontos;
        this.data = data;
        this.cor = cor;
        this.mode = mode;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
    // Getters para TableView
    public int getPosicao() { return pos; }
    public int getPontuacao() { return pontos; }
    public String getCor() { return cor; }
    public String getDificuldade() { return mode; }
}
