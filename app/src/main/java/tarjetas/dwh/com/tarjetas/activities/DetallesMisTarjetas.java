package tarjetas.dwh.com.tarjetas.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewParentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Vector;

import tarjetas.dwh.com.tarjetas.DTO.MenuServiciosDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.MenuServiciosControlDrawerObject;
import tarjetas.dwh.com.tarjetas.fragments.ControlLineaDeCreditoFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetallePromocionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosControlFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosSubMenuFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTarjetaGrafico;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTarjetasFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleNotificacionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTransaccionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.ReporteRoboExtravioFragment;
import tarjetas.dwh.com.tarjetas.fragments.RoboExtravioReportadaFragment;
import tarjetas.dwh.com.tarjetas.fragments.dialog.AumentoDeLineaCreditoDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.AvisoAumentoDeLineaCreditoAceptadaDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ReporteRoboExtravioDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ServiciosControlBloqueoTarjetaDesactivarDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ServiciosControlBloqueoTarjetaDialog;
import tarjetas.dwh.com.tarjetas.utilities.ActivarFragmentos;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;

/**
 * Created by ricar on 26/12/2016.
 */

public class DetallesMisTarjetas extends AppCompatActivity implements DetalleServiciosFragment.DetalleServiciosListener
                                                            , DetalleServiciosControlFragment.DetalleServiciosControlListener
                                                            , ServiciosControlBloqueoTarjetaDialog.ServiciosControlBloqueoTarjetaListener
                                                            , ServiciosControlBloqueoTarjetaDesactivarDialog.ServiciosControlBloqueoTarjetaDesactivarListener
                                                            , ReporteRoboExtravioFragment.ReporteExtravioListener
                                                            , ReporteRoboExtravioDialog.ReporteRoboExtravioDialogListener
                                                            , DetalleServiciosSubMenuFragment.DetalleServiciosSubMenuListener
                                                            , RoboExtravioReportadaFragment.RoboExtravioReportadaListener
                                                            , ControlLineaDeCreditoFragment.ControlLineaDeCreditoListener
                                                            , AumentoDeLineaCreditoDialog.AumentoDeLineaCreditoListener{


    private FragmentPagerAdapter adapterViewPagerSuperior;
    private ViewPager superiorViewPager;
    private InferiorPageAdapter adapterViewPagerInferior;
    private ViewPager inferiorViewPager;
    private Toolbar toolbar;
    private Switch switchDialog;

    private DialogFragment  dialog;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas_detalle);

        mContext = this;
        int idTarjeta = getIntent().getIntExtra("idTarjeta",0);

        superiorViewPager = (ViewPager) findViewById(R.id.content_tarjetas_detail_superior);
        adapterViewPagerSuperior = new SuperiorPageAdapter(getSupportFragmentManager(),idTarjeta);


        DetalleTransaccionesFragment transaccionesFragment = new DetalleTransaccionesFragment();
        DetalleNotificacionesFragment notificacionesDetalleFragment = new DetalleNotificacionesFragment();
        DetallePromocionesFragment detallePromocionesFragment = new DetallePromocionesFragment();
        DetalleServiciosFragment detalleServiciosFragment = new DetalleServiciosFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("idTarjeta",idTarjeta);
        notificacionesDetalleFragment.setArguments(bundle);
        transaccionesFragment.setArguments(bundle);
        detallePromocionesFragment.setArguments(bundle);

        ArrayList<Fragment> pages = new ArrayList<>();
        pages.add(transaccionesFragment);
        pages.add(notificacionesDetalleFragment);
        pages.add(detallePromocionesFragment);
        pages.add(detalleServiciosFragment);

        inferiorViewPager = (ViewPager) findViewById(R.id.content_tarjetas_detail_inferior);
        adapterViewPagerInferior = new InferiorPageAdapter(getSupportFragmentManager(),idTarjeta,mContext,pages);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);


        toolbar = (Toolbar) findViewById(R.id.tool_bar_Tarjetas_detalle);
        toolbar.setTitle("Mis Tarjetas");

        setSupportActionBar(toolbar);
        superiorViewPager.setAdapter(adapterViewPagerSuperior);
        inferiorViewPager.setAdapter(adapterViewPagerInferior);

        tabs.setViewPager(inferiorViewPager);


    }

    /**
     * Implementacion de regreso al menu principal de servicios.
     */
    @Override
    public void onClickRegresar() {
        adapterViewPagerInferior.setPages(3,new DetalleServiciosFragment());
    }

    /**
     * Implementacion para ejecutar la modificación de la linea de credito de una tarjeta en especificada, ya sea la tarjeta seleccionada en el detalle (titular)
     * o una adicional.
     * @param linea
     */
    @Override
    public void onSeleccionarModificarLineaCredito(ControlLineaDeCreditoFragment.TarjetasLineas linea) {
    }

    /**
     * Implementacion del aumento de la linea de credito, llama a una ventana de dialogo donde ser solicita la aprovacion por parte del cliente.
     */
    @Override
    public void onSeleccionarAumentarLimitedeLineadeCredito(double totalLineaDeCredito) {
        dialog = AumentoDeLineaCreditoDialog.getInstance(totalLineaDeCredito);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * Implementacion de boton regresar a sub menu Servicios -> Servicios
     */
    @Override
    public void onClickRegresarRoboExtravio() {
        adapterViewPagerInferior.setPages(3,new DetalleServiciosSubMenuFragment());
    }

    /**
     * Implementacion de la cancelacion del reporte del robo o extravio.
     */
    @Override
    public void onReportarTarjetaCancelar() {
        adapterViewPagerInferior.setPages(3,new ReporteRoboExtravioFragment());
    }

    @Override
    public void onReportarTarjeta() {
        dialog = new ReporteRoboExtravioDialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * Implementacion de codigo para seleccionar opciones de menu dentro de Servicios -> Control.
     * @param menu Seleccionado
     * @param view que desata el evento click.
     */
    @Override
    public void onClickSeleccionarMenu(Object menu, View view) {
        MenuServiciosControlDrawerObject optMenu = (MenuServiciosControlDrawerObject) menu;
        switch (optMenu.getId()){
            case 0 :
                switchDialog = (Switch) view.findViewById(R.id.swtServiciosControl);
                if (switchDialog.isChecked()){
                    dialog = new ServiciosControlBloqueoTarjetaDialog();
                    dialog.show(getSupportFragmentManager(), FragmentTags.TARJETAS_DETALLE_FRAGMENT);
                }else{
                    dialog = new ServiciosControlBloqueoTarjetaDesactivarDialog();
                    dialog.show(getSupportFragmentManager(), FragmentTags.TARJETAS_DETALLE_FRAGMENT);
                }
                break;
        }
    }

    /**
     * Implementacion de pantalla de Control
     */
    @Override
    public void onClickCancelar() {
        switchDialog.setChecked(false);
        switchDialog = null;
    }


    /**
     * Implementacion de la Pantalla de  control, al aceptar bloquear  tarjeta, lleva al Dialogo para aceptar el tramite
     * para bloquear y solicitar una nueva tarjeta.
     */
    @Override
    public void onClickAceptarBloqueo() {

    }

    /**
     * Implementacion de la pantalla de control, al desbloquar tarjeta.
     */
    @Override
    public void onDesactivarBloqueoDeTarjeta() {

    }

    /**
     * Codigo para reportar la tarjeta extraviada despues de aceptar en el dialogo.
     */
    @Override
    public void onReportarTarjetaExtraviadaRobada() {
        adapterViewPagerInferior.setPages(3,new RoboExtravioReportadaFragment());
    }

    /**
     * Codigo para seleccionar menu dentro de Servicios -> Servicios
     * @param menu Seleccionado
     * @param view La cual fue usada para desatar el evento Click.
     */
    @Override
    public void onSeleccionarOpcionMenu(Object menu, View view) {
        MenuServiciosDTO optMenu = (MenuServiciosDTO) menu;

        switch (optMenu.getId()){
            case 0:
                adapterViewPagerInferior.setPages(3,new ReporteRoboExtravioFragment());
                break;
            case 1:
                break;
            case 2:
                adapterViewPagerInferior.setPages(3,new ControlLineaDeCreditoFragment());
                break;
            case 3:
                break;
        }
    }

    /**
     * Implementacion del dialogo de texto donde acepta el tramite de la solicitud de aumento en la linea de credito de la tarjeta seleccionada.
     */
    @Override
    public void onAumentarLineaCredito() {
        dialog = new AvisoAumentoDeLineaCreditoAceptadaDialog();
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * Clase que controla al ViewPager superior en la actividad.
     */
    public static class SuperiorPageAdapter extends FragmentPagerAdapter{

        private static int NUM_ITEMS = 2;
        private final int idTarjeta;

        public SuperiorPageAdapter(FragmentManager fragmentManager, int idTarjeta){
            super(fragmentManager);
            this.idTarjeta = idTarjeta;
        }

        @Override
        public Fragment getItem(int position) {

            DetalleTarjetasFragment fragmentDetalleSaldos = new DetalleTarjetasFragment();
            Bundle dataSaldos = new Bundle();
            dataSaldos.putBoolean("isDetalle2",ActivarFragmentos.USAR_FRAGMENTO_DETALLE_SALDOS);
            dataSaldos.putInt("idTarjeta",idTarjeta);
            fragmentDetalleSaldos.setArguments(dataSaldos);

            DetalleTarjetasFragment fragmentDetallePuntos = new DetalleTarjetasFragment();
            Bundle dataPuntos = new Bundle();
            dataPuntos.putBoolean("isDetalle2", ActivarFragmentos.USAR_FRAGMENTO_DETALLE_PUNTOS);
            dataPuntos.putInt("idTarjeta",idTarjeta);
            fragmentDetallePuntos.setArguments(dataPuntos);


            switch (position){
                case 0:
                    return fragmentDetalleSaldos;
                case 1:
                    return fragmentDetallePuntos;
                /*case 2:
                    return new DetalleTarjetasFragment();*/
                default:
                    return null;
            }
        }



        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

    /**
     * Clase que controla al ViewPager Inferior en la actividad.
     */
    public static class InferiorPageAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

        private static int NUM_ITEMS = 4;
        private final int idTarjeta;
        private final int[] iconos = {
                R.drawable.dollar_bill
                ,R.drawable.notification
                ,R.drawable.promociones
                ,R.drawable.shopping_cart
        };

        private final Context context;
        private ArrayList<Fragment> pages;



        public void setPages(int position,Fragment fragment){
            pages.remove(position);
            pages.add(position,fragment);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        public InferiorPageAdapter(FragmentManager fragmentManager, int idTarjeta,Context context, ArrayList<Fragment> pages){
            super(fragmentManager);
            this.idTarjeta = idTarjeta;
            this.context = context;
            this.pages = pages;
        }

        @Override
        public int getPageIconResId(int position) {
            return iconos[position];
        }


        @Override
        public CharSequence getPageTitle(int position) {

            Drawable image = ResourcesCompat.getDrawable(context.getResources(),iconos[position],null);
            image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());

            SpannableStringBuilder sp;
            ImageSpan imageSpan;
            switch (position){
                case 0 :
                    sp = new SpannableStringBuilder(" Transacciones");

                    imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);

                    sp.setSpan(imageSpan, 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    return sp;
                case 1 :
                    sp = new SpannableStringBuilder(" Notificaciones");

                    imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);

                    sp.setSpan(imageSpan, 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    return sp;
                case 2 :
                    sp = new SpannableStringBuilder(" Promociones");

                    imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);

                    sp.setSpan(imageSpan, 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    return sp;
                case 3 :
                    sp = new SpannableStringBuilder(" Servicios");

                    imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);

                    sp.setSpan(imageSpan, 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    return sp;
                default:
                    sp = new SpannableStringBuilder(" Servicios");
                    imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);
                    sp.setSpan(imageSpan, 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    return sp ;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }


    /**
     * Implementacion de evento de seleccion de en el menu de Servicios.
     * @param menu que ha sido seleccionado.
     */
    @Override
    public void onClickMenu(MenuServiciosDTO menu) {
        switch (menu.getId()){
            case 0 :
                adapterViewPagerInferior.setPages(3,new DetalleServiciosControlFragment());
                break;
            case 1 :
                adapterViewPagerInferior.setPages(3,new DetalleServiciosSubMenuFragment());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
