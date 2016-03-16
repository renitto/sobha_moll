package com.example.renitto.scmapp.DAL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.renitto.scmapp.Application;
import com.example.renitto.scmapp.Model.ModelHomeContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;

//import com.singnet.peppercastle.Model.MODELReview;

/**
 * Created by SAYONI on 08-12-2015.
 */


public class NetworkManager {

    String userid;


    public static final int GET_HOME_CONTENTS = 1;


    //  Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeAdapter()).create();







    public interface onServerDataRequestListener {
        public void showData(Object data, int whatToShow);

        public void onErrorResponse(String error);
    }
    public static void SendDataToServer(final onServerDataRequestListener listener, final int whatToSend, Context context, final Object params) {
        JsonObjectRequest request=null;

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        final Gson gson = new GsonHelper().getGson();





        try {
            String object=  new JSONObject(gson.toJson(params)).toString();
            request = new JsonObjectRequest(Request.Method.POST, getUrl(whatToSend,null), new JSONObject(gson.toJson(params)), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
//                    switch (whatToSend) {
//                        case SAVE_RESERVATION:
//                        listener.showData(gson.fromJson(response.toString(), ModelSuccess.class), SAVE_RESERVATION);
//                            break;
//                        case SAVE_USER:
//                            listener.showData(gson.fromJson(response.toString(), ModelSuccess.class), SAVE_USER);
//                            break;
//                    }

                }

            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    // Error handling
                    System.out.println("Something went wrong!");
                    error.printStackTrace();
                    listener.onErrorResponse(error.getLocalizedMessage());

                    pDialog.hide();

                }
            }

            );
            pDialog.hide();
        }
        catch (Exception ex){}




      Application.getInstance().addToRequestQueue(request);
    }
    public static void GetDataFromServer(final onServerDataRequestListener listener, final int whatToFetch, Context context, String[] params) {

        final  ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();



        final Gson gson = new GsonHelper().getGson();
//       final Gson gson = new GsonBuilder()
//                .setDateFormat("dd-MM-yyyy")
//                .create();
//               .registerTypeAdapter(Date.class, new NetDateTimeAdapter())
//                .setDateFormat("dd-MM-yyyy")
//                .create();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl(whatToFetch,params),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        switch (whatToFetch) {

                            case GET_HOME_CONTENTS:
                                listener.showData(gson.fromJson(response, ModelHomeContent.class), GET_HOME_CONTENTS);
                                break;




                        }


                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
                listener.onErrorResponse(error.getLocalizedMessage());

                pDialog.hide();

            }
        });
        Application.getInstance().addToRequestQueue(stringRequest);


    }

    public static void SendToServer(String url, final Activity activity, final int whatToUpdate) {

    }

    private static String getUrl(int whatToDo, String[] params) {


       // String base_url="http://pepperapp.singnetsolutions.com.sg/service1.svc/";

        String base_url = "http://sobhacitymall.sweans.org/api/";

        String userid;
        switch (whatToDo) {


            case GET_HOME_CONTENTS:

                return base_url + "getdetails.php?tab=home&hash=fv3wqt7g1lpu4verta2q";

            default:
                return null;
        }
    }

    public static class GsonHelper {
        public Gson getGson() {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new DotNetDateDeserializer());
            builder.registerTypeAdapter(Date.class, new DotNetDateSerializer());
            return builder.create();
        }

        public class DotNetDateDeserializer implements JsonDeserializer<Date> {
            @Override
            public Date deserialize(JsonElement json, Type typfOfT, JsonDeserializationContext context) {
                try {
                    String dateStr = json.getAsString();
                    if (dateStr != null) dateStr = dateStr.replace("/Date(", "");
                    if (dateStr != null) dateStr = dateStr.replace("+0530)/", "");
                    if (dateStr != null) dateStr = dateStr.replace(")/", "");
                    long time = Long.parseLong(dateStr);
                    return new Date(time);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }

            }
        }

        public class DotNetDateSerializer implements JsonSerializer<Date> {
            @Override
            public JsonElement serialize(Date date, Type typfOfT, JsonSerializationContext context) {
                if (date == null)
                    return null;

                String dateStr = "/Date(" + date.getTime() + ")/";
                return new JsonPrimitive(dateStr);
            }
        }

    }

    public static String image_base_url="http://192.168.0.3/pepperjsonservice/";
}
