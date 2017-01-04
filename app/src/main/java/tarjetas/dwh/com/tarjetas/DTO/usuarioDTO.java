package tarjetas.dwh.com.tarjetas.DTO;

import com.google.gson.annotations.SerializedName;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;

/**
 * Created by ricar on 06/12/2016.
 */

public class usuarioDTO extends mensaje {

    @SerializedName("id_user")
    private int id_User;

    @SerializedName("app_username")
    private String app_username;

    @SerializedName("app_password")
    private String app_password;

    @SerializedName("nombre")
    private String app_nombre;

    @SerializedName("app")
    private String app_apellidop;

    @SerializedName("apm")
    private String app_apellidom;

    @SerializedName("app_mail")
    private String app_mail;

    @SerializedName("app_direccion")
    private String app_direccion;

    @SerializedName("app_imagen")
    private String app_imagen;

    @SerializedName("app_token")
    private String app_token;

    @SerializedName("app_word")
    private String app_word;

    @SerializedName("id_group")
    private int id_group;

    @SerializedName("personalization")
    private configuracionPersonalDTO personalization;

    @SerializedName("acces_alert")
    private String acces_alert;

    @SerializedName("token")
    private String token;

    @SerializedName("autho")
    private int autho;

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public String getApp_username() {
        return app_username;
    }

    public void setApp_username(String app_username) {
        this.app_username = app_username;
    }

    public String getApp_password() {
        return app_password;
    }

    public void setApp_password(String app_password) {
        this.app_password = app_password;
    }

    public String getApp_nombre() {
        return app_nombre;
    }

    public void setApp_nombre(String app_nombre) {
        this.app_nombre = app_nombre;
    }

    public String getApp_apellidop() {
        return app_apellidop;
    }

    public void setApp_apellidop(String app_apellidop) {
        this.app_apellidop = app_apellidop;
    }

    public String getApp_apellidom() {
        return app_apellidom;
    }

    public void setApp_apellidom(String app_apellidom) {
        this.app_apellidom = app_apellidom;
    }

    public String getApp_mail() {
        return app_mail;
    }

    public void setApp_mail(String app_mail) {
        this.app_mail = app_mail;
    }

    public String getApp_direccion() {
        return app_direccion;
    }

    public void setApp_direccion(String app_direccion) {
        this.app_direccion = app_direccion;
    }

    public String getApp_imagen() {
        return app_imagen;
    }

    public void setApp_imagen(String app_imagen) {
        this.app_imagen = app_imagen;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public String getApp_word() {
        return app_word;
    }

    public void setApp_word(String app_word) {
        this.app_word = app_word;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public configuracionPersonalDTO getPersonalization() {
        return personalization;
    }

    public void setPersonalization(configuracionPersonalDTO personalization) {
        this.personalization = personalization;
    }

    public String getAcces_alert() {
        return acces_alert;
    }

    public void setAcces_alert(String acces_alert) {
        this.acces_alert = acces_alert;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAutho() {
        return autho;
    }

    public void setAutho(int autho) {
        this.autho = autho;
    }

    public usuarioDTO(int id_User, String app_username, String app_password, String app_nombre, String app_apellidop, String app_apellidom, String app_mail, String app_direccion, String app_imagen, String app_token, String app_word, int id_group, configuracionPersonalDTO personalization, String acces_alert, String token, int autho) {
        super();
        this.id_User = id_User;
        this.app_username = app_username;
        this.app_password = app_password;
        this.app_nombre = app_nombre;
        this.app_apellidop = app_apellidop;
        this.app_apellidom = app_apellidom;
        this.app_mail = app_mail;
        this.app_direccion = app_direccion;
        this.app_imagen = app_imagen;
        this.app_token = app_token;
        this.app_word = app_word;
        this.id_group = id_group;
        this.personalization = personalization;
        this.acces_alert = acces_alert;
        this.token = token;
        this.autho = autho;
    }

    public usuarioDTO(){}
}
