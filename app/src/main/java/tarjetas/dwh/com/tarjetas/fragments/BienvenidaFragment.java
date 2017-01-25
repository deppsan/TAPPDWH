package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.fragments.dialog.OpcSolMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ReconoceCelularDialog;
import tarjetas.dwh.com.tarjetas.utilities.ExpValidator;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;

/**
 * Created by ricar on 30/12/2016.
 */

public class BienvenidaFragment extends Fragment implements  View.OnClickListener, View.OnTouchListener {

    Button btnRevisarTarjeta;
    EditText txtTarjetaBienvenida;
    View frameSeccionBienvenida;

    BienvenidaFragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_bienvenida,null);

        txtTarjetaBienvenida = (EditText) v.findViewById(R.id.txtCelularBienvenida);
        btnRevisarTarjeta = (Button) v.findViewById(R.id.btnRevisarCelular);
        frameSeccionBienvenida = (View) v.findViewById(R.id.frameSeccionBienvenida);

        btnRevisarTarjeta.setOnTouchListener(this);
        btnRevisarTarjeta.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRevisarCelular:
                revisaCelular(txtTarjetaBienvenida.getText().toString(),v);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.btnRevisarCelular:
                YoYo.with(Techniques.Pulse).duration(50).playOn(btnRevisarTarjeta);
                break;
        }
        return false;
    }

    private void revisaCelular(String numTarjeta, View anchorView){
        if (numTarjeta.length() != 16){

            Toast.makeText(getContext(), "El numero de tarjeta no es valido, debe de contener al menos 16 caracteres.", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(200).playOn(frameSeccionBienvenida);

        }else if(numTarjeta.equals("8114678345")){

            listener.getCeluar(numTarjeta);
            DialogFragment dialog = new ReconoceCelularDialog();
            dialog.show(getFragmentManager(), FragmentTags.BIENVENIDA_DIALOGO_CELULAR_FRAGMENT);

        }else{
            listener.getCeluar(numTarjeta);
            listener.crearNuevaCuenta();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BienvenidaFragmentListener){
            listener = (BienvenidaFragmentListener) context;
        }else{
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface BienvenidaFragmentListener{
        void getCeluar(String celular);
        void crearNuevaCuenta();
    }
}
