package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 13/04/2017.
 */

public class DetalleServiciosSaldosPagosFragment extends Fragment implements View.OnClickListener{

    private LinearLayout lstSeccion1, lstSeccion2, lstSeccion3;
    private DetalleServiciosSaldosPagosListener listener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_servicios_saldos_pagos,container,false);

        lstSeccion1 = (LinearLayout) v.findViewById(R.id.lstPagosSeccion1);
        lstSeccion2 = (LinearLayout) v.findViewById(R.id.lstPagosSeccion2);
        lstSeccion3 = (LinearLayout) v.findViewById(R.id.lstPagosSeccion3);

        View btnRegresar = v.findViewById(R.id.btnRegresarMenuServicios);
        btnRegresar.setOnClickListener(this);

        ArrayList<SaldosBase> lstDato1 = new ArrayList<>();
        ArrayList<SaldosBase> lstDato2 = new ArrayList<>();
        ArrayList<SaldosBase> lstDato3 = new ArrayList<>();

        lstDato1.add(new SaldosBase("Periodo","26 de Mayo al 24 de Junio, 2017"));
        lstDato1.add(new SaldosBase("Fecha de Corte","24 Junio 2017"));

        lstDato2.add(new SaldosBase("Pago mínimo","$980.00"));
        lstDato2.add(new SaldosBase("Pago para no generar intereses","$9,675.20"));
        lstDato2.add(new SaldosBase("Pago mínimo + MSI","$3,678.21"));


        lstDato3.add(new SaldosBase("Saldo Anterior","$3,493.82"));
        lstDato3.add(new SaldosBase("Compras y programas a plazo","$8,564.00"));
        lstDato3.add(new SaldosBase("Disposición en Efectivo","$1,000.00"));
        lstDato3.add(new SaldosBase("Pagos y depósitos","$2,500.00"));
        lstDato3.add(new SaldosBase("Intereses","$543.18"));
        lstDato3.add(new SaldosBase("Comisiones","$0.00"));
        lstDato3.add(new SaldosBase("IVA","$0.00"));
        lstDato3.add(new SaldosBase("Saldo al Corte","$7,787.90"));
        lstDato3.add(new SaldosBase("Saldo Programas a Plazo","$3,400.00"));
        lstDato3.add(new SaldosBase("Saldo Final del Periodo","$11,187.90"));

        for (SaldosBase l : lstDato1){
            View vistalista = inflater.inflate(R.layout.object_detalle_servicios_saldos_pagos_list, null);

            TextView s = (TextView) vistalista.findViewById(R.id.lblCantidadSaldosPagos);
            TextView c = (TextView) vistalista.findViewById(R.id.lblConceptoSaldosPagos);

            s.setText(l.getSaldo());
            c.setText(l.getTitulo());

            lstSeccion1.addView(vistalista);

        }
        for (SaldosBase l : lstDato2){
            View vistalista = inflater.inflate(R.layout.object_detalle_servicios_saldos_pagos_list, null);

            TextView s = (TextView) vistalista.findViewById(R.id.lblCantidadSaldosPagos);
            TextView c = (TextView) vistalista.findViewById(R.id.lblConceptoSaldosPagos);

            s.setText(l.getSaldo());
            c.setText(l.getTitulo());

            lstSeccion2.addView(vistalista);

        }
        for (SaldosBase l : lstDato3){
            View vistalista = inflater.inflate(R.layout.object_detalle_servicios_saldos_pagos_list, null);

            TextView s = (TextView) vistalista.findViewById(R.id.lblCantidadSaldosPagos);
            TextView c = (TextView) vistalista.findViewById(R.id.lblConceptoSaldosPagos);

            s.setText(l.getSaldo());
            c.setText(l.getTitulo());

            lstSeccion3.addView(vistalista);

        }
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegresarMenuServicios:
                listener.onClickRegresarRoboExtravio();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosSaldosPagosListener) {
            listener = (DetalleServiciosSaldosPagosListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public interface DetalleServiciosSaldosPagosListener{
        void onClickRegresarRoboExtravio();
    }


    private class SaldosBase{
        private String titulo;
        private String saldo;

        public SaldosBase(String titulo, String saldo) {
            this.titulo = titulo;
            this.saldo = saldo;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getSaldo() {
            return saldo;
        }

        public void setSaldo(String saldo) {
            this.saldo = saldo;
        }
    }
}
