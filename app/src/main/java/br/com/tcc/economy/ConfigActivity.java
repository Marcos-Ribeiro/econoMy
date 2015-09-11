package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.tcc.resources.Config;

public class ConfigActivity extends ActionBarActivity {

    EditText edtIp,edtWebService;
    Button btnGravarConfig;
    Config config = new Config();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        inicializaVariaveis();

        btnGravarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                config.setIp(edtIp.getText().toString());
                config.setwService(edtWebService.getText().toString());

                Intent intent = new Intent(ConfigActivity.this, LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_config, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ConfigActivity.this, LoginActivity.class);
        startActivityForResult(intent, 1);
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

        edtIp = (EditText) findViewById(R.id.edtIp);
        edtWebService = (EditText) findViewById(R.id.edtWebService);
        btnGravarConfig = (Button) findViewById(R.id.btnGravarConfig);

        edtIp.setText(config.getIp());
        edtWebService.setText(config.getwService());

    }
}
