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
public class Props {
    private String spml_last_update;
    private String uctNewMailSent;
    private String PS_MEMEBERSHIPS_SYNCHED;

    public Props() {
    }

    public Props(String spml_last_update, String uctNewMailSent, String PS_MEMEBERSHIPS_SYNCHED) {
        this.spml_last_update = spml_last_update;
        this.uctNewMailSent = uctNewMailSent;
        this.PS_MEMEBERSHIPS_SYNCHED = PS_MEMEBERSHIPS_SYNCHED;
    }

    public void setSpml_last_update(String spml_last_update) {
        this.spml_last_update = spml_last_update;
    }

    public void setUctNewMailSent(String uctNewMailSent) {
        this.uctNewMailSent = uctNewMailSent;
    }

    public void setPS_MEMEBERSHIPS_SYNCHED(String PS_MEMEBERSHIPS_SYNCHED) {
        this.PS_MEMEBERSHIPS_SYNCHED = PS_MEMEBERSHIPS_SYNCHED;
    }

    public String getSpml_last_update() {
        return spml_last_update;
    }

    public String getUctNewMailSent() {
        return uctNewMailSent;
    }

    public String getPS_MEMEBERSHIPS_SYNCHED() {
        return PS_MEMEBERSHIPS_SYNCHED;
    }
    
    
}
