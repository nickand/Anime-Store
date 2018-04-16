package test.android.testmerlin.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import test.android.testmerlin.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    public static Context mContext;

    public static Context getContext() {
        return App.mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CircularStd-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        mContext = getApplicationContext();
    }
}
