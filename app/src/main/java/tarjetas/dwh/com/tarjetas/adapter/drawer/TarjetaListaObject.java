package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 22/12/2016.
 */

public class TarjetaListaObject {
    private String idImagen;
    private String tarjetaNombre;
    private int id_c;

    public TarjetaListaObject(String idImagen, String tarjetaNombre, int id_c) {
        this.idImagen = idImagen;
        this.tarjetaNombre = tarjetaNombre;
        this.id_c = id_c;
    }

    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

    public String getTarjetaNombre() {
        return tarjetaNombre;
    }

    public void setTarjetaNombre(String tarjetaNombre) {
        this.tarjetaNombre = tarjetaNombre;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }
}
