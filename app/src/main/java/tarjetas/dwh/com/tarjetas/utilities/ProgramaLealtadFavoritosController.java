package tarjetas.dwh.com.tarjetas.utilities;

import android.content.Context;

import tarjetas.dwh.com.tarjetas.implementaciones.ProgramaLealtadFavoritosImpl;
import tarjetas.dwh.com.tarjetas.model.ProductosLealtad;

/**
 * Created by ricar on 08/08/2017.
 */

public class ProgramaLealtadFavoritosController {
    ProgramaLealtadFavoritosImpl programaLealtadFavoritos;
    Context mContext;
    public ProgramaLealtadFavoritosController(Context context){
        this.mContext = context;
        programaLealtadFavoritos = new ProgramaLealtadFavoritosImpl(mContext);
    }

    public void hacerFavoritoUnProducto(ProductosLealtad producto){
        if (producto.isFavorito()){
            programaLealtadFavoritos.setAFavoriteProduct(producto.getId(),false);
        }else{
            programaLealtadFavoritos.setAFavoriteProduct(producto.getId(),true);
        }
    };
}
