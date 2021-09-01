package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/17/2016.
 */
public class ModelBrands
{
    public Brands[] brands;

    public Brands[] getBrands ()
    {
        return brands;
    }

    public void setBrands (Brands[] brands)
    {
        this.brands = brands;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [brands = "+brands+"]";
    }

    public class Brands
    {
        public String brand_id;

        public String image;

        public String getBrand_id ()
        {
            return brand_id;
        }

        public void setBrand_id (String brand_id)
        {
            this.brand_id = brand_id;
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
            return "ClassPojo [brand_id = "+brand_id+", image = "+image+"]";
        }
    }

}


