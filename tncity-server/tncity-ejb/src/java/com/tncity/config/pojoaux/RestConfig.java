/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestConfig extends AbstractConfig {

    boolean securityIsActive = false;

    public boolean isSecurityIsActive() {
        return securityIsActive;
    }

    public void setSecurityIsActive(boolean securityIsActive) {
        this.securityIsActive = securityIsActive;
    }

}
