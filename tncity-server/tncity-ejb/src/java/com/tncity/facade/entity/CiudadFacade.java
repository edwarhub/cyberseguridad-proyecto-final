/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Ciudad;
import com.tncity.jpa.pojo.Departamentoestado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idciudad", "nombre", "iddepartamento.iddepartamento", "iddepartamento.nombre"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }

    @Override
    protected Ciudad parseObject(Object[] o) {
        Ciudad c = new Ciudad();
        c.setIdciudad((Integer) o[0]);
        c.setNombre((String) o[1]);
        c.setIddepartamento(new Departamentoestado((Integer) o[2]));
        c.getIddepartamento().setNombre((String) o[3]);
        return c;
    }

    @Override
    public void create(Ciudad obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Ciudad obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idciudad, StringBuilder err) {
        deleteGeneral(idciudad, err);
    }

}
