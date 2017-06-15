package tarjetas.dwh.com.tarjetas.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.fragments.CategoriasConfiguracionFragment;
import tarjetas.dwh.com.tarjetas.fragments.CategoriasFragment;
import tarjetas.dwh.com.tarjetas.fragments.dialog.CategoriaCambiarImagenDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.CategoriaCrearDialog;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

/**
 * Created by ricar on 16/05/2017.
 */

public class Categorias extends AppCompatActivity implements CategoriaCrearDialog.DialogCrearCategoriaListener
                                                            , CategoriasFragment.CategoriasFragmentListener
                                                            , CategoriasConfiguracionFragment.CategoriasConfiguracionFragmentListener
                                                            , CategoriaCambiarImagenDialog.CategoriaCambiarImagenListener{

    protected Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_transacciones);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setTitle("Categorias");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);




        addFragment(R.id.content_categorias, new CategoriasFragment(), CategoriasFragment.FRAGMENT_TAG);
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


    @Override
    public void acepterCrearCategoria(int imagen, int imagenDrawable, String nombreCategoria) {
        RealmAdministrator.getInstance(getBaseContext()).crearCategoria(imagen,imagenDrawable,nombreCategoria);
    }

    @Override
    public void goToDetalleTransaccionesFragment(int idCategoria) {
        Fragment fragment = new CategoriasConfiguracionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idCategoria",idCategoria);
        fragment.setArguments(bundle);

        replaceFragment(R.id.content_categorias, fragment, CategoriasConfiguracionFragment.FRAGMENT_TAG, CategoriasFragment.FRAGMENT_TAG);
    }

    @Override
    public void onCancelar() {
        onBackPressed();
    }

    @Override
    public void aceptarCambiarImagen(int imagen, int imageDrawable) {
        
    }
}
