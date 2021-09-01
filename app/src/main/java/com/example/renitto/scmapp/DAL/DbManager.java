package com.example.renitto.scmapp.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.session.PlaybackState;

import com.example.renitto.scmapp.Model.ModelBrandDetails;
import com.example.renitto.scmapp.Model.ModelBrands;
import com.example.renitto.scmapp.Model.ModelEntertainmentBrand;
import com.example.renitto.scmapp.Model.ModelFashion;
import com.example.renitto.scmapp.Model.ModelGeneralQuery;
import com.example.renitto.scmapp.Model.ModelHomeContent;
import com.example.renitto.scmapp.Model.ModelMovieDetails;
import com.example.renitto.scmapp.Model.ModelOffer;
import com.example.renitto.scmapp.Model.ModelShoppingPlanner;
import com.example.renitto.scmapp.Model.ModelSubCategories;

import java.util.ArrayList;

/**
 * Created by Renitto on 3/3/2016.
 */
public class DbManager  {

    //Shopping Planner
    public static String Table_Shopping_planner = "shopping_planner_table";
    public static String _id = "_id";
    public static String COLUMN_shopping_planner_shop_item_name = "shop_item_name";
    public static String COLUMN_shopping_planner_shop_item_status = "shop_status";


    public void insertToShoppingPlanner(Context context,ModelShoppingPlanner shoppingPlanner)
    {
        ArrayList<ModelShoppingPlanner> modelShoppingPlanners=new ArrayList<>();
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_shopping_planner_shop_item_name, shoppingPlanner.getShop_item_name());
        contentValues.put(COLUMN_shopping_planner_shop_item_status, shoppingPlanner.isShop_status());
        db.insert(Table_Shopping_planner, null, contentValues);
        Cursor c = db.query(Table_Shopping_planner,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            if(c.getInt(2)==1)
                modelShoppingPlanners.add(new ModelShoppingPlanner(c.getString(1),true));
            else
                modelShoppingPlanners.add(new ModelShoppingPlanner(c.getString(1),false));

        }
    }

    public void updateShoppingPlanner(Context context,ModelShoppingPlanner shoppingPlanner)
    {
        ArrayList<ModelShoppingPlanner> modelShoppingPlanners=new ArrayList<>();
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_shopping_planner_shop_item_status, shoppingPlanner.isShop_status());
        db.update(Table_Shopping_planner,contentValues,COLUMN_shopping_planner_shop_item_name+"='"+shoppingPlanner.getShop_item_name()+"'",null);


    }

    public void removeShoppingPlanner(Context context,ModelShoppingPlanner shoppingPlanner)
    {
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        ContentValues contentValues= new ContentValues();
        db.delete(Table_Shopping_planner, COLUMN_shopping_planner_shop_item_name + "='" + shoppingPlanner.getShop_item_name() + "'", null);
        db.close();

    }

    public void removeAllShoppingPlanner(Context context)
    {
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        ContentValues contentValues= new ContentValues();
        db.delete(Table_Shopping_planner, null, null);
        db.close();

    }

    public ArrayList<ModelShoppingPlanner> getMyshoppingPlanner(Context context)
    {
        ArrayList<ModelShoppingPlanner> modelShoppingPlanners=new ArrayList<>();
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_Shopping_planner,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {
                if (c.getInt(2) == 1)
                    modelShoppingPlanners.add(new ModelShoppingPlanner(c.getString(1), true));
                else
                    modelShoppingPlanners.add(new ModelShoppingPlanner(c.getString(1), false));

                c.moveToNext();

            }

        }
        c.close();
        db.close();
        return modelShoppingPlanners;
    }


    //Home content Table
    public static String Table_Home_content = "home_content_table";
    public static String COLUMN_home_content_banner_art = "banner_art";
    public static String COLUMN_home_content_shopping = "shopping";
    public static String COLUMN_home_content_dining = "dining";
    public static String COLUMN_home_content_entertainment = "entertainment";
    public static String COLUMN_home_content_offers = "offers";


    //Home banner slider table
    public static String Table_home_banner_slider = "home_banner_slider";
    public static String COLUMN_home_banner_title = "title";
    public static String COLUMN_home_banner_image = "image";
    public static String COLUMN_home_banner_page_id = "page_id";
    public static String COLUMN_home_banner_home_content_id = "home_content_id";






    public void insertToHomeContent(Context context,ModelHomeContent homeContent)
    {
        if(homeContent==null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_Home_content,null,null);
        db.delete(Table_home_banner_slider,null,null);
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_home_content_banner_art, homeContent.banner_art);
        contentValues.put(COLUMN_home_content_shopping, homeContent.tile_images.shopping);
        contentValues.put(COLUMN_home_content_dining, homeContent.tile_images.dining);
        contentValues.put(COLUMN_home_content_entertainment, homeContent.tile_images.entertainment);
        contentValues.put(COLUMN_home_content_offers, homeContent.tile_images.offers);
        db.insert(Table_Home_content, null, contentValues);

            for (ModelHomeContent.BannerSlider bannerSlider : homeContent.banner_slider) {
                contentValues = new ContentValues();
                contentValues.put(COLUMN_home_banner_title, bannerSlider.title);
                contentValues.put(COLUMN_home_banner_image, bannerSlider.image);
                contentValues.put(COLUMN_home_banner_page_id, bannerSlider.page_id);


                db.insert(Table_home_banner_slider, null, contentValues);

            }

        db.close();
    }




    public ModelHomeContent getHomeContent(Context context)
    {
        ModelHomeContent   homeContent=null  ;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_Home_content,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            homeContent= new ModelHomeContent();
            c.moveToFirst();
            homeContent.banner_art=c.getString(c.getColumnIndex(COLUMN_home_content_banner_art));
            homeContent.tile_images= homeContent.new ImageTitle();
            homeContent.tile_images.shopping=c.getString(c.getColumnIndex(COLUMN_home_content_shopping));
            homeContent.tile_images.dining=c.getString(c.getColumnIndex(COLUMN_home_content_dining));
            homeContent.tile_images.entertainment=c.getString(c.getColumnIndex(COLUMN_home_content_entertainment));
            homeContent.tile_images.offers=c.getString(c.getColumnIndex(COLUMN_home_content_offers));




            c=db.query(Table_home_banner_slider,null,null,null,null,null,null);
            homeContent.banner_slider= new ModelHomeContent.BannerSlider[c.getCount()];
            c.moveToFirst();

           for(int i=0;i<c.getCount();i++){
               homeContent.banner_slider[i]=homeContent.new BannerSlider();
               homeContent.banner_slider[i].image=c.getString(c.getColumnIndex(COLUMN_home_banner_image));
               homeContent.banner_slider[i].page_id=c.getInt(c.getColumnIndex(COLUMN_home_banner_page_id));
               homeContent.banner_slider[i].title=c.getString(c.getColumnIndex(COLUMN_home_banner_title));

               c.moveToNext();
            }

        }
        else
        c.close();
        db.close();
        return homeContent;
    }

    //offer Table
    public static String Table_offer_table = "offer_table";
    public static String COLUMN_offer_table_banner_art = "banner_art";


    //offer banner slider table
    public static String Table_offer_slider = "offer_slider";
    public static String COLUMN_offer_slider_title = "title";
    public static String COLUMN_offer_slider_offer_parent_id = "offer_parent_id";


    //offer item table
    public static String Table_offer_item_table = "offer_item_table";
    public static String COLUMN_offer_item_table_title = "title";
    public static String COLUMN_offer_item_table_offer_id = "offer_id";
    public static String COLUMN_offer_item_table_store_id = "store_id";
    public static String COLUMN_offer_item_table_image = "image";
    public static String COLUMN_offer_item_table_offer_parent_id = "offer_parent_id";


    public void insertToOffer(Context context,ModelOffer offer)
    {
        if (offer == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_offer_table,null,null);
        db.delete(Table_offer_slider,null,null);
        db.delete(Table_offer_item_table,null,null);

        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_offer_table_banner_art, offer.getBanner_art());

        db.insert(Table_offer_table, null, contentValues);


            for (String banner : offer.banner_slider) {
                contentValues = new ContentValues();

                contentValues.put(COLUMN_offer_slider_title, banner);

                db.insert(Table_offer_slider, null, contentValues);

            }

        for (ModelOffer.Offers myOffer : offer.offers) {
            contentValues = new ContentValues();

            contentValues.put(COLUMN_offer_item_table_offer_id, myOffer.getId());
            contentValues.put(COLUMN_offer_item_table_image, myOffer.getImage());
            contentValues.put(COLUMN_offer_item_table_store_id, myOffer.getStore_id());

            db.insert(Table_offer_item_table, null, contentValues);

        }

        db.close();
    }



    public ModelOffer getOfferData(Context context)
    {
        ModelOffer  offer=null  ;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_offer_table,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            offer= new ModelOffer();
            c.moveToFirst();
            offer.setBanner_art(c.getString(c.getColumnIndex(COLUMN_home_content_banner_art)));



            c=db.query(Table_offer_slider,null,null,null,null,null,null);
            offer.banner_slider= new String[c.getCount()];
            c.moveToFirst();

            for(int i=0;i<c.getCount();i++){
                offer.banner_slider[i]=new String();
                offer.banner_slider[i]=c.getString(c.getColumnIndex(COLUMN_offer_slider_title));
                c.moveToNext();
            }

            c=db.query(Table_offer_item_table,null,null,null,null,null,null);
            offer.offers= new ModelOffer.Offers[c.getCount()];
            c.moveToFirst();

            for(int i=0;i<c.getCount();i++){
                offer.offers[i]=offer.new Offers();
                offer.offers[i].setId(c.getString(c.getColumnIndex(COLUMN_offer_item_table_offer_id)));
                offer.offers[i].setImage(c.getString(c.getColumnIndex(COLUMN_offer_item_table_image)));
                offer.offers[i].setStore_id(c.getString(c.getColumnIndex(COLUMN_offer_item_table_store_id)));
                c.moveToNext();
            }

        }
        else
            c.close();
        db.close();
        return offer;
    }

    //offer Table
    public static String Table_about_mall = "about_mall";
    public static String COLUMN_about_mall_description = "description";
    public static String COLUMN_about_mall_image = "image";



    public void insertAboutMall(Context context, ModelGeneralQuery generalQuery)
    {
        if (generalQuery == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_about_mall,null,null);


        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_about_mall_description, generalQuery.getDescription());
        contentValues.put(COLUMN_about_mall_image, generalQuery.getImage());

        db.insert(Table_about_mall, null, contentValues);




        db.close();
    }


    public ModelGeneralQuery getAboutMall(Context context)
    {
        ModelGeneralQuery  generalQuery=null  ;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_about_mall,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            generalQuery= new ModelGeneralQuery();
            c.moveToFirst();
            generalQuery.setImage(c.getString(c.getColumnIndex(COLUMN_about_mall_image)));
            generalQuery.setDescription(c.getString(c.getColumnIndex(COLUMN_about_mall_description)));

        }
        else
            c.close();
        db.close();
        return generalQuery;
    }


    //shopping Table
    public static String Table_shopping_table = "shopping_table";
    public static String COLUMN_shopping_table_banner_art = "banner_art";


    public static String Table_subcategories = "subcategories";
    public static String COLUMN_subcategories_name = "name";
    public static String COLUMN_subcategories_P_id = "P_id";


    public static String Table_sub_banner_slider = "sub_banner_slider";
    public static String COLUMN_sub_banner_slider_image = "image";
    public static String COLUMN_P_id = "P_id";

    public static String Table_brands = "brands";
    public static String COLUMN_brands_image = "image";
    public static String COLUMN_brands_menu_card = "menu_card";
    public static String COLUMN_brands_contact_number = "contact_number";
    public static String COLUMN_brands_logo = "logo";
    public static String COLUMN_brands_title = "title";
    public static String COLUMN_brands_website = "website";
    public static String COLUMN_brands_email = "email";
    public static String COLUMN_brands_working_time = "working_time";
    public static String COLUMN_brands_description = "description";
    public static String COLUMN_brands_promo_image = "promo_image";
    public static String COLUMN_brands_promo_image_ipad = "promo_image_ipad";



    public void insertBrands(Context context, ModelBrands.Brands[] brands, String Pid)
    {
        if (brands == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();

        db.delete(Table_brands, COLUMN_P_id +"="+Pid,null);
        for(int i=0;i<brands.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_brands_image,brands[i].image);
            contentValues.put(_id,brands[i].brand_id);
            contentValues.put(COLUMN_P_id, Pid);
            db.insert(Table_brands, null, contentValues);
        }
        db.close();
    }


    public ModelBrands.Brands[] getBrands(Context context,String Pid)
    {
        ModelBrands.Brands[] brands = null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_brands,null, COLUMN_P_id +"="+Pid,null,null,null,null);


        if (c.getCount() > 0)
        {

            brands= new ModelBrands.Brands[c.getCount()];
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {
                brands[i]= new ModelBrands().new Brands();
                brands[i].brand_id=c.getString(c.getColumnIndex(_id));
                brands[i].image=c.getString(c.getColumnIndex(COLUMN_brands_image));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return brands;
    }



    public void insertBrandDetails(Context context, ModelBrandDetails details)
    {
        if (details == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_brands_menu_card,details.getMenu_card());
        contentValues.put(COLUMN_brands_contact_number,details.getContact_number());
        contentValues.put(COLUMN_brands_logo,details.getLogo());
        contentValues.put(COLUMN_brands_website,details.getWebsite());
        contentValues.put(COLUMN_brands_email,details.getEmail());
        contentValues.put(COLUMN_brands_working_time,details.getWorking_time());
        contentValues.put(COLUMN_brands_description,details.getDescription());
        contentValues.put(COLUMN_brands_title,details.getTitle());
        contentValues.put(COLUMN_brands_promo_image,details.getApp_portrait());
        contentValues.put(COLUMN_brands_promo_image_ipad,details.getIpad_portrait());



        db.update(Table_brands, contentValues, _id+"="+details.getId(),null);
        db.close();
    }

    public ModelBrandDetails GetBrandDetails(String id, Context context){
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_brands,null, _id +"="+id,null,null,null,null);
        ModelBrandDetails brandDetail=null;

        if (c.getCount() > 0)
        {
          brandDetail= new ModelBrandDetails();
            c.moveToFirst();
            brandDetail.setDescription(c.getString(c.getColumnIndex(COLUMN_brands_description)));
            brandDetail.setContact_number(c.getString(c.getColumnIndex(COLUMN_brands_contact_number)));
            brandDetail.setEmail(c.getString(c.getColumnIndex(COLUMN_brands_email)));
            brandDetail.setId(c.getString(c.getColumnIndex(_id)));
            brandDetail.setLogo(c.getString(c.getColumnIndex(COLUMN_brands_logo)));
            brandDetail.setWorking_time(c.getString(c.getColumnIndex(COLUMN_brands_working_time)));
            brandDetail.setMenu_card(c.getString(c.getColumnIndex(COLUMN_brands_menu_card)));
            brandDetail.setWebsite( c.getString(c.getColumnIndex(COLUMN_brands_website)));
            brandDetail.setTitle( c.getString(c.getColumnIndex(COLUMN_brands_title)));
            brandDetail.setApp_portrait( c.getString(c.getColumnIndex(COLUMN_brands_promo_image)));
            brandDetail.setIpad_portrait( c.getString(c.getColumnIndex(COLUMN_brands_promo_image_ipad)));

        }
        c.close();
        db.close();
        return brandDetail;

    }





    public void insertSubBannerSlider(Context context, String[] bannerslider, String Pid)
    {
        if (bannerslider == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_sub_banner_slider, COLUMN_P_id +"="+Pid,null);
        for(int i=0;i<bannerslider.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_sub_banner_slider_image, bannerslider[i]);
            contentValues.put(COLUMN_subcategories_P_id, Pid);
            db.insert(Table_sub_banner_slider, null, contentValues);
        }
        db.close();

    }

    public String[] getSubBannerSlider(Context context,String Pid)
    {
       String [] banner_sliders = null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_sub_banner_slider,null, COLUMN_P_id +"="+Pid,null,null,null,null);


        if (c.getCount() > 0)
        {
            banner_sliders= new String[c.getCount()];
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {

                banner_sliders[i] = c.getString(c.getColumnIndex(COLUMN_sub_banner_slider_image));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return banner_sliders;
    }



    public void insertSubacategories(Context context, ModelSubCategories.Dining[] dining, String Pid)
    {
        if (dining == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_subcategories,COLUMN_subcategories_P_id+"="+Pid,null);
        for(int i=0;i<dining.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_subcategories_name, dining[i].getName());
            contentValues.put(_id, dining[i].getId());
            contentValues.put(COLUMN_subcategories_P_id, Pid);
            db.insert(Table_subcategories, null, contentValues);
        }
        db.close();
    }




    public ModelSubCategories.Dining[] getSubcategories(Context context,String Pid)
    {
        ModelSubCategories subs= new ModelSubCategories();
        subs.dining=null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_subcategories,null,COLUMN_subcategories_P_id+"="+Pid,null,null,null,null);


        if (c.getCount() > 0)
        {
            subs.dining= new ModelSubCategories.Dining[c.getCount()];
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {
                ModelSubCategories.Dining myDining = subs.new Dining();

                myDining.setId(c.getString(c.getColumnIndex(_id)));
                myDining.setName(c.getString(c.getColumnIndex(COLUMN_subcategories_name)));
                subs.dining[i]=myDining;
                c.moveToNext();
            }
        }
       c.close();
        db.close();
        return subs.dining;
    }




    public void insertShopping(Context context, ModelFashion fashion)
    {
        if (fashion == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_shopping_table,null,null);


        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_shopping_table_banner_art,fashion.getBanner_art());

        db.insert(Table_shopping_table, null, contentValues);




        db.close();
    }


    public ModelFashion getShopping(Context context)
    {
        ModelFashion  fashion=null  ;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_shopping_table,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            fashion= new ModelFashion();
            c.moveToFirst();
            fashion.setBanner_art(c.getString(c.getColumnIndex(COLUMN_shopping_table_banner_art)));

        }
        else
            c.close();
        db.close();
        return fashion;
    }



    //dining Table
    public static String Table_dining_table = "dining_table";
    public static String COLUMN_dining_table_banner_art = "banner_art";


    public void insertDining(Context context, ModelFashion fashion)
    {
        if (fashion == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_dining_table,null,null);


        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_dining_table_banner_art,fashion.getBanner_art());

        db.insert(Table_dining_table, null, contentValues);




        db.close();
    }


    public ModelFashion getDining(Context context)
    {
        ModelFashion  fashion=null  ;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_dining_table,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            fashion= new ModelFashion();
            c.moveToFirst();
            fashion.setBanner_art(c.getString(c.getColumnIndex(COLUMN_dining_table_banner_art)));

        }

            c.close();
        db.close();
        return fashion;
    }



    //Entertainment Table
    public static String Table_entertainment_table = "entertainment_table";
    public static String COLUMN_entertainment_table_banner_art = "banner_art";
    public static String COLUMN_entertainment_table_contact_number = "contact_number";
    public static String COLUMN_entertainment_table_logo = "logo";
    public static String COLUMN_entertainment_table_website = "website";
    public static String COLUMN_entertainment_table_email = "email";
    public static String COLUMN_entertainment_table_working_time = "working_time";
    public static String COLUMN_entertainment_table_description = "description";




    public void insertEntertainment(Context context, ModelEntertainmentBrand entertainmentBrand)
    {
        if (entertainmentBrand == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_entertainment_table,null,null);


        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_entertainment_table_banner_art,entertainmentBrand.getBanner_art());
        contentValues.put(COLUMN_entertainment_table_contact_number,entertainmentBrand.getContact_number());
        contentValues.put(COLUMN_entertainment_table_logo,entertainmentBrand.getLogo());
        contentValues.put(COLUMN_entertainment_table_website,entertainmentBrand.getWorking_time());
        contentValues.put(COLUMN_entertainment_table_email,entertainmentBrand.getEmail());
        contentValues.put(COLUMN_entertainment_table_working_time,entertainmentBrand.getWorking_time());
        contentValues.put(COLUMN_entertainment_table_description,entertainmentBrand.getDescription());

        db.insert(Table_entertainment_table, null, contentValues);




        db.close();
    }


    public ModelEntertainmentBrand getEntertainment(Context context)
    {
        ModelEntertainmentBrand  entertainmentBrand=null  ;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_entertainment_table,null,null,null,null,null,null);
        if (c.getCount() > 0)
        {
            entertainmentBrand= new ModelEntertainmentBrand();
            c.moveToFirst();
            entertainmentBrand.setBanner_art(c.getString(c.getColumnIndex(COLUMN_entertainment_table_banner_art)));
            entertainmentBrand.setContact_number(c.getString(c.getColumnIndex(COLUMN_entertainment_table_contact_number)));
            entertainmentBrand.setLogo(c.getString(c.getColumnIndex(COLUMN_entertainment_table_logo)));
            entertainmentBrand.setWebsite(c.getString(c.getColumnIndex(COLUMN_entertainment_table_website)));
            entertainmentBrand.setEmail(c.getString(c.getColumnIndex(COLUMN_entertainment_table_email)));
            entertainmentBrand.setWorking_time(c.getString(c.getColumnIndex(COLUMN_entertainment_table_working_time)));
            entertainmentBrand.setDescription(c.getString(c.getColumnIndex(COLUMN_entertainment_table_description)));

        }
        else
            c.close();
        db.close();
        return entertainmentBrand;
    }


    //Entertainment offer Table
    public static String Table_entertainment_offer = "entertainment_offer_slider";
    public static String COLUMN_entertainment_offer_image = "image";



    public void insertEntertainmentOfferSlider(Context context, String[] bannerslider)
    {
        if (bannerslider == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_entertainment_offer, null,null);
        for(int i=0;i<bannerslider.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_entertainment_offer_image, bannerslider[i]);
            db.insert(Table_entertainment_offer, null, contentValues);
        }
        db.close();
    }

    public String[] getEntertainmentOfferSlider(Context context)
    {
        String [] banner_sliders = null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_entertainment_offer,null,null,null,null,null,null);


        if (c.getCount() > 0)
        {
            banner_sliders= new String[c.getCount()];
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {

                banner_sliders[i] = c.getString(c.getColumnIndex(COLUMN_entertainment_offer_image));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return banner_sliders;
    }




    //Upcoming movies Table
    public static String Table_upcoming_movies = "upcoming_movies";
    public static String COLUMN_upcoming_movies_image = "image";


    public void insertUpcomingMovies(Context context, String[] upcomingmovies)
    {
        if (upcomingmovies == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_upcoming_movies, null,null);
        for(int i=0;i<upcomingmovies.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_upcoming_movies_image, upcomingmovies[i]);
            db.insert(Table_upcoming_movies, null, contentValues);
        }
        db.close();
    }

    public String[] getUpcomingMovies(Context context)
    {
        String [] upcomingmovies = null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_upcoming_movies,null,null,null,null,null,null);


        if (c.getCount() > 0)
        {
            upcomingmovies= new String[c.getCount()];
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {

                upcomingmovies[i] = c.getString(c.getColumnIndex(COLUMN_upcoming_movies_image));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return upcomingmovies;
    }





    //detail_banner_slider Table
    public static String Table_detail_banner_slider = "detail_banner_slider";
    public static String COLUMN_detail_banner_slider_image = "image";
    public static String COLUMN_detail_banner_slider_P_id= "P_id";



    public void insertDetailBannerSlider(Context context, String[] bannerslider, String Pid)
    {
        if (bannerslider == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_detail_banner_slider, COLUMN_detail_banner_slider_P_id +"="+Pid,null);
        for(int i=0;i<bannerslider.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_detail_banner_slider_image, bannerslider[i]);
            contentValues.put(COLUMN_detail_banner_slider_P_id, Pid);
            db.insert(Table_detail_banner_slider, null, contentValues);
        }
        db.close();

    }

    public String[] getDetainBannerSlider(Context context,String Pid)
    {
        String [] banner_sliders = null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_detail_banner_slider,null, COLUMN_detail_banner_slider_P_id +"="+Pid,null,null,null,null);


        if (c.getCount() > 0)
        {
            banner_sliders= new String[c.getCount()];
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {

                banner_sliders[i] = c.getString(c.getColumnIndex(COLUMN_detail_banner_slider_image));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return banner_sliders;
    }


    //detail_banner_slider Table
    public static String Table_detail_offer_slider = "detail_offer_slider";
    public static String COLUMN_detail_offer_slider_image = "image";
    public static String COLUMN_detail_offer_slider_P_id= "P_id";



    public void insertDetailOfferSlider(Context context, String[] bannerslider, String Pid)
    {
        if (bannerslider == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        db.delete(Table_detail_offer_slider, COLUMN_detail_offer_slider_P_id +"="+Pid,null);
        for(int i=0;i<bannerslider.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_detail_offer_slider_image, bannerslider[i]);
            contentValues.put(COLUMN_detail_offer_slider_P_id, Pid);
            db.insert(Table_detail_offer_slider, null, contentValues);
        }
        db.close();

    }

    public String[] getDetaifferSlider(Context context,String Pid)
    {
        String [] banner_sliders = null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_detail_offer_slider,null, COLUMN_detail_offer_slider_P_id +"="+Pid,null,null,null,null);


        if (c.getCount() > 0)
        {
            banner_sliders= new String[c.getCount()];
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {

                banner_sliders[i] = c.getString(c.getColumnIndex(COLUMN_detail_offer_slider_image));
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return banner_sliders;
    }


    //detail_banner_slider Table
    public static String Table_MovieDetails = "MovieDetails";
    public static String COLUMN_MovieDetails_image = "image";
    public static String COLUMN_MovieDetails_title = "title";
    public static String COLUMN_MovieDetails_synopsis = "synopsis";
    public static String COLUMN_MovieDetails_logo = "logo";
    public static String COLUMN_MovieDetails_director = "director";
    public static String COLUMN_MovieDetails_movie_cast = "movie_cast";
    public static String COLUMN_MovieDetails_genre = "genre";
    public static String COLUMN_MovieDetails_P_id = "P_id";



    public void insertMovieDetails(Context context, ModelMovieDetails.Available movieDetails, String Pid)
    {
        if (movieDetails == null)
            return;

        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();

//        db.delete(Table_MovieDetails, COLUMN_MovieDetails_P_id +"="+Pid,null);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MovieDetails_image,movieDetails.getImage());
        contentValues.put(COLUMN_MovieDetails_title,movieDetails.getTitle());
        contentValues.put(COLUMN_MovieDetails_synopsis,movieDetails.getSynopsis());

        contentValues.put(COLUMN_MovieDetails_director,movieDetails.getDetails_director());
        contentValues.put(COLUMN_MovieDetails_movie_cast,movieDetails.getDetails_cast());
        contentValues.put(COLUMN_MovieDetails_genre,movieDetails.getDetails_genre());

            contentValues.put(COLUMN_MovieDetails_P_id, Pid);
            db.insert(Table_brands, null, contentValues);

        db.close();
    }


    public ModelMovieDetails.Available getMovieDetails(Context context,String Pid)
    {
        ModelMovieDetails.Available moviedetails = null;
        AssetesDBHelper dbhelper = new AssetesDBHelper(context);
        SQLiteDatabase db = dbhelper.openDataBase();
        Cursor c = db.query(Table_brands,null, COLUMN_P_id +"="+Pid,null,null,null,null);


        if (c.getCount() > 0)
        {




            moviedetails.setTitle(c.getString(c.getColumnIndex(COLUMN_MovieDetails_title)));
            moviedetails.setImage(c.getString(c.getColumnIndex(COLUMN_MovieDetails_image)));
            moviedetails.setSynopsis(c.getString(c.getColumnIndex(COLUMN_MovieDetails_synopsis)));
            moviedetails.setDetails_director(c.getString(c.getColumnIndex(COLUMN_MovieDetails_director)));
            moviedetails.setDetails_cast(c.getString(c.getColumnIndex(COLUMN_MovieDetails_movie_cast)));
            moviedetails.setDetails_genre(c.getString(c.getColumnIndex(COLUMN_MovieDetails_genre)));



        }
        c.close();
        db.close();
        return moviedetails;
    }






}
