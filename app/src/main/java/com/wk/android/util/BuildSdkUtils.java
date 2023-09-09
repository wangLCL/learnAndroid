package com.wk.android.util;

import android.os.Build;

public class BuildSdkUtils {
    public static boolean sdk21(){
        if (Build.VERSION.SDK_INT >= 21){
            return true;
        }
        return false;
    }
}
