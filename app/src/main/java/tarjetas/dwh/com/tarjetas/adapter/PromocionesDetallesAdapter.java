package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by ricar on 17/02/2017.
 */

public abstract class PromocionesDetallesAdapter extends BaseAdapter {
    private ArrayList<?> promociones;
    private int _layout;
    private Context context;

    public PromocionesDetallesAdapter(ArrayList<?> promociones, int _layout, Context context) {
        this.promociones = promociones;
        this._layout = _layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return promociones.size();
    }

    @Override
    public Object getItem(int position) {
        return promociones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater v = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView = v.inflate(_layout,null);
        }
        onEntrada(promociones.get(position),convertView);
        return convertView;
    }

    public abstract void onEntrada(Object promocione, View view);
}
