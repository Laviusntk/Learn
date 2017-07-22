/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.models;

/**
 *
 * @author learnproject
 */
public class DateTime {
    private String display;
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
