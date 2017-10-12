/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author learnproject
 */
public class DataStream {
    public String dsid;
    public String label;
    public String mimeType;

    public DataStream(String dsid, String label, String mimeType) {
        this.dsid = dsid;
        this.label = label;
        this.mimeType = mimeType;
    }

    public String getDsid() {
        return dsid;
    }

    public String getLabel() {
        return label;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String toString() {
        return "DataStream{" + "dsid=" + dsid + ", label=" + label + ", mimeType=" + mimeType + '}';
    }
    
}
