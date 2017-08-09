package tarjetas.dwh.com.tarjetas.implenetaciones;

import android.content.Context;

import io.realm.Realm;
import tarjetas.dwh.com.tarjetas.interfaces.ProgramaLealtadFavoritos;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

/**
 * Created by ricar on 08/08/2017.
 */

public class ProgramaLealtadFavoritosImpl implements ProgramaLealtadFavoritos {
    private Context mContext;

    @Override
    public void setAFavoriteProduct(int idProduct, boolean isfavorite) {
        RealmAdministrator.getInstance(mContext).setProductoLealtadFavorito(idProduct,isfavorite);
    }

    public ProgramaLealtadFavoritosImpl(Context context){
        this.mContext = context;
    }
}
