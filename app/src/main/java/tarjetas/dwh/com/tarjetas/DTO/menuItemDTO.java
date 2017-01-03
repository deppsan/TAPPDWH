package tarjetas.dwh.com.tarjetas.DTO;

import com.google.gson.annotations.SerializedName;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;

/**
 * Created by ricar on 16/12/2016.
 */

public class menuItemDTO extends mensaje {

    @SerializedName("menu_title")
    private String menu_title;

    @SerializedName("webicon")
    private String webicon;

    @SerializedName("ui_sref")
    private String ui_sref;

    @SerializedName("badges")
    private String badges;

    @SerializedName("id_option")
    private int id_option;

    public menuItemDTO(String menu_title, String webicon, String ui_sref, String badges, int id_option) {
        this.menu_title = menu_title;
        this.webicon = webicon;
        this.ui_sref = ui_sref;
        this.badges = badges;
        this.id_option = id_option;
    }

    public menuItemDTO(String descripcion, String errorCode, String status, String menu_title, String webicon, String ui_sref, String badges, int id_option) {
        super(descripcion, errorCode, status);
        this.menu_title = menu_title;
        this.webicon = webicon;
        this.ui_sref = ui_sref;
        this.badges = badges;
        this.id_option = id_option;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public String getWebicon() {
        return webicon;
    }

    public void setWebicon(String webicon) {
        this.webicon = webicon;
    }

    public String getUi_sref() {
        return ui_sref;
    }

    public void setUi_sref(String ui_sref) {
        this.ui_sref = ui_sref;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }

    public int getId_option() {
        return id_option;
    }

    public void setId_option(int id_option) {
        this.id_option = id_option;
    }
}
