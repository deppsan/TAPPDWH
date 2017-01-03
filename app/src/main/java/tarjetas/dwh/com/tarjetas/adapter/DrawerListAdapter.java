package tarjetas.dwh.com.tarjetas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.drawer.MenuNaviDrawer;

/**
 * Created by ricar on 16/12/2016.
 */

public class DrawerListAdapter extends ArrayAdapter<MenuNaviDrawer> {
    public DrawerListAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                                                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.object_menu_navi,null);
        }
        ImageView icon = (ImageView) convertView.findViewById(R.id.imageViewIcon);
        TextView textMenu = (TextView) convertView.findViewById(R.id.txtEntradaMenu);

        MenuNaviDrawer item = getItem(position);

        icon.setImageResource(item.getIdImage());
        textMenu.setText(item.getTextMenu());

        return convertView;
    }
}
