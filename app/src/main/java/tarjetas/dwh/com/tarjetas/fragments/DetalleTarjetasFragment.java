package tarjetas.dwh.com.tarjetas.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;
import tarjetas.dwh.com.tarjetas.DTO.TarjetasDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.DetallesTarjetasSaldosAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.DetallesTarjetaSaldosObject;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 27/12/2016.
 */

public class DetalleTarjetasFragment extends Fragment implements TarjetasApiClient.TarjetasApiGetSaldosDetalle{
    private int idTarjeta;
    private String title;
    private ImageView imgTarjeta;
    private TextView txtNombreTarjeta;
    private TextView txtNumeroTarjeta;
    private ListView lstDetalles;
    private boolean isDetalle2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_tarjeta1,container,false);
        txtNombreTarjeta = (TextView) view.findViewById(R.id.txtNomTarDetalle1);
        txtNumeroTarjeta = (TextView) view.findViewById(R.id.txtNumTarDetalle1);
        imgTarjeta       = (ImageView) view.findViewById(R.id.image_tarjeta_one);
        lstDetalles      = (ListView)   view.findViewById(R.id.lstDetalles1);


        TarjetasApiClient.getInstance().getDetalleSaldosTarjetas(idTarjeta,getContext(),this);

        return view;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        idTarjeta = bundle.getInt("idTarjeta");
        isDetalle2 = bundle.getBoolean("isDetalle2");
    }

    public DetalleTarjetasFragment(){}

    @Override
    public void onSaldoRecibido(TarjetasDTO tarjeta) {
        txtNombreTarjeta.setText(tarjeta.getTarjeta());
        txtNumeroTarjeta.setText("**** **** **** 1234");
        Picasso.with(getContext()).load(tarjeta.getImagen()).placeholder(R.drawable.bankcard).into(imgTarjeta);
        ArrayList<DetallesTarjetaSaldosObject> data = new ArrayList<DetallesTarjetaSaldosObject>();


        if (isDetalle2){
            data.add(new DetallesTarjetaSaldosObject("Saldo Inicial",String.valueOf(tarjeta.getPuntosSaldoInicial())));
            data.add(new DetallesTarjetaSaldosObject("Puntos Generados",String.valueOf(tarjeta.getpGenerados())));
            data.add(new DetallesTarjetaSaldosObject("Puntos Utilizados/Vencidos",String.valueOf(tarjeta.getpVencidos())));
            data.add(new DetallesTarjetaSaldosObject("Saldo Actual",String.valueOf(tarjeta.getSaldoActual())));
        }else{
            data.add(new DetallesTarjetaSaldosObject("Línea de Credito",String.valueOf(tarjeta.getLcredito())));
            data.add(new DetallesTarjetaSaldosObject("Saldo al Corte",String.valueOf(tarjeta.getSaldo())));
            data.add(new DetallesTarjetaSaldosObject("Crédito disponible",String.valueOf(tarjeta.getSdisponible())));
        }

        lstDetalles.setAdapter(new DetallesTarjetasSaldosAdapter(data,R.layout.object_detalle_1_list,getActivity().getApplicationContext()) {
            @Override
            public void onEntrada(Object saldo, View view) {
                TextView atriuto = (TextView) view.findViewById(R.id.txtDescripcionDetalle1);
                TextView valor = (TextView) view.findViewById(R.id.txtDetalle1);

                atriuto.setText(((DetallesTarjetaSaldosObject)saldo).getAtributo());
                valor.setText(((DetallesTarjetaSaldosObject)saldo).getValor());
            }
        });
    }

    @Override
    public void onFallaAlRecibirSaldo(mensaje error) {

    }


}
