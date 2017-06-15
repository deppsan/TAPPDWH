package tarjetas.dwh.com.tarjetas.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tarjetas.dwh.com.tarjetas.R;

/**
 * Created by ricar on 27/03/2017.
 */

public class DetalleServiciosIngresarNipFragment extends Fragment implements View.OnClickListener {

    private DetalleServiciosIngresarNipListener listener;
    private Button btnVerificarnNip;
    private Dialog dialog;
    private String a,b,c,d;
    private EditText txtNIPA,txtNIPB,txtNIPC,txtNIPD, txtDiaNIPA, txtDiaNIPB, txtDiaNIPC, txtDiaNIPD;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_ingresar_nip,container,false);

        View btnRegresar = (View) v.findViewById(R.id.btnRegresarMenuServicios);
        btnVerificarnNip = (Button) v.findViewById(R.id.btnVerificarnNip);

        txtNIPA = (EditText) v.findViewById(R.id.txtNIPA);
        txtNIPB = (EditText) v.findViewById(R.id.txtNIPB);
        txtNIPC = (EditText) v.findViewById(R.id.txtNIPC);
        txtNIPD = (EditText) v.findViewById(R.id.txtNIPD);

        btnVerificarnNip.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnRegresarMenuServicios:
                listener.regresarASubMenuServicios();
                break;
            case R.id.btnVerificarnNip:
                a = txtNIPA.getText().toString();
                b = txtNIPB.getText().toString();
                c = txtNIPC.getText().toString();
                d = txtNIPD.getText().toString();

                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_detalle_confirmar_ingresar_nip);
                Button btn = (Button) dialog.findViewById(R.id.btnCancelarIngresarNIPDialog);

                txtDiaNIPA = (EditText) dialog.findViewById(R.id.txtDiaNIPA);
                txtDiaNIPB = (EditText) dialog.findViewById(R.id.txtDiaNIPB);
                txtDiaNIPC = (EditText) dialog.findViewById(R.id.txtDiaNIPC);
                txtDiaNIPD = (EditText) dialog.findViewById(R.id.txtDiaNIPD);

                btn.setOnClickListener(this);
                dialog.show();

                break;
            case R.id.btnCancelarIngresarNIPDialog:
                dialog.dismiss();
                break;
            case R.id.btnConfirmarIngresarNIPDialog:
                boolean ver = true;
                if (!a.equals(txtDiaNIPA.getText().toString())){
                    ver = false;
                }else if (!b.equals(txtDiaNIPB.getText().toString())){
                    ver = false;
                }else if (!c.equals(txtDiaNIPC.getText().toString())){
                    ver = false;
                }else if (!d.equals(txtDiaNIPD.getText().toString())){
                    ver = false;
                }

                if (!ver){
                    Toast.makeText(getContext(), "Los numeros no coinciden con los previamente ingresados", Toast.LENGTH_SHORT).show();
                }else{
                    listener.aceptarCambioDeNip();
                    dialog.dismiss();
                    break;
                }
            break;
        }
    }

    public interface DetalleServiciosIngresarNipListener{
        void regresarASubMenuServicios();
        void aceptarCambioDeNip();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosIngresarNipListener) {
            listener = (DetalleServiciosIngresarNipListener) context;
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
