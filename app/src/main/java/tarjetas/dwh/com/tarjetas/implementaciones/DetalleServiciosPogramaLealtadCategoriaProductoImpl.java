package tarjetas.dwh.com.tarjetas.implementaciones;


import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosProgramaLealtadFragmentOne;
import tarjetas.dwh.com.tarjetas.interfaces.DetalleServiciosPogramaLealtadCategoriaProducto;

import android.support.v4.app.Fragment;

/**
 * Created by ricar on 21/08/2017.
 */

public class DetalleServiciosPogramaLealtadCategoriaProductoImpl implements DetalleServiciosPogramaLealtadCategoriaProducto {


    private DetalleServiciosProgramaLealtadFragmentOne.ProgramaLealtadPageAdapter adapter;
    private static DetalleServiciosPogramaLealtadCategoriaProductoImpl instance = null;
    private final int PAGE = 2;

    public static DetalleServiciosPogramaLealtadCategoriaProductoImpl getInstance(DetalleServiciosProgramaLealtadFragmentOne.ProgramaLealtadPageAdapter adapter){
        if (instance == null){
            instance = new DetalleServiciosPogramaLealtadCategoriaProductoImpl(adapter);
        }
        return instance;
    }
    public static DetalleServiciosPogramaLealtadCategoriaProductoImpl getInstance(){
        if (instance == null){
            instance = new DetalleServiciosPogramaLealtadCategoriaProductoImpl();
        }
        return instance;
    }

    private DetalleServiciosPogramaLealtadCategoriaProductoImpl(DetalleServiciosProgramaLealtadFragmentOne.ProgramaLealtadPageAdapter adapter){
        this.adapter = adapter;
    }
    private DetalleServiciosPogramaLealtadCategoriaProductoImpl(){    }


    @Override
    public void SeleccionarCategoria(Fragment fragment) {
        adapter.setPages(PAGE,fragment);
        adapter.notifyDataSetChanged();
    }
}
