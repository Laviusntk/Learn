package learnapplication.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 *
 * @author nkateko
 */
public class User {
    private long createdDate;
    private DateTime createdTime;
    private String displayId;
    private String displayName;
    private String eid;
    private String email;
    private String firstName;
    private String id;
    private String lastModified;
    private String lastName;
    private String modifiedDate;
    private DateTime modifiedTime;
    private String owner;
    private String password;
    private String props;
    private String reference;
    private String sortName;
    private String type;
    private String url;
    private String entityReference;
    private String entityURL;
    private String entityId;
    private String entityTitle;

    public User() {
    }

    public User(long createdDate, DateTime createdTime, String displayId, String displayName, String eid, String email, String firstName, String id, String lastModified, String lastName, String modifiedDate, DateTime modifiedTime, String owner, String password, String props, String reference, String sortName, String type, String url, String entityReference, String entityURL, String entityId, String entityTitle) {
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

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedTime(DateTime createdTime) {
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

    public void setModifiedTime(DateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProps(String props) {
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

    public long getCreatedDate() {
        return createdDate;
    }

    public DateTime getCreatedTime() {
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

    public DateTime getModifiedTime() {
        return modifiedTime;
    }

    public String getOwner() {
        return owner;
    }

    public String getPassword() {
        return password;
    }

    public String getProps() {
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
        return "User{" + "displayName=" + displayName + ", type=" + type + ", url=" + url + '}';
    }
    
    
}
