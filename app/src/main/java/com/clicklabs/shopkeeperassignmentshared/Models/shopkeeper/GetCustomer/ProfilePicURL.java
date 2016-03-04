
package com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.GetCustomer;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//@Generated("org.jsonschema2pojo")
public class ProfilePicURL implements Serializable{

    @SerializedName("thumbnail")
    @Expose
    private Object thumbnail;
    @SerializedName("original")
    @Expose
    private Object original;

    /**
     * 
     * @return
     *     The thumbnail
     */
    public Object getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The original
     */
    public Object getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    public void setOriginal(Object original) {
        this.original = original;
    }

}
