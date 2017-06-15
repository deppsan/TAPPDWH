package tarjetas.dwh.com.tarjetas.utilities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import tarjetas.dwh.com.tarjetas.DTO.Categoria;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.model.Categorias;
import tarjetas.dwh.com.tarjetas.model.Tarjeta;
import tarjetas.dwh.com.tarjetas.model.Usuario;

/**
 * Created by ricar on 25/01/2017.
 */

public class RealmAdministrator {

    private final String REALM_DB = "TAPP4.realm";

    private static RealmAdministrator instance;

    private Realm myRealm;
    private Context context;

    private RealmAdministrator(Context context){
        this.context = context;
        myRealm = Realm.getInstance(
                new RealmConfiguration.Builder(this.context).name(REALM_DB).build()
        );
    }

    public static RealmAdministrator getInstance(Context context){
        if (instance == null){
            instance = new RealmAdministrator(context);
        }
        return instance;
    }

    public void guardarTarjeta(String tarjetaNumero, String token){
        openRealm();
        Tarjeta tarjeta = myRealm.createObject(Tarjeta.class);
        tarjeta.setNumTarjeta(tarjetaNumero);
        tarjeta.setToken(token);
        tarjeta.setEstatus(false);
        closeRealm();
    }

    public void updateStatusTarjeta(boolean status,String numTarjeta){
        openRealm();
        Tarjeta tarjeta = myRealm.where(Tarjeta.class).equalTo("numTarjeta",numTarjeta).findFirst();
        tarjeta.setEstatus(status);
        closeRealm();
    }


    public void logAllTarjetas(){
        openRealm();
        RealmResults<Tarjeta> tarjetas = myRealm.where(Tarjeta.class).findAll();
        for (Tarjeta t : tarjetas){
            Log.d("tarjeta",t.toString());
        }
        closeRealm();
    }

    public ArrayList<Categorias> getAllCategorias(){
        ArrayList<Categorias> categorias = new ArrayList<>();
        RealmResults<Categorias> categoriasRealmResults = myRealm.where(Categorias.class).findAll();
        for (Categorias c : categoriasRealmResults){
            categorias.add(c);
        }
        return categorias;


    }

    public void setUsuario(String userName,String password){
        openRealm();
        Usuario usuario = myRealm.createObject(Usuario.class);

        usuario.setUserName(userName);
        usuario.setPassword(password);

        closeRealm();
    }

    public Usuario getUsuario(String user){
        Usuario usuarioVer = myRealm.where(Usuario.class).equalTo("userName",user).findFirst();
        return usuarioVer;
    }

    public boolean checkUsuario(String user){
        boolean ver = false;
        Usuario usuarioVer = getUsuario(user);
        if(usuarioVer != null){
            if (usuarioVer.getUserName().equals(user)){
                ver = true;
            }
        }
        return ver;
    }

    public void crearCategoriasBase(){
        if (getAllCategorias().size() == 0){

            openRealm();


            Categorias gastos = new Categorias();
            gastos.setCapacidad(2000);
            gastos.setId(1);
            gastos.setImagen(R.drawable.property_black);
            gastos.setNombre("Gastos");
            gastos.setImagenDrawer(R.drawable.property_white);
            gastos.setRecurrence(0);
            myRealm.copyToRealmOrUpdate(gastos);


            closeRealm();
            openRealm();
            Categorias viajes = new Categorias();
            viajes.setCapacidad(2000);
            viajes.setId(2);
            viajes.setImagen(R.drawable.airport_black);
            viajes.setNombre("viajes");
            viajes.setImagenDrawer(R.drawable.airport_white);
            viajes.setRecurrence(0);
            myRealm.copyToRealmOrUpdate(viajes);


            closeRealm();
            openRealm();
            Categorias hogar = new Categorias();
            hogar.setCapacidad(2000);
            hogar.setId(3);
            hogar.setImagen(R.drawable.home_black);
            hogar.setNombre("hogar");
            hogar.setImagenDrawer(R.drawable.home_white);
            hogar.setRecurrence(0);
            myRealm.copyToRealmOrUpdate(hogar);

            closeRealm();
            openRealm();

            Categorias compras = new Categorias();

            compras.setCapacidad(2000);
            compras.setId(4);
            compras.setImagen(R.drawable.shopping_cart_black);
            compras.setNombre("compras");
            compras.setImagenDrawer(R.drawable.shopping_cart_white);
            compras.setRecurrence(0);

            myRealm.copyToRealmOrUpdate(compras);

            closeRealm();
        }

    }

    public void crearCategoria(int image, int imageDrawer, String nombre){
        openRealm();
        Categorias nuevaCategoria = new Categorias();
        int newId = myRealm.where(Categorias.class).max("id").intValue() + 1;

        nuevaCategoria.setId(newId);
        nuevaCategoria.setImagen(image);
        nuevaCategoria.setImagenDrawer(imageDrawer);
        nuevaCategoria.setNombre(nombre);
        nuevaCategoria.setCapacidad(0);
        nuevaCategoria.setRecurrence(0);

        myRealm.copyToRealmOrUpdate(nuevaCategoria);

        closeRealm();
    }

    public int getDrawableByCategory(int idCategoria, boolean isDrawer ){
        int i;
        openRealm();
            RealmResults<Categorias> contains = myRealm.where(Categorias.class).equalTo("id",idCategoria).findAll();
            if (isDrawer){
                i = contains.first().getImagenDrawer();
            }else{
                i = contains.first().getImagen();
            }
        closeRealm();

        return i;

    }

    public Categorias getCategoryById(int idCategoria){
        Categorias categorias;

        openRealm();
        RealmResults<Categorias> categoriasRealmResults = myRealm.where(Categorias.class).equalTo("id",idCategoria).findAll();
        categorias = categoriasRealmResults.first();
        closeRealm();

        return categorias;
    }

    public void updateCategoriaById(Categoria categoria){
        Categorias dbCategoria = getCategoryById(categoria.getId());

        openRealm();

        dbCategoria.setCapacidad(categoria.getCapacidad());
        dbCategoria.setNombre(categoria.getNombre());
        dbCategoria.setRecurrence(categoria.getRecurrence());
        dbCategoria.setImagen(categoria.getImagen());
        dbCategoria.setImagenDrawer(categoria.getImagenDrawer());

        myRealm.copyToRealmOrUpdate(dbCategoria);
        closeRealm();
    }

    private void openRealm(){
        myRealm.beginTransaction();
    }
    private void closeRealm(){
        myRealm.commitTransaction();
    }
}
