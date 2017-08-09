package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.MenuServiciosDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;

/**
 * Created by ricar on 24/03/2017.
 */

public class DetalleServiciosNipMenuFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    private ListView lstMenu;
    private DetalleServiciosNipMenuListener listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_nip_menu,container,false);

        View btnRegresar = (View) v.findViewById(R.id.btnRegresarMenuServicios);
        btnRegresar.setOnClickListener(this);

        lstMenu = (ListView) v.findViewById(R.id.lstMenuNip);

        ArrayList<MenuServiciosDTO> items  = new ArrayList<>();
        items.add(new MenuServiciosDTO(R.drawable.arrow_right_red,"¿Requiere el envío de un nuevo NIP?",0));
        items.add(new MenuServiciosDTO(R.drawable.arrow_right_red,"¿Quiere asignar su propio NIP?",1));

        lstMenu.setAdapter(new TransaccionesAdapter(items,R.layout.object_detalle_servicios_servicios_nip_menu_list,getContext()) {
            @Override
            public void onEntrada(Object items, View view, int position) {
                MenuServiciosDTO item = (MenuServiciosDTO) items;

                TextView  txt = (TextView) view.findViewById(R.id.txtMenuDescripcion);
                ImageView img = (ImageView) view.findViewById(R.id.imgFlecha);

                txt.setText(item.getMenu());
                Picasso.with(getContext()).load(item.getImagen()).into(img);
            }
        });

        lstMenu.setOnItemClickListener(this);


        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MenuServiciosDTO item = (MenuServiciosDTO) lstMenu.getAdapter().getItem(position);
        listener.seleccionarOpcionMenu(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosNipMenuListener) {
            listener = (DetalleServiciosNipMenuListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onClick(View v) {
        listener.onClickRegresarRoboExtravio();
    }

    public interface DetalleServiciosNipMenuListener{
        void seleccionarOpcionMenu(MenuServiciosDTO item);
        void onClickRegresarRoboExtravio();
    }
}
