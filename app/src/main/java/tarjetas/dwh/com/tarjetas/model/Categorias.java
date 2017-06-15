package tarjetas.dwh.com.tarjetas.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ricar on 25/05/2017.
 */

public class Categorias extends RealmObject {
    @PrimaryKey
    private int id;

    private String nombre;
    private double capacidad;
    private int imagen;
    private int imagenDrawer;
    private int recurrence;

    public Categorias() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getImagenDrawer() {
        return imagenDrawer;
    }

    public void setImagenDrawer(int imagenDrawer) {
        this.imagenDrawer = imagenDrawer;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }
}

