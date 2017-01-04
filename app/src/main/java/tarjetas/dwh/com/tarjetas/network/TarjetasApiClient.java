package tarjetas.dwh.com.tarjetas.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tarjetas.dwh.com.tarjetas.DTO.Mensaje.errorMessage;
import tarjetas.dwh.com.tarjetas.DTO.Mensaje.mensaje;
import tarjetas.dwh.com.tarjetas.DTO.TarjetasDTO;
import tarjetas.dwh.com.tarjetas.DTO.authDTO;
import tarjetas.dwh.com.tarjetas.DTO.menuItemDTO;
import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.network.conection.conexionHTTP;

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

    private TarjetasApiClient(){


    }

    public void addNuevoUsuario(usuarioDTO usuario, Context context, final TarjetasApiAddUsuario listener){

        if (usuario == null){
            listener.onUsuarioAgregadoExitoso();
        }else{
            listener.onUsuarioAgregadoFalla();
        }

        /*new conexionHTTP().getInstance().getJsonResponse(context
                , BASE_URL
                , new conexionHTTP.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(int statusCode) {

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
        new conexionHTTP().getInstance().getJsonResponse(context
                                                        , BASE_URL + "f_login_one.php?user=" + userName
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
    }

    public void validaPassword(final String userName, final String password, Context context, final TarjetasApiPasswordListener listener){
        new conexionHTTP().getInstance().getJsonResponse(context
                                                        , BASE_URL + "f_login_two.php?username=" + userName + "&pass=" + password
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
    public interface TarjetasApiAddUsuario{
        void onUsuarioAgregadoExitoso();
        void onUsuarioAgregadoFalla();
    }
}
