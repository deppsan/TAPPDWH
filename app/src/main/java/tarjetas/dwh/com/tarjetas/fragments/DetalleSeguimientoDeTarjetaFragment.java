package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * Created by ricar on 26/03/2017.
 */

public class DetalleSeguimientoDeTarjetaFragment extends Fragment implements View.OnClickListener {

    private ListView lstSeguimiento;
    private DetalleSeguimientoDeTarjetaListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_lista_base,container,false);

        lstSeguimiento = (ListView) v.findViewById(R.id.lstServiciosMenuInterno);
        View btnRegresar = v.findViewById(R.id.btnRegresarMenuServicios);

        ArrayList<item> descripciones = new ArrayList<>();
        descripciones.add(new item("Tramite","by Banorte on 11 Apr","Tu tarjeta número 8765 ya fue grabada en nuestro centro de personalización."));
        descripciones.add(new item("Mensajeria","by Banorte on 11 Apr","Tu tarjeta numero 8765 ya fue asignada a la mensajeria DHL para su entrega, te deberá llegar en 5 días hábiles."));
        descripciones.add(new item("Tramite","by Banorte on 11 Apr","Tu tarjeta número 8765 ya fue grabada en nuestro centro de personalización."));
        descripciones.add(new item("Mensajeria","by Banorte on 11 Apr","Tu tarjeta numero 8765 ya fue asignada a la mensajeria DHL para su entrega, te deberá llegar en 5 días hábiles."));
        descripciones.add(new item("Tramite","by Banorte on 11 Apr","Tu tarjeta número 8765 ya fue grabada en nuestro centro de personalización."));
        descripciones.add(new item("Mensajeria","by Banorte on 11 Apr","Tu tarjeta numero 8765 ya fue asignada a la mensajeria DHL para su entrega, te deberá llegar en 5 días hábiles."));

        lstSeguimiento.setAdapter(new TransaccionesAdapter(descripciones,R.layout.object_servicios_seguimiento_tarjeta,getContext()) {
            @Override
            public void onEntrada(Object descripcion, View view) {
                item item1 = (item) descripcion;
                TextView fecha = (TextView) view.findViewById(R.id.lblSeguimientoFecha);
                fecha.setText(item1.getFecha());
                TextView titulo = (TextView) view.findViewById(R.id.lblSeguimientoTitulo);
                titulo.setText(item1.getTitulo());
                TextView mensaje = (TextView) view.findViewById(R.id.lblSeguimientoMensaje);
                mensaje.setText(item1.getMensaje());
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        });
        View lstFooter = inflater.inflate(R.layout.object_detalle_seguimiento_boton_final,container,false);
        Button btnActivar = (Button) lstFooter.findViewById(R.id.btnActivarDesdeSeguimiento);
        lstSeguimiento.addFooterView(lstFooter);

        btnRegresar.setOnClickListener(this);
        btnActivar.setOnClickListener(this);


        return v;
    }

    private class item{
        String titulo,fecha,mensaje;

        public item(String titulo, String fecha, String mensaje) {
            this.titulo = titulo;
            this.fecha = fecha;
            this.mensaje = mensaje;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getFecha() {
            return fecha;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegresarMenuServicios:
                listener.onClickRegresar();
                break;
            case R.id.btnActivarDesdeSeguimiento:
                listener.seleccionarActivarTarjetaDesdeSeguimiento();
                break;
        }
    }

    public interface DetalleSeguimientoDeTarjetaListener{
        void onClickRegresar();
        void seleccionarActivarTarjetaDesdeSeguimiento();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleSeguimientoDeTarjetaListener) {
            listener = (DetalleSeguimientoDeTarjetaListener) context;
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
