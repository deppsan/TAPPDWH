package tarjetas.dwh.com.tarjetas.DTO;

/**
 * Created by ricar on 14/06/2017.
 */

public class Categoria {    private int id;

    private String nombre;
    private double capacidad;
    private int imagen;
    private int imagenDrawer;
    private int recurrence;

    public Categoria() {
    }

    public Categoria(int id, String nombre, double capacidad, int imagen, int imagenDrawer, int recurrence) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.imagen = imagen;
        this.imagenDrawer = imagenDrawer;
        this.recurrence = recurrence;
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
