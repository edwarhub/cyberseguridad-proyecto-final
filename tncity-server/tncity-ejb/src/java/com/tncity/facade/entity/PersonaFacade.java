/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Persona;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idpersona", "nombres", "apellidos", "numdocumento", "email", "numTelefono", "direccion"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"nombres", "apellidos", "numdocumento"};
        return attrs;
    }

    @Override
    protected Persona parseObject(Object[] o) {
        Persona p = new Persona(new Long(o[0].toString()));
        p.setNombres((String) o[1]);
        p.setApellidos((String) o[2]);
        if (o[3] != null) {
            p.setNumdocumento(new BigInteger(o[3].toString()));
        }
        p.setEmail((String) o[4]);
        if (o[5] != null) {
            p.setNumTelefono(new Long(o[5].toString()));
        }
        p.setDireccion((String) o[6]);
        return p;
    }

    public Persona findByNumDocumento(BigInteger numdocumento) {
        String hql = "SELECT p FROM Persona p WHERE p.numdocumento='" + numdocumento + "'";
        return objectFromHQL(hql);
    }

    @Override
    public void create(Persona obj, StringBuilder err) {
        Persona p = findByNumDocumento(obj.getNumdocumento());
        if (p != null) {
            err.append("El número de documento ").append(obj.getNumdocumento()).append(" ya está registrado !");
            return;
        }
        createGeneral(obj, err);
    }

    @Override
    public void edit(Persona obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        deleteGeneral(valueId, err);
    }

}
