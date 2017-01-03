package tarjetas.dwh.com.tarjetas.DTO.Mensaje;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ricar on 13/12/2016.
 */

public class mensaje{

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("errorCode")
    private String errorCode;

    @SerializedName("status")
    private String status;

    public mensaje() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public mensaje(String descripcion, String errorCode, String status) {
        this.descripcion = descripcion;
        this.errorCode = errorCode;
        this.status = status;
    }
}
