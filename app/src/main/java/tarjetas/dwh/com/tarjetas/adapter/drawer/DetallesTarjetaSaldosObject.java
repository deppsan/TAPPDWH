package tarjetas.dwh.com.tarjetas.adapter.drawer;

/**
 * Created by ricar on 28/12/2016.
 */

public class DetallesTarjetaSaldosObject {
    private String atributo;
    private String valor;

    public DetallesTarjetaSaldosObject(String atributo, String valor) {
        this.atributo = atributo;
        this.valor = valor;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
