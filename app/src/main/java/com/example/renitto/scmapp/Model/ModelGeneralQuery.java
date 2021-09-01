package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 4/8/2016.
 */
public class ModelGeneralQuery
{
    private String description;

    private String image;

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
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
        return "ClassPojo [description = "+description+", image = "+image+"]";
    }
}
