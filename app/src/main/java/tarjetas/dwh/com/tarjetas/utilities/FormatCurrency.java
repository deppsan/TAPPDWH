package tarjetas.dwh.com.tarjetas.utilities;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by ricar on 28/06/2017.
 */

public class FormatCurrency {

    private final static String CurrentLocal = "EN";

    public static String getFormatCurrency(Double amount){
        String quantity = "$ ";

        Locale local = new Locale(CurrentLocal);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(local);

        quantity += numberFormat.format(amount);



        return quantity;
    }
}
