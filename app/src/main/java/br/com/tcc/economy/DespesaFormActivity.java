package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;


public class DespesaFormActivity extends ActionBarActivity {

    TextView edtDescricao, edtValor, edtDataVencimento;
    Spinner spinCategoria, spinConta;
    Switch swPago, swFixo;
    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa_form);

        inicializaVariaveis();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONArray jsonCateg = connectionWS(config.getIp() + config.getwService() + "/categorias","categoria");
        JSONArray jsonConta = connectionWS(config.getIp() + config.getwService() + "/contas/" + config.getUsuarioLogado().getId(),"conta");

        ArrayList<String> categs = new ArrayList<>();
        ArrayList<String> contas = new ArrayList<>();

        try {

            for (int i = 0; i < jsonCateg.length(); i++) {
                categs.add(jsonCateg.getJSONObject(i).getString("descricao"));
            }
            for (int i = 0; i < jsonConta.length(); i++) {
                contas.add(jsonConta.getJSONObject(i).getString("descricao"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterCateg = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, categs);

        ArrayAdapter<String> adapterConta = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, contas);

        spinCategoria.setAdapter(adapterCateg);
        spinConta.setAdapter(adapterConta);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_despesa_form, menu);
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
        Intent intent = new Intent(DespesaFormActivity.this, DespesaActivity.class);
        startActivity(intent);
    }

    public void inicializaVariaveis(){
        edtDescricao = (TextView) findViewById(R.id.edtDescricao);
        edtValor = (TextView) findViewById(R.id.edtValor);
        edtDataVencimento = (TextView) findViewById(R.id.edtDataInicial);

        spinCategoria = (Spinner) findViewById(R.id.spinCategoria);
        spinConta = (Spinner) findViewById(R.id.spinConta);

        swFixo = (Switch) findViewById(R.id.swFixo);
        swPago = (Switch) findViewById(R.id.swPago);

        Date dtAtual = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String data = DateFormat.getDateInstance().format(new Date());

        edtDataVencimento.setText(data);

    }

    public JSONArray connectionWS(String url, String classe) {
        JSONServer jsonParser = new JSONServer();
        JSONArray jArr = jsonParser.GetJSONArray(url, "GET",classe);

        return jArr;
    }

    public JSONObject connectionWSObj(String url, String method) {
        JSONServer jsonParser = new JSONServer();
        JSONObject jObj = jsonParser.GetJSONObject(url, method, "despesa", null);

        return jObj;
    }

}
