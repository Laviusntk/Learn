/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

/**
 *
 * @author learnproject
 */
public class Resource {
    private String author;
    private String authorId;
    private String container;
    private String copyrightAlert;
    private String description;
    private String endDate;
    private String fromDate;
    private String modifiedDate;
    private String numChildren;
    private String quota;
    private String size;
    private String title;
    private String type;
    private String url;
    private String usage;
    private String hidden;
    private String visible;
    private String entityReference;
    private String entityURL;
    private String entityTitle;

    public Resource() {
    }

    public Resource(String author, String authorId, String container, String copyrightAlert, String description, String endDate, String fromDate, String modifiedDate, String numChildren, String quota, String size, String title, String type, String url, String usage, String hidden, String visible, String entityReference, String entityURL, String entityTitle) {
        this.author = author;
        this.authorId = authorId;
        this.container = container;
        this.copyrightAlert = copyrightAlert;
        this.description = description;
        this.endDate = endDate;
        this.fromDate = fromDate;
        this.modifiedDate = modifiedDate;
        this.numChildren = numChildren;
        this.quota = quota;
        this.size = size;
        this.title = title;
        this.type = type;
        this.url = url;
        this.usage = usage;
        this.hidden = hidden;
        this.visible = visible;
        this.entityReference = entityReference;
        this.entityURL = entityURL;
        this.entityTitle = entityTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public void setCopyrightAlert(String copyrightAlert) {
        this.copyrightAlert = copyrightAlert;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setNumChildren(String numChildren) {
        this.numChildren = numChildren;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public void setEntityReference(String entityReference) {
        this.entityReference = entityReference;
    }

    public void setEntityURL(String entityURL) {
        this.entityURL = entityURL;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContainer() {
        return container;
    }

    public String getCopyrightAlert() {
        return copyrightAlert;
    }

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public String getNumChildren() {
        return numChildren;
    }

    public String getQuota() {
        return quota;
    }

    public String getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getUsage() {
        return usage;
    }

    public String getHidden() {
        return hidden;
    }

    public String getVisible() {
        return visible;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public String getEntityURL() {
        return entityURL;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    @Override
    public String toString() {
        return "Resource{" + "author=" + author + ",\n authorId=" + authorId + ",\n container=" + container + ",\n copyrightAlert=" + copyrightAlert + ",\n description=" + description + ",\n endDate=" + endDate + ",\n fromDate=" + fromDate + ",\n modifiedDate=" + modifiedDate + ",\n numChildren=" + numChildren + ",\n quota=" + quota + ",\n size=" + size + ",\n title=" + title + ",\n type=" + type + ",\n url=" + url + ",\n usage=" + usage + ",\n hidden=" + hidden + ",\n visible=" + visible + ",\n entityReference=" + entityReference + ",\n entityURL=" + entityURL + ",\n entityTitle=" + entityTitle + '}';
    }
}
