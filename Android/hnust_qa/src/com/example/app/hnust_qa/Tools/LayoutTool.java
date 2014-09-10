package com.example.app.hnust_qa.Tools;

import android.app.ActionBar;
import android.app.Activity;

/**
 * Created by Administrator on 2014/8/11 0011.
 */
public class LayoutTool {

    public static void  setCustomTitle(Activity activity,int layout){
        activity.getActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        activity.getActionBar().setCustomView(layout);
    }




}
