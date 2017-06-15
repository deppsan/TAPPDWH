package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by ricar on 16/02/2017.
 */

public abstract class NotificacionesDetallesAdapter extends BaseAdapter {
    private ArrayList<?> notificaciones;
    private int _layout;
    private Context context;

    public NotificacionesDetallesAdapter(ArrayList<?> notificaciones, int _layout, Context context) {
        this.notificaciones = notificaciones;
        this._layout = _layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notificaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return notificaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater v = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView = v.inflate(_layout,null);
        }
        onEntrada(notificaciones.get(position),convertView);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    public abstract  void onEntrada(Object notificacion, View view);
}
