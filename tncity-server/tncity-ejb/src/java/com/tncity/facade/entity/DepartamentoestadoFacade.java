/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Departamentoestado;
import com.tncity.jpa.pojo.Pais;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class DepartamentoestadoFacade extends AbstractFacade<Departamentoestado> {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public DepartamentoestadoFacade() {
        super(Departamentoestado.class);
    }
    
    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"iddepartamento", "nombre", "idpais.idpais", "idpais.nombre"};
        return attrs;
    }
    
    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombre"};
        return attrs;
    }
    
    @Override
    protected Departamentoestado parseObject(Object[] o) {
        Departamentoestado d = new Departamentoestado((Integer) o[0]);
        d.setNombre((String) o[1]);
        d.setIdpais(new Pais((Integer) o[2]));
        d.getIdpais().setNombre((String) o[3]);
        return d;
    }
    
    @Override
    public void create(Departamentoestado obj, StringBuilder err) {
        createGeneral(obj, err);
    }
    
    @Override
    public void edit(Departamentoestado obj, StringBuilder err) {
        editGeneral(obj, err);
    }
    
    @Override
    public void delete(Object iddepartamento, StringBuilder err) {
        deleteGeneral(iddepartamento, err);
    }
    
}
