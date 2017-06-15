package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 10/04/2017.
 */

public class ProgramaLealtadObject {
    private String img;
    private String descripcion;
    private String precio;

    public ProgramaLealtadObject(String img, String descripcion, String precio) {
        this.img = img;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
