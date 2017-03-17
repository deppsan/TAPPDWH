package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import tarjetas.dwh.com.tarjetas.DTO.MenuServiciosDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;

/**
 * Created by ricar on 22/02/2017.
 */

public class DetalleServiciosFragment extends Fragment{

    ListView lstMenuServiciosPrincipal;
    DetalleServiciosListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_menu,container,false);

        lstMenuServiciosPrincipal = (ListView) v.findViewById(R.id.lstServiciosMenu);

        ArrayList<MenuServiciosDTO> datos = new ArrayList<MenuServiciosDTO>();
        datos.add(new MenuServiciosDTO(R.drawable.settings,"Controles de Tarjeta",0));
        datos.add(new MenuServiciosDTO(R.drawable.hammer,"Servicios",1));
        datos.add(new MenuServiciosDTO(R.drawable.user,"Programa de Lealtad",2));
        datos.add(new MenuServiciosDTO(R.drawable.bankcardfilled,"Activar Tarjeta",3));

        lstMenuServiciosPrincipal.setAdapter(new TransaccionesAdapter(datos,R.layout.object_servicios_menu_list,getContext()) {
            @Override
            public void onEntrada( final Object menu, View view) {
                ImageView img = (ImageView) view.findViewById(R.id.imgServiciosMenu);
                TextView text = (TextView) view.findViewById(R.id.lblServiciosMenu);

                final MenuServiciosDTO opMenu = (MenuServiciosDTO) menu;
                Picasso.with(getContext())
                        .load(((MenuServiciosDTO)menu).getImagen())
                        .placeholder(R.drawable.bankcard)
                        .into(img);
                text.setText(((MenuServiciosDTO)menu).getMenu());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        v.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.gray));

                        final Handler h = new Handler();
                        TimerTask doAsynchronusTask = new TimerTask() {
                            @Override
                            public void run() {
                                h.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        v.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.white));
                                        listener.onClickMenu(opMenu);
                                    }
                                });
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(doAsynchronusTask,100);
                    }
                });
            }
        });
        return v;
    }

    public DetalleServiciosFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface DetalleServiciosListener{
        void onClickMenu(MenuServiciosDTO menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosListener) {
            listener = (DetalleServiciosListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
