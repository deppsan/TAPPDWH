package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.MenuServiciosDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;

/**
 * Created by ricar on 14/03/2017.
 */

public class DetalleServiciosSubMenuFragment extends Fragment implements View.OnClickListener{

    ListView lstMenu;
    private DetalleServiciosSubMenuListener listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_servicios_lista_base, container, false);

        lstMenu = (ListView) view.findViewById(R.id.lstServiciosMenuInterno);
        View btnRegresar = view.findViewById(R.id.btnRegresarMenuServicios);

        ArrayList<MenuServiciosDTO> menu = new ArrayList<>();

        menu.add(new MenuServiciosDTO(R.drawable.error,"Reporte de Robo y Extravío",0));
        menu.add(new MenuServiciosDTO(R.drawable.dollar_bill,"Saldos y Pagos",1));
        menu.add(new MenuServiciosDTO(R.drawable.bankcardfilled,"Control de linea de Credito",2));
        menu.add(new MenuServiciosDTO(R.drawable.pinkeyboard,"Asignación del Nip",3));

        lstMenu.setAdapter(new TransaccionesAdapter(menu,R.layout.object_servicios_menu_list,getContext()) {
            @Override
            public void onEntrada(final Object menu, View view) {
                MenuServiciosDTO optMenu = (MenuServiciosDTO) menu;

                TextView txtDescripcion = (TextView) view.findViewById(R.id.lblServiciosMenu);
                ImageView imgMenu = (ImageView) view.findViewById(R.id.imgServiciosMenu);

                txtDescripcion.setText(optMenu.getMenu());
                Picasso.with(getContext()).load(optMenu.getImagen()).placeholder(R.drawable.bankcard).into(imgMenu);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSeleccionarOpcionMenu(menu,v);
                    }
                });
            }
        });
        btnRegresar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegresarMenuServicios:
                listener.onClickRegresar();
                break;
        }
    }

    public interface DetalleServiciosSubMenuListener{
        void onSeleccionarOpcionMenu(Object menu, View view);
        void onClickRegresar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosSubMenuListener) {
            listener = (DetalleServiciosSubMenuListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener= null;
    }
}
