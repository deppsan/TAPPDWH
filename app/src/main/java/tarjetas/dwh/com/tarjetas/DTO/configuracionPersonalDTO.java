package tarjetas.dwh.com.tarjetas.DTO;

import com.google.gson.annotations.SerializedName;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;

/**
 * Created by ricar on 12/12/2016.
 */

public class configuracionPersonalDTO extends mensaje {

    @SerializedName("app_imagen")
    private String app_imagen;

    @SerializedName("app_banner")
    private String app_banner;

    @SerializedName("app_color1")
    private String app_color1;

    @SerializedName("app_color2")
    private String app_color2;

    public String getApp_color2() {
        return app_color2;
    }

    public void setApp_color2(String app_color2) {
        this.app_color2 = app_color2;
    }

    public String getApp_imagen() {
        return app_imagen;
    }

    public void setApp_imagen(String app_imagen) {
        this.app_imagen = app_imagen;
    }

    public String getApp_banner() {
        return app_banner;
    }

    public void setApp_banner(String app_banner) {
        this.app_banner = app_banner;
    }

    public String getApp_color1() {
        return app_color1;
    }

    public void setApp_color1(String app_color1) {
        this.app_color1 = app_color1;
    }

    public configuracionPersonalDTO(String app_imagen, String app_banner, String app_color1, String app_color2) {
        super();
        this.app_imagen = app_imagen;
        this.app_banner = app_banner;
        this.app_color1 = app_color1;
        this.app_color2 = app_color2;
    }
}
