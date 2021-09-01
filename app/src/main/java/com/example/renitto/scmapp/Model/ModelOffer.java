package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/27/2016.
 */
public class ModelOffer {
    public String[] banner_slider;

    public Offers[] offers;

    private String banner_art;

    public String[] getBanner_slider ()
    {
        return banner_slider;
    }

    public void setBanner_slider (String[] banner_slider)
    {
        this.banner_slider = banner_slider;
    }

    public Offers[] getOffers ()
    {
        return offers;
    }

    public void setOffers (Offers[] offers)
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
        return "ClassPojo [banner_slider = "+banner_slider+", offers = "+offers+", banner_art = "+banner_art+"]";
    }

    public class Offers
    {
        private String id;

        private String store_id;

        private String image;

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getStore_id ()
        {
            return store_id;
        }

        public void setStore_id (String store_id)
        {
            this.store_id = store_id;
        }

        public String getImage ()
        {
            return image;
        }

        public void setImage (String image)
        {
            this.image = image;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", store_id = "+store_id+", image = "+image+"]";
        }
    }
}