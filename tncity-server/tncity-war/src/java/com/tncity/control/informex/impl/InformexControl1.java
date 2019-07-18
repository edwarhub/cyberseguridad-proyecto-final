/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex.impl;

import com.tncity.control.general.FacesUtil;
import com.tncity.control.informex.AbstractInformexControl;
import com.tncity.informex.InformexAbstract;
import com.tncity.informex.InformexReg;
import com.tncity.informex.impl.InformexDs1;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class InformexControl1 extends AbstractInformexControl {

    Date desde;
    Date hasta;

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    @Override
    public InformexAbstract buildModel(InformexReg infR, String protocolHostPortPath) {
        InformexAbstract infB = new InformexDs1(desde, hasta, "createdAt", "ASC",infR,protocolHostPortPath);
        return infB;
    }

    @Override
    public boolean validaSuccessful(StringBuilder err) {
        return true;
    }
}
