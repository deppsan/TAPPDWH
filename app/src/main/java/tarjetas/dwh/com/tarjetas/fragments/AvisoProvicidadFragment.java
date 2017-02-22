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
 * Created by ricar on 25/01/2017.
 */

public class AvisoProvicidadFragment extends Fragment implements View.OnClickListener {
    
    private Button btnAceptar;
    private AvisoPrivacidadListener listener;

    
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_privacidad,null);
        btnAceptar = (Button) v.findViewById(R.id.btnAceptrPrivacidad);
        btnAceptar.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAceptrPrivacidad:
                listener.onAceptarPrivacidad();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AvisoPrivacidadListener) {
            listener = (AvisoPrivacidadListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface AvisoPrivacidadListener{
        void onAceptarPrivacidad();
    }
}
