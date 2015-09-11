package br.com.tcc.economy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.tcc.model.Usuario;
import br.com.tcc.resources.Config;
import br.com.tcc.server.JSONServer;

public class LoginActivity extends Activity {

    EditText edtLogin, edtSenha;
    Button btnLogin, btnConfig;
    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaVariaveis();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = edtLogin.getText().toString();
                String pin = edtSenha.getText().toString();

                try{

                    JSONObject json = connectionWS(config.getIp() + config.getwService() + "/login/" + user + "/" + pin + "");

                    if (json != null){
                        Usuario usuario = new Usuario();

                        usuario.setId(json.getInt("id"));
                        usuario.setNome(json.getString("nome").toString());
                        usuario.setPin(json.getString("pin"));

                        config.setUsuarioLogado(usuario);

                        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                        startActivity(intent);

                    }else if (json == null){
                        message("Atenção!", "Usuário ou senha inválidos!");
                    }

                }catch (JSONException e){
                    message("ERRO: ", "Detalhes Técnicos: \n" + e.toString());
                }
            }
        });

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ConfigActivity.class);
                startActivity(intent);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


    public void inicializaVariaveis(){

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnConfig = (Button) findViewById(R.id.btnConfig);

        edtLogin.setText("faesb");
        edtSenha.setText("faesb2015");
    }

    public void message(String titulo, String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(titulo);
        builder.setMessage(msg);
        builder.setNeutralButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public JSONObject connectionWS(String url) {
        JSONServer jsonParser = new JSONServer();
        JSONObject jObj = jsonParser.GetJSONObject(url,"GET","usuario",null);

        return jObj;

    }
}
