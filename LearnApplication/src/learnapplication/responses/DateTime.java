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
    private String display;
    private String time;

    public DateTime() {
    }

    public String getDisplay() {
        return display;
    }

    public String getTime() {
        return time;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
