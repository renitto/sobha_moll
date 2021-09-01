package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/16/2016.
 */

public class ModelEntertainmentBrand
{
    private String contact_number;

    private String logo;

    public String[] upcoming;

    private String website;

    private String email;

    private String working_time;

    private String description;

    public String[] offers;

    private String banner_art;

    public String getContact_number ()
    {
        return contact_number;
    }

    public void setContact_number (String contact_number)
    {
        this.contact_number = contact_number;
    }

    public String getLogo ()
    {
        return logo;
    }

    public void setLogo (String logo)
    {
        this.logo = logo;
    }

    public String[] getUpcoming ()
    {
        return upcoming;
    }

    public void setUpcoming (String[] upcoming)
    {
        this.upcoming = upcoming;
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

    public String[] getOffers ()
    {
        return offers;
    }

    public void setOffers (String[] offers)
    {
        this.offers = offers;
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
        return "ClassPojo [contact_number = "+contact_number+", logo = "+logo+", upcoming = "+upcoming+", website = "+website+", email = "+email+", working_time = "+working_time+", description = "+description+", offers = "+offers+", banner_art = "+banner_art+"]";
    }
}


