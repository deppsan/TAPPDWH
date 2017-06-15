package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.ProgramaLealtadObject;

/**
 * Created by ricar on 10/04/2017.
 */

public class DetalleServiciosProgramaLealtadFragment extends Fragment implements View.OnClickListener {

    private View btnRegresar;
    private ListView lstProgramaLealtad;
    private DetalleServiciosProgramaLealtadListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_programa_lealtad,container,false);
        btnRegresar = (View) v.findViewById(R.id.btnRegresarMenuServicios);
        lstProgramaLealtad = (ListView) v.findViewById(R.id.lstProgramaLealtad);

        btnRegresar.setOnClickListener(this);

        ArrayList<ProgramaLealtadObject> datos = new ArrayList<>();

        datos.add(new ProgramaLealtadObject("http://blog.homedepot.com.mx/wp-content/uploads/2014/06/estufas_interiores.jpg","Estufa piso Mabe","52,957"));
        datos.add(new ProgramaLealtadObject("http://currys.cdn.dixons.com/css/themes/apple_ipad_air/img/ipad-intro.png","iPad 64 gb","76,670"));
        datos.add(new ProgramaLealtadObject("http://2.wlimg.com/product_images/bc-full/dir_99/2945407/home-theater-1056595.jpg","Teatro en casa LG","94,332"));


        lstProgramaLealtad.setAdapter(new TransaccionesAdapter(datos,R.layout.object_servicios_programa_lealtad_list,getContext()) {
            @Override
            public void onEntrada(Object datos, View view) {
                ImageView img;
                TextView descripcion;
                TextView precio;

                ProgramaLealtadObject dato = (ProgramaLealtadObject) datos;


                img = (ImageView) view.findViewById(R.id.imgPorgramaLealtad);
                descripcion = (TextView) view.findViewById(R.id.lblDescripcionProgLealtad);
                precio = (TextView) view.findViewById(R.id.lblPrecioProgLealtad);

                precio.setText(dato.getPrecio());
                descripcion.setText(dato.getDescripcion());
                Picasso.with(getContext()).load(dato.getImg()).resize(150,150).into(img);
            }
        });




        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegresarMenuServicios:
                listener.onClickRegresar();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosProgramaLealtadListener) {
            listener = (DetalleServiciosProgramaLealtadListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public interface DetalleServiciosProgramaLealtadListener{
        void onClickRegresar();
    }
}
