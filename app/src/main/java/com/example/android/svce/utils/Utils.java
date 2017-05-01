package com.example.android.svce.utils;

import java.lang.String;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jennifernghinguyen on 4/24/17.
 */

public final class Utils {

    public static String trimEmailPart(String email){
        if(email.indexOf('@')!=-1) {
            return email.substring(0, email.lastIndexOf('@'));
        }
        return email;
    }

    public static String getTodayDate(){
        android.text.format.DateFormat dateFormat = new android.text.format.DateFormat();
        return dateFormat.format("yyyy-MM-dd", new Date()).toString();

    }
}
