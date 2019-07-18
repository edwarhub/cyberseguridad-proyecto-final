/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.CiudadFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Ciudad;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "ciudadControl")
@RequestScoped
public class CiudadControl extends AbstractControl<Ciudad> {

    @EJB
    CiudadFacade facade;

    public CiudadControl() {
        super(Ciudad.class);
        attrOrd="nombre";
        ascDesc="ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Ciudad obj) {
        return obj.getNombre()+" - "+obj.getIddepartamento().getNombre();
    }

}
