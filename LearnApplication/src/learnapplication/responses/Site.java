/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.responses;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author learnproject
 */
public class Site {

    private String contactEmail;
    private String contactName;
    private String createdDate;
    private DateTime createdTime;
    private String description;
    private String htmlDescription;
    private String htmlShortDescription;
    private String iconUrl;
    private String iconUrlFull;
    private String id;
    private String infoUrl;
    private String infoUrlFull;
    private String joinerRole;
    private String lastModified;
    private String maintainRole;
    private String modifiedDate;
    private DateTime modifiedTime;
    private String owner;
    private Props props;
    private String providerGroupId;
    private String reference;
    private String shortDescription;
    private String siteGroups;
    private SiteOwner siteOwner;
    private SitePage[] sitePages;
    private String skin;
    private String softlyDeletedDate;
    private String title;
    private String type;
    private String[] userRoles;
    private String activeEdit;
    private String customPageOrdered;
    private String empty;
    private String joinable;
    private String pubView;
    private String published;
    private String softlyDeleted;
    private String entityReference;
    private String entityURL;
    private String entityId;
    private String entityTitle;
    public ArrayList<Resource> mycontent;
    
    public Site() {
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public DateTime getCreatedTime() {
        return createdTime;
    }

    public String getDescription() {
        return description;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public String getHtmlShortDescription() {
        return htmlShortDescription;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getIconUrlFull() {
        return iconUrlFull;
    }

    public String getId() {
        return id;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public String getInfoUrlFull() {
        return infoUrlFull;
    }

    public String getJoinerRole() {
        return joinerRole;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getMaintainRole() {
        return maintainRole;
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

    public Props getProps() {
        return props;
    }

    public String getProviderGroupId() {
        return providerGroupId;
    }

    public String getReference() {
        return reference;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getSiteGroups() {
        return siteGroups;
    }

    public SiteOwner getSiteOwner() {
        return siteOwner;
    }

    public SitePage[] getSitePages() {
        return sitePages;
    }

    public String getSkin() {
        return skin;
    }

    public String getSoftlyDeletedDate() {
        return softlyDeletedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String[] getUserRoles() {
        return userRoles;
    }

    public String getActiveEdit() {
        return activeEdit;
    }

    public String getCustomPageOrdered() {
        return customPageOrdered;
    }

    public String getEmpty() {
        return empty;
    }

    public String getJoinable() {
        return joinable;
    }

    public String getPubView() {
        return pubView;
    }

    public String getPublished() {
        return published;
    }

    public String getSoftlyDeleted() {
        return softlyDeleted;
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
        return "Site{" + "contactEmail=" + contactEmail + ", contactName=" + contactName + ", createdDate=" + createdDate + ", createdTime=" + createdTime + ", description=" + description + ", htmlDescription=" + htmlDescription + ", htmlShortDescription=" + htmlShortDescription + ", iconUrl=" + iconUrl + ", iconUrlFull=" + iconUrlFull + ", id=" + id + ", infoUrl=" + infoUrl + ", infoUrlFull=" + infoUrlFull + ", joinerRole=" + joinerRole + ", lastModified=" + lastModified + ", maintainRole=" + maintainRole + ", modifiedDate=" + modifiedDate + ", modifiedTime=" + modifiedTime + ", owner=" + owner + ", props=" + props + ", providerGroupId=" + providerGroupId + ", reference=" + reference + ", shortDescription=" + shortDescription + ", siteGroups=" + siteGroups + ", siteOwner=" + siteOwner + ", sitePages=" + sitePages + ", skin=" + skin + ", softlyDeletedDate=" + softlyDeletedDate + ", title=" + title + ", type=" + type + ", userRoles=" + userRoles + ", activeEdit=" + activeEdit + ", customPageOrdered=" + customPageOrdered + ", empty=" + empty + ", joinable=" + joinable + ", pubView=" + pubView + ", published=" + published + ", softlyDeleted=" + softlyDeleted + ", entityReference=" + entityReference + ", entityURL=" + entityURL + ", entityId=" + entityId + ", entityTitle=" + entityTitle + "}\n";
    }
}
