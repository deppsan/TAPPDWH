package tarjetas.dwh.com.tarjetas.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 28/03/2017.
 */

public class DetalleServiciosActivarTarjetaActivadaDialog extends DialogFragment {

    private int tarjetaNumero;
    private DetalleServiciosActivarTarjetaActivadaListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        tarjetaNumero = getArguments().getInt("tarjetaNumero");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Resources reso = getResources();
        String string1  = reso.getString(R.string.dialogMensajeActivaciones1);
        String string2 = reso.getString(R.string.dialogMensajeActivaciones2);
        builder.setTitle(R.string.dialogTituloActivaciones)
                .setMessage(string1 + " " + tarjetaNumero + " " +string2 + " Visa Infinite")
                .setPositiveButton(R.string.btnAceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     listener.aceptarActivarTarjeta();
                    }
                });
        return builder.create();
    }

    public interface DetalleServiciosActivarTarjetaActivadaListener{
        void aceptarActivarTarjeta();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosActivarTarjetaActivadaListener) {
            listener = (DetalleServiciosActivarTarjetaActivadaListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static DetalleServiciosActivarTarjetaActivadaDialog newInstance(int num){
        DetalleServiciosActivarTarjetaActivadaDialog instance = new DetalleServiciosActivarTarjetaActivadaDialog();
        Bundle args = new Bundle();

        args.putInt("tarjetaNumero",num);
        instance.setArguments(args);

        return instance;
    }
}
