
package com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.Place;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MapsPractice {

    @SerializedName("predictions")
    @Expose


    private List<Prediction1> predictions = new ArrayList<Prediction1>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * 
     * @return
     *     The predictions
     */
    public List<Prediction1> getPredictions() {
        return predictions;
    }

    /**
     * 
     * @param predictions
     *     The predictions
     */
    public void setPredictions(List<Prediction1> predictions) {
        this.predictions = predictions;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
