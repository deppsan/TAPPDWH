package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 13/02/2017.
 */

public class TransaccionesObjectDrawer {

    private int id;
    private String fechaTransaccion;
    private String detalleTransaccion;
    private double montoTransaccion;
    private String puntosTransaccion;
    private int colorCategoria;

    public TransaccionesObjectDrawer(int id,String fechaTransaccion, String detalleTransaccion, double montoTransaccion, String puntosTransaccion, int colorCategoria) {
        this.id = id;
        this.fechaTransaccion = fechaTransaccion;
        this.detalleTransaccion = detalleTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.puntosTransaccion = puntosTransaccion;
        this.colorCategoria = colorCategoria;
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

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public String getPuntosTransaccion() {
        return puntosTransaccion;
    }

    public void setPuntosTransaccion(String puntosTransaccion) {
        this.puntosTransaccion = puntosTransaccion;
    }

    public int getColorCategoria() {
        return colorCategoria;
    }

    public void setColorCategoria(int colorCategoria) {
        this.colorCategoria = colorCategoria;
    }
}
