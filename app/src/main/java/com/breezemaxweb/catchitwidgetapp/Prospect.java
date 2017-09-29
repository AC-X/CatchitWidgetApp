package com.breezemaxweb.catchitwidgetapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by user on 2017-08-29.
 * Retrieves data from prospect.json
 */

public class Prospect {
    public String id;
    public String companyname;
    public String image;
    public String createdate;
    public String salesstage;

    public static ArrayList<Prospect> getProspectsFromFile(Context ctxt) {
        final ArrayList<Prospect> prospectList = new ArrayList<>();


        try {
            //Load data

            JSONObject json = new JSONObject(loadJsonFromAsset(ctxt));
            JSONArray prospects = json.getJSONArray("prospect");

            // Get Prospect Objects From Data
            for (int i = 0; i < prospects.length(); i++) {
                Prospect prospect = new Prospect();

                prospect.id = prospects.getJSONObject(i).getString("id");
                prospect.companyname = prospects.getJSONObject(i).getString("company");
                prospect.image = prospects.getJSONObject(i).getString("image");
                prospect.createdate = prospects.getJSONObject(i).getString("createdate");
                prospect.salesstage = prospects.getJSONObject(i).getString("salestage");

                prospectList.add(prospect);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return  prospectList;
    }

    private static String loadJsonFromAsset(Context context) {
        String json1 = null;

        try {
            InputStream is = context.getResources().openRawResource(R.raw.prospect);
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
