package br.com.tcc.model;

import java.io.Serializable;

/**
 * Created by Marcos Ribeiro on 13/08/2015.
 */
public class Categoria implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;

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

}
