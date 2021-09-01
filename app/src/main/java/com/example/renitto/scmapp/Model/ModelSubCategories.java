package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/29/2016.
 */
public class ModelSubCategories
{
    public Dining[] dining;

    public Dining[] shopping;

    public Dining[] getDining ()
    {
        return dining;
    }

    public void setDining (Dining[] dining)
    {
        this.dining = dining;
    }

    public Dining[] getShopping ()
    {
        return shopping;
    }

    public void setShopping (Dining[] shopping)
    {
        this.shopping = shopping;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dining = "+dining+", shopping = "+shopping+"]";
    }
    public class Dining
    {
        private String id;

        private String name;

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", name = "+name+"]";
        }
    }
//    public class Shopping
//    {
//        private String id;
//
//        private String name;
//
//        public String getId ()
//        {
//            return id;
//        }
//
//        public void setId (String id)
//        {
//            this.id = id;
//        }
//
//        public String getName ()
//        {
//            return name;
//        }
//
//        public void setName (String name)
//        {
//            this.name = name;
//        }
//
//        @Override
//        public String toString()
//        {
//            return "ClassPojo [id = "+id+", name = "+name+"]";
//        }
//    }

}