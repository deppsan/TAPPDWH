package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 08/12/2016.
 */

public class LoginFragment extends Fragment implements View.OnClickListener, TarjetasApiClient.TarjetasApiPasswordListener {

    private TextView lblUsuario;
    private Button btnPassword;
    private EditText txtPassword;
    private String user;

    private LoginFragmentAuthListener CallbackAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_password, container, false);

        lblUsuario = (TextView) v.findViewById(R.id.lblUsuario);
        btnPassword = (Button) v.findViewById(R.id.btnEvalPass);
        txtPassword = (EditText) v.findViewById(R.id.txtPassword);

        this.user = getArguments().getString("userName");

        lblUsuario.setText(user);
        btnPassword.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnEvalPass:
                TarjetasApiClient.getInstance().validaPassword(user,txtPassword.getText().toString(),v.getContext(),this);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof LoginFragmentAuthListener){
            CallbackAuth = (LoginFragmentAuthListener) context;
        }else{
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        CallbackAuth = null;
    }

    @Override
    public void onPasswordValidate(usuarioDTO user) {
        CallbackAuth.doInAuthTrue(user);
    }

    @Override
    public void onPasswordNoValidate(String mensaje) {
        Log.d("No validado",mensaje);
    }

    public interface LoginFragmentAuthListener{
        void doInAuthTrue(usuarioDTO usuario);
    }
}

