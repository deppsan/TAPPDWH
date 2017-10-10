package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.CategoriasAdapter;
import tarjetas.dwh.com.tarjetas.fragments.dialog.CategoriaCrearDialog;
import tarjetas.dwh.com.tarjetas.model.Categorias;
import tarjetas.dwh.com.tarjetas.model.Transacciones;
import tarjetas.dwh.com.tarjetas.utilities.CircleTransform;
import tarjetas.dwh.com.tarjetas.utilities.FormatCurrency;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;
import tarjetas.dwh.com.tarjetas.utilities.custom.CircleFillView;

/**
 * Created by ricar on 16/05/2017.
 */

public class CategoriasFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    GridView gridView;
    CategoriasFragmentListener listener;
    View vHolder;
    int idCategoriaSeleccionada;


    CustomOnClickListener customOnClickListener= new CustomOnClickListener();

    public static final String FRAGMENT_TAG = "CategoriasFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_categorias,container,false);

        gridView = (GridView) v.findViewById(R.id.grdCategorias);

        loadGridCategory();
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);


        return v;
    }

    private void loadGridCategory(){
        final ArrayList<Categorias> datos = RealmAdministrator.getInstance(getContext()).getAllCategorias();
        Categorias agregarCategoria = new Categorias();
        agregarCategoria.setImagenDrawer(R.drawable.add);
        agregarCategoria.setNombre("Agregar");
        agregarCategoria.setCapacidad(0);
        datos.add(agregarCategoria);
        gridView.setAdapter(new CategoriasAdapter(datos,R.layout.object_categorias_grid,getContext()) {
            @Override
            public void onEntradaSet(View v, ViewHolder mHolder) {
                mHolder.montoActual = (TextView) v.findViewById(R.id.lblMontoActual);
                mHolder.imageView = (ImageView) v.findViewById(R.id.imgCategoria);
                mHolder.categoriaNombre = (TextView) v.findViewById(R.id.lblCategoriaNombre);
                mHolder.imgEditar = (ImageView) v.findViewById(R.id.imgEditar);
                mHolder.imgEliminar = (ImageView) v.findViewById(R.id.imgEliminar);
                mHolder.imgGrafica = (ImageView) v.findViewById(R.id.imgGrafica);
                mHolder.circleFillView = new CircleFillView(getActivity());
                LinearLayout view = (LinearLayout) v.findViewById(R.id.circle_fill_area);

                view.addView(mHolder.circleFillView);

            }

            @Override
            public void onEntrada(Object categorias, ViewHolder mHolder, int position) {
                Categorias d = (Categorias) categorias;
                mHolder.categoriaNombre.setText(d.getNombre());

                if (d.getCapacidad() == 0){
                    mHolder.montoActual.setText("");
                }else{
                    mHolder.montoActual.setText(FormatCurrency.getFormatCurrency(d.getCapacidad()));
                }

                mHolder.circleFillView.setStrokeColor(Color.WHITE);
                mHolder.circleFillView.setStrokeWidth(1);

                int id = d.getId();

                ArrayList<Transacciones> transacciones = RealmAdministrator.getInstance(getContext()).getTransaccionesbyCategory(id);
                double total = 0;
                double porcentaje;

                for (Transacciones t : transacciones){
                    total += t.getValor();
                }

                porcentaje = (total * 100 ) / d.getCapacidad();
                int porcentajeInt = ((Number)porcentaje).intValue();

                if (porcentajeInt < 50){
                    mHolder.circleFillView.setFillColor(ContextCompat.getColor(getContext(),R.color.verde_categoria));
                } else if (porcentajeInt >= 50 && porcentajeInt < 100){
                    mHolder.circleFillView.setFillColor(ContextCompat.getColor(getContext(),R.color.amarillo_categoria));
                } else {
                    mHolder.circleFillView.setFillColor(ContextCompat.getColor(getContext(),R.color.rojo_categoria));
                }

                porcentajeInt = (porcentajeInt > 100) ? 100 : porcentajeInt;
                mHolder.circleFillView.setValue(porcentajeInt);

                mHolder.imgGrafica.setOnClickListener(customOnClickListener);
                mHolder.imgEditar.setOnClickListener(customOnClickListener);
                mHolder.imgEliminar.setOnClickListener(customOnClickListener);

                Picasso.with(getContext()).load(d.getImagenDrawer()).into(mHolder.imageView);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ((parent.getAdapter().getCount() -1) == position){
          new CategoriaCrearDialog(getContext()){
              @Override
              public void setCancelable(boolean flag) {
                  super.setCancelable(false);
              }

              @Override
              public void actualizarGrid() {
                CategoriasAdapter adapter = (CategoriasAdapter) gridView.getAdapter();
                  Categorias agregarCategoria = new Categorias();
                  agregarCategoria.setImagen(R.drawable.plus_100);
                  agregarCategoria.setNombre("Agregar");
                  agregarCategoria.setCapacidad(0);

                  final ArrayList<Categorias> datos = RealmAdministrator.getInstance(getContext()).getAllCategorias();
                  datos.add(agregarCategoria);
                  adapter.updateData(datos);
              }
          }.show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        int i = parent.getCount();
        if ( position != i - 1){
            if (vHolder == null){
                vHolder = view.findViewById(R.id.ViewCaratula);
            }else{
                vHolder.setVisibility(View.INVISIBLE);
                vHolder = view.findViewById(R.id.ViewCaratula);
            }
            vHolder.setVisibility(View.VISIBLE);

            Categorias categorias = (Categorias) parent.getAdapter().getItem(position);
            idCategoriaSeleccionada = categorias.getId();
        }


        return false;
    }

    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgEditar:
                    listener.goToDetalleTransaccionesFragment(idCategoriaSeleccionada);
                    break;
                case R.id.imgGrafica:
                    Toast.makeText(getContext(), "Grafica", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgEliminar:
                    deleteCategoryFromTheGrid(idCategoriaSeleccionada);
                    break;
            }
            if (vHolder != null){
                if (vHolder.getVisibility() == View.VISIBLE){
                    vHolder.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void deleteCategoryFromTheGrid(int idCategory){
        RealmAdministrator.getInstance(getContext()).deleteCategoryById(idCategory);

        CategoriasAdapter adapter = (CategoriasAdapter) gridView.getAdapter();
        ArrayList<Categorias> datos = RealmAdministrator.getInstance(getContext()).getAllCategorias();

        Categorias agregarCategoria = new Categorias();
        agregarCategoria.setImagen(R.drawable.add);
        agregarCategoria.setNombre("Agregar");
        agregarCategoria.setCapacidad(0);
        datos.add(agregarCategoria);
        adapter.updateData(datos);
    }


    public interface CategoriasFragmentListener{
        void goToDetalleTransaccionesFragment( int idCategoria);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CategoriasFragmentListener) {
            listener = (CategoriasFragmentListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadGridCategory();
    }
}
