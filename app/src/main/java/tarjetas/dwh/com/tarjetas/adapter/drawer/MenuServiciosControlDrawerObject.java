package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 12/03/2017.
 */

public class MenuServiciosControlDrawerObject {

    private String txtTitulo;
    private String txtDescripcion;
    private int id;

    public MenuServiciosControlDrawerObject(String txtTitulo, String txtDescripcion, int id) {
        this.txtTitulo = txtTitulo;
        this.txtDescripcion = txtDescripcion;
        this.id = id;
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
