package cifer.com.java_use.Util;

import android.app.Application;
import android.content.Context;




/**
 * Created by cifer
 * on 2018/5/22 14:51.
 * 内存泄漏检验
 */

public class LeakApplication extends Application {
/*    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher= setupLeakCanary();
    }
    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        LeakApplication leakApplication = (LeakApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }*/


}
