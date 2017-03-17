    package tarjetas.dwh.com.tarjetas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.NotificacionesDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.NotificacionesDetallesAdapter;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 16/02/2017.
 */

public class DetalleNotificacionesFragment extends Fragment implements TarjetasApiClient.TarjetasApiNotificacionesListener {
    private ListView lstNotificaciones;
    private int idTarjeta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_notificaciones,container,false);
        lstNotificaciones = (ListView) v.findViewById(R.id.lstDetalleNotificaciones);

        TarjetasApiClient.getInstance().getNotificacionesPorTarjeta("",getContext(),this);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        idTarjeta = bundle.getInt("idTarjeta");
    }

    public DetalleNotificacionesFragment(){}

    @Override
    public void onNotificacionesRecividas(ArrayList<NotificacionesDTO> notificaciones) {
        lstNotificaciones.setAdapter(new NotificacionesDetallesAdapter(notificaciones,R.layout.object_notificaciones_list,getActivity().getApplicationContext()) {
            @Override
            public void onEntrada(Object notificacion, View view) {
                TextView txtNotificacion = (TextView) view.findViewById(R.id.lblDetallesNotificaciones);
                txtNotificacion.setText(((NotificacionesDTO)notificacion).getFecha()+" - " + ((NotificacionesDTO)notificacion).getDescripcion());

               /* if (((NotificacionesDTO) notificacion).getStatus() == 0){
                        txtNotificacion.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
                }*/
            }
        });
    }

    @Override
    public void onNotificacionesFalla() {

    }
}
