package tarjetas.dwh.com.tarjetas.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;
import tarjetas.dwh.com.tarjetas.DTO.MenuServiciosDTO;
import tarjetas.dwh.com.tarjetas.DTO.TarjetasDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.CategoriasDrawerAdapter;
import tarjetas.dwh.com.tarjetas.adapter.TransaccionesAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.MenuServiciosControlDrawerObject;
import tarjetas.dwh.com.tarjetas.adapter.drawer.TransaccionesObjectDrawer;
import tarjetas.dwh.com.tarjetas.fragments.ControlLineaCreditoModificarLineaFragment;
import tarjetas.dwh.com.tarjetas.fragments.ControlLineaDeCreditoFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleNotificacionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetallePromocionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleSeguimientoDeTarjetaFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosActivarTarjetaFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosControlFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosIngresarNipFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosNipMenuFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosProgramaLealtadFavoritosFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosSaldosPagosFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosSolicitarNipFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleServiciosSubMenuFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTarjetasFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTransaccionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.ReporteRoboExtravioFragment;
import tarjetas.dwh.com.tarjetas.fragments.RoboExtravioReportadaFragment;
import tarjetas.dwh.com.tarjetas.fragments.dialog.AsignacionDeNipDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.AumentoDeLineaCreditoDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.AvisoAumentoDeLineaCreditoAceptadaDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.DetalleServiciosActivarTarjetaActivadaDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.DetalleServiciosActivarTarjetaAvisoDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.DetalleServiciosControlLineaAvisoConfirmadoModificarLinea;
import tarjetas.dwh.com.tarjetas.fragments.dialog.DetalleServiciosControlLineaConfirmarModificarLineaDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.CategoriaCrearDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ReporteRoboExtravioDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ServiciosControlBloqueoTarjetaDesactivarDialog;
import tarjetas.dwh.com.tarjetas.fragments.dialog.ServiciosControlBloqueoTarjetaDialog;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;
import tarjetas.dwh.com.tarjetas.utilities.ActivarFragmentos;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

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
                                                            , AumentoDeLineaCreditoDialog.AumentoDeLineaCreditoListener
                                                            , DetalleServiciosNipMenuFragment.DetalleServiciosNipMenuListener
                                                            , DetalleServiciosIngresarNipFragment.DetalleServiciosIngresarNipListener
                                                            , DetalleServiciosSolicitarNipFragment.DetalleServiciosSolicitarNipListener
                                                            , DetalleSeguimientoDeTarjetaFragment.DetalleSeguimientoDeTarjetaListener
                                                            , DetalleServiciosActivarTarjetaFragment.DetalleServiciosActivarTarjetaListener
                                                            , DetalleServiciosActivarTarjetaAvisoDialog.DetalleServiciosActivarTarjetaAvisoListener
                                                            , DetalleServiciosActivarTarjetaActivadaDialog.DetalleServiciosActivarTarjetaActivadaListener
                                                            , DetalleServiciosSaldosPagosFragment.DetalleServiciosSaldosPagosListener
                                                            , ControlLineaCreditoModificarLineaFragment.ControlLineaCreditoModificarLineaListener
                                                            , DetalleServiciosControlLineaConfirmarModificarLineaDialog.DetalleServiciosControlLineaConfirmarAumentoLineaDialogListener
                                                            , DetalleServiciosControlLineaAvisoConfirmadoModificarLinea.DetalleServiciosControlLineaAvisoConfirmadoModificarLineaListener
                                                            , DetalleTransaccionesFragment.DetalleTransaccionesListener
                                                            , CategoriaCrearDialog.DialogCrearCategoriaListener
                                                            , View.OnClickListener
                                                            , TarjetasApiClient.TarjetasApiGetSaldosDetalle{


    private FragmentPagerAdapter adapterViewPagerSuperior;
    private ViewPager superiorViewPager;
    private InferiorPageAdapter adapterViewPagerInferior;
    private ViewPager inferiorViewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ListView lstNavigationCategory;
    private Switch switchDialog;
    private View holdView;
    private String holdTarjetaNumero;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TransaccionesAdapter tAdapterHolder;
    private int adapterPosition;
    private ArrayList<tarjetas.dwh.com.tarjetas.model.Categorias> categoriesImages;
    private ImageView imgExpandir, image_tarjeta_one;

    private DialogFragment  dialog;

    private Context mContext;

    private boolean isColapsed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas_detalle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        mContext = this;
        int idTarjeta = getIntent().getIntExtra("idTarjeta",0);

        superiorViewPager = (ViewPager) findViewById(R.id.content_tarjetas_detail_superior);
        adapterViewPagerSuperior = new SuperiorPageAdapter(getSupportFragmentManager(),idTarjeta);
        imgExpandir = (ImageView) findViewById(R.id.imgExpandir);
        image_tarjeta_one = (ImageView) findViewById(R.id.image_tarjeta_one);
        imgExpandir.setOnClickListener(this);


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
        toolbar.setTitleTextColor(ContextCompat.getColor(mContext,R.color.white));

        setSupportActionBar(toolbar);


        /**
         * Secciones de la pantalla
         */
        superiorViewPager.setAdapter(adapterViewPagerSuperior);
        inferiorViewPager.setAdapter(adapterViewPagerInferior);

        tabs.setViewPager(inferiorViewPager);

        lstNavigationCategory = (ListView) findViewById(R.id.right_navigator_category);

        drawerLayout = (DrawerLayout) findViewById(R.id.nav_mis_detalles);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);



        categoriesImages = cargarInformacionMenuCategorias();

        lstNavigationCategory.setAdapter(new CategoriasDrawerAdapter(categoriesImages,R.layout.object_categorias_lista,getApplicationContext()) {
            @Override
            public void onEntradaSet(View v, CategoriasDrawerAdapter.ViewHolder mHolder) {
                mHolder.imageView = (ImageView) v.findViewById(R.id.imgLogoCategoria);
            }

            @Override
            public void onEntrada(Object categorias, CategoriasDrawerAdapter.ViewHolder mHolder, int position) {
                tarjetas.dwh.com.tarjetas.model.Categorias categoria = (tarjetas.dwh.com.tarjetas.model.Categorias) categorias;
                if ((int) categoria.getImagen() == R.drawable.plus_white){
                    Picasso.with(getApplicationContext()).load((int)categoria.getImagen()).resize(50,50).into(mHolder.imageView);
                }else{
                    Picasso.with(getApplicationContext()).load(RealmAdministrator.getInstance(getBaseContext()).getDrawableByCategory((int)categoria.getId(),true)).resize(50,50).into(mHolder.imageView);
                }

            }
        });

        lstNavigationCategory.getOnItemClickListener();
        lstNavigationCategory.setOnItemClickListener(new DrawerItemClickListener());


        lstNavigationCategory.bringToFront();
        drawerLayout.requestLayout();

        TarjetasApiClient.getInstance().getDetalleSaldosTarjetas(idTarjeta,this,this);


    }

    private ArrayList<tarjetas.dwh.com.tarjetas.model.Categorias> cargarInformacionMenuCategorias(){

        ArrayList<tarjetas.dwh.com.tarjetas.model.Categorias> categorias = RealmAdministrator.getInstance(getBaseContext()).getAllCategorias();

        tarjetas.dwh.com.tarjetas.model.Categorias catAgregar = new tarjetas.dwh.com.tarjetas.model.Categorias();
        catAgregar.setImagen(R.drawable.plus_white);
        categorias.add(catAgregar);
        return categorias;
    }

    /**
     * Implementacion de regreso al menu principal de servicios.
     */
    @Override
    public void onClickRegresar() {
        adapterViewPagerInferior.setPages(3,new DetalleServiciosFragment());
    }

    /**
     * Implementacion de boton para activar tarjetas en Servicios->Activar
     */
    @Override
    public void onPresionarActivarTarjeta(View view, String tarjetaNumero) {
        holdView = view;
        holdTarjetaNumero  = tarjetaNumero;

        dialog = new DetalleServiciosActivarTarjetaAvisoDialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * Implementacion de pantalla de seguimiento de la tarjeta, para enviar a la pantalla de activacion de tarjetas.
     */
    @Override
    public void seleccionarActivarTarjetaDesdeSeguimiento() {
        adapterViewPagerInferior.setPages(3,new DetalleServiciosActivarTarjetaFragment());
    }

    /**
     * Implementacion para ejecutar la modificación de la linea de credito de una tarjeta en especificada, ya sea la tarjeta seleccionada en el detalle (titular)
     * o una adicional.
     * @param linea
     */
    @Override
    public void onSeleccionarModificarLineaCredito(ControlLineaDeCreditoFragment.TarjetasLineas linea) {
        adapterViewPagerInferior.setPages(3,new ControlLineaCreditoModificarLineaFragment());
    }

    /**
     * Implementacion del aumento de la linea de credito, llama a una ventana de dialogo donde ser solicita la aprovacion por parte del cliente.
     */
    @Override
    public void onSeleccionarAumentarLimitedeLineadeCredito(double totalLineaDeCredito) {
        dialog = AumentoDeLineaCreditoDialog.getInstance(totalLineaDeCredito);
        dialog.setCancelable(false);
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
    public void onClickSeleccionarMenu(Object menu, View view, int position) {
        MenuServiciosControlDrawerObject optMenu = (MenuServiciosControlDrawerObject) menu;

        switch (optMenu.getId()){
            case 0 :
                switchDialog = (Switch) view.findViewById(R.id.swtServiciosControl);
                if (switchDialog.isChecked()){
                    dialog = new ServiciosControlBloqueoTarjetaDialog();
                    dialog.setCancelable(false);
                    dialog.show(getSupportFragmentManager(), FragmentTags.TARJETAS_DETALLE_FRAGMENT);
                }else{
                    dialog = new ServiciosControlBloqueoTarjetaDesactivarDialog();
                    dialog.setCancelable(false);
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
                adapterViewPagerInferior.setPages(3,new DetalleServiciosSaldosPagosFragment());
                break;
            case 2:
                adapterViewPagerInferior.setPages(3,new ControlLineaDeCreditoFragment());
                break;
            case 3:
                adapterViewPagerInferior.setPages(3,new DetalleServiciosNipMenuFragment());
                break;
        }
    }

    /**
     * Implementacion del dialogo de texto donde acepta el tramite de la solicitud de aumento en la linea de credito de la tarjeta seleccionada.
     */
    @Override
    public void onAumentarLineaCredito() {
        dialog = new AvisoAumentoDeLineaCreditoAceptadaDialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * Implementecion de funcionalidad del menu en Detalle->Servicios->Servicios->Solicitud de Nip
     * @param item Opcion del Menu Seleccionada
     */
    @Override
    public void seleccionarOpcionMenu(MenuServiciosDTO item) {
        switch(item.getId()){
            case 0:
                adapterViewPagerInferior.setPages(3,new DetalleServiciosSolicitarNipFragment());
                break;
            case 1:
                adapterViewPagerInferior.setPages(3,new DetalleServiciosIngresarNipFragment());
                break;
        }
    }

    /**
     * Implementacion de pantalla Detalle->Servicios->Servicios->Solicitar NIP->Envio de Nip para solicitar al banco nuevo nip.
     */
    @Override
    public void solicitarNipABanco() {
        dialog = new AsignacionDeNipDialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * Implementacion de boton para regresar de Solicitar Nip a Asignacion de Nip
     */
    @Override
    public void regresarASubMenuServicios() {
        adapterViewPagerInferior.setPages(3,new DetalleServiciosNipMenuFragment());
    }

    @Override
    public void aceptarCambioDeNip() {

    }

    /**
     * Implementacion de respuesta al Dialogo de Aviso al activar una Tarjeta desde Servicios-ActivarTarjeta
     */
    @Override
    public void onAceptarActivarTarjetaAviso() {
        int i = Integer.parseInt(holdTarjetaNumero);
        dialog = DetalleServiciosActivarTarjetaActivadaDialog.newInstance(i);

        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);


        holdTarjetaNumero = null;
    }

    /**
     * Implementacion de segundo Dialogo de Aviso, donde explica que ya fue activada la tarjeta despues de aceptar en
     * el dialogo anterior.
     */
    @Override
    public void aceptarActivarTarjeta() {
        Button button = (Button) holdView;
        holdView = null;

        button.setText(R.string.btnDesactivar);
        button.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
    }

    /**
     * ControlLineaCreditoModificarLineaFragment Listener para ejecutar cuando se acepta la modificacion de la linea de credito seleccionada
     */
    @Override
    public void onModificarLineaAceptar(String aumento) {
        dialog = DetalleServiciosControlLineaConfirmarModificarLineaDialog.getInstance(aumento);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * ControlLineaCreditoModificarLineaFragment Listener para regresar al control de lineas de credito.
     */
    @Override
    public void onModificarLineaCancelar() {
        adapterViewPagerInferior.setPages(3,new ControlLineaDeCreditoFragment());
    }

    /**
     * DetalleServiciosControlLineaConfirmarModificarLineaDialog
     */
    @Override
    public void onClickAceptarAumentoLinea() {
        dialog = new DetalleServiciosControlLineaAvisoConfirmadoModificarLinea();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),FragmentTags.TARJETAS_DETALLE_FRAGMENT);
    }

    /**
     * DetalleServiciosControlLineaConfirmarModificarLineaDialog
     */
    @Override
    public void onClickCancelarAumentoLinea() {

    }

    /**
     * DetalleServiciosControlLineaAvisoConfirmadoModificarLinea
     */
    @Override
    public void onConfirmadoModificacion() {
        adapterViewPagerInferior.setPages(3,new ControlLineaDeCreditoFragment());
    }

    /**
     * DetalleTransaccionesFragment Abre el menu de categorias una vez seleccionada la transacccion objetivo
     */
    @Override
    public void onLongPressTransaccion(int position, TransaccionesAdapter adapter) {
        drawerLayout.openDrawer(Gravity.RIGHT);
        adapterPosition = position;
        tAdapterHolder = adapter;
    }

    /**
     * CategoriaCrearDialog
     */
    @Override
    public void acepterCrearCategoria(int imagen, int imagenDrawable, String nombreCategoria) {
        RealmAdministrator.getInstance(getBaseContext()).crearCategoria(imagen,imagenDrawable,nombreCategoria);
    }

    @Override
    public void onSaldoRecibido(TarjetasDTO tarjeta) {

        Picasso.with(this).load(tarjeta.getImagen()).placeholder(R.drawable.bankcard).into(image_tarjeta_one);
    }

    @Override
    public void onFallaAlRecibirSaldo(mensaje error) {

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
                ,R.drawable.settings
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
            case 2 :
                adapterViewPagerInferior.setPages(3,new DetalleSeguimientoDeTarjetaFragment());
                break;
            case 3:
                adapterViewPagerInferior.setPages(3,new DetalleServiciosProgramaLealtadFavoritosFragment());
                break;
            case 4:
                adapterViewPagerInferior.setPages(3, new DetalleServiciosActivarTarjetaFragment());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if((parent.getAdapter().getCount() -1) == position){
                DialogCrearCategoriaImpl dialog = new DialogCrearCategoriaImpl(DetallesMisTarjetas.this);
                dialog.setCancelable(false);
                dialog.show();
            }else{
                ((TransaccionesObjectDrawer) tAdapterHolder.getItem(adapterPosition)).setColorCategoria(position+1);
                tAdapterHolder.notifyDataSetChanged();
                tAdapterHolder = null;
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawers();
                }
            }
        }
    }

    class DialogCrearCategoriaImpl extends CategoriaCrearDialog {

        public DialogCrearCategoriaImpl(Context context) {
            super(context);
        }

        @Override
        public void actualizarGrid() {
            CategoriasDrawerAdapter adapter = (CategoriasDrawerAdapter)lstNavigationCategory.getAdapter();
            categoriesImages = cargarInformacionMenuCategorias();
            adapter.updateData(categoriesImages);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detalle,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnCategorias:
                Intent i = new Intent(this,Categorias.class);
                startActivity(i);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imgExpandir:
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams ps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ViewPager.LayoutParams vp = new ViewPager.LayoutParams();

                if(isColapsed){
                    ps.weight = 2;
                    p.weight = 1.3f;
                    isColapsed = false;
                    imgExpandir.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.solapa));
                    image_tarjeta_one.setVisibility(View.VISIBLE);
                }else{
                    p.weight = 1;
                    ps.weight = 4;
                    isColapsed = true;
                    imgExpandir.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.solapadown));
                    image_tarjeta_one.setVisibility(View.GONE);
                }

                View i = (LinearLayout) inferiorViewPager.getParent();
                i.setLayoutParams(p);

                View s = (LinearLayout) superiorViewPager.getParent();
                s.setLayoutParams(ps);
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        CategoriasDrawerAdapter adapter = (CategoriasDrawerAdapter)lstNavigationCategory.getAdapter();
        categoriesImages = cargarInformacionMenuCategorias();
        adapter.updateData(categoriesImages);
    }
}
