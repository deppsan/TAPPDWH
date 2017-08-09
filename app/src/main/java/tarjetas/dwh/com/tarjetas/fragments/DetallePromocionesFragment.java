package tarjetas.dwh.com.tarjetas.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.PromocionesDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.PromocionesDetallesAdapter;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 17/02/2017.
 */

public class DetallePromocionesFragment extends Fragment implements TarjetasApiClient.TarjetasApiPromocionesListener{

    private ListView lstPromociones;
    private int idTarjeta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detalle_promociones,container,false);
        lstPromociones = (ListView) v.findViewById(R.id.lstDetallePromociones);

        TarjetasApiClient.getInstance().getPromocionesPorTarjeta("",getContext(),this);

        return v;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        idTarjeta = bundle.getInt("idTarjeta");
    }

    public DetallePromocionesFragment() {
    }

    @Override
    public void onPromocionesRevicidas(final ArrayList<PromocionesDTO> promociones) {
        lstPromociones.setAdapter(new PromocionesDetallesAdapter(promociones,R.layout.object_promociones_list,getActivity().getApplicationContext()) {
            @Override
            public void onEntrada(Object promocione, View view) {
                ImageView imgTienda = (ImageView) view.findViewById(R.id.imgPromociones);
                TextView txtDescripcion = (TextView) view.findViewById(R.id.lblPromociones);


                Picasso picasso = Picasso.with(getContext());
                int imagenTipo = 0;

                switch (((PromocionesDTO)promocione).getImgTienda()){
                    case 1:
                        imagenTipo = R.drawable.liverpool_face;
                        break;
                    case 2:
                        imagenTipo = R.drawable.travel;
                        break;
                    case 3:
                        imagenTipo = R.drawable.restaurant;
                }

                picasso.load(imagenTipo).placeholder(R.drawable.markfilled).into(imgTienda);
                txtDescripcion.setText(((PromocionesDTO)promocione).getDescripcion());
            }
        });
    }
}
