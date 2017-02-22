package tarjetas.dwh.com.tarjetas.DTO;

/**
 * Created by ricar on 22/02/2017.
 */

public class MenuServiciosDTO {

    private int imagen;
    private String menu;

    public MenuServiciosDTO(int imagen, String menu) {
        this.imagen = imagen;
        this.menu = menu;
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
}
