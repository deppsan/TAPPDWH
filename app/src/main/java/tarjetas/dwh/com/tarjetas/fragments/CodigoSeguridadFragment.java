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

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 07/01/2017.
 */

public class CodigoSeguridadFragment extends Fragment implements  View.OnClickListener, TarjetasApiClient.TarjetasApiRevisarCodigoSeguridadListener {

    EditText txtCodigoSeguridad;
    Button  btnRevisarCodigo;
    CodigoSeguridadListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_codigo_seguridad,null);

        txtCodigoSeguridad = (EditText) v.findViewById(R.id.txtCodigoSeguridad);
        btnRevisarCodigo = (Button) v.findViewById(R.id.btnEvaluarCodigoSeguridad);
        btnRevisarCodigo.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEvaluarCodigoSeguridad:
                TarjetasApiClient.getInstance().revisarCodigoSeguridad(txtCodigoSeguridad.getText().toString(),getContext(),this);
                break;
        }
    }

    @Override
    public void onCodigoCorrecto() {
        listener.onCodigoCorrecto();
    }

    @Override
    public void onCodigoIncorrecto() {
        listener.onCodigoIncorrecto();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CodigoSeguridadListener) {
            listener = (CodigoSeguridadListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface CodigoSeguridadListener{
        void onCodigoCorrecto();
        void onCodigoIncorrecto();
    }
}
