package br.com.tcc.resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.tcc.economy.R;
import br.com.tcc.model.Conta;

/**
 * Created by Marcos Ribeiro on 13/08/2015.
 */
public class AdapterConta extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Conta> itens;

    /*CONSTRUTOR*/
    public AdapterConta(Context context, List<Conta> i){
        this.itens = i;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itens.get(position).getId();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final ListViewCampos campos;
        //se a view estiver nula (nunca criada), inflamos o layout nela.
        if (v == null){
            v = mInflater.inflate(R.layout.list_item,null);

            campos = new ListViewCampos();

            campos.campo1 = (TextView) v.findViewById(R.id.lblCampo1);
            campos.campo2 = (TextView) v.findViewById(R.id.lblCampo2);
            campos.campo3 = (TextView) v.findViewById(R.id.lblCampo3);
            campos.campo4 = (TextView) v.findViewById(R.id.lblCampo4);
            campos.lblPago = (TextView) v.findViewById(R.id.lblPago);

            v.setTag(campos);
        } else{
            //se a view já existe pega os itens.
            campos = (ListViewCampos) v.getTag();
            v.setTag(campos);
        }

        //pega os dados da lista //e define os valores nos itens.
        Conta item = itens.get(position);

        campos.campo1.setText(item.getDescricao());
        campos.campo2.setText("Código: " + item.getId().toString());
        campos.campo3.setVisibility(View.INVISIBLE);
        campos.campo4.setVisibility(View.INVISIBLE);
        campos.lblPago.setVisibility(View.INVISIBLE);

        return v;
    }

}
