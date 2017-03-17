package tarjetas.dwh.com.tarjetas.DTO;

/**
 * Created by ricar on 22/02/2017.
 */

public class MenuServiciosDTO {

    public int id;
    private int imagen;
    private String menu;

    public MenuServiciosDTO(int imagen, String menu, int id) {
        this.imagen = imagen;
        this.menu = menu;
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
