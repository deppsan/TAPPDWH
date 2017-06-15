package tarjetas.dwh.com.tarjetas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 21/04/2017.
 */

public class CodigoDeActivacionFragment extends Fragment implements View.OnClickListener {

    EditText txtClaveActivacion;
    Button btnActivarTarjeta;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_codigo_activacion,container,false);

        txtClaveActivacion = (EditText) v.findViewById(R.id.txtCodigoActivacion);
        btnActivarTarjeta = (Button) v.findViewById(R.id.btnEvaluarCodigoActivacion);
        btnActivarTarjeta.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
