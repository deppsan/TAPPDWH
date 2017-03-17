package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;

/**
 * Created by ricar on 13/02/2017.
 */

public abstract class TransaccionesAdapter extends BaseAdapter {

    private ArrayList<?> transacciones;
    private int _layout;
    private Context context;

    public TransaccionesAdapter(ArrayList<?> transacciones, int _layout, Context context) {
        this.transacciones = transacciones;
        this._layout = _layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return transacciones.size();
    }

    @Override
    public Object getItem(int position) {
        return transacciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater v = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = v.inflate(_layout,null);
        }
        onEntrada(transacciones.get(position),convertView);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public abstract void onEntrada(Object saldo, View view);


}
