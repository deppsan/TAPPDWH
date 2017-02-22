package tarjetas.dwh.com.tarjetas.DTO;

/**
 * Created by ricar on 17/02/2017.
 */

public class PromocionesDTO {
    private int imgTienda;
    private String descripcion;
    private int imgTipo;

    public PromocionesDTO(int imgTienda, String descripcion, int imgTipo) {
        this.imgTienda = imgTienda;
        this.descripcion = descripcion;
        this.imgTipo = imgTipo;
    }

    public int getImgTienda() {
        return imgTienda;
    }

    public void setImgTienda(int imgTienda) {
        this.imgTienda = imgTienda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgTipo() {
        return imgTipo;
    }

    public void setImgTipo(int imgTipo) {
        this.imgTipo = imgTipo;
    }
}
