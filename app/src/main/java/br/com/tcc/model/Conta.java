package br.com.tcc.model;

/**
 * Created by Marcos Ribeiro on 13/08/2015.
 */
public class Conta {
    private Integer id;
    private String descricao;
    private Integer idusuario;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
}
