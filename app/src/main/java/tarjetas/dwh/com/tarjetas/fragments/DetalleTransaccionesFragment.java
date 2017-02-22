package tarjetas.dwh.com.tarjetas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.TransaccionesDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.TransaccionesObjectDrawer;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 13/02/2017.
 */

public class DetalleTransaccionesFragment extends Fragment implements TarjetasApiClient.TarjetasApiTransaccionesListener {

    private ListView lstTransacciones;
    private int idTarjeta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_transacciones,container,false);
        lstTransacciones = (ListView) v.findViewById(R.id.lstTransacciones);

        lstTransacciones.setOnItemClickListener(new onTransaccionSeleccionada());


        TarjetasApiClient.getInstance().getTransaccionesTarjeta("",getContext(),this);
        return v;
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        idTarjeta = bundle.getInt("idTarjeta");
    }
    public DetalleTransaccionesFragment(){}


    @Override
    public void onTransaccionesRecividas(ArrayList<TransaccionesDTO> transacciones) {
        ArrayList<TransaccionesObjectDrawer> data = new ArrayList<TransaccionesObjectDrawer>();

        for (TransaccionesDTO t : transacciones){
            data.add(new TransaccionesObjectDrawer(t.getFechaTransaccion(),t.getDetalleTransaccion(),t.getValorTransaccion(),t.getPuntos(),t.getCategoria()));
        }
        lstTransacciones.setAdapter(new TransaccionesAdapter(data,R.layout.object_transacciones_listado,getActivity().getApplicationContext()) {
            @Override
            public void onEntrada(Object saldo, View view) {
                TextView fecha = (TextView) view.findViewById(R.id.horaTransaccion);
                TextView descripcion = (TextView) view.findViewById(R.id.TiendaTransaccion);
                TextView gasto = (TextView) view.findViewById(R.id.TransaccionGasto);
                TextView puntos = (TextView) view.findViewById(R.id.TransaccionPuntos);
                ImageView categoria = (ImageView) view.findViewById((R.id.imgCategoria));
                int imagenFlag = R.drawable.flaggray;

                fecha.setText(((TransaccionesObjectDrawer)saldo).getFechaTransaccion());
                descripcion.setText(((TransaccionesObjectDrawer)saldo).getDetalleTransaccion());
                gasto.setText(((TransaccionesObjectDrawer)saldo).getMontoTransaccion());
                puntos.setText(((TransaccionesObjectDrawer)saldo).getPuntosTransaccion());

                switch (((TransaccionesObjectDrawer)saldo).getColorCategoria()){
                    case 1:
                        imagenFlag = R.drawable.flagblack;
                        break;
                    case 2:
                        imagenFlag = R.drawable.flagblue;
                        break;
                    case 3:
                        imagenFlag = R.drawable.flagred;
                        break;
                    case 4:
                        imagenFlag = R.drawable.flaggreen;
                        break;
                }

                Picasso.with(getContext()).load(imagenFlag).into(categoria);

            }
        });

    }

    private void tarjetaSeleccionada(int position){
        Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    private class onTransaccionSeleccionada implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            tarjetaSeleccionada(position);
        }
    }

    @Override
    public void onTransaccionesFalla() {

    }


}
