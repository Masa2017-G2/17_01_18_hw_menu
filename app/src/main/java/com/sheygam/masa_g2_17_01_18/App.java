package com.sheygam.masa_g2_17_01_18;

import android.app.Application;
import android.content.Context;

/**
 * Created by gregorysheygam on 17/01/2018.
 */

public class App extends Application {
    private static Context context;
    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        if(context == null){
            context = this;
        }
        super.onCreate();
    }
}
