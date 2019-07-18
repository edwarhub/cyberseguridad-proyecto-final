/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Persona;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "personaControl")
@RequestScoped
public class PersonaControl extends AbstractControl<Persona> {

    @EJB
    PersonaFacade facade;

    public PersonaControl() {
        super(Persona.class);
        attrOrd = "idpersona";
        ascDesc = "DESC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Persona obj) {
        return obj.getNombres() + " " + obj.getApellidos() + " " + obj.getNumdocumento();
    }

    public void nuevo() {
        obj.setIdusuarioCreated(facesUtil.getCurrentUser());
        obj.setIdusuarioUpdated(facesUtil.getCurrentUser());
        obj.setUpdatedAt(new Date());
        obj.setCreatedAt(new Date());
        createDefault();
    }

    public void editar() {
        obj.setIdusuarioUpdated(facesUtil.getCurrentUser());
        obj.setUpdatedAt(new Date());
        editDefault();
    }

    public void recuperaByNumDocumento(String numDoc) {
        obj = facade.findByNumDocumento(new BigInteger(numDoc));
    }

}
