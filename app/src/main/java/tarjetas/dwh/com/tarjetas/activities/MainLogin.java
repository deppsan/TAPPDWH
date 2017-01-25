package tarjetas.dwh.com.tarjetas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.fragments.BienvenidaFragment;
import tarjetas.dwh.com.tarjetas.fragments.CodigoSeguridadFragment;
import tarjetas.dwh.com.tarjetas.fragments.FormularioUsuarioFragment;
import tarjetas.dwh.com.tarjetas.fragments.LoginFragment;
import tarjetas.dwh.com.tarjetas.fragments.PasswordFragment;
import tarjetas.dwh.com.tarjetas.fragments.dialog.EmailMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.OpcSolMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ReconoceCelularDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.SMSMetodoSeguridadDialog;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;
import tarjetas.dwh.com.tarjetas.utilities.UtilsSharedPreference;


public class MainLogin extends AppCompatActivity implements PasswordFragment.PasswordFragmentAuthListener
        , LoginFragment.LoginFragmentAuthListener
        , ReconoceCelularDialog.ReconoceCelularFragmentListener
        , BienvenidaFragment.BienvenidaFragmentListener
        , OpcSolMetodoSeguridadDialog.OpcSolMetodoSeguridadListener
        , EmailMetodoSeguridadDialog.EmailMetodoSeguridadListener
        , SMSMetodoSeguridadDialog.SMSMetodoSeguridadListener
        , CodigoSeguridadFragment.CodigoSeguridadListener{

    private String userName;
    private final Bundle bundle = new Bundle();
    private String celular;
    private DialogFragment dialog = null;
    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_login);


        if (UtilsSharedPreference.getInstance(this).checkUserSharedPreference()) {
            goToMainActivity();
        } else if (UtilsSharedPreference.getInstance(this).getEsperaCodigo()){
            addFragment(R.id.frameContent,new CodigoSeguridadFragment(),FragmentTags.LOGIN_CODIGO_SEGURIDAD_FRAGMENT);
        } else if (UtilsSharedPreference.getInstance(this).isCelularAsigned()) {
            addFragment(R.id.frameContent, new PasswordFragment(), FragmentTags.LOGIN_FRAGMENT);
        }else{
            addFragment(R.id.frameContent,new BienvenidaFragment(),FragmentTags.BIENVENIDA_FRAGMENT);
        }
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }


    private void goToMainActivity(){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void crearNuevaCuenta() {
        replaceFragment(R.id.frameContent,new FormularioUsuarioFragment(),FragmentTags.LOGIN_FORMULARIO_USUARIO_FRAGMENT,FragmentTags.BIENVENIDA_FRAGMENT);
    }

    @Override
    public void continuarConLaCuenta() {
        UtilsSharedPreference.getInstance(this).setCelularNumber(celular);
        fm.popBackStack();
        replaceFragment(R.id.frameContent,new PasswordFragment(),FragmentTags.LOGIN_FRAGMENT,FragmentTags.BIENVENIDA_FRAGMENT);
    }

    private void enEsperaCodigoSeguridad(){
        UtilsSharedPreference.getInstance(this).setCelularNumber(celular);
        UtilsSharedPreference.getInstance(this).setEsperaCodigo(true);
        fm.popBackStack();
        replaceFragment(R.id.frameContent,new CodigoSeguridadFragment(),FragmentTags.LOGIN_CODIGO_SEGURIDAD_FRAGMENT,FragmentTags.LOGIN_FORMULARIO_USUARIO_FRAGMENT);
    }

    @Override
    public void getCeluar(String celular) {
        this.celular = celular;
    }

    @Override
    public void doInAuthTrue(String userName) {
        this.userName = userName;
        LoginFragment loginfragment = new LoginFragment();

        bundle.putString("userName",this.userName);
        loginfragment.setArguments(bundle);

        replaceFragment(R.id.frameContent,loginfragment,FragmentTags.PASSWORD_FRAGMENT,FragmentTags.LOGIN_FRAGMENT);
    }

    @Override
    public void doInAuthTrue(usuarioDTO usuario) {
        UtilsSharedPreference.getInstance(this).setUserSharedPreference(usuario);
        goToMainActivity();
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

    @Override
    public void onCodigoCorrecto() {
        fm.popBackStack();
        UtilsSharedPreference.getInstance(this).setEsperaCodigo(false);
        replaceFragment(R.id.frameContent,new PasswordFragment(),FragmentTags.PASSWORD_FRAGMENT,FragmentTags.LOGIN_CODIGO_SEGURIDAD_FRAGMENT);
    }

    @Override
    public void onCodigoIncorrecto() {
        Toast.makeText(this, "Codigo de Seguridad Incorrecto", Toast.LENGTH_SHORT).show();
    }
}
