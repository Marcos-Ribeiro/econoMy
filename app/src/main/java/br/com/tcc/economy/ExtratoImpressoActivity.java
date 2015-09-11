package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.tcc.model.Despesa;
import br.com.tcc.resources.AdapterDespesa;


public class ExtratoImpressoActivity extends ActionBarActivity {
    //private SimpleAdapter sa;
    private ListView list;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato_impresso);

        inicializaVariaveis();

        ArrayList<Despesa> listaDespesa = new ArrayList<Despesa>();

        Despesa desp = new Despesa();

        desp.setValor(10.0);
        desp.setDescricao("lalalalalal 111");
        desp.setId(1);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(20.0);
        desp.setDescricao("lalalalalal 2222");
        desp.setId(2);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 3333");
        desp.setId(3);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 3333");
        desp.setId(3);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 4444");
        desp.setId(4);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 55");
        desp.setId(5);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 66666");
        desp.setId(6);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 77777");
        desp.setId(7);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 88888");
        desp.setId(8);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 99");
        desp.setId(9);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 10");
        desp.setId(10);
        listaDespesa.add(desp);

        desp = new Despesa();
        desp.setValor(5.0);
        desp.setDescricao("lalalalalal 11");
        desp.setId(11);
        listaDespesa.add(desp);

        list.setAdapter(new AdapterDespesa(this, listaDespesa));

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtratoImpressoActivity.this, ExtratoActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_extrato_impresso, menu);
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
        Intent intent = new Intent(ExtratoImpressoActivity.this, ExtratoActivity.class);
        startActivity(intent);
    }

    public void inicializaVariaveis(){
        list = (ListView) findViewById(R.id.listaExtrato);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

    }
}
