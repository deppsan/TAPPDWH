package tarjetas.dwh.com.tarjetas.utilities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import tarjetas.dwh.com.tarjetas.DTO.Categoria;
import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.model.Categorias;
import tarjetas.dwh.com.tarjetas.model.ProductosLealtad;
import tarjetas.dwh.com.tarjetas.model.Tarjeta;
import tarjetas.dwh.com.tarjetas.model.Transacciones;
import tarjetas.dwh.com.tarjetas.model.Usuario;

/**
 * Created by ricar on 25/01/2017.
 */

public class RealmAdministrator {

    private final String REALM_DB = "TDB_2.realm";

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

    public void crearTransccionesDommy(){
        if(getAllTransacciones().size() == 0){
            openRealm();

            Transacciones trasaccion1 = myRealm.createObject(Transacciones.class);
            trasaccion1.setId(1);
            trasaccion1.setFecha("2016-08-01 08:00:00");
            trasaccion1.setDetalle("Computer.com");
            trasaccion1.setPuntos("+20 Puntos");
            trasaccion1.setValor(600);
            trasaccion1.setCategoria(1);

            Transacciones trasaccion2 = myRealm.createObject(Transacciones.class);
            trasaccion2.setId(2);
            trasaccion2.setFecha("2016-08-01 08:00:00");
            trasaccion2.setDetalle("Computer.com");
            trasaccion2.setPuntos("+20 Puntos");
            trasaccion2.setValor(600);
            trasaccion2.setCategoria(2);

            Transacciones trasaccion3 = myRealm.createObject(Transacciones.class);
            trasaccion3.setId(3);
            trasaccion3.setFecha("2016-08-01 08:00:00");
            trasaccion3.setDetalle("Computer.com");
            trasaccion3.setPuntos("+20 Puntos");
            trasaccion3.setValor(600);
            trasaccion3.setCategoria(3);

            Transacciones trasaccion4 = myRealm.createObject(Transacciones.class);
            trasaccion4.setId(4);
            trasaccion4.setFecha("2016-08-01 08:00:00");
            trasaccion4.setDetalle("Computer.com");
            trasaccion4.setPuntos("+20 Puntos");
            trasaccion4.setValor(600);
            trasaccion4.setCategoria(4);

            Transacciones trasaccion5 = myRealm.createObject(Transacciones.class);
            trasaccion5.setId(5);
            trasaccion5.setFecha("2016-08-01 08:00:00");
            trasaccion5.setDetalle("Computer.com");
            trasaccion5.setPuntos("+20 Puntos");
            trasaccion5.setValor(600);
            trasaccion5.setCategoria(1);

            Transacciones trasaccion6 = myRealm.createObject(Transacciones.class);
            trasaccion6.setId(6);
            trasaccion6.setFecha("2016-08-01 08:00:00");
            trasaccion6.setDetalle("Computer.com");
            trasaccion6.setPuntos("+20 Puntos");
            trasaccion6.setValor(600);
            trasaccion6.setCategoria(2);

            Transacciones trasaccion7 = myRealm.createObject(Transacciones.class);
            trasaccion7.setId(7);
            trasaccion7.setFecha("2016-08-01 08:00:00");
            trasaccion7.setDetalle("Computer.com");
            trasaccion7.setPuntos("+20 Puntos");
            trasaccion7.setValor(600);
            trasaccion7.setCategoria(3);

            Transacciones trasaccion8 = myRealm.createObject(Transacciones.class);
            trasaccion8.setId(8);
            trasaccion8.setFecha("2016-08-01 08:00:00");
            trasaccion8.setDetalle("Computer.com");
            trasaccion8.setPuntos("+20 Puntos");
            trasaccion8.setValor(600);
            trasaccion8.setCategoria(4);

            Transacciones trasaccion9 = myRealm.createObject(Transacciones.class);
            trasaccion9.setId(9);
            trasaccion9.setFecha("2016-08-01 08:00:00");
            trasaccion9.setDetalle("Computer.com");
            trasaccion9.setPuntos("+20 Puntos");
            trasaccion9.setValor(600);
            trasaccion9.setCategoria(1);

            Transacciones trasaccion10 = myRealm.createObject(Transacciones.class);
            trasaccion10.setId(10);
            trasaccion10.setFecha("2016-08-01 08:00:00");
            trasaccion10.setDetalle("Computer.com");
            trasaccion10.setPuntos("+20 Puntos");
            trasaccion10.setValor(600);
            trasaccion10.setCategoria(2);

            Transacciones trasaccion11 = myRealm.createObject(Transacciones.class);
            trasaccion11.setId(11);
            trasaccion11.setFecha("2016-08-01 08:00:00");
            trasaccion11.setDetalle("Computer.com");
            trasaccion11.setPuntos("+20 Puntos");
            trasaccion11.setValor(600);
            trasaccion11.setCategoria(3);

            closeRealm();
        }
    }

