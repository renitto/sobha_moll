package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/17/2016.
 */

public class ModelFashion
{
    public String[] banner_slider;

    public String title;

    public String banner_art;

    public String[] getBanner_slider ()
    {
        return banner_slider;
    }

    public void setBanner_slider (String[] banner_slider)
    {
        this.banner_slider = banner_slider;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getBanner_art ()
    {
        return banner_art;
    }

    public void setBanner_art (String banner_art)
    {
        this.banner_art = banner_art;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [banner_slider = "+banner_slider+", title = "+title+", banner_art = "+banner_art+"]";
    }
}