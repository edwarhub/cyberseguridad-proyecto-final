/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "informexList")
public class InformexListXml {

    List<InformexReg> lsfInformex = new ArrayList<>();

    @XmlElement(name = "informex")
    public List<InformexReg> getLsfInformex() {
        return lsfInformex;
    }

    public void setLsfInformex(List<InformexReg> lsfInformex) {
        this.lsfInformex = lsfInformex;
    }

}
