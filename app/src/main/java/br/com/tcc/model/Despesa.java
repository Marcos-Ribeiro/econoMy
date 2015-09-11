package br.com.tcc.model;

/**
 * Created by Marcos Ribeiro on 10/05/2015.
 */
import java.util.Date;
import java.util.List;

public class Despesa {
    /**
     iddespesa serial NOT NULL,
     iddefdespesa integer NOT NULL,
     idconta integer NOT NULL,
     idcategoria integer NOT NULL,
     idusuario integer NOT NULL,
     descricao character varying(100) NOT NULL,
     valor numeric NOT NULL,
     diautil integer,
     repetir integer,
     datainicial date,
     **/

    private Integer id;
    private Integer iddefdespesa;
    private Integer idconta;
    private Integer idcategoria;
    private Integer idusuario;
    private String descricao;
    private Integer diautil;
    private Integer repetir;
    private Double valor;
    private String datainicial;

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
    public Integer getIddefdespesa() {
        return iddefdespesa;
    }
    public void setIddefdespesa(Integer iddefdespesa) {
        this.iddefdespesa = iddefdespesa;
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
    public String getDatainicial() {
        return datainicial;
    }
    public void setDatainicial(String datainicial) {
        this.datainicial = datainicial;
    }
    public Integer getIdcategoria() {
        return idcategoria;
    }
    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }
    public Integer getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    public Integer getIdmov() {
        return idmov;
    }
    public void setIdmov(Integer idmov) {
        this.idmov = idmov;
    }
    public String getDatamov() {
        return datamov;
    }
    public void setDatamov(String datamov) {
        this.datamov = datamov;
    }
    public boolean isPago() {
        return pago;
    }
    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
