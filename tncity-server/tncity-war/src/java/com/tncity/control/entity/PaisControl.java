/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PaisFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Pais;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "paisControl")
@RequestScoped
public class PaisControl extends AbstractControl<Pais> {

    @EJB
    PaisFacade facade;

    public PaisControl() {
        super(Pais.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Pais obj) {
        return obj.getNombre();
    }

}
