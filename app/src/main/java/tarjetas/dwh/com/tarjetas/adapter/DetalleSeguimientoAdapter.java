package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ricar on 18/07/2017.
 */

public abstract class DetalleSeguimientoAdapter extends BaseAdapter {

    private ArrayList<?> seguimientos;
    private int _layout;
    private Context context;

    public DetalleSeguimientoAdapter(ArrayList<?> seguimientos, int _layout, Context context) {
        this.seguimientos = seguimientos;
        this._layout = _layout;
        this.context = context;
    }

    public void actualizarDatos(ArrayList<?> seguimientos){
        this.seguimientos = seguimientos;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return seguimientos.size();
    }

    @Override
    public Object getItem(int position) {
        return seguimientos.get(position);
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


        onEntrada(seguimientos.get(position),mHolder,position);

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public abstract void onEntradaSet(View v, ViewHolder mHolder);
    public abstract void onEntrada(Object control, ViewHolder mHolder, int position);


    public class ViewHolder{

        public TextView fecha;

        public TextView titulo;

        public TextView mensaje;

        public ImageView img_seguimiento;
    }
}
