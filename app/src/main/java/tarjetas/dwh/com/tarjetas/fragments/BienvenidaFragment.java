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
import tarjetas.dwh.com.tarjetas.fragments.dialog.EmailMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.OpcSolMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.SMSMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.SinOpcionesDeSeguridadDialog;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

/**
 * Created by ricar on 30/12/2016.
 */

public class BienvenidaFragment extends Fragment implements  View.OnClickListener, View.OnTouchListener, TarjetasApiClient.TarjetasApiRevisarTarjeta {

    Button btnRevisarTarjeta;
    EditText txtTarjetaBienvenida;
    View frameSeccionBienvenida;

    DialogFragment dialog = null;

    BienvenidaListener listener ;

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
                verificacionTarjeta(txtTarjetaBienvenida.getText().toString());
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

    private void verificacionTarjeta(String numTarjeta){
        if (numTarjeta.length() != 16){
            Toast.makeText(getContext(), "El numero de tarjeta no es valido, debe de contener al menos 16 caracteres.", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(200).playOn(frameSeccionBienvenida);
        }else{
            listener.setTarjetaSharedPreference(numTarjeta);
            TarjetasApiClient.getInstance().confirmarTarjeta(numTarjeta,getContext(),this);
        }
    }

    private void guardarTarjeta(String token){
        RealmAdministrator.getInstance(getContext()).guardarTarjeta(txtTarjetaBienvenida.getText().toString(),token);
    }

    @Override
    public void onTarjetaRevisadaExitosamente(int opcion, String token) {
        boolean ver = false;
        switch (opcion){
            case 1:
                dialog = new OpcSolMetodoSeguridadDialog();
                ver = true;
                break;
            case 2:
                dialog = new SMSMetodoSeguridadDialog();
                ver = true;
                break;
            case 3:
                dialog = new EmailMetodoSeguridadDialog();
                ver = true;
                break;
            default:
                dialog = new SinOpcionesDeSeguridadDialog();
                break;
        }
        if (ver){
            guardarTarjeta(token);
        }
        dialog.show(getFragmentManager(),FragmentTags.BIENVENIDA_FRAGMENT);
    }

    public interface BienvenidaListener{
        void setTarjetaSharedPreference(String numTerjeta);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BienvenidaListener) {
            listener = (BienvenidaListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onTarjetaFallo() {
        Toast.makeText(getContext(), "Error al verificar la tarjeta de credito Banorte", Toast.LENGTH_SHORT).show();
    }
}
