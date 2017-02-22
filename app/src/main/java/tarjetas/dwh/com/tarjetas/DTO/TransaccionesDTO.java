package tarjetas.dwh.com.tarjetas.DTO;

/**
 * Created by ricar on 14/02/2017.
 */

public class TransaccionesDTO {

    private String fechaTransaccion;
    private String detalleTransaccion;
    private String puntos;
    private String valorTransaccion;
    private int categoria;

    public TransaccionesDTO(String fechaTransaccion, String detalleTransaccion, String puntos, String valorTransaccion, int categoria) {
        this.fechaTransaccion = fechaTransaccion;
        this.detalleTransaccion = detalleTransaccion;
        this.puntos = puntos;
        this.valorTransaccion = valorTransaccion;
        this.categoria = categoria;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getDetalleTransaccion() {
        return detalleTransaccion;
    }

    public void setDetalleTransaccion(String detalleTransaccion) {
        this.detalleTransaccion = detalleTransaccion;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(String valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
