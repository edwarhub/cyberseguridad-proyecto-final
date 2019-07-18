/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.InformexHistoFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.InformexHisto;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author desarrollo02
 */
@ManagedBean
@RequestScoped
public class InformexHistoControl extends AbstractControl<InformexHisto> {

    @EJB
    InformexHistoFacade facade;
    InformexHisto obj = new InformexHisto();

    public InformexHistoControl() {
        super(InformexHisto.class);
        attrOrd = "fechaIni";
        ascDesc = "DESC";
    }

    @Override
    public InformexHisto getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = (InformexHisto) obj;
    }

    public String getRecuperaAllObj() {
        Integer pag = facesUtil.getFacesParamInteger("pag_");
        if (pag == null) {
            pag = 0;
        }

        this.lst = facade.listAll(pag * this.maxRegLista, this.maxRegLista, attrOrd, ascDesc);
        return "";
    }

    public long getRecuperaNumAllObj() {
        this.totalConsulta = facade.countAll();
        return this.totalConsulta;
    }

    @Override
    protected AbstractFacade getFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String displayObj(InformexHisto obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
