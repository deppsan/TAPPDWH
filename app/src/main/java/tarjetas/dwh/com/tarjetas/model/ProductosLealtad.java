package tarjetas.dwh.com.tarjetas.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ricar on 01/08/2017.
 */

public class ProductosLealtad extends RealmObject{
    private int id;
    private String image, descripcion;
    private boolean favorito,sugerido;
    private int tipoCategoria;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public ProductosLealtad(int id, String image, String descripcion, boolean favorito, boolean sugerido, int tipoCategoria, double amount) {
        this.id = id;
        this.image = image;
        this.descripcion = descripcion;
        this.favorito = favorito;
        this.sugerido = sugerido;
        this.tipoCategoria = tipoCategoria;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(int tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public boolean isSugerido() {
        return sugerido;
    }

    public void setSugerido(boolean sugerido) {
        this.sugerido = sugerido;
    }

    public ProductosLealtad() {
    }
}
