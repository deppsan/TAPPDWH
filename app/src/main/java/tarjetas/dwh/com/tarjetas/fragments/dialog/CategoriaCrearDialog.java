package tarjetas.dwh.com.tarjetas.fragments.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.CrearCategoriasAdapter;

/**
 * Created by ricar on 23/05/2017.
 */

public abstract class CategoriaCrearDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemClickListener{

    Context mContext;
    EditText nombreCategoria;
    GridView grdImagenesCategoria;
    Button btnAceptar,btnCancelar;
    TypedArray arr_image;

    private int imageWhiteSelected = 0;
    private int imageSelected = 0;
    private String categoryName;

    DialogCrearCategoriaListener listener;

    public CategoriaCrearDialog(Context context){
        super(context);

        this.mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_detalle_categorias);

        nombreCategoria = (EditText) findViewById(R.id.txtNombreCategoriaNueva);
        grdImagenesCategoria = (GridView) findViewById(R.id.grdImagenesCategorias);
        btnAceptar = (Button) findViewById(R.id.btnAceptarCrearCategoria);
        btnCancelar = (Button) findViewById(R.id.btnCancelarCrearCategoria);

        arr_image = mContext.getResources().obtainTypedArray(R.array.images_categories);

        ArrayList<HashMap<String,Integer>> datos = new ArrayList<>();

        HashMap<String,Integer> data = new HashMap<>();
        data.put("black",R.drawable.health_black);
        data.put("white",R.drawable.health_white);
        datos.add(data);

        HashMap<String,Integer> data2 = new HashMap<>();
        data2.put("black",R.drawable.stationery_black);
        data2.put("white",R.drawable.stationery_white);
        datos.add(data2);

        HashMap<String,Integer> data3 = new HashMap<>();
        data3.put("black",R.drawable.clothes_black);
        data3.put("white",R.drawable.clothes_white);
        datos.add(data3);

        HashMap<String,Integer> data4 = new HashMap<>();
        data4.put("black",R.drawable.shopping_cart_black);
        data4.put("white",R.drawable.shopping_cart_white);
        datos.add(data4);

        HashMap<String,Integer> data5 = new HashMap<>();
        data5.put("black",R.drawable.airport_black);
        data5.put("white",R.drawable.airport_white);
        datos.add(data5);

        HashMap<String,Integer> data6 = new HashMap<>();
        data6.put("black",R.drawable.church_black);
        data6.put("white",R.drawable.church_white);
        datos.add(data6);

        HashMap<String,Integer> data7 = new HashMap<>();
        data7.put("black",R.drawable.restaurant_black);
        data7.put("white",R.drawable.restaurant_white);
        datos.add(data7);

        HashMap<String,Integer> data8 = new HashMap<>();
        data8.put("black",R.drawable.work_black);
        data8.put("white",R.drawable.work_white);
        datos.add(data8);

        HashMap<String,Integer> data9 = new HashMap<>();
        data9.put("black",R.drawable.bussiness_black);
        data9.put("white",R.drawable.bussiness_white);
        datos.add(data9);

        HashMap<String,Integer> data10 = new HashMap<>();
        data10.put("black",R.drawable.inversion_black);
        data10.put("white",R.drawable.inversion_white);
        datos.add(data10);

        HashMap<String,Integer> data11 = new HashMap<>();
        data11.put("black",R.drawable.property_black);
        data11.put("white",R.drawable.property_white);
        datos.add(data);


        grdImagenesCategoria.setAdapter(new CrearCategoriasAdapter(datos,R.layout.object_crear_categorias_item,mContext) {
            @Override
            public void onEntradaSet(View v, ViewHolder mHolder) {
                mHolder.imageView = (ImageView) v.findViewById(R.id.imgCategoria);
            }

            @Override
            public void onEntrada(Object categorias, ViewHolder mHolder, int position) {
                HashMap<String,Integer> data = ((HashMap<String, Integer>) categorias);
                Picasso.with(mContext).load(data.get("black")).resize(50,50).into(mHolder.imageView);
            }
        });

        grdImagenesCategoria.setOnItemClickListener(this);
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAceptarCrearCategoria:
                String nombre = nombreCategoria.getText().toString();
                if (imageSelected != 0 && imageWhiteSelected != 0 && nombre.length() != 0){
                    listener.acepterCrearCategoria(imageSelected,imageWhiteSelected,nombre);
                    actualizarGrid();
                    dismiss();
                }else{
                    Toast.makeText(mContext, "Debes ingresar un nombre y seleccionar un icono para poder agregar una categoria nueva.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancelarCrearCategoria:
                dismiss();
                break;
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mContext instanceof DialogCrearCategoriaListener) {
            listener = (DialogCrearCategoriaListener) mContext;
        } else {
            throw new IllegalArgumentException(mContext.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        listener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,Integer> imagenes = ((HashMap<String,Integer>) parent.getAdapter().getItem(position));

        imageSelected = imagenes.get("black");
        imageWhiteSelected = imagenes.get("white");
    }

    public abstract void actualizarGrid();

    public interface DialogCrearCategoriaListener{
        void acepterCrearCategoria(int imagen, int imagenDrawable, String nombreCategoria);
    }
}
