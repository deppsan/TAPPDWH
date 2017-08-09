package tarjetas.dwh.com.tarjetas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.interfaces.FragmentPageLifeCycle;

/**
 * Created by ricar on 28/07/2017.
 */

public class DetalleServiciosProgramaLealtadFragmentOne extends Fragment implements ViewPager.OnPageChangeListener{

    TabLayout tabs;
    ViewPager viewPager;
    LinearLayout btnRegresar;

    ProgramaLealtadPageAdapter pageAdapter;

    int currentPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_servicios_programa_lealtad_1,container,false);

        tabs = (TabLayout) v.findViewById(R.id.tabs_productos);
        viewPager = (ViewPager) v.findViewById(R.id.frame_fragment_viewpager);
        btnRegresar = (LinearLayout) v.findViewById(R.id.btnRegresarMenuServicios);

        pageAdapter = new ProgramaLealtadPageAdapter(getChildFragmentManager(),getContext());

        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(this);

        tabs.setupWithViewPager(viewPager);

        return v;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        FragmentPageLifeCycle fragmentToShow = (FragmentPageLifeCycle) pageAdapter.getItem(position);
        fragmentToShow.onResumeFragment();

        currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class ProgramaLealtadPageAdapter extends FragmentStatePagerAdapter {

        private static int NUM_ITEMS = 3;
        private int[] titles = {
                R.string.tab_title_recomendados,
                R.string.tab_title_mas_productos,
                R.string.tab_title_favoritos
        };
        Context mcontext;


        public ProgramaLealtadPageAdapter(FragmentManager fm,Context context) {
            super(fm);
            this.mcontext = context;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return new DetalleServiciosProgramaLealtadRecomendadosFragment();
                case 1:
                    return new DetalleServiciosProgramaLealtadFavoritosFragment();
                case 2:
                    return new DetalleServiciosProgramaLealtadFavoritosFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String t = mcontext.getResources().getString(titles[position]);
            return t;
        }
    }

}
