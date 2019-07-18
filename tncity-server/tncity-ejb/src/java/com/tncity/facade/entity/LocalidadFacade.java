/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Localidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class LocalidadFacade extends AbstractFacade<Localidad> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public LocalidadFacade() {
        super(Localidad.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idlocalidad", "nombre", "idciudad.idciudad", "idciudad.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Localidad parseObject(Object[] o) {
        Localidad l = new Localidad((Integer) o[0]);
        l.setNombre((String) o[1]);
        l.setIdciudad(new Ciudad((Integer) o[2]));
        l.getIdciudad().setNombre((String) o[3]);
        return l;
    }

    @Override
    public void create(Localidad obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Localidad obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idlocalidad, StringBuilder err) {
        deleteGeneral(idlocalidad, err);
    }

}
