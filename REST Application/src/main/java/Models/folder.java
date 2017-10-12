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
public class folder {
    private String title;
    private String author;  
    private String icon;
    private String url;
    private String className;

    public folder(String title, String author, String icon, String url, String _classname) {
        this.title = title;
        this.author = author;
        this.icon = icon;
        this.url = url;
        this.className = _classname;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIcon() {
        return icon;
    }

    public String getUrl() {
        return url;
    }

    public String getClassName() {
        return className;
    }
        
}
