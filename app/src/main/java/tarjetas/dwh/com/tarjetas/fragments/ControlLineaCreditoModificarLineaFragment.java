package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.utilities.FormatCurrency;

/**
 * Created by ricar on 19/04/2017.
 */

public class ControlLineaCreditoModificarLineaFragment extends Fragment implements View.OnClickListener {

    Button btnAceptar, btnCancelar;
    EditText txtAumentoLinea;
    ControlLineaCreditoModificarLineaListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_control_linea_credito_aumentar_linea,container,false);

        LinearLayout lstModificarLinea = (LinearLayout) v.findViewById(R.id.lstAumentoLinea);
        btnAceptar = (Button) v.findViewById(R.id.btnAceptarModificarLinea);
        btnCancelar = (Button) v.findViewById(R.id.btnCancelarModificarLinea);
        txtAumentoLinea = (EditText) v.findViewById(R.id.txtAumentoLinea);

        View btnRegresar = v.findViewById(R.id.btnRegresarMenuServicios);


        btnCancelar.setOnClickListener(this);
        btnAceptar.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);

        ArrayList<itemLista> datos = new ArrayList<>();
        datos.add(new itemLista("Línea Titual Total", FormatCurrency.getFormatCurrency(50000.00)));
        datos.add(new itemLista("Tarjeta Adi. 1 7890",""));
        datos.add(new itemLista("Nombre de la Tarjeta Adicional",""));
        datos.add(new itemLista("Línea Actual",FormatCurrency.getFormatCurrency(50000.00)));
        datos.add(new itemLista("Línea Nueva",""));


        for (itemLista l : datos){
            View vistalista = inflater.inflate(R.layout.object_detalle_servicios_saldos_pagos_list, null);

            TextView s = (TextView) vistalista.findViewById(R.id.lblCantidadSaldosPagos);
            TextView c = (TextView) vistalista.findViewById(R.id.lblConceptoSaldosPagos);

            s.setText(l.getSaldo());
            c.setText(l.getDescripcion());

            lstModificarLinea.addView(vistalista);

        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancelarModificarLinea:
                listener.onModificarLineaCancelar();
                break;
            case R.id.btnAceptarModificarLinea:
                String aumento = txtAumentoLinea.getText().toString();
                listener.onModificarLineaAceptar(aumento);
                break;
            case R.id.btnRegresarMenuServicios:
                listener.onModificarLineaCancelar();
                break;
        }
    }

    private class itemLista{
        String descripcion;
        String saldo;

        public itemLista(String descripcion, String saldo) {
            this.descripcion = descripcion;
            this.saldo = saldo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getSaldo() {
            return saldo;
        }

        public void setSaldo(String saldo) {
            this.saldo = saldo;
        }
    }

    public interface ControlLineaCreditoModificarLineaListener{
        void onModificarLineaAceptar(String cantidad);
        void onModificarLineaCancelar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ControlLineaCreditoModificarLineaListener) {
            listener = (ControlLineaCreditoModificarLineaListener) context;
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
