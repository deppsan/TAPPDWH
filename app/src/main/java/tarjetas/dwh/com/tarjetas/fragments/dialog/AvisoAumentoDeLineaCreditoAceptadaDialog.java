package tarjetas.dwh.com.tarjetas.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 17/03/2017.
 */

public class AvisoAumentoDeLineaCreditoAceptadaDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.titleDialogAumentarLinea)
                .setMessage(R.string.mensajeAumentoDeLineaAceptada)
                .setPositiveButton(R.string.btnAceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
