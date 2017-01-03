package tarjetas.dwh.com.tarjetas.DTO;

import com.google.gson.annotations.SerializedName;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;

/**
 * Created by ricar on 22/12/2016.
 */

public class TarjetasDTO extends mensaje {

    @SerializedName("id_c")
    private int id_c;

    @SerializedName("id_user")
    private int id_user;

    @SerializedName("id_card")
    private int id_card;

    @SerializedName("tarjeta")
    private String tarjeta;

    @SerializedName("imagen")
    private String imagen;

    @SerializedName("Saldo")
    private double Saldo;

    @SerializedName("Lcredito")
    private double Lcredito;

    @SerializedName("Sdisponible")
    private double Sdisponible;


    @SerializedName("puntosSaldoInicial")
    private int puntosSaldoInicial;

    @SerializedName("pGenerados")
    private int pGenerados;

    @SerializedName("pVencidos")
    private int pVencidos;

    @SerializedName("saldoActual")
    private int saldoActual;

    public TarjetasDTO(String descripcion, String errorCode, String status, int id_c, int id_user, int id_card, String tarjeta, String imagen, double saldo, double lcredito, double sdisponible, int puntosSaldoInicial, int pGenerados, int pVencidos, int saldoActual) {
        super(descripcion, errorCode, status);
        this.id_c = id_c;
        this.id_user = id_user;
        this.id_card = id_card;
        this.tarjeta = tarjeta;
        this.imagen = imagen;
        Saldo = saldo;
        Lcredito = lcredito;
        Sdisponible = sdisponible;
        this.puntosSaldoInicial = puntosSaldoInicial;
        this.pGenerados = pGenerados;
        this.pVencidos = pVencidos;
        this.saldoActual = saldoActual;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double saldo) {
        Saldo = saldo;
    }

    public double getLcredito() {
        return Lcredito;
    }

    public void setLcredito(double lcredito) {
        Lcredito = lcredito;
    }

    public double getSdisponible() {
        return Sdisponible;
    }

    public void setSdisponible(double sdisponible) {
        Sdisponible = sdisponible;
    }

    public int getPuntosSaldoInicial() {
        return puntosSaldoInicial;
    }

    public void setPuntosSaldoInicial(int puntosSaldoInicial) {
        this.puntosSaldoInicial = puntosSaldoInicial;
    }

    public int getpGenerados() {
        return pGenerados;
    }

    public void setpGenerados(int pGenerados) {
        this.pGenerados = pGenerados;
    }

    public int getpVencidos() {
        return pVencidos;
    }

    public void setpVencidos(int pVencidos) {
        this.pVencidos = pVencidos;
    }

    public int getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(int saldoActual) {
        this.saldoActual = saldoActual;
    }
}
