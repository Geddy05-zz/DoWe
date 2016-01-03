package com.cal.evento.evento.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by geddy on 27/12/15.
 */
public class FacebookEvents {

    private String iD;
    private String name;
    private String description;
    private Date start_time;
    private Location location;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public FacebookEvents(String id){
        this.iD = id;
    }

    public String getiD(){
        return iD;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return  this.description;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setLocation(JSONObject place) {
        Location location = new Location();
        try {
            location.Name = (place.has("name")) ? place.getString("name") : null ;
            location.id =  (place.has("id")) ? place.getLong("id") : 0 ;
            if( place.has("location")) {
                JSONObject jsonLoc = place.getJSONObject("location");
                location.City = (jsonLoc.has("city")) ? jsonLoc.getString("city") : null;
                location.Country = (jsonLoc.has("country")) ? jsonLoc.getString("country") : null;
                location.street = (jsonLoc.has("street")) ? jsonLoc.getString("street") : null;
                location.zip = (jsonLoc.has("zip")) ? jsonLoc.getString("zip") : null;
                location.latitude = (jsonLoc.has("latitude")) ? jsonLoc.getDouble("latitude") : 0;
                location.longitude = (jsonLoc.has("longitude")) ? jsonLoc.getDouble("longitude") : 0;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
