/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.BarrioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Barrio;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "barrioControl")
@RequestScoped
public class BarrioControl extends AbstractControl<Barrio> {

    @EJB
    BarrioFacade facade;

    public BarrioControl() {
        super(Barrio.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Barrio obj) {
        return obj.getNombre() + " - " + obj.getIdlocalidad().getNombre() + " - " + obj.getIdlocalidad().getIdciudad().getNombre();
    }

}
