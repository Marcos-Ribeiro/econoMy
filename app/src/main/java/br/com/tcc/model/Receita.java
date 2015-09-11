package br.com.tcc.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Marcos Ribeiro on 16/05/2015.
 */
public class Receita {

    private Integer id;
    private Integer idcategoria;
    private Integer idusuario;
    private String descricao;
    private Integer iddefreceita;
    private Integer diautil;
    private Integer repetir;
    private String datainicial;
    private Integer idconta;
    private Double valor;

    /*mov*/
    private Integer idmov;
    private String datamov;
    private Boolean pago;

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
    public Integer getIddefreceita() {
        return iddefreceita;
    }
    public void setIddefreceita(Integer iddefreceita) {
        this.iddefreceita = iddefreceita;
    }
    public Integer getDiautil() {
        return diautil;
    }
    public void setDiautil(Integer diautil) {
        this.diautil = diautil;
    }
    public Integer getRepetir() {
        return repetir;
    }
    public void setRepetir(Integer repetir) {
        this.repetir = repetir;
    }
    public String getDatainicial() {
        return datainicial;
    }
    public void setDatainicial(String datainicial) {
        this.datainicial = datainicial;
    }
    public Integer getIdconta() {
        return idconta;
    }
    public void setIdconta(Integer idconta) {
        this.idconta = idconta;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Integer getIdcategoria() {
        return idcategoria;
    }
    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }
    public Integer getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    public String getDatamov() {
        return datamov;
    }
    public Integer getIdmov() {
        return idmov;
    }
    public void setIdmov(Integer idmov) {
        this.idmov = idmov;
    }
    public void setDatamov(String datamov) {
        this.datamov = datamov;
    }
    public Boolean isPago() {
        return pago;
    }
    public void setPago(Boolean pago) {
        this.pago = pago;
    }
}


