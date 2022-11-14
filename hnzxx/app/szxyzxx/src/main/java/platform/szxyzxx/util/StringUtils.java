package platform.szxyzxx.util;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isNumeric(String string){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(string).matches();
    }


    public static boolean isNotNumeric(String string){
        return !isNumeric(string);
    }

}
