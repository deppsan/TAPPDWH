package tarjetas.dwh.com.tarjetas.activities.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.menuItemDTO;
import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.adapter.DrawerListAdapter;
import tarjetas.dwh.com.tarjetas.adapter.drawer.MenuNaviDrawer;
import tarjetas.dwh.com.tarjetas.fragments.PromocionesFragment;
import tarjetas.dwh.com.tarjetas.fragments.TarjetasListaFragment;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;
import tarjetas.dwh.com.tarjetas.utilities.FragmentTags;
import tarjetas.dwh.com.tarjetas.utilities.UtilsSharedPreference;

/**
 * Created by ricar on 26/12/2016.
 */

public class BaseActivity extends AppCompatActivity implements
        TarjetasApiClient.TarjetasApiGetMenuListener {
    protected Toolbar toolbar;
    protected usuarioDTO user;
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected ListView listView;
    protected Bundle bundle = new Bundle();


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigationDrawer);
        listView = (ListView) findViewById(R.id.left_drawer_list);

        setSupportActionBar(toolbar);

        //Coloca al final del menu de Navegacion la opcion de salir de aplicacion(cerrar sesion)
        View footerView = ((LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.object_menu_navi_logout,null,false);
        listView.addFooterView(footerView);
        listView.getOnItemClickListener();


        /**
         * Toda esta seccion sirve para hacer aparecer y hacer funcionar el boton
         * del menu de navegacion en la ToolBar
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        /**
         * Seccion para llenar el menu de navegacion (navigation Drawer)
         */
        user = UtilsSharedPreference.getInstance(this).getUserSharedPreference();
        TarjetasApiClient.getInstance().getMenu(user.getId_User(),getApplicationContext(),this);

        /* Se agrega el listener para el menu de navegacion*/
        listView.setOnItemClickListener(new DrawerItemClickListener());


    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    protected void changeFragmentFromMenu(Object object, String fragmentTag, Fragment fragment, String title){
        Fragment nextFragment = (Fragment) object;
        if(!fragment.getTag().equals(fragmentTag)){

            nextFragment.setArguments(bundle);
            replaceFragment(R.id.content_frame,nextFragment,fragmentTag,fragment.getTag());
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            setTitle(title);

            Toast.makeText(this, "Voy a:" + fragmentTag, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Me quedo en: " + fragment.getTag(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Crea completamente el menu de navegacion en base a la respuesta de opciones del WS.
     * @param menuArray Array con las opciones.
     */
    @Override
    public void onMenuRecibido(menuItemDTO[] menuArray) {
        ArrayList<MenuNaviDrawer> items = new ArrayList<MenuNaviDrawer>();
        int idIcon = 0;

        items.add(new MenuNaviDrawer(R.drawable.bankcard,"Mis Tarjetas",0));
        for(int i = 0; i < menuArray.length; i++){
            switch (menuArray[i].getId_option()){
                case 2:
                    idIcon = R.drawable.pricetag;
                    break;
                case 3:
                    idIcon = R.drawable.appointmentremainder;
                    break;
                case 4:
                    idIcon = R.drawable.markfilled;
                    break;
                case 5:
                    idIcon = R.drawable.phone;
                    break;
                case 6:
                    idIcon = R.drawable.questionmark;
                    break;
                case 7:
                    idIcon = R.drawable.settingsfill;
                    break;
            }
            items.add(new MenuNaviDrawer(idIcon,menuArray[i].getMenu_title(),menuArray[i].getId_option()));
        }
        listView.setAdapter(new DrawerListAdapter(this,items));
    }

    @Override
    public void onErrorAlRecibirMenu(String error) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }

    /**
     * Listener del Listview del Menu de Navegacion, que llama a la funcion de cambio de Fragmentos
     */
    public class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /*Funcion para cambiar los fragmentos, segun se seleccione en el menu*/
    private void selectItem(int position) {
        if (position == listView.getChildCount() - 1){
            /*Elimina los datos del usuario y cierra la aplicacion*/
            UtilsSharedPreference.getInstance(this).eraseUserSharedPreferences();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }else{
            /*Activa el fragmento seleccionado*/
            MenuNaviDrawer menuSeleccion = (MenuNaviDrawer) listView.getAdapter().getItem(position);
            Fragment fragment = (Fragment)getSupportFragmentManager().findFragmentById(R.id.content_frame);
            switch (menuSeleccion.getId_option()){
                case 0:/*Mis Tarjetas*/
                    changeFragmentFromMenu(new TarjetasListaFragment(),FragmentTags.TARJETAS_LISTA_FRAGMENT,fragment,"Mis Tarjetas");
                    break;
                case 2:/*Promociones*/
                    changeFragmentFromMenu(new PromocionesFragment(),FragmentTags.PROMOCIONES_FRAGMENT,fragment,"Promociones");
                    break;
                case 6:/*Promociones*/
                    UtilsSharedPreference.getInstance(this).eraseAllSharedPreferences();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    break;
            }
            drawerLayout.closeDrawers();
        }
    }
}
