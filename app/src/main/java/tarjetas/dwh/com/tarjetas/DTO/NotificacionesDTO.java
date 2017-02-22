package tarjetas.dwh.com.tarjetas.DTO;

/**
 * Created by ricar on 16/02/2017.
 */
public class NotificacionesDTO {
    private String fecha;
    private String descripcion;
    private int status;

    public NotificacionesDTO(String fecha, String descripcion, int status) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.status = status;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
