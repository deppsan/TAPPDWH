package tarjetas.dwh.com.tarjetas.model;

import io.realm.RealmObject;

/**
 * Created by ricar on 25/01/2017.
 */

public class Tarjeta extends RealmObject {

    private int numTarjeta;
    private int token;

    public Tarjeta() {}

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}


