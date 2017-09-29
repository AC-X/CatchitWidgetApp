package com.breezemaxweb.catchitwidgetapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by user on 2017-09-27.
 * // Temp Activity View Holder
 * This shows the detail view of the listview
 */


public class Meetings extends Activity {


    TextView comp;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingdetail);

        String word=getIntent().getStringExtra(WidgetProvider.EXTRA_WORD);

        if (word == null) {
            word="An Error Has Occured";
        }


        comp = (TextView) findViewById(R.id.company);


        comp.setText(word);




    }


}
