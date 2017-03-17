package tarjetas.dwh.com.tarjetas.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;

/**
 * Created by ricar on 23/02/2017.
 */

public class fragmentoPrueb extends Fragment {
    
    Button pruba;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_prueba,container,false);
        pruba = (Button)v.findViewById(R.id.pruebabtn);
        
        pruba.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_detalle_categorias);
                dialog.setTitle("Clasificar");

                ListView lstNuevaCategoria = (ListView) dialog.findViewById(R.id.lstNuevaCategoria);
                ListView lstCategoria = (ListView) dialog.findViewById(R.id.lstCategorias);
                Button btnCancelar  = (Button) dialog.findViewById(R.id.btnCancelarCategoria);
                Button btnAceptarCategoria = (Button) dialog.findViewById(R.id.btnAceptarCambioCategoria);

                ArrayList<Categoria> categorias  = new ArrayList<Categoria>();

                categorias.add(new Categoria(1,"Restaurantes"));
                categorias.add(new Categoria(2,"Compras"));
                categorias.add(new Categoria(3,"Servicios"));
                categorias.add(new Categoria(4,"Otros"));

                categorias.add(new Categoria(1,"Restaurantes"));
                categorias.add(new Categoria(2,"Compras"));
                categorias.add(new Categoria(3,"Servicios"));
                categorias.add(new Categoria(4,"Otros"));

                categorias.add(new Categoria(1,"Restaurantes"));
                categorias.add(new Categoria(2,"Compras"));
                categorias.add(new Categoria(3,"Servicios"));
                categorias.add(new Categoria(4,"Otros"));

                categorias.add(new Categoria(1,"Restaurantes"));
                categorias.add(new Categoria(2,"Compras"));
                categorias.add(new Categoria(3,"Servicios"));
                categorias.add(new Categoria(4,"Otros"));


                lstCategoria.setAdapter(new TransaccionesAdapter(categorias,R.layout.object_detalle_categorias_lista,getContext()) {
                    @Override
                    public void onEntrada(Object saldo, View view) {
                        TextView text = (TextView) view.findViewById(R.id.txtCategorias);
                        ImageView image = (ImageView) view.findViewById(R.id.imgCategoriaColor);
                        int color = ((Categoria)saldo).getColor();
                        Drawable drawableCompat = ContextCompat.getDrawable(getContext(),R.drawable.circle);

                        ColorFilter filter = new LightingColorFilter(Color.BLUE,Color.RED);
                        drawableCompat.setColorFilter(filter);

/*
                        switch (color){
                            case 1:
                                break;
                            case 2:
                                drawableCompat.setColorFilter(new PorterDuffColorFilter(0xFF3300, PorterDuff.Mode.MULTIPLY));
                                break;
                            case 3:
                                drawableCompat.setColorFilter(new PorterDuffColorFilter(0x3366FF, PorterDuff.Mode.MULTIPLY));
                                break;
                            case 4:
                                drawableCompat.setColorFilter(new PorterDuffColorFilter(0xFFFF33, PorterDuff.Mode.MULTIPLY));
                                break;
                        }*/

                        image.setImageDrawable(drawableCompat);

                        text.setText(((Categoria)saldo).getNombre());

                    }
                });

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }
        });
        return v;
    }

    public fragmentoPrueb(){}

    private class Categoria{
        private int color;
        private String nombre;

        public Categoria(int color, String nombre) {
            this.color = color;
            this.nombre = nombre;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
}
