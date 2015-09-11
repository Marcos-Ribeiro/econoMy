package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import br.com.tcc.model.Conta;
import br.com.tcc.resources.AdapterConta;
import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;


public class ContaActivity extends ActionBarActivity {

    ListView lvListaConta;
    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        inicializaVariaveis();
        JSONArray json = connectionWS(config.getIp() + config.getwService() + "/contas/" + config.getUsuarioLogado().getId());

        ArrayList<Conta> listaConta = new ArrayList<Conta>();

        for (int i = 0; i < json.length(); i++){
            Conta conta = new Conta();
            try{
                conta.setDescricao(json.getJSONObject(i).getString("descricao").toString());
                conta.setId(json.getJSONObject(i).getInt("id"));
                conta.setIdusuario(json.getJSONObject(i).getInt("idusuario"));

            }catch (JSONException e){
                e.printStackTrace();
            }

            listaConta.add(conta);

        }

        lvListaConta.setAdapter(new AdapterConta(this,listaConta));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_conta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ContaActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void inicializaVariaveis(){
        lvListaConta = (ListView) findViewById(R.id.lvListaConta);
    }

    public JSONArray connectionWS(String url) {
        JSONServer jsonParser = new JSONServer();
        JSONArray jArr = jsonParser.GetJSONArray(url, "GET","conta");

        return jArr;

    }
}
