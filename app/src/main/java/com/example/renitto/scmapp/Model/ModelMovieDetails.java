package com.example.renitto.scmapp.Model;

import java.io.Serializable;

/**
 * Created by Renitto on 3/20/2016.
 */
public class ModelMovieDetails
{
    private String title;

    private String banner_art;

    private Available[] available;

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

    public Available[] getAvailable ()
    {
        return available;
    }

    public void setAvailable (Available[] available)
    {
        this.available = available;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", banner_art = "+banner_art+", available = "+available+"]";
    }

    public class Available
    {
        private String details_genre;

        private String id;

        private Screen[] screen;

        private String title;

        private String details_cast;

        private String details_director;

        private String synopsis;

        private String image;

        private String book_now;

        public String getDetails_genre ()
        {
            return details_genre;
        }

        public void setDetails_genre (String details_genre)
        {
            this.details_genre = details_genre;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public Screen[] getScreen ()
        {
            return screen;
        }

        public void setScreen (Screen[] screen)
        {
            this.screen = screen;
        }

        public String getTitle ()
        {
            return title;
        }

        public void setTitle (String title)
        {
            this.title = title;
        }

        public String getDetails_cast ()
        {
            return details_cast;
        }

        public void setDetails_cast (String details_cast)
        {
            this.details_cast = details_cast;
        }

        public String getDetails_director ()
        {
            return details_director;
        }

        public void setDetails_director (String details_director)
        {
            this.details_director = details_director;
        }

        public String getSynopsis ()
        {
            return synopsis;
        }

        public void setSynopsis (String synopsis)
        {
            this.synopsis = synopsis;
        }

        public String getImage ()
        {
            return image;
        }

        public void setImage (String image)
        {
            this.image = image;
        }

        public String getBook_now ()
        {
            return book_now;
        }

        public void setBook_now (String book_now)
        {
            this.book_now = book_now;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [details_genre = "+details_genre+", id = "+id+", screen = "+screen+", title = "+title+", details_cast = "+details_cast+", details_director = "+details_director+", synopsis = "+synopsis+", image = "+image+", book_now = "+book_now+"]";
        }
    }

    public class Screen
    {
        private String screen;

        private String timing;

        public String getScreen ()
        {
            return screen;
        }

        public void setScreen (String screen)
        {
            this.screen = screen;
        }

        public String getTiming ()
        {
            return timing;
        }

        public void setTiming (String timing)
        {
            this.timing = timing;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [screen = "+screen+", timing = "+timing+"]";
        }
    }
}
