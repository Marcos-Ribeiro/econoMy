package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.model.Despesa;
import br.com.tcc.model.Receita;
import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;

public class PrincipalActivity extends ActionBarActivity {

    TextView lblTotalDespesas, lblTotalReceitas;
    Double totalDesp = 0.0, totalRec=0.0;
    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        inicializaVariaveis();

        List<Despesa> lDesp;
        lDesp = getDespesas();
        List<Receita> lRec;
        lRec = getReceitas();

        for (int i = 0; i < lDesp.size(); i++){
            totalDesp +=lDesp.get(i).getValor();
        }
        for (int i = 0; i < lRec.size(); i++){
            totalRec+=lRec.get(i).getValor();
        }

        lblTotalDespesas.setText("R$ " + totalDesp.toString());
        lblTotalReceitas.setText("R$ " + totalRec.toString());

  }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return super.onCreateOptionsMenu(menu);
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
        switch (item.getItemId()) {
            case R.id.receita:
                Intent ireceita = new Intent(PrincipalActivity.this, ReceitaActivity.class);
                startActivity(ireceita);
                return true;
            case R.id.despesa:
                Intent idespesa = new Intent(PrincipalActivity.this, DespesaActivity.class);
                startActivity(idespesa);
                return true;
            case R.id.saldo:
                Intent isaldo = new Intent(PrincipalActivity.this, ExtratoActivity.class);
                startActivity(isaldo);
                return true;
            case R.id.categoria:
                Intent icateg = new Intent(PrincipalActivity.this, CategoriaActivity.class);
                startActivity(icateg);
                return true;
            case R.id.conta:
                Intent iconta = new Intent(PrincipalActivity.this, ContaActivity.class);
                startActivity(iconta);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        config.setUsuarioLogado(null);
        Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void inicializaVariaveis(){

        lblTotalDespesas = (TextView) findViewById(R.id.lblTotalDespesas);
        lblTotalReceitas = (TextView) findViewById(R.id.lblTotalReceitas);
  }

    public List<Despesa> getDespesas()
    {
        JSONArray json = connectionWS(config.getIp() + config.getwService() + "/despesas/" + config.getUsuarioLogado().getId(),"despesa");
        ArrayList<Despesa> listaDespesa = new ArrayList<Despesa>();

        for (int i = 0; i < json.length(); i++){
            Despesa desp = new Despesa();

            try{
                desp.setValor(json.getJSONObject(i).getDouble("valor"));
                desp.setDescricao(json.getJSONObject(i).getString("descricao").toString());
                desp.setId(json.getJSONObject(i).getInt("id"));
            }catch (JSONException e){
                e.printStackTrace();
            }

            listaDespesa.add(desp);
        }
        return listaDespesa;
    }

    public List<Receita> getReceitas()
    {
        JSONArray json = connectionWS(config.getIp() + config.getwService() + "/receitas/" + config.getUsuarioLogado().getId(), "receita");
        ArrayList<Receita> listaReceita = new ArrayList<Receita>();

        for (int i = 0; i < json.length(); i++){
            Receita receita = new Receita();

            try{
                receita.setValor(json.getJSONObject(i).getDouble("valor"));
                receita.setDescricao(json.getJSONObject(i).getString("descricao").toString());
                receita.setId(json.getJSONObject(i).getInt("id"));
            }catch (JSONException e){
                e.printStackTrace();
            }

            listaReceita.add(receita);
        }
        return listaReceita;
    }

    public JSONArray connectionWS(String url, String type) {
        JSONServer jsonParser = new JSONServer();
        JSONArray jArr = jsonParser.GetJSONArray(url, "GET",  type);

        return jArr;
    }
}
