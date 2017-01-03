package tarjetas.dwh.com.tarjetas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 29/12/2016.
 */

public class DetalleTarjetaGrafico extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_tarjeta2,container,false);

        return v;
    }
}
