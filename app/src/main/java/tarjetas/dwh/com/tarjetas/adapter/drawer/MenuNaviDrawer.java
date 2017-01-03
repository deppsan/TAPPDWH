package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 16/12/2016.
 */

public class MenuNaviDrawer {
    private int idImage;
    private String textMenu;
    private int id_option;


    public MenuNaviDrawer(int idImage, String textMenu, int id_option) {
        this.idImage = idImage;
        this.textMenu = textMenu;
        this.id_option = id_option;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getTextMenu() {
        return textMenu;
    }

    public void setTextMenu(String textMenu) {
        this.textMenu = textMenu;
    }

    public int getId_option() {
        return id_option;
    }

    public void setId_option(int id_option) {
        this.id_option = id_option;
    }
}
