package tarjetas.dwh.com.tarjetas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.activities.base.BaseActivity;
import tarjetas.dwh.com.tarjetas.adapter.drawer.TarjetaListaObject;
import tarjetas.dwh.com.tarjetas.fragments.TarjetasListaFragment;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;

/**
 * Created by ricar on 15/12/2016.
 */

public class MainPage extends BaseActivity implements TarjetasListaFragment.TarjetasListaFragmentListener{

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
        Intent intent = new Intent(this,DetallesMisTarjetas.class);
        intent.putExtra("idTarjeta",tarjeta.getId_c());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"OnResume",Toast.LENGTH_SHORT).show();
    }
}


