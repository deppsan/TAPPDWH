package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ricar on 06/04/2017.
 */

public abstract class ServiciosControlAdapter extends BaseAdapter {


    private ArrayList<?> controles;
    private int _layout;
    private Context context;

    public ServiciosControlAdapter(ArrayList<?> controles, int _layout, Context context) {
        this.controles = controles;
        this._layout = _layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return controles.size();
    }

    @Override
    public Object getItem(int position) {
        return controles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView == null){
            try{

                LayoutInflater v = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = v.inflate(_layout,null);
                mHolder = new ViewHolder();

                onEntradaSet(convertView,mHolder);
                convertView.setTag(mHolder);

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            mHolder = (ViewHolder) convertView.getTag();
        }


        onEntrada(controles.get(position),mHolder,position);

        return convertView;
    }


    public abstract void onEntradaSet(View v, ViewHolder mHolder);
    public abstract void onEntrada(Object control, ViewHolder mHolder,int position);

    public static class ViewHolder{
        public TextView txtTitulo;
        public TextView txtDescripcion;
        public Switch swtControl;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
