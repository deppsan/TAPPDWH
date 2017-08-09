package tarjetas.dwh.com.tarjetas.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.errorMessage;
import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;
import tarjetas.dwh.com.tarjetas.DTO.NotificacionesDTO;
import tarjetas.dwh.com.tarjetas.DTO.PromocionesDTO;
import tarjetas.dwh.com.tarjetas.DTO.TarjetasDTO;
import tarjetas.dwh.com.tarjetas.DTO.TransaccionesDTO;
import tarjetas.dwh.com.tarjetas.DTO.authDTO;
import tarjetas.dwh.com.tarjetas.DTO.menuItemDTO;
import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.model.Transacciones;
import tarjetas.dwh.com.tarjetas.model.Usuario;
import tarjetas.dwh.com.tarjetas.network.conection.conexionHTTP;
import tarjetas.dwh.com.tarjetas.utilities.RealmAdministrator;

/**
 * Created by ricar on 13/12/2016.
 */

public class TarjetasApiClient {

    private static final String      BASE_URL   = "http://tarjetas-ws.esy.es/";
    private static final GsonBuilder builder    = new GsonBuilder();
    public static final Gson        gson       = builder.create();

    private static TarjetasApiClient instance;

    public static synchronized TarjetasApiClient getInstance(){
        if(instance == null){
            instance = new TarjetasApiClient();
        }
        return instance;
    }

    private TarjetasApiClient(){    }

    public void revisarCodigoSeguridad(String codigo, Context context, final TarjetasApiRevisarCodigoSeguridadListener listener ){
        if (codigo.equalsIgnoreCase("ASDFG")){
            listener.onCodigoCorrecto();
        }else{
            listener.onCodigoIncorrecto();
        }
    }

    public void confirmarTarjeta(String numTarjeta,Context context, final TarjetasApiRevisarTarjeta listener){

        if (numTarjeta.equals("1234123412341234")){
            listener.onTarjetaRevisadaExitosamente(1,"ASDF1234ZXCV5678QWER");
        }else{
            listener.onTarjetaFallo();
        }

            //listener.onUsuarioAgregadoExitoso(2);
            //listener.onUsuarioAgregadoExitoso(3);


        /*new conexionHTTP().getInstance().getJsonResponse(context
                , BASE_URL
                , new conexionHTTP.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(int statusCode) {
                        listener.onTarjetaFallo();
                    }
                });*/
    }

