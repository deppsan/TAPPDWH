package tarjetas.dwh.com.tarjetas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.activities.base.BaseActivity;
import tarjetas.dwh.com.tarjetas.adapter.drawer.TarjetaListaObject;
import tarjetas.dwh.com.tarjetas.fragments.AgregarTarjetaFragment;
import tarjetas.dwh.com.tarjetas.fragments.CodigoDeActivacionFragment;
import tarjetas.dwh.com.tarjetas.fragments.CodigoSeguridadFragment;
import tarjetas.dwh.com.tarjetas.fragments.TarjetasListaFragment;
import tarjetas.dwh.com.tarjetas.fragments.dialog.EmailMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.OpcSolMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.SMSMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;
import tarjetas.dwh.com.tarjetas.utilities.UtilsSharedPreference;

/**
 * Created by ricar on 15/12/2016.
 */

public class MainPage extends BaseActivity implements TarjetasListaFragment.TarjetasListaFragmentListener
                                                    ,AgregarTarjetaFragment.AgregarTarjetaListener
                                                    ,OpcSolMetodoSeguridadDialog.OpcSolMetodoSeguridadListener
                                                    ,SMSMetodoSeguridadDialog.SMSMetodoSeguridadListener
                                                    ,EmailMetodoSeguridadDialog.EmailMetodoSeguridadListener {
    DialogFragment dialog;
    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TarjetasListaFragment Lista_Tarjetas_Fragment = new TarjetasListaFragment();
        bundle.putString("user",TarjetasApiClient.gson.toJson(user));
        Lista_Tarjetas_Fragment.setArguments(bundle);

        addFragment(R.id.content_frame, Lista_Tarjetas_Fragment, FragmentTags.TARJETAS_LISTA_FRAGMENT);
        setTitle(R.string.TitleMisTarjetas);
    }

    @Override
    public void onGotoTarjetasDetalle(TarjetaListaObject tarjeta) {
        Intent intent = new Intent(this,DetallesMisTarjetasUno.class);
        intent.putExtra("idTarjeta",tarjeta.getId_c());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnAgregarTarjeta:
                replaceFragment(R.id.content_frame,new AgregarTarjetaFragment(),FragmentTags.BIENVENIDA_DIALOGO_CELULAR_FRAGMENT,FragmentTags.TARJETAS_LISTA_FRAGMENT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAgregarTarjeta(String tarjeta) {
        //replaceFragment(R.id.content_frame, new CodigoDeActivacionFragment(),FragmentTags.LOGIN_CODIGO_SEGURIDAD_FRAGMENT,FragmentTags.TARJETAS_LISTA_FRAGMENT);
    }

    @Override
    public void onMailSelected() {
        dialog = new EmailMetodoSeguridadDialog();
        dialog.show(getSupportFragmentManager(),FragmentTags.LOGIN_FORMULARIO_USUARIO_FRAGMENT);

    }

    @Override
    public void onSMSSelected() {
        dialog = new SMSMetodoSeguridadDialog();
        dialog.show(getSupportFragmentManager(),FragmentTags.LOGIN_FORMULARIO_USUARIO_FRAGMENT);

    }

    @Override
    public void accionAceptar() {
        enEsperaCodigoSeguridad();
    }

    @Override
    public void aceptarAccion() {
        enEsperaCodigoSeguridad();
    }

    private void enEsperaCodigoSeguridad(){
//        UtilsSharedPreference.getInstance(this).setEsperaCodigo(true);
        fm.popBackStack();
        replaceFragment(R.id.content_frame, new CodigoDeActivacionFragment(),FragmentTags.LOGIN_CODIGO_SEGURIDAD_FRAGMENT,FragmentTags.TARJETAS_LISTA_FRAGMENT);
    }
}


