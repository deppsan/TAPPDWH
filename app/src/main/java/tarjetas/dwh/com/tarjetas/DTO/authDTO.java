package tarjetas.dwh.com.tarjetas.DTO;

import com.google.gson.annotations.SerializedName;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;

/**
 * Created by ricar on 06/12/2016.
 */

public class authDTO extends mensaje {
    @SerializedName("auth")
    private int auth;

    @SerializedName("palabra")
    private String palabra;

    @SerializedName("acces_alert")
    private String acces_alert;

    public authDTO(int auth, String palabra, String acces_alert) {
        super();
        this.auth = auth;
        this.palabra = palabra;
        this.acces_alert = acces_alert;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getAcces_alert() {
        return acces_alert;
    }

    public void setAcces_alert(String acces_alert) {
        this.acces_alert = acces_alert;
    }
}
