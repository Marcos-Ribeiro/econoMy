package br.com.tcc.economy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.tcc.resources.DatePickerFragment;


public class ExtratoActivity extends ActionBarActivity {

    EditText edtDataInicial, edtDataFinal;
    Spinner spinner;
    Button btnExtrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        inicializaVariaveis();

        String[] values = new String[] {"Banco do Brasil","Bradesco"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        spinner.setAdapter(adapter);


        btnExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtratoActivity.this, ExtratoImpressoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_extrato, menu);
        return false;
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
        Intent intent = new Intent(ExtratoActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void inicializaVariaveis(){

        edtDataInicial = (EditText) findViewById(R.id.edtDataInicial);
        edtDataFinal = (EditText) findViewById(R.id.edtDataFinal);
        btnExtrato = (Button) findViewById(R.id.btnExtrato);
        spinner = (Spinner) findViewById(R.id.spinner);

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
