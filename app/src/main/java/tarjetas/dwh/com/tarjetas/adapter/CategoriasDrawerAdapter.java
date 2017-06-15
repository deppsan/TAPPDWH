package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ricar on 02/05/2017.
 */

public abstract class CategoriasDrawerAdapter extends BaseAdapter {
    private ArrayList<?> categorias;
    private int _layout;
    private Context mContext;

    public CategoriasDrawerAdapter(ArrayList<?> categorias, int _layout, Context mContext) {
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
        public ImageView imageView;
    }
}
