package Models;

public class DataStreamObject {

    public String xsd;
    public String xmlns;
    public String baseURL;
    public DataStream[] datastream;
    public String pid;

    public DataStreamObject(String xsd, String xmlns, String baseURL, DataStream[] datastream, String pid) {
        this.xsd = xsd;
        this.xmlns = xmlns;
        this.baseURL = baseURL;
        this.datastream = datastream;
        this.pid = pid;
    }

    public String getXsd() {
        return xsd;
    }

    public String getXmlns() {
        return xmlns;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public DataStream[] getDatastream() {
        return datastream;
    }

    public String getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "DataStreamObject:{" + "xsd:" + xsd + ", xmlns:" + xmlns + ", baseURL:" + baseURL + ", datastream:" + datastream + ", pid:" + pid + '}';
    }
    
}
