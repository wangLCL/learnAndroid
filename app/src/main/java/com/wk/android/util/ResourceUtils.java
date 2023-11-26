package com.wk.android.util;

import android.app.Application;
import android.content.res.Resources;

public class ResourceUtils {
    private static Application sApplication;
    public static void init(Application application){
        sApplication = application;
    }

    /**
     * id --> string
     * @param id
     * @return
     */
    private static CharSequence stringIdToCharSequence(int id) {
        checkInitStatus();
        try {
            // 如果这是一个资源 id
            return sApplication.getResources().getText(id);
        } catch (Resources.NotFoundException ignored) {
            // 如果这是一个 int 整数
            return String.valueOf(id);
        }
    }

    private static void checkInitStatus() {
        if (sApplication == null) {
            throw new IllegalStateException("Toaster has not been initialized");
        }
    }

}
