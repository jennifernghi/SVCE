package com.example.android.svce.utils;

import java.lang.String;
/**
 * Created by jennifernghinguyen on 4/24/17.
 */

public final class StringUtils {

    public static String trimEmailPart(String email){
        return email.substring(0, email.lastIndexOf('@'));
    }
}
