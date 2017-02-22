package tarjetas.dwh.com.tarjetas.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import com.astuetz.PagerSlidingTabStrip;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.fragments.DetallePromocionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTarjetaGrafico;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTarjetasFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleNotificacionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.DetalleTransaccionesFragment;
import tarjetas.dwh.com.tarjetas.utilities.ActivarFragmentos;

/**
 * Created by ricar on 26/12/2016.
 */

public class DetallesMisTarjetas extends AppCompatActivity {


    private FragmentPagerAdapter adapterViewPagerSuperior;
    private ViewPager superiorViewPager;
    private FragmentPagerAdapter adapterViewPagerInferior;
    private ViewPager inferiorViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas_detalle);

        int idTarjeta = getIntent().getIntExtra("idTarjeta",0);

        superiorViewPager = (ViewPager) findViewById(R.id.content_tarjetas_detail_superior);
        adapterViewPagerSuperior = new SuperiorPageAdapter(getSupportFragmentManager(),idTarjeta);

        inferiorViewPager = (ViewPager) findViewById(R.id.content_tarjetas_detail_inferior);
        adapterViewPagerInferior = new InferiorPageAdapter(getSupportFragmentManager(),idTarjeta,getApplicationContext());

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);


        toolbar = (Toolbar) findViewById(R.id.tool_bar_Tarjetas_detalle);
        toolbar.setTitle("Mis Tarjetas");

        setSupportActionBar(toolbar);
        superiorViewPager.setAdapter(adapterViewPagerSuperior);
        inferiorViewPager.setAdapter(adapterViewPagerInferior);
        tabs.setViewPager(inferiorViewPager);


    }

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

    public static class InferiorPageAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {


        private static int NUM_ITEMS = 4;
        private final int idTarjeta;
        private final int[] iconos = {
                R.drawable.dollar_bill
                ,R.drawable.notification
                ,R.drawable.promociones
                ,R.drawable.settings
        };

        private final Context context;

        @Override
        public Fragment getItem(int position) {
            DetalleTransaccionesFragment transaccionesFragment = new DetalleTransaccionesFragment();
            DetalleNotificacionesFragment notificacionesDetalleFragment = new DetalleNotificacionesFragment();
            DetallePromocionesFragment detallePromocionesFragment = new DetallePromocionesFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("idTarjeta",idTarjeta);
            notificacionesDetalleFragment.setArguments(bundle);
            transaccionesFragment.setArguments(bundle);
            detallePromocionesFragment.setArguments(bundle);

            switch (position){
                case 0:
                    return transaccionesFragment;
                case 1:
                    return notificacionesDetalleFragment;
                case 2:
                    return detallePromocionesFragment;
                case 3:
                    return new DetalleTarjetaGrafico();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        public InferiorPageAdapter(FragmentManager fragmentManager, int idTarjeta,Context context){
            super(fragmentManager);
            this.idTarjeta = idTarjeta;
            this.context = context;
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

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
