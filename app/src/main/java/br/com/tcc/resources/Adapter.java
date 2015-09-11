package br.com.tcc.resources;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Marcos Ribeiro on 09/05/2015.
 */
public class Adapter extends BaseAdapter {
    private Context context;
    private final int[] param;
    private final android.view.ViewGroup.LayoutParams params;

    // Método
    public Adapter(Context context, int[] fotos,
                        android.view.ViewGroup.LayoutParams params) {
        this.context = context;
        this.param = fotos;
        this.params = params;
    }

    public int getCount() {
        return param.length;
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imagem = new ImageView(context);
        imagem.setImageResource(param[position]);
        imagem.setAdjustViewBounds(true);
        imagem.setLayoutParams(params);
        return imagem;

    }
}