    public void getDetalleSaldosTarjetas(int idTarjeta,Context context, final TarjetasApiGetSaldosDetalle listener){
        new conexionHTTP().getInstance().getJsonResponse(context
                , BASE_URL + "f_tarjetas_data.php?id_s=" + idTarjeta
                , new conexionHTTP.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("salgod",result);
                        TarjetasDTO[] tarjetas = gson.fromJson(result,TarjetasDTO[].class);

                        TarjetasDTO tarjeta = tarjetas[0];

                        tarjeta.setPuntosSaldoInicial(9000);
                        tarjeta.setpGenerados(1296);
                        tarjeta.setpVencidos(300);
                        tarjeta.setSaldoActual(9990);

                        listener.onSaldoRecibido(tarjeta);
                        /*if (tarjeta[0].getErrorCode().equals("00")){
                        }else{
                            mensaje mensaje = gson.fromJson(result, tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje.class);
                            listener.onFallaAlRecibirSaldo(mensaje);
                        }*/
                    }
                    @Override
                    public void onError(int statusCode) {
                        mensaje error = new mensaje(errorMessage.ERROR_AL_EJECUTAR_WEB_SERVICES,"01","");
                        listener.onFallaAlRecibirSaldo(error);
                    }
                });
    }

    public void getListaMisTarjetas(int idUser,Context context,final TarjetasApiGetMisTarjetasListener listener){
        new conexionHTTP().getInstance().getJsonResponse(context,
                BASE_URL + "f_tarjetas.php?id=" + String.valueOf(idUser),
                new conexionHTTP.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        TarjetasDTO[] tarjetasArray = gson.fromJson(result,TarjetasDTO[].class);
                        listener.onTarjetasRecibidas(tarjetasArray);
                    }

                    @Override
                    public void onError(int statusCode) {
                        mensaje error = new mensaje(errorMessage.ERROR_AL_EJECUTAR_WEB_SERVICES,"01","");
                        listener.onFallaEnTarjetasRecibidas(error);
                    }
                });
    }

    public void getMenu(final int userId,Context context, final TarjetasApiGetMenuListener listener){
        new conexionHTTP().getInstance().getJsonResponse(context
                                                        , BASE_URL + "f_menu.php?user=" + userId
                                                        , new conexionHTTP.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        menuItemDTO[] menuArray = gson.fromJson(result, menuItemDTO[].class);
                        if (menuArray.length > 0){
                            listener.onMenuRecibido(menuArray);
                        }
                        else{
                            listener.onErrorAlRecibirMenu(errorMessage.NO_CONTIENE_OPCIONES_MENU);
                        }
                    }

                    @Override
                    public void onError(int statusCode) {
                        listener.onErrorAlRecibirMenu(String.valueOf(statusCode));

                    }
                });
    }

    public void validaUsuario(final String userName, Context context, final TarjetasApiUserListener listener){
        if(RealmAdministrator.getInstance(context).checkUsuario(userName)){
            new conexionHTTP().getInstance().getJsonResponse(context
                    , BASE_URL + "f_login_one.php?user=" + "demo"
                    , new conexionHTTP.VolleyCallback() {
                        authDTO auth;
                        @Override
                        public void onSuccess(String result) {
                            auth = gson.fromJson(result,authDTO.class);
                            if(auth.getErrorCode().equals("00")){
                                if(auth.getAuth() == 1){
                                    listener.onUserValidate(userName);
                                }else{
                                    listener.onUserNoValidate(auth.getDescripcion());
                                }
                            }else{
                                Log.e("Error desde Servidor",auth.getDescripcion());
                                listener.onUserNoValidate(auth.getDescripcion());
                            }
                        }
                        @Override
                        public void onError(int statusCode) {
                            Log.e("Error de Conexion","Fallo al hacer llamada a WS, Codigo de Status : " + statusCode);
                            listener.onUserNoValidate(String.valueOf(statusCode));
                        }
                    });
        }else{
            Toast.makeText(context, "Error, el usuario no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void validaPassword(final String userName, final String password, Context context, final TarjetasApiPasswordListener listener){
        Usuario usuario = RealmAdministrator.getInstance(context).getUsuario(userName);
        if (usuario.getPassword().equals(password)){
            new conexionHTTP().getInstance().getJsonResponse(context
                    , BASE_URL + "f_login_two.php?username=" + "demo" + "&pass=" + "Password1"
                    , new conexionHTTP.VolleyCallback() {
                        usuarioDTO user;
                        @Override
                        public void onSuccess(String result) {
                            user = gson.fromJson(result,usuarioDTO.class);
                            if(user.getErrorCode().equals("00")){
                                if (user.getAutho() == 1){
                                    listener.onPasswordValidate(user);
                                }else{
                                    listener.onPasswordNoValidate(user.getAcces_alert());
                                }
                            }else{
                                listener.onPasswordNoValidate(user.getDescripcion());
                            }
                        }
                        @Override
                        public void onError(int statusCode) {
                            Log.e("Error de Conexion","Fallo al hacer llamada a WS, Codigo de Status : " + statusCode);
                            listener.onPasswordNoValidate(String.valueOf(statusCode));
                        }
                    });
        }else{
            Toast.makeText(context, "La contraseña esta incorrecta", Toast.LENGTH_SHORT).show();
        }
        
    }

    /*public void prueba(final String userName,Context context,final TarjetasApiUserListener listener){
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("user",userName);
        new conexionHTTP().getInstance().putJsonResponse(context
                                                        , BASE_URL + "f_login_one.php"
                                                        , params
                                                        , new conexionHTTP.VolleyCallback() {
                    authDTO auth;
                    @Override
                    public void onSuccess(String result) {
                        auth = gson.fromJson(result,authDTO.class);
                        if(auth.getErrorCode().equals("00")){
                            if(auth.getAuth() == 1){
                                listener.onUserValidate(userName);
                            }else{
                                listener.onUserNoValidate(auth.getDescripcion());
                            }
                        }else{
                            Log.e("Error desde Servidor",auth.getDescripcion());
                            listener.onUserNoValidate(auth.getDescripcion());
                        }
                    }

                    @Override
                    public void onError(int statusCode) {
                        Log.e("Error de Conexion","Fallo al hacer llamada a WS, Codigo de Status : " + statusCode);
                        listener.onUserNoValidate(String.valueOf(statusCode));
                    }
                });
    }*/

    public void getTransaccionesTarjeta(String token,Context context, final TarjetasApiTransaccionesListener listener){
        ArrayList<TransaccionesDTO> transacciones = new ArrayList<TransaccionesDTO>();
        /*transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,1));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,2));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,3));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,4));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,1));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,2));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,3));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,4));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,1));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,2));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,3));
        transacciones.add(new TransaccionesDTO("2016-08-01 08:00:00","Computer.com","+20 Puntos",600,4));*/

        ArrayList<Transacciones> t = RealmAdministrator.getInstance(context).getAllTransacciones();

        for (Transacciones t1 : t){
            transacciones.add(new TransaccionesDTO(t1.getId(),t1.getFecha(),t1.getDetalle(),t1.getPuntos(),t1.getValor(),t1.getCategoria()));
        }

        listener.onTransaccionesRecividas(transacciones);
    }

    public void getNotificacionesPorTarjeta(String token,Context context, final TarjetasApiNotificacionesListener listener){
        ArrayList<NotificacionesDTO> notificaciones = new ArrayList<NotificacionesDTO>();
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","El Numero NIP de la tarjeta ha sido Generado",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","NIP Generado y Actualizado Correctamente",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","NIP Generado y Actualizado Correctamente",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","El Numero NIP de la tarjeta ha sido Generado",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","Su tarjeta Visa Inifite Card 8083 ha sido resportada como robada y/o extraviada su Num de folio es : 52959",0));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","Su tarjeta **** **** **** 7890 Se ha activado exitosamente",0));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","Su tarjeta Visa infinite Card 8083 ha sido reportada como robada y/o extraviada su Num de folio es : 36401",0));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","El Numero NIP de la tarjeta ha sido Generado",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","NIP Generado y Actualizado Correctamente",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","NIP Generado y Actualizado Correctamente",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","El Numero NIP de la tarjeta ha sido Generado",1));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","Su tarjeta Visa Inifite Card 8083 ha sido resportada como robada y/o extraviada su Num de folio es : 52959",0));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","Su tarjeta **** **** **** 7890 Se ha activado exitosamente",0));
        notificaciones.add(new NotificacionesDTO("2016-10-13 11:00:40","Su tarjeta Visa infinite Card 8083 ha sido reportada como robada y/o extraviada su Num de folio es : 36401",0));
        listener.onNotificacionesRecividas(notificaciones);
    }

    public void getPromocionesPorTarjeta(String token, Context context, final TarjetasApiPromocionesListener listener){
        ArrayList<PromocionesDTO> promociones = new ArrayList<PromocionesDTO>();
        promociones.add(new PromocionesDTO(1,"6 meses sin intereses. Todo el mes de Agosto.",1));
        promociones.add(new PromocionesDTO(2,"De junio a Agosto difiere a 12 meses sin intereses todos tus consumos en lineas aereas, llama al 0100 Banorte, o da click aquí.",2));
        promociones.add(new PromocionesDTO(3,"Todos los viernes obtén puntos dobles en tus consumos en restaurantes.",3));
        promociones.add(new PromocionesDTO(1,"6 meses sin intereses. Todo el mes de Agosto.",1));
        promociones.add(new PromocionesDTO(2,"De junio a Agosto difiere a 12 meses sin intereses todos tus consumos en lineas aereas, llama al 0100 Banorte, o da click aquí.",2));
        promociones.add(new PromocionesDTO(3,"Todos los viernes obtén puntos dobles en tus consumos en restaurantes.",3));
        promociones.add(new PromocionesDTO(1,"6 meses sin intereses. Todo el mes de Agosto.",1));
        promociones.add(new PromocionesDTO(2,"De junio a Agosto difiere a 12 meses sin intereses todos tus consumos en lineas aereas, llama al 0100 Banorte, o da click aquí.",2));
        promociones.add(new PromocionesDTO(3,"Todos los viernes obtén puntos dobles en tus consumos en restaurantes.",3));
        promociones.add(new PromocionesDTO(1,"6 meses sin intereses. Todo el mes de Agosto.",1));
        promociones.add(new PromocionesDTO(2,"De junio a Agosto difiere a 12 meses sin intereses todos tus consumos en lineas aereas, llama al 0100 Banorte, o da click aquí.",2));
        promociones.add(new PromocionesDTO(3,"Todos los viernes obtén puntos dobles en tus consumos en restaurantes.",3));
        promociones.add(new PromocionesDTO(1,"6 meses sin intereses. Todo el mes de Agosto.",1));
        promociones.add(new PromocionesDTO(2,"De junio a Agosto difiere a 12 meses sin intereses todos tus consumos en lineas aereas, llama al 0100 Banorte, o da click aquí.",2));
        promociones.add(new PromocionesDTO(3,"Todos los viernes obtén puntos dobles en tus consumos en restaurantes.",3));
        listener.onPromocionesRevicidas(promociones);
    }



    public interface TarjetasApiUserListener{
        void onUserValidate(String user);
        void onUserNoValidate(String mensaje);
    }
    public interface TarjetasApiPasswordListener{
        void onPasswordValidate(usuarioDTO usuario);
        void onPasswordNoValidate(String usuario);
    }
    public interface TarjetasApiGetMenuListener{
        void onMenuRecibido(menuItemDTO[] menuArray);
        void onErrorAlRecibirMenu(String error);
    }
    public interface TarjetasApiGetMisTarjetasListener{
        void onTarjetasRecibidas(TarjetasDTO[] tarjetasArray);
        void onFallaEnTarjetasRecibidas(mensaje error);
    }

    public interface TarjetasApiGetSaldosDetalle{
        void onSaldoRecibido(TarjetasDTO tarjeta);
        void onFallaAlRecibirSaldo(mensaje error);
    }
    public interface TarjetasApiRevisarTarjeta{
        void onTarjetaRevisadaExitosamente(int opcion,String token);
        void onTarjetaFallo();
    }

    public interface TarjetasApiRevisarCodigoSeguridadListener{
        void onCodigoCorrecto();
        void onCodigoIncorrecto();
    }

    public interface TarjetasApiTransaccionesListener{
        void onTransaccionesRecividas(ArrayList<TransaccionesDTO> transacciones);
        void onTransaccionesFalla();
    }

    public interface TarjetasApiNotificacionesListener{
        void onNotificacionesRecividas(ArrayList<NotificacionesDTO> notificaciones);
        void onNotificacionesFalla();
    }

    public interface TarjetasApiPromocionesListener{
        void onPromocionesRevicidas(ArrayList<PromocionesDTO> promociones);
    }
}
