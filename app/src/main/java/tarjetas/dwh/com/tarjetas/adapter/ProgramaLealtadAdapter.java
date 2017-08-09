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
 * Created by ricar on 07/08/2017.
 */

public abstract class ProgramaLealtadAdapter extends BaseAdapter {

    private ArrayList<?> productos;
    private int _layout;
    private Context context;

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ProgramaLealtadAdapter(ArrayList<?> productos, int _layout, Context context) {
        this.productos = productos;
        this._layout = _layout;
        this.context = context;
    }

    public void actualizarDatos(ArrayList<?> productos){
        this.productos = productos;
        this.notifyDataSetChanged();
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


        onEntrada(productos.get(position),mHolder,position);

        return convertView;    }


    public abstract void onEntradaSet(View v, ViewHolder mHolder);
    public abstract void onEntrada(Object producto, ViewHolder mHolder,int position);

    public class ViewHolder{
        public ImageView productImage, favoriteImage;
        public TextView amount, description;
    }
}
