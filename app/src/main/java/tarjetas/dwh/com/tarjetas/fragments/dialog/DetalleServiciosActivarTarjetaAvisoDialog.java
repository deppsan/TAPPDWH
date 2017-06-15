package tarjetas.dwh.com.tarjetas.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 28/03/2017.
 */

public class DetalleServiciosActivarTarjetaAvisoDialog extends DialogFragment {
    private DetalleServiciosActivarTarjetaAvisoListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.btnActivar).setMessage(R.string.dialogMensajeActivarTarjeta)
                .setPositiveButton(R.string.btnConfirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onAceptarActivarTarjetaAviso();
                    }
                })
                .setNegativeButton(R.string.btnCancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public interface DetalleServiciosActivarTarjetaAvisoListener{
        void onAceptarActivarTarjetaAviso();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosActivarTarjetaAvisoListener) {
            listener = (DetalleServiciosActivarTarjetaAvisoListener) context;
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
