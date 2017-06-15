package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;

/**
 * Created by ricar on 27/03/2017.
 */

public class DetalleServiciosActivarTarjetaFragment extends Fragment implements View.OnClickListener {

    ListView lstTarjetas;

    private DetalleServiciosActivarTarjetaListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_detalle_servicios_lista_base,container,false);

        lstTarjetas = (ListView) v.findViewById(R.id.lstServiciosMenuInterno);
        View btnRegresar = v.findViewById(R.id.btnRegresarMenuServicios);

        btnRegresar.setOnClickListener(this);

        ArrayList<ObjectAcitvarTarjeta>  data = new ArrayList<>();
        data.add(new ObjectAcitvarTarjeta(true,"1234",true));
        data.add(new ObjectAcitvarTarjeta(false,"1235",false));
        data.add(new ObjectAcitvarTarjeta(false,"1236",true));

        lstTarjetas.setAdapter(new TransaccionesAdapter(data,R.layout.object_detalle_activar_tarjeta_lista,getContext()) {
            @Override
            public void onEntrada(Object tarjetas, View view) {
                TextView tipoTarjeta = (TextView) view.findViewById(R.id.lblTipoTarjeta);
                TextView tarjetaNum = (TextView) view.findViewById(R.id.lblActivarTarjetaNumero);
                Button btnActivarTarjeta = (Button) view.findViewById(R.id.btnActivarDesactivarTarjeta);

                final ObjectAcitvarTarjeta tarjeta = (ObjectAcitvarTarjeta) tarjetas;

                if (tarjeta.isTipoTarjeta()){
                    tipoTarjeta.setText(R.string.TarjetaTitular);
                }else{
                    tipoTarjeta.setText(R.string.TarjetaAdicional);
                }

                tarjetaNum.setText(tarjeta.getTarjetaNum());

                if (!tarjeta.isStatus()){
                    btnActivarTarjeta.setText(R.string.btnActivar);
                    btnActivarTarjeta.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.green));
                }else{
                    btnActivarTarjeta.setText(R.string.btnDesactivar);
                    btnActivarTarjeta.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.red));
                }

                btnActivarTarjeta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onPresionarActivarTarjeta(v, tarjeta.getTarjetaNum());
                    }
                });

            }
        });


        return v ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegresarMenuServicios:
                    listener.onClickRegresar();
                break;
        }
    }

    public interface  DetalleServiciosActivarTarjetaListener{
        void onClickRegresar();
        void onPresionarActivarTarjeta(View view,String tarjetaNumero);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosActivarTarjetaListener) {
            listener = (DetalleServiciosActivarTarjetaListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public class ObjectAcitvarTarjeta{
        private boolean tipoTarjeta;
        private String tarjetaNum;
        private boolean status;

        public ObjectAcitvarTarjeta(boolean tipoTarjeta, String tarjetaNum, boolean status) {
            this.tipoTarjeta = tipoTarjeta;
            this.tarjetaNum = tarjetaNum;
            this.status = status;
        }

        public boolean isTipoTarjeta() {
            return tipoTarjeta;
        }

        public void setTipoTarjeta(boolean tipoTarjeta) {
            this.tipoTarjeta = tipoTarjeta;
        }

        public String getTarjetaNum() {
            return tarjetaNum;
        }

        public void setTarjetaNum(String tarjetaNum) {
            this.tarjetaNum = tarjetaNum;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
