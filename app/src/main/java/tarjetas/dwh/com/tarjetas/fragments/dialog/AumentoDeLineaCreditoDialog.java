package tarjetas.dwh.com.tarjetas.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.utilities.FormatCurrency;

/**
 * Created by ricar on 16/03/2017.
 */

public class AumentoDeLineaCreditoDialog extends DialogFragment {

    private int idTarjeta;
    private double maxLineaCredito;

    private AumentoDeLineaCreditoListener listener;

    public static AumentoDeLineaCreditoDialog getInstance(double maxLineaCredito){
        AumentoDeLineaCreditoDialog aumentoDeLineaCreditoDialog = new AumentoDeLineaCreditoDialog();
        aumentoDeLineaCreditoDialog.setMaxLineaCredito(maxLineaCredito);

        return aumentoDeLineaCreditoDialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String text1 = getContext().getString(R.string.mensajeDialogAumentarLinea1);
        String text2 = getContext().getString(R.string.mensajeDialogAumentarLinea2);

        builder.setMessage(text1 + " " + FormatCurrency.getFormatCurrency(maxLineaCredito)+ " " + text2).setTitle(R.string.titleDialogAumentarLinea)
                .setPositiveButton(R.string.btnConfirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onAumentarLineaCredito();
                    }
                })
                .setNegativeButton(R.string.btnCancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public interface AumentoDeLineaCreditoListener{
        void onAumentarLineaCredito();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AumentoDeLineaCreditoListener) {
            listener = (AumentoDeLineaCreditoListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public double getMaxLineaCredito() {
        return maxLineaCredito;
    }

    public void setMaxLineaCredito(double maxLineaCredito) {
        this.maxLineaCredito = maxLineaCredito;
    }
}
