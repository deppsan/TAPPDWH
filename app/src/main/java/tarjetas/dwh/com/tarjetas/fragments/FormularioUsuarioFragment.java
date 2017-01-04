package tarjetas.dwh.com.tarjetas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.fragments.dialog.AdvertenciaUsurioDialog;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;
import tarjetas.dwh.com.tarjetas.utilities.EmailValidator;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;

/**
 * Created by ricar on 03/01/2017.
 */

public class FormularioUsuarioFragment extends Fragment implements View.OnClickListener, TarjetasApiClient.TarjetasApiAddUsuario {

    private EditText txtNombreusuario;
    private EditText txtApPaterno;
    private EditText txtApMaterno;
    private EditText txtUsuario;
    private EditText txtContraseña;
    private EditText txtConfContraseña;
    private EditText txtEmail;
    private Button btnCrearUsuario;

    private final String ERROR_CAMPO_NECESARIO = "Campo Requerido";
    private final String ERROR_CONTRASEÑAS = "Contraseña no coincide";
    private final String ERROR_FORMATO_CORREO = "El formato de correo no es correcto";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_formulario,container,false);

        txtNombreusuario = (EditText) v.findViewById(R.id.txtNombreUsuario);
        txtApPaterno = (EditText) v.findViewById(R.id.txtApellidoPaterno);
        txtApMaterno = (EditText) v.findViewById(R.id.txtApellidoMaterno);
        txtUsuario = (EditText) v.findViewById(R.id.txtUsuarioFormulario);
        txtContraseña = (EditText) v.findViewById(R.id.txtContraseñaFromulario);
        txtConfContraseña = (EditText) v.findViewById(R.id.txtConfirmaContraseña);
        txtEmail = (EditText) v.findViewById(R.id.txtCorreoFromulario);

        btnCrearUsuario = (Button) v.findViewById(R.id.btnCrearUsuario);
        btnCrearUsuario.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCrearUsuario:
                if (validaCampos()){
                    usuarioDTO user = null;
                    TarjetasApiClient.getInstance().addNuevoUsuario(user,getContext(),this);
                    Toast.makeText(getContext(), "Campos correcto", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Campos Incorrectos", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean isValidEmail(String email){
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validaCampos(){
        boolean valida = true;
       /* if (txtNombreusuario.getText().toString().trim().equals("")){
            txtNombreusuario.setError(ERROR_CAMPO_NECESARIO);
            valida = false;
        }

        if (txtApPaterno.getText().toString().trim().equals("")){
            txtApPaterno.setError(ERROR_CAMPO_NECESARIO);
            valida = false;
        }

        if (txtApMaterno.getText().toString().trim().equals("")){
            txtApMaterno.setError(ERROR_CAMPO_NECESARIO);
            valida = false;
        }

        if (txtUsuario.getText().toString().trim().equals("")){
            txtUsuario.setError(ERROR_CAMPO_NECESARIO);
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

        if (txtEmail.getText().toString().trim().equals("")){
            txtEmail.setError(ERROR_CAMPO_NECESARIO);
            valida = false;
        }else{
            if(!EmailValidator.getInstance().validate(txtEmail.getText().toString())){
                txtEmail.setError(ERROR_FORMATO_CORREO);
                valida = false;
            }
        }

        if (!txtContraseña.getText().toString().trim().equalsIgnoreCase(txtConfContraseña.getText().toString().trim())){
            txtConfContraseña.setError(ERROR_CONTRASEÑAS);
            valida = false;
        }*/

        return  valida;
    }


    @Override
    public void onUsuarioAgregadoExitoso() {

    }

    @Override
    public void onUsuarioAgregadoFalla() {
        DialogFragment dialog = new AdvertenciaUsurioDialog();
        dialog.show(getFragmentManager(), FragmentTags.LOGIN_FORMULARIO_USUARIO_FRAGMENT);
    }
}


