package br.com.tcc.resources;

import br.com.tcc.model.Usuario;

/**
 * Created by Marcos Ribeiro on 21/05/2015.
 */
public class Config {
    private static String ip = "http://192.168.2.150:8080/";
    private static String wService = "wsfinancas";
    private static Usuario usuarioLogado;

    public static void setIp(String ip) {
        Config.ip = ip;
    }

    public static String getIp() {
        return ip;
    }

    public static void setwService(String wService) {
        Config.wService = wService;
    }

    public static String getwService() {
        return wService;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
