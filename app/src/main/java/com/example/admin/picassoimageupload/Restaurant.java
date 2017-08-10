package com.example.admin.picassoimageupload;


/**
 * RESTAURANT CLASS
 */

public class Restaurant {
//    implements Serializable

    String restaurantId;
    String restaurantname;
    String restaurantimageurl;
    String restaurantlocation;

    public Restaurant(){

    }


 public Restaurant(String restaurantId,  String restaurantname, String restaurantimageurl,  String restaurantlocation) {

     this.restaurantId = restaurantId;
     this.restaurantname = restaurantname;
     this.restaurantimageurl = restaurantimageurl;
     this.restaurantlocation = restaurantlocation;
 }


    public String getRestaurantname (){
        return restaurantname;
    }
public String getRestaurantId(){
    return restaurantId;
}

public String getRestaurantimageurl(){
    return restaurantimageurl;
}

public String getRestaurantlocation() {
    return restaurantlocation;
}
}
