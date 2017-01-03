package tarjetas.dwh.com.tarjetas.DTO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ricar on 06/12/2016.
 */

public class errorDTO {

    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public errorDTO(String error) {
        this.error = error;
    }
}
