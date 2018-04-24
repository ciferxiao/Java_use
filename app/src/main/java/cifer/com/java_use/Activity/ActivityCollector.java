package cifer.com.java_use.Activity;

import android.app.Activity;

import java.util.ArrayList;

/**
* created at 2018/4/16 10:13
**/


public class ActivityCollector {
    private static ArrayList<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void RemoveActivity(Activity activity){
        activities.remove(activity);
    }

    public void finishAll(){
        for(Activity activity : activities){
            activity.finish();
        }

    }
}
