package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 12/03/2017.
 */

public class MenuServiciosControlDrawerObject {

    private String txtTitulo;
    private String txtDescripcion;
    private int id;
    private boolean isActive;

    public MenuServiciosControlDrawerObject(String txtTitulo, String txtDescripcion, int id, boolean isActive) {
        this.txtTitulo = txtTitulo;
        this.txtDescripcion = txtDescripcion;
        this.id = id;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(String txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public String getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(String txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
