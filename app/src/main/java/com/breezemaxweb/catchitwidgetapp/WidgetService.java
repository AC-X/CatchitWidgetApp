package com.breezemaxweb.catchitwidgetapp;

/**
 * Created by user on 2017-09-15.
 * Install for Widget.  This is only used for the LISTVIEW which is currently disabled, until a
 * future update.
 * REQUIRES MeetingsViewsFactory, NextStep.java and Prospect.java
 */

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return(new MeetingsViewsFactory(this.getApplicationContext(),
                intent));
    }
}
