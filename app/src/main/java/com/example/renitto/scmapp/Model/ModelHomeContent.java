package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/14/2016.
 */
public class ModelHomeContent {

    public String banner_art;
    public BannerSlider[] banner_slider;
    public ImageTitle tile_images;

    public class BannerSlider {
        public String title; public String image; public int  page_id;
    }

    public class ImageTitle {
        public String shopping;
        public String dining;
        public String entertainment;
        public String offers;

        public String getShopping() {
            return shopping;
        }

        public void setShopping(String shopping) {
            this.shopping = shopping;
        }

        public String getDining() {
            return dining;
        }

        public void setDining(String dining) {
            this.dining = dining;
        }

        public String getEntertainment() {
            return entertainment;
        }

        public void setEntertainment(String entertainment) {
            this.entertainment = entertainment;
        }

        public String getOffers() {
            return offers;
        }

        public void setOffers(String offers) {
            this.offers = offers;
        }
    }
}
