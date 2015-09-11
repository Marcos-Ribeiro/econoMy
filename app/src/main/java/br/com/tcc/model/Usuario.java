package br.com.tcc.model;

/**
 * Created by Marcos Ribeiro on 20/05/2015.
 */
public class Usuario {

    private int id;
    private String nome;
    private String pin;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
}
