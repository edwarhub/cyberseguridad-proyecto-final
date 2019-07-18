/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.InformexHisto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class InformexHistoFacade extends AbstractFacade<InformexHisto> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public InformexHistoFacade() {
        super(InformexHisto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void nuevo(InformexHisto infH) {
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            sess.save(infH);

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();

    }

    private String queryAll(boolean isCount, String attrOrder, String desAsc) {

        String select = "SELECT obj ";
        String orderBy = "  ORDER BY obj." + attrOrder + " " + desAsc;

        if (isCount) {
            select = "SELECT COUNT(obj) ";
            orderBy = "";
        }

        String hql = select
                + " FROM InformexHisto obj"
                + orderBy;

        return hql;
    }

    @Override
    public long countAll() {
        String hql = queryAll(true, "", "");
        return numFromHQL(hql, new Long(0)).intValue();
    }

    public List<InformexHisto> listAll(String attrOrder, String desAsc) {
        String hql = queryAll(false, attrOrder, desAsc);
        return this.findList(hql);
    }

    public List<InformexHisto> listAll(int firstReg, int maxReg, String attrOrder, String desAsc) {
        String hql = queryAll(false, attrOrder, desAsc);
        return this.findList(hql, firstReg, maxReg);
    }

    @Override
    protected String[] attrFullTextCriteria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String[] attrsQueryLight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected InformexHisto parseObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(InformexHisto obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(InformexHisto obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
