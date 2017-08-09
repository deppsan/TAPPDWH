package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.DetalleSeguimientoAdapter;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;

/**
 * Created by ricar on 26/03/2017.
 */

public class DetalleSeguimientoDeTarjetaFragment extends Fragment implements View.OnClickListener {

//    private ListView lstSeguimiento;
    private DetalleSeguimientoDeTarjetaListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_vista_base,container,false);

//        lstSeguimiento = (ListView) v.findViewById(R.id.lstServiciosMenuInterno);
        LinearLayout lstSeguimiento = (LinearLayout) v.findViewById(R.id.lstBase);
        View btnRegresar = inflater.inflate(R.layout.object_button_regresar_,container,false);

        ArrayList<item> descripciones = new ArrayList<>();
        descripciones.add(new item("Tramite","by Banorte on 11 Apr","Tu tarjeta número 8765 ya fue grabada en nuestro centro de personalización."));
        descripciones.add(new item("Mensajeria","by Banorte on 11 Apr","Tu tarjeta numero 8765 ya fue asignada a la mensajeria DHL para su entrega, te deberá llegar en 5 días hábiles."));
        descripciones.add(new item("Tramite","by Banorte on 11 Apr","Tu tarjeta número 8765 ya fue grabada en nuestro centro de personalización."));
        descripciones.add(new item("Mensajeria","by Banorte on 11 Apr","Tu tarjeta numero 8765 ya fue asignada a la mensajeria DHL para su entrega, te deberá llegar en 5 días hábiles."));
        descripciones.add(new item("Tramite","by Banorte on 11 Apr","Tu tarjeta número 8765 ya fue grabada en nuestro centro de personalización."));
        descripciones.add(new item("Mensajeria","by Banorte on 11 Apr","Tu tarjeta numero 8765 ya fue asignada a la mensajeria DHL para su entrega, te deberá llegar en 5 días hábiles."));



        /*lstSeguimiento.setAdapter(new DetalleSeguimientoAdapter(descripciones,R.layout.object_servicios_seguimiento_tarjeta_one,getContext()) {
            @Override
            public void onEntradaSet(View v, ViewHolder mHolder) {
                mHolder.fecha = (TextView) v.findViewById(R.id.lblSeguimientoFecha);
                mHolder.titulo = (TextView) v.findViewById(R.id.lblSeguimientoTitulo);
                mHolder.mensaje = (TextView) v.findViewById(R.id.lblSeguimientoMensaje);
                mHolder.img_seguimiento = (ImageView) v.findViewById(R.id.img_seguimiento);
            }

            @Override
            public void onEntrada(Object control, ViewHolder mHolder, int position) {
                item item1 = (item) control;

                mHolder.mensaje.setText(item1.getMensaje());
                mHolder.titulo.setText(item1.getTitulo());
                mHolder.fecha.setText(item1.getFecha());

                if (position == (getCount() - 1)){
                    mHolder.img_seguimiento.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.check_marker));
                }
            }
            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        });*/


        View lstFooter = inflater.inflate(R.layout.object_detalle_seguimiento_boton_final,container,false);
        Button btnActivar = (Button) lstFooter.findViewById(R.id.btnActivarDesdeSeguimiento);

        lstSeguimiento.addView(btnRegresar);

        for (item i : descripciones){



            View view = inflater.inflate(R.layout.object_servicios_seguimiento_tarjeta_one,container,false);
            TextView lblSeguimientoFecha = (TextView) view.findViewById(R.id.lblSeguimientoFecha);
            TextView lblSeguimientoTitulo = (TextView) view.findViewById(R.id.lblSeguimientoTitulo);
            TextView lblSeguimientoMensaje = (TextView) view.findViewById(R.id.lblSeguimientoMensaje);
            ImageView img_seguimiento = (ImageView) view.findViewById(R.id.img_seguimiento);


            lblSeguimientoMensaje.setText(i.getMensaje());
            lblSeguimientoTitulo.setText(i.getTitulo());
            lblSeguimientoFecha.setText(i.getFecha());

            if (descripciones.get(descripciones.size() -1) == i){
                img_seguimiento.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.check_marker));
            }

            lstSeguimiento.addView(view);
        }

        lstSeguimiento.addView(lstFooter);


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
            case R.id.btnObjectRegresar:
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
