package tarjetas.dwh.com.tarjetas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.ProgramaLealtadAdapter;
import tarjetas.dwh.com.tarjetas.interfaces.FragmentPageLifeCycle;
import tarjetas.dwh.com.tarjetas.model.ProductosLealtad;
import tarjetas.dwh.com.tarjetas.utilities.FormatCurrency;
import tarjetas.dwh.com.tarjetas.utilities.ProgramaLealtadFavoritosController;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

/**
 * Created by ricar on 01/08/2017.
 */

public class DetalleServiciosProgramaLealtadRecomendadosFragment extends Fragment implements AdapterView.OnItemClickListener, FragmentPageLifeCycle {
    private ListView lstProgramaLealtad;
    private ProgramaLealtadFavoritosController controlarFavoritos;
    private ProgramaLealtadAdapter programaLealtadAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_programa_lealtad,container,false);
        lstProgramaLealtad = (ListView) v.findViewById(R.id.lstProgramaLealtad);
        controlarFavoritos = new ProgramaLealtadFavoritosController(getContext());

        ArrayList<ProductosLealtad> productos = RealmAdministrator.getInstance(getContext()).getProductosLealtadSugeridos();
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
        lstProgramaLealtad.setAdapter(programaLealtadAdapter);

        lstProgramaLealtad.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProgramaLealtadAdapter adapter = (ProgramaLealtadAdapter)parent.getAdapter();
        ProductosLealtad p = (ProductosLealtad) adapter.getItem(position);

        controlarFavoritos.hacerFavoritoUnProducto(p);
        updateListView();

    }

    private void updateListView(){
        ArrayList<ProductosLealtad> productosLealtads = RealmAdministrator.getInstance(getContext()).getProductosLealtadSugeridos();
        programaLealtadAdapter.actualizarDatos(productosLealtads);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onResumeFragment() {
        updateListView();
    }
}