    public ArrayList<Transacciones> getAllTransacciones(){
        ArrayList<Transacciones> transacciones = new ArrayList<>();
        RealmResults<Transacciones> transaccionesRealmResults = myRealm.where(Transacciones.class).findAll();
        for (Transacciones t : transaccionesRealmResults){
            transacciones.add(t);
        }
        return transacciones;
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
            try{
                if (isDrawer){
                    i = contains.first().getImagenDrawer();
                }else{
                    i = contains.first().getImagen();
                }
            }catch (Exception e){
                Log.e("Error Controlado RealmAdministrator","No se encuentra la categoria previamente asignada");
                i = R.drawable.bankcard;
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

    public void deleteCategoryById(int idCategory){
        openRealm();

        RealmResults<Categorias> results = myRealm.where(Categorias.class).equalTo("id",idCategory).findAll();
        results.clear();

        closeRealm();
    }

    public ArrayList<Transacciones> getTransaccionesbyCategory(int idCategoria){
        ArrayList<Transacciones> result = new ArrayList<>();

        openRealm();
        RealmResults<Transacciones> results = myRealm.where(Transacciones.class).equalTo("categoria",idCategoria).findAll();
        closeRealm();

        for (Transacciones t : results){
            result.add(t);
        }

        return result;
    }

    public void updateTransacctionCategory(int idTransaction, int idCategory){
        openRealm();
        RealmResults<Transacciones> tResult = myRealm.where(Transacciones.class).equalTo("id",idTransaction).findAll();
        Transacciones t = tResult.first();
        t.setCategoria(idCategory);
        closeRealm();
    }

    public void createProductosLealtadBase(){
        openRealm();

        ProductosLealtad productosLealtad = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad1 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad2 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad3 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad4 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad5 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad6 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad7 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad8 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad9 = myRealm.createObject(ProductosLealtad.class);
        ProductosLealtad productosLealtad10 = myRealm.createObject(ProductosLealtad.class);


        productosLealtad9.setId(1);
        productosLealtad9.setDescripcion("Estufa piso Mabe");
        productosLealtad9.setAmount(52957);
        productosLealtad9.setImage("http://blog.homedepot.com.mx/wp-content/uploads/2014/06/estufas_interiores.jpg");
        productosLealtad9.setTipoCategoria(1);
        productosLealtad9.setSugerido(true);
        productosLealtad9.setFavorito(false);

        productosLealtad10.setId(2);
        productosLealtad10.setDescripcion("iPad 64 gb");
        productosLealtad10.setAmount(76670);
        productosLealtad10.setImage("http://currys.cdn.dixons.com/css/themes/apple_ipad_air/img/ipad-intro.png");
        productosLealtad10.setTipoCategoria(2);
        productosLealtad10.setSugerido(true);
        productosLealtad10.setFavorito(false);

        productosLealtad6.setId(3);
        productosLealtad6.setDescripcion("Estufa piso Mabe");
        productosLealtad6.setAmount(52957);
        productosLealtad6.setImage("http://blog.homedepot.com.mx/wp-content/uploads/2014/06/estufas_interiores.jpg");
        productosLealtad6.setTipoCategoria(1);
        productosLealtad6.setSugerido(false);
        productosLealtad6.setFavorito(false);

        productosLealtad7.setId(4);
        productosLealtad7.setDescripcion("iPad 64 gb");
        productosLealtad7.setAmount(76670);
        productosLealtad7.setImage("http://currys.cdn.dixons.com/css/themes/apple_ipad_air/img/ipad-intro.png");
        productosLealtad7.setTipoCategoria(2);
        productosLealtad7.setSugerido(false);
        productosLealtad7.setFavorito(false);

        productosLealtad8.setId(5);
        productosLealtad8.setDescripcion("Teatro en Casa LG");
        productosLealtad8.setAmount(52957);
        productosLealtad8.setImage("http://2.wlimg.com/product_images/bc-full/dir_99/2945407/home-theater-1056595.jpg");
        productosLealtad8.setTipoCategoria(3);
        productosLealtad8.setSugerido(true);
        productosLealtad8.setFavorito(false);

        productosLealtad3.setId(6);
        productosLealtad3.setDescripcion("Estufa piso Mabe");
        productosLealtad3.setAmount(52957);
        productosLealtad3.setImage("http://blog.homedepot.com.mx/wp-content/uploads/2014/06/estufas_interiores.jpg");
        productosLealtad3.setTipoCategoria(1);
        productosLealtad3.setSugerido(false);
        productosLealtad3.setFavorito(false);

        productosLealtad4.setId(7);
        productosLealtad4.setDescripcion("iPad 64 gb");
        productosLealtad4.setAmount(76670);
        productosLealtad4.setImage("http://currys.cdn.dixons.com/css/themes/apple_ipad_air/img/ipad-intro.png");
        productosLealtad4.setTipoCategoria(2);
        productosLealtad4.setSugerido(false);
        productosLealtad4.setFavorito(false);

        productosLealtad5.setId(8);
        productosLealtad5.setDescripcion("Teatro en Casa LG");
        productosLealtad5.setAmount(52957);
        productosLealtad5.setImage("http://2.wlimg.com/product_images/bc-full/dir_99/2945407/home-theater-1056595.jpg");
        productosLealtad5.setTipoCategoria(3);
        productosLealtad5.setSugerido(false);
        productosLealtad5.setFavorito(false);

        productosLealtad.setId(9);
        productosLealtad.setDescripcion("Estufa piso Mabe");
        productosLealtad.setAmount(52957);
        productosLealtad.setImage("http://blog.homedepot.com.mx/wp-content/uploads/2014/06/estufas_interiores.jpg");
        productosLealtad.setTipoCategoria(1);
        productosLealtad.setSugerido(false);
        productosLealtad.setFavorito(false);

        productosLealtad1.setId(10);
        productosLealtad1.setDescripcion("iPad 64 gb");
        productosLealtad1.setAmount(76670);
        productosLealtad1.setImage("http://currys.cdn.dixons.com/css/themes/apple_ipad_air/img/ipad-intro.png");
        productosLealtad1.setTipoCategoria(2);
        productosLealtad1.setSugerido(false);
        productosLealtad1.setFavorito(false);

        productosLealtad2.setId(11);
        productosLealtad2.setDescripcion("Teatro en Casa LG");
        productosLealtad2.setAmount(52957);
        productosLealtad2.setImage("http://2.wlimg.com/product_images/bc-full/dir_99/2945407/home-theater-1056595.jpg");
        productosLealtad2.setTipoCategoria(3);
        productosLealtad2.setSugerido(false);
        productosLealtad2.setFavorito(false);


        closeRealm();
    }

    public ArrayList<ProductosLealtad> getProductosLealtadByCategory(int idCategoriy){
        ArrayList<ProductosLealtad> productosLealtads = new ArrayList<ProductosLealtad>();

        RealmResults<ProductosLealtad> results = myRealm.where(ProductosLealtad.class).equalTo("tipoCategoria",idCategoriy).findAll();

        for (ProductosLealtad p : results){
            productosLealtads.add(p);
        }

        return productosLealtads;
    }

    public ArrayList<ProductosLealtad> getProductosLealtadFavoritos(){
        ArrayList<ProductosLealtad> productosLealtads = new ArrayList<ProductosLealtad>();
        RealmResults<ProductosLealtad> results = myRealm.where(ProductosLealtad.class).equalTo("favorito",true).findAll();

        for (ProductosLealtad p : results){
            productosLealtads.add(p);
        }
        return productosLealtads;
    }


    public ArrayList<ProductosLealtad> getProductosLealtadSugeridos(){
        ArrayList<ProductosLealtad> productosLealtads = new ArrayList<ProductosLealtad>();
        RealmResults<ProductosLealtad> results = myRealm.where(ProductosLealtad.class).equalTo("sugerido",true).findAll();

        for (ProductosLealtad p : results){
            productosLealtads.add(p);
        }
        return productosLealtads;
    }

    public ArrayList<ProductosLealtad> getProductosLealtadPorCategoria(int idCategoria){
        ArrayList<ProductosLealtad> productosLealtads = new ArrayList<ProductosLealtad>();
        RealmResults<ProductosLealtad> results = myRealm.where(ProductosLealtad.class).equalTo("tipoCategoria",idCategoria).findAll();

        for (ProductosLealtad p : results){
            productosLealtads.add(p);
        }
        return productosLealtads;
    }

    public void setProductoLealtadFavorito(int idProducto,boolean isFavorite){
        openRealm();

            RealmResults<ProductosLealtad> results = myRealm.where(ProductosLealtad.class).equalTo("id",idProducto).findAll();
            ProductosLealtad p = results.first();

            p.setFavorito(isFavorite);

        closeRealm();
    }


    private void openRealm(){
        myRealm.beginTransaction();
    }
    private void closeRealm(){
        myRealm.commitTransaction();
    }
}
