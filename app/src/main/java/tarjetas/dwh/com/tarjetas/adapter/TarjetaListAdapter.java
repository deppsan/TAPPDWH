package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by ricar on 22/12/2016.
 */

public abstract class TarjetaListAdapter extends BaseAdapter {

    private ArrayList<?> tarjetas;
    private int Layout_Tarjetas_objeto;
    private Context context;

    public TarjetaListAdapter(ArrayList<?> tarjetas, int layout_Tarjetas_objeto, Context context) {
        this.tarjetas = tarjetas;
        Layout_Tarjetas_objeto = layout_Tarjetas_objeto;
        this.context = context;
    }



    @Override
    public int getCount() {
        return tarjetas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarjetas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(Layout_Tarjetas_objeto,null);
        }

        onEntrada(tarjetas.get(position),convertView);
        return convertView;
    }

    public abstract void onEntrada(Object tarjeta, View view);
}
