package com.breezemaxweb.catchitwidgetapp;

/**
 * Created by user on 2017-09-15.
 * Install for Widget.  This is only used for the LISTVIEW which is currently disabled, until a
 * future update.
 * REQUIRES WidgetService, NextStep.java and Prospect.java
 */

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MeetingsViewsFactory implements
        RemoteViewsService.RemoteViewsFactory {

    private int meetingCount = 0;
    private String[] company, meettime, meetsubject;
    String[] items;
    private Context ctxt=null;
    private int appWidgetId;
    private String[] Generator(Context ctxt){

        //GET JSON

        // Getting Current Date to String
        Calendar c = Calendar.getInstance();
        //System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());


        SimpleDateFormat dtime = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dtime.format(c.getTime());

        //**GET DATA TO CHECK -- FROM Local JSON File meetings
        // Retrieving Data From Json File
        //TODO: Change from JSON file to database.  Make sure database is pulling CURRENT NextStep
        final ArrayList<NextStep> nextStepsList = NextStep.getNextStepsFromFile(ctxt);
        final ArrayList<Prospect> prospectsList = Prospect.getProspectsFromFile(ctxt);

        company = new String[nextStepsList.size()];
        meettime = new String[nextStepsList.size()];
        meetsubject = new String[nextStepsList.size()];

        for(int i = 0; i < nextStepsList.size(); i++) {

            NextStep nextStep = nextStepsList.get(i);
            String meetingType = nextStep.meetingtype;
           // Toast.makeText(ctxt, meetingType, Toast.LENGTH_LONG).show();
            //Check if in person or call meeting type.
            if (meetingType.matches("person")) {

                    String chkDate = nextStep.whendate;
                    //Toast.makeText(ctxt, chkDate, Toast.LENGTH_LONG).show();
                    if (chkDate.matches(formattedDate)) {

                        String chkTime = nextStep.whentime;


                        try {
                            //Current Time
                            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(formattedTime);
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTime(time1);
                            //Log.d("myTag", time1.toString());

                            //Time 10 hours From Current Time
                            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(formattedTime);
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.setTime(time2);
                            calendar2.add(Calendar.HOUR, 10);
                            //calendar2.add(Calendar.DATE, 1);


                            //Time To Be Checked, from file

                            Date d = new SimpleDateFormat("HH:mm:ss").parse(chkTime);
                            Calendar calendar3 = Calendar.getInstance();
                            calendar3.setTime(d);
                            //calendar3.add(Calendar.DATE, 1);


                            Date x = calendar3.getTime();
                            // Log.d("myTag2", (calendar2.getTime().toString()));
                            //Final Check
                            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {

                                for (int y = 0; y < prospectsList.size(); y++){
                                    Prospect prospect = prospectsList.get(y);

                                    if(nextStep.companyid.matches(prospect.id)){
                                        company[meetingCount] = prospect.companyname;
                                    }
                                    meettime[meetingCount] = nextStep.whentime;
                                    meetsubject[meetingCount] = nextStep.subject;
                                }
                                meetingCount = meetingCount + 1;

                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }


            }
        }


        String[] item = new String[meetingCount];
        if (meetingCount != 0){
            //****END GET JSON
            //Concat Meeting Info into one string
            for (int pros = 0; pros < meetingCount; pros++){

                item[pros] = company[pros] + ": \n" + meetsubject[pros] + " at \n" + meettime[pros];

            }
        } else {
            item[0] = "No Meetings At This Time";
        }
        //End Concat

        return item;
    }

    public MeetingsViewsFactory(Context ctxt, Intent intent) {
        this.ctxt=ctxt;
        items = Generator(ctxt);
        appWidgetId=
                intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        // no-op
    }

    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
         return(items.length);
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews row=
                new RemoteViews(ctxt.getPackageName(), R.layout.row);

        row.setTextViewText(android.R.id.text1, items[position]);

        Intent i=new Intent();
        Bundle extras=new Bundle();

        extras.putString(WidgetProvider.EXTRA_WORD, items[position]);
        extras.putInt(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        i.putExtras(extras);
        row.setOnClickFillInIntent(android.R.id.text1, i);


        return(row);
    }

    @Override
    public RemoteViews getLoadingView() {
        return(null);
    }

    @Override
    public int getViewTypeCount() {
        return(1);
    }

    @Override
    public long getItemId(int position) {
        return(position);
    }

    @Override
    public boolean hasStableIds() {
        return(true);
    }

    @Override
    public void onDataSetChanged() {
        // no-op
    }

}
