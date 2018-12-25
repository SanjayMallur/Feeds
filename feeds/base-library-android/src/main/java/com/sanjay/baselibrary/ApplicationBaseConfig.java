package com.sanjay.baselibrary;

import android.app.Application;

/**
 * Created by Sanjay
 * Base application class
 */

public abstract class ApplicationBaseConfig extends Application {

//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

//        if (BuildConfig.DEBUG) {
//            if (LeakCanary.isInAnalyzerProcess(this)) {
//                // This process is dedicated to LeakCanary for heap analysis.
//                // You should not init your app in this process.
//                return;
//            }
//            refWatcher = LeakCanary.install(this);
//        }
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        ApplicationBaseConfig application = (ApplicationBaseConfig) context.getApplicationContext();
//        return application.refWatcher;
//    }


}
