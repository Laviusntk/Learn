/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AuthStatus;
import Models.UploadResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author learnproject
 */
@RestController
public class DefaultController {

    @RequestMapping("/")
    public AuthStatus upload() throws Exception {
        return new AuthStatus(201,"Default end-point");
    }
}
