package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 24/03/2017.
 */

public class DetalleServiciosSolicitarNipFragment extends Fragment implements View.OnClickListener {
    Button btnSolicitarNip;
    private DetalleServiciosSolicitarNipListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_solicitar_nip,container,false);



        View btnRegresar = (View) v.findViewById(R.id.btnRegresarMenuServicios);
        btnRegresar.setOnClickListener(this);

        btnSolicitarNip = (Button) v.findViewById(R.id.btnSolicitarNip);
        btnSolicitarNip.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSolicitarNip:
                listener.solicitarNipABanco();
                break;
            case R.id.btnRegresarMenuServicios:
                listener.regresarASubMenuServicios();
                break;
        }
    }

    public interface DetalleServiciosSolicitarNipListener{
        void solicitarNipABanco();
        void regresarASubMenuServicios();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosSolicitarNipListener) {
            listener = (DetalleServiciosSolicitarNipListener) context;
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
