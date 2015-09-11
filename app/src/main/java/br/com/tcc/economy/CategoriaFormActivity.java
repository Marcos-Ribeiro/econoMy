package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.tcc.model.Categoria;
import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;


public class CategoriaFormActivity extends ActionBarActivity {

    EditText edtDescricao;
    Config config = new Config();
    Categoria categ = new Categoria();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_form);

        inicializaVariaveis();

        Intent i = getIntent();
        categ = (Categoria) i.getSerializableExtra("categoria");

        if(categ != null){
            edtDescricao.setText(categ.getDescricao());
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_form, menu);
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
            case R.id.salvar:
                if (categ != null) {
                    gravar("PUT", categ);
                }else{
                    gravar("POST", categ);
                }
                return true;

            case R.id.cancelar:
                Intent icancelar = new Intent(CategoriaFormActivity.this, CategoriaActivity.class);
                startActivity(icancelar);
                return true;
            case R.id.deletar:
                Intent ideletar = new Intent(CategoriaFormActivity.this, CategoriaActivity.class);
                startActivity(ideletar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CategoriaFormActivity.this, CategoriaActivity.class);
        startActivity(intent);
    }

    public String connectionWS(String url, String type, String metodo, String jsonParam) {
        JSONServer jsonParser = new JSONServer();
        jsonParser.GetJSONObject(url, metodo, type, jsonParam);
        return "OK";
    }

    public void inicializaVariaveis(){
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
    }


    public void gravar(String metodo,Categoria categ){
        try {
            JSONObject jsonEnvio = new JSONObject();
            jsonEnvio.put("id",categ.getId().toString());
            jsonEnvio.put("descricao", edtDescricao.getText().toString());

            String retorno = connectionWS(config.getIp() + config.getwService() + "/categorias","categoria",metodo, jsonEnvio.toString());

            if (retorno == "OK"){
                Intent isalvar = new Intent(CategoriaFormActivity.this, CategoriaActivity.class);
                startActivity(isalvar);
            }else{
                Toast.makeText(this,"Atenção! Erro ao salvar categoria!",Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
