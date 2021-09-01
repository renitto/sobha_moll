package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 4/26/2016.
 */
public class ModelHomeEvent
{
    private String title;

    private String contents;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getContents ()
    {
        return contents;
    }

    public void setContents (String contents)
    {
        this.contents = contents;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", contents = "+contents+"]";
    }
}
