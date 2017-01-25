package tarjetas.dwh.com.tarjetas.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import tarjetas.dwh.com.tarjetas.DTO.usuarioDTO;
import tarjetas.dwh.com.tarjetas.network.TarjetasApiClient;

/**
 * Created by ricar on 19/12/2016.
 */

public class UtilsSharedPreference {

    private static final String PREFS_NAME = "com.tarjetas.sharedPreferance";

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static UtilsSharedPreference instance;

    private UtilsSharedPreference(Context context){
        preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor  = preferences.edit();
    }

    public static synchronized UtilsSharedPreference getInstance(Context context){
        if (instance == null){
            instance = new UtilsSharedPreference(context);
        }
        return instance;
    }

    public static boolean checkUserSharedPreference(){
        boolean ver = false;
        if (preferences.contains("user")){
            ver = true;
        }
        return ver;
    }

    public static boolean isCelularAsigned(){
        boolean ver = false;
        if (preferences.contains("celularNumber")){
            ver = true;
        }
        return ver;    }

    public static void setCelularNumber(String celularNumber){
        editor.putString("celularNumber",celularNumber);
        editor.commit();
    }

    public static void setUserSharedPreference(usuarioDTO user){
        String userJson = TarjetasApiClient.gson.toJson(user);
        editor.putString("user",userJson);
        editor.commit();
    }

    public static usuarioDTO getUserSharedPreference(){
        String userJson  = preferences.getString("user","error");
        usuarioDTO user = TarjetasApiClient.gson.fromJson(userJson,usuarioDTO.class);

        return user;
    }

    public static void eraseUserSharedPreferences(){
        editor.remove("user");
        editor.commit();
    }

    public static void eraseAllSharedPreferences(){
        editor.clear();
        editor.commit();
    }

    public static void setEsperaCodigo(boolean settear){
        editor.putBoolean("esperaCodigo",settear);
        editor.commit();
    }

    public boolean getEsperaCodigo(){
        boolean ver = preferences.getBoolean("esperaCodigo",false);
        return ver;
    }

}
