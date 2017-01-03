package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by ricar on 28/12/2016.
 */

public abstract class DetallesTarjetasSaldosAdapter extends BaseAdapter {
    private ArrayList<?> Saldos;
    private int _Layout;
    private Context context;

    public DetallesTarjetasSaldosAdapter(ArrayList<?> saldos, int _Layout, Context context) {
        Saldos = saldos;
        this._Layout = _Layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Saldos.size();
    }

    @Override
    public Object getItem(int position) {
        return Saldos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater v =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = v.inflate(_Layout,null);
        }
        onEntrada(Saldos.get(position),convertView);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public abstract void onEntrada(Object saldo, View view);
}
