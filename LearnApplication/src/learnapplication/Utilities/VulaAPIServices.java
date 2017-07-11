package learnapplication.Utilities;

/**
 * @author lavius nkateko motileng : mtllav001 2017
 */
public class VulaAPIServices {
    /*
    * Stores the user's current active site id. @TO DO
    */
    private String currentSiteID = "<site-id>";
    /*
    * Stores the   site id. @TO DO:
    */
    private String siteID = "<site-id>";    
    /*
    * Retrieve user's profile.
    */
    private String USER_PROFILE_SERVICE = "https://vula.uct.ac.za/direct/user/current.json"; 
    /*
    * Check if user a member of a given site.
    * Get site ID, search it in the list of memberships.
    */
    private String MEMBERSHIP_SERVICE = "https://vula.uct.ac.za/direct/membership.json";
    /*
    * Get the list of all assignments and automatically upload archive them.
    * Get the site ID of the assignments, use it to get the details of the site.
    * When upload the assignment details to the repository together with the 
    * details of the course it belongs to.
    */
    private String ASSIGNMENTS_SERVICE = "https://vula.uct.ac.za/direct/assignment/my.json";
    /*
    * Get the list of all content/resources for a given site.
    */
    private String RESOURCES_SERVICE = "https://vula.uct.ac.za/direct/content/site/"+siteID+".json";
    /*
    * Get the list of all user's sites.
    */
    private String SITES_SERVICES = "https://vula.uct.ac.za/direct/site.json";
    
    
}
