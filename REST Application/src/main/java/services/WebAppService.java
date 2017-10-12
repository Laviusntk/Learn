/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.services;

import javax.jws.WebService;

/**
 *
 * @author learnproject
 */
@WebService
public class WebAppService {
    public void constructor(){
    }
    
    public String Hellow(String s){
        return "hellow " + s;
    }
}
