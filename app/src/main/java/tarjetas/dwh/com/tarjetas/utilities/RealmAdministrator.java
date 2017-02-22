package tarjetas.dwh.com.tarjetas.utilities;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import tarjetas.dwh.com.tarjetas.model.Tarjeta;
import tarjetas.dwh.com.tarjetas.model.Usuario;

/**
 * Created by ricar on 25/01/2017.
 */

public class RealmAdministrator {

    private final String REALM_DB = "TAPP.realm";

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


    private void openRealm(){
        myRealm.beginTransaction();
    }
    private void closeRealm(){
        myRealm.commitTransaction();
    }
}
