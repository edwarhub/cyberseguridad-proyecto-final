/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;

import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.Cadena;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    public static final Integer USUARIO_SYS = -1;
    public static final Integer USUARIO_ADMIN = 0;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idusuario", "username", "idpersona.idpersona", "idpersona.nombres", "idpersona.apellidos", "idpersona.numdocumento"};
        return attrs;
    }

    @Override
    protected Usuario parseObject(Object[] o) {
        Usuario u = new Usuario((Integer) o[0]);
        u.setUsername((String) o[1]);
        u.setIdpersona(new Persona(new Long(o[2].toString())));
        u.getIdpersona().setNombres((String) o[3]);
        u.getIdpersona().setApellidos((String) o[4]);
        u.getIdpersona().setNumdocumento(new BigInteger(o[5].toString()));
        return u;
    }

    public List<Usuario> listFullText(String query, int firstReg, int maxReg) {
        String language = "spanish";

        String sql = "SELECT u.idusuario,u.username,p.idpersona,p.nombres,p.apellidos,p.numdocumento "
                + "   FROM Usuario u, Persona p "
                + "   WHERE to_tsvector(u.username||' '||p.nombres||' '||p.apellidos||' '||p.numdocumento) @@ to_tsquery('" + language + "','" + new Cadena().reemplazaEspacios(query, "&").trim() + "')"
                + "  AND u.idpersona=p.idpersona";

        List<Object[]> lst = findNativeGeneric(sql, firstReg, maxReg);
        List<Usuario> lstU = new ArrayList<>();
        for (Object[] o : lst) {
            Usuario u = parseObject(o);
            lstU.add(u);
        }
        return lstU;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"username"};
        return attrs;
    }

    public Usuario buscarByLogin(String login) {
        List<Usuario> l = findList("SELECT u FROM Usuario u WHERE u.username='" + login + "'");
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void create(Usuario obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Usuario obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void cambiarPass(Integer idusuario, String pass1, String pass2, StringBuilder err) {
        if (!pass1.equals(pass2)) {
            err.append("Las contraseñas deben ser iguales!");
            return;
        }
        beginTransaction();
        try {
            SessionImpl sess = getSess();

            String sql = "UPDATE usuario SET contrasena=md5('" + pass1 + "') WHERE idusuario=" + idusuario;
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            err.append(e.toString());
            rollbackTransaction();
            e.printStackTrace();
        }
        endTransaction();
    }

}
