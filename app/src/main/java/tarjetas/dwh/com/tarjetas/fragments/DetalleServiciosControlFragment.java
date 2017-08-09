package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.ServiciosControlAdapter;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.MenuServiciosControlDrawerObject;

/**
 * Created by ricar on 12/03/2017.
 */

public class DetalleServiciosControlFragment extends Fragment implements AdapterView.OnItemClickListener {

    DetalleServiciosControlListener listener;
    ListView lstMenuControl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_servicios_lista_base,container,false);

        View btnRegresar = inflater.inflate(R.layout.object_button_regresar_,container,false);

        lstMenuControl = (ListView) view.findViewById(R.id.lstServiciosMenuInterno);

        ArrayList<MenuServiciosControlDrawerObject> menu = new ArrayList<>();

        menu.add(new MenuServiciosControlDrawerObject("Bloquear Tarjeta Temporalmente","Activar esta funcionalidad no permite hacer ningun tipo de cargo a la tarjeta",0,false));
        menu.add(new MenuServiciosControlDrawerObject("Bloquear Trx Internacionales","Activar esta funcionalidad no aceptara ninguna actividad proveniente del extranjer, incluye cargos automaticos",1,false));
        menu.add(new MenuServiciosControlDrawerObject("Bloquear Transacciones sin plastico","Activar esta funcionalidad no aceptara cargos provenientes de actividad online o telefonica donde no este presente la tarjeta",2,false));
        menu.add(new MenuServiciosControlDrawerObject("Bloquear Transacciones en ATM","Activar esta funcionalidad no permitira el uso de la tarjeta en ATMs para obtener dinero",3,false));
        menu.add(new MenuServiciosControlDrawerObject("Bloqueo de Cargos Periódicos","Activar essta funcionalidad no permitira los cargos recurrentes y automaticos hechos en la tarjeta",4,false));


        lstMenuControl.setAdapter(new ServiciosControlAdapter(menu,R.layout.object_servicios_controles_list,getContext()) {
           @Override
           public void onEntradaSet(View v, ViewHolder mHolder) {

               mHolder.txtTitulo = (TextView)  v.findViewById(R.id.txtItemControlCabecera);
               mHolder.txtDescripcion = (TextView) v.findViewById(R.id.txtItemControlDescripcion);
               mHolder.swtControl = (Switch) v.findViewById(R.id.swtServiciosControl);

           }

           @Override
           public void onEntrada(final Object control, final ViewHolder mHolder, final int position) {

               mHolder.txtTitulo.setText(((MenuServiciosControlDrawerObject)control).getTxtTitulo());
               mHolder.txtDescripcion.setText(((MenuServiciosControlDrawerObject)control).getTxtDescripcion());
               mHolder.swtControl.setChecked(((MenuServiciosControlDrawerObject)control).isActive());
               mHolder.swtControl.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if (mHolder.swtControl.isChecked()){
                           ((MenuServiciosControlDrawerObject) control).setActive(true);
                       }else{
                           ((MenuServiciosControlDrawerObject) control).setActive(false);
                       }
                       listener.onClickSeleccionarMenu(control,v,position);
                   }
               });
           }
        });

        lstMenuControl.addHeaderView(btnRegresar);
        lstMenuControl.setOnItemClickListener(this);

        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetalleServiciosControlListener) {
            listener = (DetalleServiciosControlListener) context;
        } else {
            throw new IllegalArgumentException(context.toString() + "debe de implementar en onAttach");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.btnObjectRegresar){
            listener.onClickRegresar();
        }
    }

    public interface DetalleServiciosControlListener{
        void onClickRegresar();
        void onClickSeleccionarMenu(Object menu,View view, int position);
    }
}
