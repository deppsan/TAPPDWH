package tarjetas.dwh.com.tarjetas.DTO;

/**
 * Created by ricar on 14/02/2017.
 */

public class TransaccionesDTO {


    private int id;
    private String fechaTransaccion;
    private String detalleTransaccion;
    private String puntos;
    private double valorTransaccion;
    private int categoria;

    public TransaccionesDTO(int id, String fechaTransaccion, String detalleTransaccion, String puntos, double valorTransaccion, int categoria) {
        this.id = id;
        this.fechaTransaccion = fechaTransaccion;
        this.detalleTransaccion = detalleTransaccion;
        this.puntos = puntos;
        this.valorTransaccion = valorTransaccion;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(double valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
