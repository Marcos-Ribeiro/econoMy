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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.tcc.model.Categoria;
import br.com.tcc.resources.AdapterCategoria;
import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;


public class CategoriaActivity extends ActionBarActivity {

    Config config = new Config();
    ListView lvListCategoria;
    ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        inicializaVariaveis();

        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        populaListView();

        lvListCategoria.setClickable(true);
        lvListCategoria.setOnItemClickListener( new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Categoria categ = (Categoria) parent.getItemAtPosition(position);
                Intent i = new Intent(CategoriaActivity.this, CategoriaFormActivity.class);
                i.putExtra("categoria", categ);

                startActivity(i);

            }
        });


        lvListCategoria.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CategoriaActivity.this);

                builder.setTitle("Excluir");
                builder.setMessage("Deseja mesmo excluir esse item?");
                builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Categoria categ = listaCategoria.get(position);
                        JSONObject retorno = connectionWSObj(config.getIp() + config.getwService() + "/categorias/" + categ.getId(),"DELETE");

                        populaListView();
                        try {
                            Toast.makeText(CategoriaActivity.this, retorno.getString("msg"), Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.menu_categoria, menu);
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
        switch (item.getItemId()) {
            case R.id.novo:
                Intent inovo = new Intent(CategoriaActivity.this, CategoriaFormActivity.class);
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
        Intent intent = new Intent(CategoriaActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

    public JSONArray connectionWS(String url, String method) {
        JSONServer jsonParser = new JSONServer();
        JSONArray jArr = jsonParser.GetJSONArray(url, method, "categoria");

        return jArr;
    }

    public JSONObject connectionWSObj(String url, String method) {
        JSONServer jsonParser = new JSONServer();
        JSONObject jObj = jsonParser.GetJSONObject(url, method, "categoria", null);

        return jObj;
    }

    public void inicializaVariaveis(){
        lvListCategoria = (ListView) findViewById(R.id.lvListCategoria);
    }

    public void populaListView(){

        listaCategoria.clear();

        JSONArray json = connectionWS(config.getIp() + config.getwService() + "/categorias", "GET");

        for (int i = 0; i < json.length(); i++){
            Categoria categ = new Categoria();

            try{
                categ.setDescricao(json.getJSONObject(i).getString("descricao").toString());
                categ.setId(json.getJSONObject(i).getInt("id"));
            }catch (JSONException e){
                e.printStackTrace();
            }

            listaCategoria.add(categ);
        }

        lvListCategoria.setAdapter(new AdapterCategoria(this, listaCategoria));

    }

}
