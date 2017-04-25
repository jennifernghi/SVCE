package com.example.android.svce.utils;

import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jennifernghinguyen on 4/24/17.
 */

public final class Utils {

    public static String trimEmailPart(String email){
        return email.substring(0, email.lastIndexOf('@'));
    }

    public static String getTodayDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(new Date());

    }
}
