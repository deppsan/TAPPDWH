package tarjetas.dwh.com.tarjetas.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 19/04/2017.
 */

public class DetalleServiciosControlLineaConfirmarModificarLineaDialog extends DialogFragment {

    private String linea;
    private DetalleServiciosControlLineaConfirmarAumentoLineaDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String mensaje = "Su línea a quedado en $" + linea + ", ¿Está de acuerdo?";

        builder.setTitle(R.string.dialogTituloConfirmarAumetarLinea).setMessage(mensaje)
                .setPositiveButton(R.string.btnConfirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onClickAceptarAumentoLinea();
                    }
                })
                .setNegativeButton(R.string.btnCancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onClickCancelarAumentoLinea();
                    }
                });

        return builder.create();
    }

    public static DetalleServiciosControlLineaConfirmarModificarLineaDialog getInstance(String linea){
        DetalleServiciosControlLineaConfirmarModificarLineaDialog dialog = new DetalleServiciosControlLineaConfirmarModificarLineaDialog();
        dialog.setLinea(linea);

        return dialog;
    }

    public interface DetalleServiciosControlLineaConfirmarAumentoLineaDialogListener{
        void onClickAceptarAumentoLinea();
        void onClickCancelarAumentoLinea();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosControlLineaConfirmarAumentoLineaDialogListener) {
            listener = (DetalleServiciosControlLineaConfirmarAumentoLineaDialogListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }
}
