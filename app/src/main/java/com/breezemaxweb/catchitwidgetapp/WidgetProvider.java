package com.breezemaxweb.catchitwidgetapp;

/**
 * Created by user on 2017-09-15. Brant
 * This is required for the widget to work.  Certain areas are commented out for a future update.
 * The ListView works via JSON files.  Not updating when the date changes.
 */

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

  public static String EXTRA_WORD=
          "com.breezemaxweb.catchitwidgetapp.lorem.WORD";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        final int N = appWidgetIds.length;


        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];




           // Create an Intent to launch ExampleActivity
            // A) CHANGE THE CLASS TO THE APPROPRIATE ACTIVITY YOU WISH TO NAVIGATE TO(R.id.my_events)
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            // B) CHANGE THE CLASS TO THE APPROPRIATE ACTIVITY YOU WISH TO NAVIGATE TO (R.id.add_prospects)
            Intent intent2 = new Intent(context, SecondActivity.class);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 1, intent2, 0);
            // C) CHANGE THE CLASS TO THE APPROPRIATE ACTIVITY YOU WISH TO NAVIGATE TO (R.id.appointments)
            Intent intent3 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 2, intent3, 0);
            // D) CHANGE THE CLASS TO THE APPROPRIATE ACTIVITY YOU WISH TO NAVIGATE TO (R.id.view_prospects)
            Intent intent4 = new Intent(context, SecondActivity.class);
            PendingIntent pendingIntent4 = PendingIntent.getActivity(context, 3, intent4, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            // A)
            views.setOnClickPendingIntent(R.id.my_events, pendingIntent);
            // B)
            views.setOnClickPendingIntent(R.id.add_prospect, pendingIntent2);
            // C)
            views.setOnClickPendingIntent(R.id.appointments, pendingIntent3);
            // D)
            views.setOnClickPendingIntent(R.id.view_prospects, pendingIntent4);
            /*
            //LIST ADD ON: (Disabled until future update)
            Intent svcIntent=new Intent(context, WidgetService.class);

            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

            views.setRemoteAdapter(R.id.words, svcIntent);

            Intent clickIntent=new Intent(context, Meetings.class);
            PendingIntent clickPI = PendingIntent.getActivity(context, 4,
                    clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            views.setPendingIntentTemplate(R.id.words, clickPI);
            //List Add ON END*/

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

}
