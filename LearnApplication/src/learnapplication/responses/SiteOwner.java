/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.responses;

/**
 *
 * @author learnproject
 */
public class SiteOwner {
    String userDisplayName;
    String userEntityURL;
    String userId;

    public SiteOwner() {
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public String getUserEntityURL() {
        return userEntityURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public void setUserEntityURL(String userEntityURL) {
        this.userEntityURL = userEntityURL;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SiteOwner{" + "userDisplayName=" + userDisplayName + ", userEntityURL=" + userEntityURL + ", userId=" + userId + '}';
    }
    
    
}
