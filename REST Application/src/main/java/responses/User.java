package responses;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author nkateko
 */
public class User {
    private String createdDate;
    private Map<String,String> createdTime;
    private String displayId;
    private String displayName;
    private String eid;
    private String email;
    private String firstName;
    private String id;
    private String lastModified;
    private String lastName;
    private String modifiedDate;
    private Map<String,String> modifiedTime;
    private String owner;
    private String password;
    private Map<String, String> props;
    private String reference;
    private String sortName;
    private String type;
    private String url;
    private String entityReference;
    private String entityURL;
    private String entityId;
    private String entityTitle;
    public ArrayList<Site> sites;
    
    public User() {
    }

    public User(String createdDate, Map<String,String> createdTime, String displayId, String displayName, String eid, String email, String firstName, String id, String lastModified, String lastName, String modifiedDate, Map<String,String> modifiedTime, String owner, String password, Map<String, String> props, String reference, String sortName, String type, String url, String entityReference, String entityURL, String entityId, String entityTitle) {
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.displayId = displayId;
        this.displayName = displayName;
        this.eid = eid;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastModified = lastModified;
        this.lastName = lastName;
        this.modifiedDate = modifiedDate;
        this.modifiedTime = modifiedTime;
        this.owner = owner;
        this.password = password;
        this.props = props;
        this.reference = reference;
        this.sortName = sortName;
        this.type = type;
        this.url = url;
        this.entityReference = entityReference;
        this.entityURL = entityURL;
        this.entityId = entityId;
        this.entityTitle = entityTitle;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedTime(Map<String,String> createdTime) {
        this.createdTime = createdTime;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setModifiedTime(Map<String,String> modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEntityReference(String entityReference) {
        this.entityReference = entityReference;
    }

    public void setEntityURL(String entityURL) {
        this.entityURL = entityURL;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Map<String,String> getCreatedTime() {
        return createdTime;
    }

    public String getDisplayId() {
        return displayId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEid() {
        return eid;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getLastName() {
        return lastName;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public Map<String,String> getModifiedTime() {
        return modifiedTime;
    }

    public String getOwner() {
        return owner;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public String getReference() {
        return reference;
    }

    public String getSortName() {
        return sortName;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public String getEntityURL() {
        return entityURL;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    @Override
    public String toString() {
        return "User{" + "createdDate=" + createdDate + ",\n createdTime=" + createdTime + ",\n displayId=" + displayId + ",\n displayName=" + displayName + ",\n eid=" + eid + ",\n email=" + email + ",\n firstName=" + firstName + ",\n id=" + id + ",\n lastModified=" + lastModified + ",\n lastName=" + lastName + ",\n modifiedDate=" + modifiedDate + ",\n modifiedTime=" + modifiedTime + ",\n owner=" + owner + ",\n password=" + password + ",\n props=" + props + ",\n reference=" + reference + ",\n sortName=" + sortName + ",\n type=" + type + ",\n url=" + url + ",\n entityReference=" + entityReference + ",\n entityURL=" + entityURL + ",\n entityId=" + entityId + ",\n entityTitle=" + entityTitle + '}';
    }


}
