package tarjetas.dwh.com.tarjetas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 03/01/2017.
 */

public class FormularioUsuarioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_formulario,container,false);

        return v;
    }
}
