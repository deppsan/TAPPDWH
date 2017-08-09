package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.fragments.dialog.EmailMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.OpcSolMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.SMSMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.SinOpcionesDeSeguridadDialog;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;
import tarjetas.dwh.com.tarjetas.utilities.ExpValidator;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;
import tarjetas.dwh.com.tarjetas.utilities.UtilsSharedPreference;

/**
 * Created by ricar on 03/01/2017.
 */

public class FormularioUsuarioFragment extends Fragment implements View.OnClickListener{

    private EditText txtNombreusuario;
    private EditText txtContraseña;
    private EditText txtConfContraseña;
    private Button btnCrearUsuario;

    private final String ERROR_CAMPO_NECESARIO = "Campo Requerido";
    private final String ERROR_CONTRASEÑAS = "Contraseña no coincide";

    private FormularioUsuarioListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_formulario,container,false);

        txtNombreusuario = (EditText) v.findViewById(R.id.txtNombreUsuario);
        txtContraseña    = (EditText) v.findViewById(R.id.txtContraseñaFromulario);
        txtConfContraseña = (EditText) v.findViewById(R.id.txtConfirmaContraseña);

        btnCrearUsuario = (Button) v.findViewById(R.id.btnCrearUsuario);
        btnCrearUsuario.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCrearUsuario:
                if (validaCampos()){
                    RealmAdministrator.getInstance(getContext()).setUsuario(txtNombreusuario.getText().toString(),txtContraseña.getText().toString());
                    listener.onUsuarioCreado();
                }else {
                    Toast.makeText(getContext(), "Uno o más campos necesitan ser llenados", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean validaCampos(){
        boolean valida = true;
        if (txtNombreusuario.getText().toString().trim().equals("")){
            txtNombreusuario.setError(ERROR_CAMPO_NECESARIO);
            valida = false;
        }

        if (txtContraseña.getText().toString().trim().equals("")){
            txtContraseña.setError(ERROR_CAMPO_NECESARIO);
            valida = false;
        }

        if (txtConfContraseña.getText().toString().trim().equals("")){
            txtConfContraseña.setError(ERROR_CAMPO_NECESARIO);
            valida = false;
        }

        if (!txtContraseña.getText().toString().trim().equalsIgnoreCase(txtConfContraseña.getText().toString().trim())){
            txtConfContraseña.setError(ERROR_CONTRASEÑAS);
            valida = false;
        }

        return  valida;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FormularioUsuarioListener) {
            listener = (FormularioUsuarioListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface FormularioUsuarioListener{
        void onUsuarioCreado();
        void onUsuarioFalla();
    }

}


