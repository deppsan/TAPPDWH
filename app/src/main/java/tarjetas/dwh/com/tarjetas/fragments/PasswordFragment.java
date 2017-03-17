package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 01/12/2016.
 */

public class PasswordFragment extends Fragment implements View.OnClickListener, TarjetasApiClient.TarjetasApiUserListener {
    private TextView txUser;
    private Button btn;
    private Context context;

    PasswordFragmentAuthListener callBackAuth;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanState){
         View v = inflater.inflate(R.layout.fragment_login,container,false);

         context= v.getContext();
         txUser = (TextView) v.findViewById(R.id.txtUsuario);
         btn    = (Button) v.findViewById(R.id.btnContinuar);

         btn.setOnClickListener(this);

         return v;
     }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnContinuar:
                TarjetasApiClient.getInstance().validaUsuario(txUser.getText().toString(),v.getContext(),this);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof PasswordFragmentAuthListener){
            callBackAuth = (PasswordFragmentAuthListener) context;
        }else{
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBackAuth = null;
    }

    @Override
    public void onUserValidate(String user) {
        callBackAuth.doInAuthTrue(user);
    }

    @Override
    public void onUserNoValidate(String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }

    public interface PasswordFragmentAuthListener{
        void doInAuthTrue(String userName);
    }
}
