package com.example.renitto.scmapp.DAL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.renitto.scmapp.Application;
import com.example.renitto.scmapp.Model.ModelBrandDetails;
import com.example.renitto.scmapp.Model.ModelBrands;
import com.example.renitto.scmapp.Model.ModelEntertainmentBrand;
import com.example.renitto.scmapp.Model.ModelFashion;
import com.example.renitto.scmapp.Model.ModelGeneralQuery;
import com.example.renitto.scmapp.Model.ModelHomeContent;
import com.example.renitto.scmapp.Model.ModelHomeEvent;
import com.example.renitto.scmapp.Model.ModelMovieDetails;
import com.example.renitto.scmapp.Model.ModelOffer;
import com.example.renitto.scmapp.Model.ModelSubCategories;
import com.example.renitto.scmapp.Presenter.FragmentHome;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
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
    public static final int GET_ENTERTAINMENT_BRAND_CONTENTS = 2;
    public static final int GET_SHOPPING_FASHION_CONTENTS = 3;
    public static final int GET_BRAND_CONTENTS = 4;
    public static final int GET_BRAND_DETAIL_CONTENTS = 5;
    public static final int GET_MOVIE_DETAIL_CONTENTS = 6 ;
    public static final int GET_OFFER_DETAIL_CONTENTS = 7 ;
    public static final int GET_SUBCATEGORY_CONTENTS = 8 ;
    public static final int GET_GENERALQUERY_CONTENTS = 9 ;
    public static final int GET_HOME_EVENTS =10 ;

    public static final int SAVE_EMAIL =11 ;


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
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);

        final Gson gson = new GsonHelper().getGson();





        try {
            String object=  new JSONObject(gson.toJson(params)).toString();
            request = new JsonObjectRequest(Request.Method.POST, getUrl(whatToSend,null), new JSONObject(gson.toJson(params)), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    switch (whatToSend) {
                        case SAVE_EMAIL:
                        listener.showData(gson.fromJson(response.toString(), String.class), SAVE_EMAIL);
                            break;

                    }

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


    public static void GetDataFromServer(final onServerDataRequestListener listener, final int whatToFetch, final Context context, String[] params) {

//if(new ConnectionDetector(context).isConnectingToInternet()) {

    final ProgressDialog pDialog = new ProgressDialog(context);
        if(whatToFetch!=GET_BRAND_DETAIL_CONTENTS) {
            pDialog.setMessage("Loading...");
            pDialog.show();
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.setCancelable(false);
        }


    final Gson gson = new GsonHelper().getGson();
//       final Gson gson = new GsonBuilder()
//                .setDateFormat("dd-MM-yyyy")
//                .create();
//               .registerTypeAdapter(Date.class, new NetDateTimeAdapter())
//                .setDateFormat("dd-MM-yyyy")
//                .create();
    StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl(whatToFetch, params),
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    switch (whatToFetch) {

                        case GET_HOME_CONTENTS:
                           ModelHomeContent myHomeContent=gson.fromJson(response, ModelHomeContent.class);
                            if (myHomeContent != null) {
                                new DbManager().insertToHomeContent(context, myHomeContent);
                                ((FragmentHome) listener).setSliders(myHomeContent);
                            }
                            //   listener.showData(gson.fromJson(response, ModelHomeContent.class), GET_HOME_CONTENTS);
                            break;

                        case GET_ENTERTAINMENT_BRAND_CONTENTS:


                            listener.showData(gson.fromJson(response, ModelEntertainmentBrand.class), GET_ENTERTAINMENT_BRAND_CONTENTS);
                            break;

                        case GET_SHOPPING_FASHION_CONTENTS:


                            listener.showData(gson.fromJson(response, ModelFashion.class), GET_SHOPPING_FASHION_CONTENTS);
                            break;

                        case GET_BRAND_CONTENTS:


                            listener.showData(gson.fromJson(response, ModelBrands.class), GET_BRAND_CONTENTS);
                            break;

                        case GET_BRAND_DETAIL_CONTENTS:


                            listener.showData(gson.fromJson(response, ModelBrandDetails.class), GET_BRAND_DETAIL_CONTENTS);
                            break;
                        case GET_MOVIE_DETAIL_CONTENTS:


                            listener.showData(gson.fromJson(response, ModelMovieDetails.class), GET_MOVIE_DETAIL_CONTENTS);
                            break;

                        case GET_OFFER_DETAIL_CONTENTS:


                            listener.showData(gson.fromJson(response, ModelOffer.class), GET_OFFER_DETAIL_CONTENTS);

                            break;

                        case GET_SUBCATEGORY_CONTENTS:



                            listener.showData(gson.fromJson(response, ModelSubCategories.class), GET_SUBCATEGORY_CONTENTS);

                            break;

                        case GET_GENERALQUERY_CONTENTS:


                            listener.showData(gson.fromJson(response, ModelGeneralQuery.class), GET_GENERALQUERY_CONTENTS);

                            break;

                        case GET_HOME_EVENTS:


                            listener.showData(gson.fromJson(response, ModelHomeEvent.class), GET_HOME_EVENTS);

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
    stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    Application.getInstance().addToRequestQueue(stringRequest);


//    else
//    Toast.makeText(context,"Please check your internet connection and try again !",Toast.LENGTH_LONG).show();

    }

    public static void SendToServer(String url, final Activity activity, final int whatToUpdate) {

    }

    private static String getUrl(int whatToDo, String[] params) {


       // String base_url="http://pepperapp.singnetsolutions.com.sg/service1.svc/";

//        String base_url = "http://sobhacitymall.sweans.org/api/";
        String base_url = "http://www.sobhacitymall.com/api/";
        String hash_key = "fv3wqt7g1lpu4verta2q";

        String userid;
        switch (whatToDo) {


            case GET_HOME_CONTENTS:

                return base_url + "getdetails.php?tab=home&hash=" + hash_key;

            case GET_ENTERTAINMENT_BRAND_CONTENTS:

                return base_url + "get-brand-details.php?brand-id=inox&hash=" + hash_key;

            case GET_SHOPPING_FASHION_CONTENTS:

                String cat_id=params[0];
                return base_url + "get-category-details.php?category-id="+ cat_id +"&hash=" + hash_key;

            case GET_BRAND_CONTENTS:

                String brand_cat_id=params[0];

                return base_url + "get-category-brands.php?category-id="+ brand_cat_id +"&hash=" + hash_key;

            case GET_BRAND_DETAIL_CONTENTS:
                String branch_id=  params[0];
                return base_url + "get-brand-details.php?brand-id=" + branch_id +"&hash=" + hash_key;

            case GET_MOVIE_DETAIL_CONTENTS:
                String movie_cat_id=  params[0];
                return base_url + "get-movies.php?category=" + movie_cat_id +"&hash=" + hash_key;

            case GET_OFFER_DETAIL_CONTENTS:

                return base_url + "get-offers-page-details.php?&hash=" + hash_key;

            case GET_SUBCATEGORY_CONTENTS:

                return base_url + "general-queries.php?action=get-category-list&hash=" + hash_key;

            case GET_GENERALQUERY_CONTENTS:

                return base_url + "general-queries.php?action=get-page-details&page-id=about&hash=" + hash_key;

            case GET_HOME_EVENTS:
                String home_event_id = params[0];
                return base_url + "general-queries.php?action=get-page-details&page-id="+ home_event_id +"&hash=" + hash_key;

            case SAVE_EMAIL:
//                String home_event_id = params[0];
                return base_url ;

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


}
