/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex;


import com.tncity.util.JaxbUtil;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

@Stateless
@LocalBean
public class InformexListFacade {

    private static InformexListXml informexList;
    private static HashMap<Integer, InformexReg> mapInformexList = new HashMap<>();

    public InformexListXml getInformexBuild() {

        String pckF = "/com/tncity/informex/informex.xml";

        if (pckF != null && informexList == null) {
            informexList = build(pckF);
            for (InformexReg inf : informexList.lsfInformex) {
                mapInformexList.put(inf.getId(), inf);
            }
        }

        return informexList;
    }

    public InformexReg findInformexByIf(Integer id) {
        return mapInformexList.get(id);
    }

    private InformexListXml build(String pathFilePackage) {
        InformexListXml b = new InformexListXml();
        try {
            InputStream in = this.getClass().getResourceAsStream(pathFilePackage);
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String xml = s.hasNext() ? s.next() : "";
            in.close();
            b = JaxbUtil.xmlToObject(xml, InformexListXml.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

}
