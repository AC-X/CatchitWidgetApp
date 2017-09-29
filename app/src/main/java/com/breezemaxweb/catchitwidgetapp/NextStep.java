package com.breezemaxweb.catchitwidgetapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by user on 2017-08-29.
 */

public class NextStep {
    public String id;
    public String companyid;
    public String subject;
    public String createdate;
    public String meetingtype;
    public String whendate;
    public String whentime;
    public String notifyme;

    public static ArrayList<NextStep> getNextStepsFromFile(Context ctxt) {
        final ArrayList<NextStep> nextStepList = new ArrayList<>();


        try {
            //Load data

            JSONObject json = new JSONObject(loadJsonFromAsset(ctxt));
            JSONArray nextsteps = json.getJSONArray("nextstep");

            // Get Prospect Objects From Data
            for (int i = 0; i < nextsteps.length(); i++) {
                NextStep nextStep = new NextStep();

                nextStep.id = nextsteps.getJSONObject(i).getString("id");
                nextStep.companyid = nextsteps.getJSONObject(i).getString("companyid");
                nextStep.subject = nextsteps.getJSONObject(i).getString("subject");
                nextStep.createdate = nextsteps.getJSONObject(i).getString("createdate");
                nextStep.meetingtype = nextsteps.getJSONObject(i).getString("meetingtype");
                nextStep.whendate = nextsteps.getJSONObject(i).getString("whendate");
                nextStep.whentime = nextsteps.getJSONObject(i).getString("whentime");
                nextStep.notifyme = nextsteps.getJSONObject(i).getString("notifyme");

                nextStepList.add(nextStep);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Sort by date
        Collections.sort(nextStepList, new Comparator<NextStep>() {
            public int compare(NextStep o1, NextStep o2) {
                return o1.whentime.compareTo(o2.whentime);
            }
        });
        //Toast.makeText(ctxt, "JSON GO", Toast.LENGTH_LONG).show();
        return  nextStepList;
    }

    private static String loadJsonFromAsset(Context context) {
        String json1 = null;

        try {
            InputStream is = context.getResources().openRawResource(R.raw.nextstep);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json1 = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json1;
    }

}
