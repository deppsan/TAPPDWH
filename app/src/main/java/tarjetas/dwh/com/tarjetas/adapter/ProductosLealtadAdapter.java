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
 * Created by ricar on 14/08/2017.
 */

public abstract class ProductosLealtadAdapter extends BaseAdapter {

    private ArrayList<?> productos;
    private int _layout;
    private Context mContext;

    public ProductosLealtadAdapter(ArrayList<?> productos, int _layout, Context context) {
        this.productos = productos;
        this._layout = _layout;
        this.mContext = context;
    }


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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;

        if (convertView == null){
            try{
                LayoutInflater v = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        return convertView;
    }
    public abstract void onEntradaSet(View v, ViewHolder mHolder);
    public abstract void onEntrada(Object productos, ViewHolder mHolder, int position);


    public class ViewHolder{
        public TextView descpcion;
        public int id;
    }
}
