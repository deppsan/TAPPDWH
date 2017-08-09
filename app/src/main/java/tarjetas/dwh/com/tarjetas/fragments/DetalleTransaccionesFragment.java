package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import tarjetas.dwh.com.tarjetas.utilities.FormatCurrency;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

/**
 * Created by ricar on 13/02/2017.
 */

public class DetalleTransaccionesFragment extends Fragment implements TarjetasApiClient.TarjetasApiTransaccionesListener, AdapterView.OnItemLongClickListener {

    private ListView lstTransacciones;
    private int idTarjeta;

    private DetalleTransaccionesListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_transacciones,container,false);
        lstTransacciones = (ListView) v.findViewById(R.id.lstTransacciones);

        lstTransacciones.setOnItemLongClickListener(this);


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
            data.add(new TransaccionesObjectDrawer(t.getId(),t.getFechaTransaccion(),t.getDetalleTransaccion(),t.getValorTransaccion(),t.getPuntos(),t.getCategoria()));
        }
        lstTransacciones.setAdapter(new TransaccionesAdapter(data,R.layout.object_transacciones_listado,getActivity().getApplicationContext()) {
            @Override
            public void onEntrada(Object saldo, View view, int position) {
                TextView fecha = (TextView) view.findViewById(R.id.horaTransaccion);
                TextView descripcion = (TextView) view.findViewById(R.id.TiendaTransaccion);
                TextView gasto = (TextView) view.findViewById(R.id.TransaccionGasto);
                TextView puntos = (TextView) view.findViewById(R.id.TransaccionPuntos);
                ImageView categoria = (ImageView) view.findViewById((R.id.imgCategoria));

                fecha.setText(((TransaccionesObjectDrawer)saldo).getFechaTransaccion());
                descripcion.setText(((TransaccionesObjectDrawer)saldo).getDetalleTransaccion());
                gasto.setText(FormatCurrency.getFormatCurrency(((TransaccionesObjectDrawer)saldo).getMontoTransaccion()));
                puntos.setText(((TransaccionesObjectDrawer)saldo).getPuntosTransaccion());

                Picasso.with(getContext()).load(RealmAdministrator.getInstance(getContext()).getDrawableByCategory(((TransaccionesObjectDrawer)saldo).getColorCategoria(),false)).into(categoria);

            }
        });

    }

    @Override
    public void onTransaccionesFalla() {}


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        listener.onLongPressTransaccion(position,(TransaccionesAdapter)parent.getAdapter());
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleTransaccionesListener) {
            listener = (DetalleTransaccionesListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null ;
    }

    public interface DetalleTransaccionesListener{
        void onLongPressTransaccion(int position, TransaccionesAdapter adapter);
    }
}
