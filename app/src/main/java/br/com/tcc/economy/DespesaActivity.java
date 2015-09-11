package br.com.tcc.economy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.model.Categoria;
import br.com.tcc.model.Despesa;
import br.com.tcc.resources.AdapterDespesa;
import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;

public class DespesaActivity extends ActionBarActivity {

    TextView lblTotalDespesa;
    ListView lvListaDespesa;
    double totalDesp = 0.00;
    Config config = new Config();
    ArrayList<Despesa> listaDespesa = new ArrayList<Despesa>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        inicializaVariaveis();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        populaListView();

        lvListaDespesa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DespesaActivity.this);

                builder.setTitle("Excluir");
                builder.setMessage("Deseja mesmo excluir esse item?");
                builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Despesa desp = listaDespesa.get(position);
                        JSONObject retorno = connectionWSObj(config.getIp() + config.getwService() + "/despesas/" + desp.getIdmov() + "/" + desp.getId(),"DELETE");

                        populaListView();
                        try {
                            Toast.makeText(DespesaActivity.this, retorno.getString("msg"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {}
                    }
                });
                builder.setNeutralButton("Cancelar", null);

                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

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
        switch (item.getItemId()) {
            case R.id.novo:
                Intent inovo = new Intent(DespesaActivity.this, DespesaFormActivity.class);
                startActivity(inovo);
                return true;
            case R.id.atualizar:
                populaListView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DespesaActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void inicializaVariaveis(){
        lvListaDespesa = (ListView) findViewById(R.id.lvListDespesa);
        lblTotalDespesa = (TextView) findViewById(R.id.lblTotalDespesa);

    }



    public void populaListView(){

        listaDespesa.clear();
        totalDesp = 0.0;
        JSONArray json = connectionWS(config.getIp() + config.getwService() + "/despesas/" + config.getUsuarioLogado().getId());

        for (int i = 0; i < json.length(); i++){
            Despesa desp = new Despesa();

            try{
                desp.setId(json.getJSONObject(i).getInt("id"));
                desp.setIddefdespesa(json.getJSONObject(i).getInt("iddefdespesa"));
                desp.setIdcategoria(json.getJSONObject(i).getInt("idcategoria"));
                desp.setIdusuario(json.getJSONObject(i).getInt("idusuario"));
                desp.setDescricao(json.getJSONObject(i).getString("descricao").toString());
                desp.setValor(json.getJSONObject(i).getDouble("valor"));
                desp.setDiautil(json.getJSONObject(i).getInt("diautil"));
                desp.setRepetir(json.getJSONObject(i).getInt("repetir"));
                desp.setDatainicial(json.getJSONObject(i).getString("datainicial"));

                desp.setIdmov(json.getJSONObject(i).getInt("idmov"));
                desp.setDatamov(json.getJSONObject(i).getString("datamov"));
                desp.setPago(json.getJSONObject(i).getBoolean("pago"));

            }catch (JSONException e){
                e.printStackTrace();
            }

            listaDespesa.add(desp);

            totalDesp += desp.getValor();
        }

        lblTotalDespesa.setText("Total: R$ " + totalDesp);
        lvListaDespesa.setAdapter(new AdapterDespesa(this, listaDespesa));
    }

    public JSONArray connectionWS(String url) {
        JSONServer jsonParser = new JSONServer();
        JSONArray jArr = jsonParser.GetJSONArray(url, "GET","despesa");

        return jArr;
    }

    public JSONObject connectionWSObj(String url, String method) {
        JSONServer jsonParser = new JSONServer();
        JSONObject jObj = jsonParser.GetJSONObject(url, method, "despesa", null);

        return jObj;
    }
    /*public void message(String titulo, String msg){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(titulo);
        builder.setMessage(msg);
        builder.setNeutralButton("OK", null);
        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/
}

