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
public class SitePage {
    private String id;
    private String layout;
    private String layoutTitle;
    private Props2 props;
    private String reference;
    private String siteId;
    private String skin;
    private String title;
    private String titleCustom;
    private String url;
    private String activeEdit;
    private String homePage;
    private String popUp;

    public SitePage() {
    }

    public String getId() {
        return id;
    }

    public String getLayout() {
        return layout;
    }

    public String getLayoutTitle() {
        return layoutTitle;
    }

    public Props2 getProps() {
        return props;
    }

    public String getReference() {
        return reference;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getSkin() {
        return skin;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleCustom() {
        return titleCustom;
    }

    public String getUrl() {
        return url;
    }

    public String getActiveEdit() {
        return activeEdit;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getPopUp() {
        return popUp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public void setLayoutTitle(String layoutTitle) {
        this.layoutTitle = layoutTitle;
    }

    public void setProps(Props2 props) {
        this.props = props;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleCustom(String titleCustom) {
        this.titleCustom = titleCustom;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setActiveEdit(String activeEdit) {
        this.activeEdit = activeEdit;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public void setPopUp(String popUp) {
        this.popUp = popUp;
    }

    @Override
    public String toString() {
        return "SitePage{" + "id=" + id + ", layout=" + layout + ", layoutTitle=" + layoutTitle + ", props=" + props + ", reference=" + reference + ", siteId=" + siteId + ", skin=" + skin + ", title=" + title + ", titleCustom=" + titleCustom + ", url=" + url + ", activeEdit=" + activeEdit + ", homePage=" + homePage + ", popUp=" + popUp + '}';
    }
    
    
    
}
