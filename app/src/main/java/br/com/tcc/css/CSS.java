package br.com.tcc.css;

/**
 * Created by Marcos Ribeiro on 07/05/2015.
 */

public class CSS {
    private String styleCSS = "" +
            "view{background-color: #3F8; style-guide-visible: true;}" +
            "EditText{background-color: #EEE;}" +
            "Button{background-color: #DDD;}" +
            "gridView{background-color: #EEE;}" +
            "TextView{background-color: transparent; font-size:18pt; }" +
            "ListView{background-color: #EEE;}" +
            "#lblTotalDespesa{background-color: transparent; font-style: bold; font-size: 18pt;}" +
            "#lblTotalReceita{background-color: transparent; font-style: bold; font-size: 18pt;}";

    private String loginStyle = "" +
            "view{background-color: #333; }" +
            "EditText{background-color: #f9f9f9;}" +
            "Button{background-color: #3388CC; border-radius: 2px;}";

    private  String principalStyle = "" +
            "gridView{background-color: #FFF; border: 3dp; border-color:#5F8}" +
            "#btnListaDespesa{background-color: #3388CC; }" +
            "#btnListaReceita{background-color: #3388CC;}" +
            "#btnListaSaldo{background-color: #3388CC; }" +
            "textView{background-color: transparent; font-size:18pt; }";

    public String novo = "" +
            "#button1{background-color:#43cbb5;}" +
            "#button2{background-color:#ea324e;}" +
            "#button3{background-color:#3a3e4a;}";

    private String listaStyle = "" +
            "#lblCampo1{background-color: transparent; font-size:20pt; }" +
            "#lblCampo2{background-color: transparent; font-size:16pt; }" +
            "#lblCampo3{background-color: transparent; font-size:22pt; }" +
            "Button{background-color: #3388CC;}" +
            "#lblTotalDespesa{background-color: transparent; font-style: bold; font-size: 20pt; }" +
            "#lblTotalReceita{background-color: transparent; font-style: bold; font-size: 20pt; }" +
            "#lblTotalExtrato{background-color: transparent; font-style: bold; font-size: 20pt; }";


    private String extratoStyle = "" +
            "Button{background-color: #3F8;}";

    public void setExtratoStyle(String extratoStyle) {
        this.extratoStyle = extratoStyle;
    }

    public String getExtratoStyle() {
        return extratoStyle;
    }

    public String getListaStyle() {
        return listaStyle;
    }

    public void setListaStyle(String listaStyle) {
        this.listaStyle = listaStyle;
    }

    public void setLoginStyle(String loginStyle) {
        this.loginStyle = loginStyle;
    }

    public String getLoginStyle() {
        return loginStyle;
    }

    public void setPrincipalStyle(String principalStyle) {
        this.principalStyle = principalStyle;
    }

    public String getPrincipalStyle() {
        return principalStyle;
    }

    public void setStyleCSS(String styleCSS) {
        this.styleCSS = styleCSS;
    }

    public String getStyleCSS() {
        return styleCSS;
    }
}
