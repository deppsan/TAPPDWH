package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;
import tarjetas.dwh.com.tarjetas.DTO.TarjetasDTO;
import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TarjetaListAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.TarjetaListaObject;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 22/12/2016.
 */

public class TarjetasListaFragment extends Fragment implements TarjetasApiClient.TarjetasApiGetMisTarjetasListener {

    private ListView lstTarjetas;
    private usuarioDTO user;
    TarjetasListaFragmentListener fragmentListener;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mis_tarjetas,container,false);
        user = TarjetasApiClient.gson.fromJson(getArguments().getString("user"),usuarioDTO.class);
        lstTarjetas = (ListView) v.findViewById(R.id.LstTarjetas);

        TarjetasApiClient.getInstance().getListaMisTarjetas(user.getId_User(),v.getContext(),this);
        lstTarjetas.setOnItemClickListener(new onTarjetaSelected());

        return v;
    }

    public void tarjetaSeleccionada(int position){

        TarjetaListaObject tarjeta = (TarjetaListaObject) lstTarjetas.getAdapter().getItem(position);

        fragmentListener.onGotoTarjetasDetalle(tarjeta);


    }

    private class onTarjetaSelected implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            tarjetaSeleccionada(position);
        }
    }

    @Override
    public void onTarjetasRecibidas(TarjetasDTO[] tarjetasArray) {
        ArrayList<TarjetaListaObject> tarjetas = new ArrayList<TarjetaListaObject>();

        for(TarjetasDTO tarjeta: tarjetasArray) {
            tarjetas.add(new TarjetaListaObject(tarjeta.getImagen(), tarjeta.getTarjeta(),tarjeta.getId_c()));
        }

        lstTarjetas.setAdapter(new TarjetaListAdapter(tarjetas,R.layout.object_tarjetas_list, getActivity().getApplicationContext()) {
            @Override
            public void onEntrada(Object tarjeta, View view) {
                TextView text = (TextView) view.findViewById(R.id.txtNombreTarjeta);
                ImageView image = (ImageView) view.findViewById(R.id.imgTarjetaLista);

                text.setText(((TarjetaListaObject) tarjeta).getTarjetaNombre());
                Picasso.with(getContext())
                        .load(((TarjetaListaObject) tarjeta).getIdImagen())
                        .placeholder(R.drawable.bankcard)
                        .into(image);

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TarjetasListaFragmentListener){
            fragmentListener = (TarjetasListaFragmentListener) context;
        }else{
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Override
    public void onFallaEnTarjetasRecibidas(mensaje error) {

    }

    public interface TarjetasListaFragmentListener{
        void onGotoTarjetasDetalle(TarjetaListaObject tarjeta);
    }
}
