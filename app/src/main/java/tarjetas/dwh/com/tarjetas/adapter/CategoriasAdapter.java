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
 * Created by ricar on 16/05/2017.
 */

public abstract class CategoriasAdapter extends BaseAdapter {
    private ArrayList<?> categorias;
    private int _layout;
    private Context mContext;

    public CategoriasAdapter(ArrayList<?> categorias, int _layout, Context mContext) {
        this.categorias = categorias;
        this._layout = _layout;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return categorias.get(position);
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


        onEntrada(categorias.get(position),mHolder,position);

        return convertView;
    }

    public void updateData(ArrayList<?> data){
        categorias = data;
        notifyDataSetChanged();
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public abstract void onEntradaSet(View v, ViewHolder mHolder);
    public abstract void onEntrada(Object categorias, ViewHolder mHolder, int position);

    public class ViewHolder{
        public TextView montoActual;
        public ImageView imageView,imgEditar,imgGrafica;
        public TextView categoriaNombre;
    }
}
