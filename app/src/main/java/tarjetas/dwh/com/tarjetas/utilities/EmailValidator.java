package tarjetas.dwh.com.tarjetas.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ricar on 03/01/2017.
 */

public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;
    private static EmailValidator instance;

    private static final String EMAIL_PATTERN =  "(?!.*\\.\\.)(\"[!-~ ]+\"|[0-9A-Z!#-'*-\\/=?^-~]+)@((?![-])[A-Za-z0-9-]*[A-Za-z-]+[A-Za-z0-9-]*(?![-])\\.*)+\\.[a-z]+";

    private EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
    public static synchronized EmailValidator getInstance(){
        if(instance == null){
            instance = new EmailValidator();
        }
        return instance;
    }


    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();


    }
}
