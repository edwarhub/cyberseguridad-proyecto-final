/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Pais;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class PaisFacade extends AbstractFacade<Pais> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PaisFacade() {
        super(Pais.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idpais", "nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre","iso2","iso3"};
        return attrs;
    }

    @Override
    protected Pais parseObject(Object[] o) {
        Pais p = new Pais((Integer) o[0]);
        p.setNombre((String) o[1]);
        return p;
    }

    @Override
    public void create(Pais obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Pais obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idpais, StringBuilder err) {
        deleteGeneral(idpais, err);
    }

}
