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
public class Props2 {
 private String is_home_page;

    public Props2() {
    }

    public Props2(String is_home_page) {
        this.is_home_page = is_home_page;
    }

    public String getIs_home_page() {
        return is_home_page;
    }

    public void setIs_home_page(String is_home_page) {
        this.is_home_page = is_home_page;
    }

    @Override
    public String toString() {
        return "Props2{" + "is_home_page=" + is_home_page + '}';
    }
 
}
