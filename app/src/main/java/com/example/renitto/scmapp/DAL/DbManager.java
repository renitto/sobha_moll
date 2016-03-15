package com.example.renitto.scmapp.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.renitto.scmapp.Model.ModelShoppingPlanner;

import java.util.ArrayList;

/**
 * Created by Renitto on 3/3/2016.
 */
public class DbManager  {

    //Shopping Planner
    public static String Table_Shopping_planner = "shopping_planner_table";
    public static String Shopping_planner_id = "_id";
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
            if(c.getInt(2)==1)
           modelShoppingPlanners.add(new ModelShoppingPlanner(c.getString(1),true));
            else
                modelShoppingPlanners.add(new ModelShoppingPlanner(c.getString(1),false));

        }
        c.close();
        db.close();
        return modelShoppingPlanners;
    }
}
