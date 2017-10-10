package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.ProductosLealtadAdapter;
import tarjetas.dwh.com.tarjetas.adapter.ProgramaLealtadAdapter;
import tarjetas.dwh.com.tarjetas.implementaciones.DetalleServiciosPogramaLealtadCategoriaProductoImpl;
import tarjetas.dwh.com.tarjetas.interfaces.FragmentPageLifeCycle;
import tarjetas.dwh.com.tarjetas.model.ProductosLealtad;
import tarjetas.dwh.com.tarjetas.utilities.FormatCurrency;
import tarjetas.dwh.com.tarjetas.utilities.ProgramaLealtadFavoritosController;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

import static android.widget.AdapterView.OnItemClickListener;

/**
 * Created by ricar on 17/08/2017.
 */

public class DetalleServiciosProgramaLealtadMasProductosFragment extends Fragment implements FragmentPageLifeCycle, OnItemClickListener{

    GridView grd_menu_productos;
    ListView list_menu_productos;

    ProgramaLealtadAdapter programaLealtadAdapter;
    ProgramaLealtadFavoritosController setFavorite;


    adapter adapta;
    static DetalleServiciosProgramaLealtadMasProductosFragment instance = null;

    int hCategoria = 0;

    DetalleServiciosPogramaLealtadCategoriaProductoImpl detalleServiciosPogramaLealtadCategoriaProducto;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_programa_lealtad_mas_productos,container,false);
        grd_menu_productos = (GridView) v.findViewById(R.id.grd_menu_productos);
        list_menu_productos = (ListView) v.findViewById(R.id.list__menu_productos);

        ArrayList<categoriaProducto> data = new ArrayList<>();

        data.add(new categoriaProducto("Cocina",1));
        data.add(new categoriaProducto("Electronica",2));
        data.add(new categoriaProducto("Electrodomesticos",3));

        adapta = new adapter(data,R.layout.object_programa_lealtad_mas_productos,getContext());
        grd_menu_productos.setAdapter(adapta);
        grd_menu_productos.setOnItemClickListener(this);

        list_menu_productos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgramaLealtadAdapter adapter = (ProgramaLealtadAdapter)parent.getAdapter();
                ProductosLealtad p = (ProductosLealtad) adapter.getItem(position);

                setFavorite.hacerFavoritoUnProducto(p);
                updateListView();
            }
        });

        detalleServiciosPogramaLealtadCategoriaProducto = DetalleServiciosPogramaLealtadCategoriaProductoImpl.getInstance();

        return v;
    }

    public static DetalleServiciosProgramaLealtadMasProductosFragment getInstance(){
        if (instance == null){
            instance = new DetalleServiciosProgramaLealtadMasProductosFragment();
        }
        return instance;
    }

    @Override
    public void onResumeFragment() {
        updateListView();

        grd_menu_productos.setVisibility(View.VISIBLE);
        list_menu_productos.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        categoriaProducto i = (categoriaProducto)adapta.getItem(position);
         cargarListaProductosPorCategoria(i.id);
    }

    private void cargarListaProductosPorCategoria(int idCategoria){
        grd_menu_productos.setVisibility(View.GONE);
        list_menu_productos.setVisibility(View.VISIBLE);

        ArrayList<ProductosLealtad> productos = RealmAdministrator.getInstance(getContext()).getProductosLealtadPorCategoria(idCategoria);
        hCategoria = idCategoria;
        programaLealtadAdapter = new ProgramaLealtadAdapter(productos,R.layout.object_servicios_programa_lealtad_list,getContext()) {
            @Override
            public void onEntradaSet(View v, ViewHolder mHolder) {
                mHolder.amount = (TextView) v.findViewById(R.id.lblPrecioProgLealtad);
                mHolder.description = (TextView) v.findViewById(R.id.lblDescripcionProgLealtad);
                mHolder.productImage = (ImageView) v.findViewById(R.id.imgPorgramaLealtad);
                mHolder.favoriteImage = (ImageView) v.findViewById(R.id.imgFavorito);
            }

            @Override
            public void onEntrada(Object producto, ViewHolder mHolder, int position) {

                ProductosLealtad p = (ProductosLealtad) producto;

                mHolder.amount.setText(FormatCurrency.getFormatCurrency(p.getAmount()));
                mHolder.description.setText(p.getDescripcion());

                if (p.isFavorito()){
                    mHolder.favoriteImage.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.star_50_gold_fill));
                }else{
                    mHolder.favoriteImage.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.star_50_fill));
                }

                Picasso.with(getContext()).load(p.getImage()).into(mHolder.productImage);
            }
        };
        list_menu_productos.setAdapter(programaLealtadAdapter);

        setFavorite = new ProgramaLealtadFavoritosController(getContext());
    }

    private class adapter extends ProductosLealtadAdapter{
        public adapter(ArrayList<?> productos, int _layout, Context context) {
            super(productos, _layout, context);
        }
        @Override
        public void onEntradaSet(View v, ViewHolder mHolder) {
            mHolder.descpcion = (TextView) v.findViewById(R.id.txt_nombre_categoria_producto);
        }

        @Override
        public void onEntrada(Object productos, ViewHolder mHolder, int position) {
            categoriaProducto p = (categoriaProducto) productos;
            mHolder.descpcion.setText(p.categoria);
            mHolder.id = p.id;
        }
    }

    public void updateListView(){
        ArrayList<ProductosLealtad> productosLealtads = RealmAdministrator.getInstance(getContext()).getProductosLealtadPorCategoria(hCategoria);
        try{
            programaLealtadAdapter.actualizarDatos(productosLealtads);
        }catch (Exception e){
            Log.e("DetalleServiciosProgramaLealtadFavoritosFragment",e.getMessage());
        }
    }

    private class categoriaProducto{
        public String categoria;
        public int id;

        public categoriaProducto(String categoria, int id) {
            this.categoria = categoria;
            this.id = id;
        }
    }

}
