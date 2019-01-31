package com.example.geoguessswipe;

import android.graphics.drawable.Drawable;

/**
 * This class is used to create an object. This has two variables one for the drawable id an the
 * other for the boolean.
 */
public class GeoImage {
    //Variables
    private Drawable id;
    private boolean EU;

    //Constructor
    public GeoImage(Drawable id, boolean EU) {
        this.id = id;
        this.EU = EU;
    }

    //Get
    public Drawable getId() {
        return id;
    }
    //Set
    public void setId(Drawable id) {
        this.id = id;
    }
    //Get
    public boolean isEU() {
        return EU;
    }
    //Set
    public void setEU(boolean EU) {
        this.EU = EU;
    }

}
