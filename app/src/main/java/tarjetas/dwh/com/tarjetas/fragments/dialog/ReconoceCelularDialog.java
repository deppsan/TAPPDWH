package tarjetas.dwh.com.tarjetas.fragments.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 02/01/2017.
 */

public class ReconoceCelularDialog extends DialogFragment {
    public ReconoceCelularFragmentListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.preguntaCelular)
                .setPositiveButton(R.string.continuarCuenta, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.continuarConLaCuenta();
                    }
                })
                .setNegativeButton(R.string.crearCuentaNueva, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.crearNuevaCuenta();
                    }
                })
                .setTitle(R.string.TitleApp);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ReconoceCelularFragmentListener){
            listener = (ReconoceCelularFragmentListener) context;
        }else{
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ReconoceCelularFragmentListener{
        void crearNuevaCuenta();
        void continuarConLaCuenta();
    }
}
