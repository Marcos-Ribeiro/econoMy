package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import br.com.tcc.model.Receita;
import br.com.tcc.resources.AdapterReceita;
import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;


public class ReceitaActivity extends ActionBarActivity {

    TextView lblTotalReceita;
    ListView lvListaReceita;
    double totalReceita = 0.00;
    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        inicializaVariaveis();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONArray json = connectionWS(config.getIp() + config.getwService() + "/receitas/" + config.getUsuarioLogado().getId());

        ArrayList<Receita> listaReceita = new ArrayList<Receita>();

        for (int i = 0; i < json.length(); i++){
            Receita receita = new Receita();

            try{
                receita.setId(json.getJSONObject(i).getInt("id"));
                receita.setIddefreceita(json.getJSONObject(i).getInt("iddefreceita"));
                receita.setIdcategoria(json.getJSONObject(i).getInt("idcategoria"));
                receita.setIdusuario(json.getJSONObject(i).getInt("idusuario"));
                receita.setDescricao(json.getJSONObject(i).getString("descricao").toString());
                receita.setValor(json.getJSONObject(i).getDouble("valor"));
                receita.setDiautil(json.getJSONObject(i).getInt("diautil"));
                receita.setRepetir(json.getJSONObject(i).getInt("repetir"));
                receita.setDatainicial(json.getJSONObject(i).getString("datainicial"));

                receita.setIdmov(json.getJSONObject(i).getInt("idmov"));
                receita.setDatamov(json.getJSONObject(i).getString("datamov"));
                receita.setPago(json.getJSONObject(i).getBoolean("pago"));

            }catch (JSONException e){
                e.printStackTrace();
            }

            listaReceita.add(receita);

            totalReceita += receita.getValor();
        }

        lblTotalReceita.setText("Total: R$ " + totalReceita);
        lvListaReceita.setAdapter(new AdapterReceita(this, listaReceita));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_despesa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReceitaActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void inicializaVariaveis(){
        lvListaReceita = (ListView) findViewById(R.id.lvListReceita);
        lblTotalReceita = (TextView) findViewById(R.id.lblTotalReceita);

    }

    public JSONArray connectionWS(String url) {
        JSONServer jsonParser = new JSONServer();

        JSONArray jArr = jsonParser.GetJSONArray(url, "GET", "receita");

        return jArr;

    }
}
