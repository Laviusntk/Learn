/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author learnproject
 */
public class DateTime {
    @SerializedName("display")
    @Expose    
    private String display;
    
    @SerializedName("time")
    @Expose    
    private long time;

    public DateTime() {
    }

    public DateTime(String display, long time) {
        this.display = display;
        this.time = time;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDisplay() {
        return display;
    }

    public long getTime() {
        return time;
    }
}
