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
 * Created by ricar on 14/03/2017.
 */

public class ReporteRoboExtravioFragment extends Fragment implements View.OnClickListener {

    private View btnRegresar;
    private Button btnReporte;

    private ReporteExtravioListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reporte_robo_extravio,container,false);

        btnRegresar = (View) v.findViewById(R.id.btnRegresarMenuServicios);
        btnReporte = (Button) v.findViewById(R.id.btnReportarTarjeta);

        btnRegresar.setOnClickListener(this);
        btnReporte.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegresarMenuServicios:
                listener.onClickRegresarRoboExtravio();
                break;
            case R.id.btnReportarTarjeta:
                listener.onReportarTarjeta();
                break;
        }
    }

    public interface ReporteExtravioListener{
        void onClickRegresarRoboExtravio();
        void onReportarTarjeta();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReporteExtravioListener) {
            listener = (ReporteExtravioListener) context;
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
