/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Barrio;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Localidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class BarrioFacade extends AbstractFacade<Barrio> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BarrioFacade() {
        super(Barrio.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idbarrio","nombre", "idlocalidad.idlocalidad", "idlocalidad.nombre", "idlocalidad.idciudad.idciudad", "idlocalidad.idciudad.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Barrio parseObject(Object[] o) {
        Barrio b = new Barrio((Integer) o[0]);
        b.setNombre((String) o[1]);
        b.setIdlocalidad(new Localidad((Integer) o[2]));
        b.getIdlocalidad().setNombre((String) o[3]);
        b.getIdlocalidad().setIdciudad(new Ciudad((Integer) o[4]));
        b.getIdlocalidad().getIdciudad().setNombre((String) o[5]);
        return b;
    }

    @Override
    public void create(Barrio obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Barrio obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }

}
