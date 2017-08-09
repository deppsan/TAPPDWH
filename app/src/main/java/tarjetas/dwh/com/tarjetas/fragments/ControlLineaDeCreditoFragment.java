package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;
import tarjetas.dwh.com.tarjetas.model.Tarjeta;
import tarjetas.dwh.com.tarjetas.utilities.FormatCurrency;

/**
 * Created by ricar on 15/03/2017.
 */

public class ControlLineaDeCreditoFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ControlLineaDeCreditoListener listener;
    
    private View btnRegresar;
    private ListView lstControlLineaCredito;
    private TextView lblTotalLineaCredito;
    private View ViewTitular;

    private double totalLineaCredito;
    private ArrayList<Double> lineasAmount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_control_linea_credito,container,false);
        
        btnRegresar = inflater.inflate(R.layout.object_button_regresar_,container,false);
        lstControlLineaCredito = (ListView) v.findViewById(R.id.lstControlLineaCredito);


        ViewTitular = inflater.inflate(R.layout.object_control_linea_credito_tarjeta_titular,null,false);
        lblTotalLineaCredito = (TextView) ViewTitular.findViewById(R.id.lblLineaTitularTotal);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             listener.onClickRegresarRoboExtravio();
            }
        });
        lblTotalLineaCredito.setText("70000");

        ArrayList<TarjetasLineas> tarjetasLineases = new ArrayList<>();

        tarjetasLineases.add(new TarjetasLineas(1234,true,50000));
        tarjetasLineases.add(new TarjetasLineas(1235,false,10000));
        tarjetasLineases.add(new TarjetasLineas(1236,false,10000));


        for (TarjetasLineas tl : tarjetasLineases){
            totalLineaCredito += tl.getLimiteLinea();
        }


        lblTotalLineaCredito.setText(FormatCurrency.getFormatCurrency(totalLineaCredito));

        lstControlLineaCredito.setAdapter(new TransaccionesAdapter(tarjetasLineases,R.layout.object_detalle_control_linea_credito_list,getContext()) {
            @Override
            public void onEntrada(Object Lineas, View view, int position) {
                final TarjetasLineas linea = (TarjetasLineas) Lineas;

                TextView txtTipoLinea = (TextView) view.findViewById(R.id.lblTipoTarjeta);
                TextView txtNumTarjeta = (TextView) view.findViewById(R.id.lblNumeroTarjeta);
                TextView txtLimiteCredito = (TextView) view.findViewById(R.id.lblLineaCredito);

                if (linea.isTitutlar()){
                    txtTipoLinea.setText(R.string.TarjetaTitular);
                }else{
                    txtTipoLinea.setText(R.string.TarjetaAdicional);
                }

                txtNumTarjeta.setText(String.valueOf(linea.getNumTarjeta()));

                txtLimiteCredito.setText(FormatCurrency.getFormatCurrency(linea.getLimiteLinea()));

                TextView btnModificarLinea  = (TextView) view.findViewById(R.id.btnModificarLinea);
                btnModificarLinea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSeleccionarModificarLineaCredito(linea);
                    }
                });
            }
        });
        View footer = inflater.inflate(R.layout.object_control_linea_credito_final,null,false);

        lstControlLineaCredito.addHeaderView(btnRegresar);
        lstControlLineaCredito.addHeaderView(ViewTitular);
        lstControlLineaCredito.addFooterView(footer);

        lstControlLineaCredito.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listener.onSeleccionarAumentarLimitedeLineadeCredito(totalLineaCredito);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ControlLineaDeCreditoListener) {
            listener = (ControlLineaDeCreditoListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    public interface ControlLineaDeCreditoListener{
        void onSeleccionarModificarLineaCredito(TarjetasLineas linea);
        void onSeleccionarAumentarLimitedeLineadeCredito(double totalLineaDeCredito);
        void onClickRegresarRoboExtravio();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public class TarjetasLineas{
        private int numTarjeta;
        private boolean isTitutlar;
        private double limiteLinea;

        public TarjetasLineas(int numTarjeta, boolean isTitutlar, double limiteLinea) {
            this.numTarjeta = numTarjeta;
            this.isTitutlar = isTitutlar;
            this.limiteLinea = limiteLinea;
        }

        public int getNumTarjeta() {
            return numTarjeta;
        }

        public void setNumTarjeta(int numTarjeta) {
            this.numTarjeta = numTarjeta;
        }

        public boolean isTitutlar() {
            return isTitutlar;
        }

        public void setTitutlar(boolean titutlar) {
            isTitutlar = titutlar;
        }

        public double getLimiteLinea() {
            return limiteLinea;
        }

        public void setLimiteLinea(double limiteLinea) {
            this.limiteLinea = limiteLinea;
        }
    }
}
