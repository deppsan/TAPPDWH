package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 13/02/2017.
 */

public class TransaccionesObjectDrawer {
    private String fechaTransaccion;
    private String detalleTransaccion;
    private String montoTransaccion;
    private String puntosTransaccion;
    private int colorCategoria;

    public TransaccionesObjectDrawer(String fechaTransaccion, String detalleTransaccion, String montoTransaccion, String puntosTransaccion, int colorCategoria) {
        this.fechaTransaccion = fechaTransaccion;
        this.detalleTransaccion = detalleTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.puntosTransaccion = puntosTransaccion;
        this.colorCategoria = colorCategoria;
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

    public String getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(String montoTransaccion) {
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
