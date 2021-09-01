package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/17/2016.
 */



public class ModelBrandDetails
{
    private String menu_card;

    private String contact_number;

    private String id;

    private String logo;

    public String[] banner_slider;

    private String title;

    public String[] offer_slider;

    private String website;

    private String app_portrait;


    private String ipad_portrait;

    private String email;

    private String working_time;

    private String description;

    public String getMenu_card ()
    {
        return menu_card;
    }

    public void setMenu_card (String menu_card)
    {
        this.menu_card = menu_card;
    }

    public String getContact_number ()
    {
        return contact_number;
    }

    public void setContact_number (String contact_number)
    {
        this.contact_number = contact_number;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLogo ()
    {
        return logo;
    }

    public void setLogo (String logo)
    {
        this.logo = logo;
    }

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

    public String[] getOffer_slider ()
    {
        return offer_slider;
    }

    public void setOffer_slider (String[] offer_slider)
    {
        this.offer_slider = offer_slider;
    }

    public String getWebsite ()
    {
        return website;
    }

    public void setWebsite (String website)
    {
        this.website = website;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getWorking_time ()
    {
        return working_time;
    }

    public void setWorking_time (String working_time)
    {
        this.working_time = working_time;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getApp_portrait() {
        return app_portrait;
    }

    public void setApp_portrait(String app_portrait) {
        this.app_portrait = app_portrait;
    }

    public String getIpad_portrait() {
        return ipad_portrait;
    }

    public void setIpad_portrait(String ipad_portrait) {
        this.ipad_portrait = ipad_portrait;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [menu_card = "+menu_card+", contact_number = "+contact_number+", id = "+id+", logo = "+logo+", banner_slider = "+banner_slider+", title = "+title+", offer_slider = "+offer_slider+", website = "+website+", email = "+email+", working_time = "+working_time+", description = "+description+"]";
    }
}



