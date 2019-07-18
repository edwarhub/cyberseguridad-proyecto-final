/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.facade.entity;


import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.InformexPermiso;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.impl.SessionImpl;

@Stateless
public class InformexPermisoFacade extends AbstractFacade<InformexPermiso> {

    public static final String TIPO_USUARIO = "USUARIO";
    public static final String TIPO_PERFIL = "PERFIL";

    @PersistenceUnit
    private EntityManagerFactory emf;

    public InformexPermisoFacade() {
        super(InformexPermiso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param idpersona
     * @param idinforme
     * @return MAP < KEY: idinforme, Value: isAutorize
     */
    public HashMap<Integer, Boolean> mapPermisos(Long idpersona, Integer idinforme) {
        HashMap<Integer, Boolean> map = new HashMap<>();

        String andInforme = "";
        if (idinforme != null) {
            andInforme = " AND ip.idinforme=" + idinforme;
        }

        String hql = "SELECT ip.idpermiso,ip.idinforme,ip.tipo"
                + "   FROM InformexPermiso ip "
                + "  WHERE (ip.idpersonaPermiso.idpersona=" + idpersona
                + "     OR ip.idperfil.idperfil IN (SELECT p.idperfil.idperfil"
                + "                                   FROM PerfilHasUsuario p "
                + "                                  WHERE p.idusuario.idpersona.idpersona=" + idpersona + "))" + andInforme
                + " ORDER BY ip.idpermiso ASC ";
        List<Object[]> lst = findGeneric(hql);
        for (Object[] o : lst) {
            InformexPermiso ip = new InformexPermiso((Long) o[0]);
            ip.setIdinforme((Integer) o[1]);
            ip.setTipo((String) o[2]);

            map.put(ip.getIdinforme(), true);
        }

        return map;
    }

    public List<Persona> listPersonaAutorizadaByInforme(Long idinforme) {

        List<Persona> lstP = new ArrayList<>();

        String hql = " SELECT ip.idinforme,"//0
                + "           ip.idpersonaPermiso.idpersona,"//1
                + "           ip.idpersonaPermiso.nombres,"//2
                + "           ip.idpersonaPermiso.apellidos,"//3
                + "           ip.idpersonaPermiso.numdocumento "//4
                + "    FROM InformexPermiso ip "
                + "    WHERE ip.idinforme = " + idinforme
                + "    ORDER BY ip.idpersonaPermiso.apellidos ASC";

        List<Object[]> lst = this.findGeneric(hql);

        for (Object[] obj : lst) {
            Persona p = new Persona((Long) obj[1]);

            p.setNombres((String) obj[2]);
            p.setApellidos((String) obj[3]);
            p.setNumdocumento(new BigInteger(obj[4].toString()));

            lstP.add(p);
        }

        return lstP;

    }

    public List<Perfil> listPerfilAutorizadaByInforme(Long idinforme) {

        List<Perfil> lstP = new ArrayList<>();

        String hql = " SELECT ip.idinforme,"//0
                + "           ip.idperfil.idperfil,"//1
                + "           ip.idperfil.nombre "//2
                + "    FROM InformexPermiso ip "
                + "    WHERE ip.idinforme = " + idinforme
                + "    ORDER BY ip.idperfil.nombre ASC";

        List<Object[]> lst = this.findGeneric(hql);

        for (Object[] obj : lst) {
            Perfil p = new Perfil((Integer) obj[1]);

            p.setNombre((String) obj[2]);

            lstP.add(p);
        }

        return lstP;

    }

    public void agregarUsuario(Integer idInforme, Integer idUsuario, StringBuilder error) {

        beginTransaction();
        SessionImpl sess = getSess();

        try {

            String sql = "";

            Usuario u = (Usuario) sess.get(Usuario.class, idUsuario);
            Persona p = u.getIdpersona();

            sql = "     SELECT ip.* "
                    + " FROM informex_permiso ip "
                    + " WHERE ip.idpersona_permiso = " + p.getIdpersona()
                    + "  AND ip.idinforme = " + idInforme;

            List l = sess.createSQLQuery(sql).list();

            if (l != null && !l.isEmpty()) {
                error.append(p.getNombres()).append(" ")
                        .append(p.getApellidos()).append(" ya esta autorizado para el informe");
                return;
            }

            InformexPermiso ip = new InformexPermiso();

            ip.setIdpersonaPermiso(p);
            ip.setTipo(TIPO_USUARIO);
            ip.setIdinforme(idInforme);

            sess.save(ip);

        } catch (Exception e) {
            error.append("FALLA: agregando persona al informe: ").append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }

        endTransaction();

    }

    public void agregarPerfil(Integer idInforme, Integer idPerfil, StringBuilder error) {

        beginTransaction();
        SessionImpl sess = getSess();

        try {

            String sql = "";

            Perfil p = (Perfil) sess.get(Perfil.class, idPerfil);

            sql = "     SELECT ip.* "
                    + " FROM informex_permiso ip "
                    + " WHERE ip.idperfil = " + p.getIdperfil()
                    + "  AND ip.idinforme = " + idInforme;

            List l = sess.createSQLQuery(sql).list();

            if (l != null && !l.isEmpty()) {
                error.append("El perfil ").append(p.getNombre()).append(" ya esta autorizado para el informe");
                return;
            }

            InformexPermiso ip = new InformexPermiso();

            ip.setIdperfil(p);
            ip.setTipo(TIPO_PERFIL);
            ip.setIdinforme(idInforme);

            sess.save(ip);

        } catch (Exception e) {
            error.append("FALLA: agregando perfil al informe: ").append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }

        endTransaction();

    }

    public void eliminarUsuario(Integer idInforme, Integer idPersona, StringBuilder error) {

        beginTransaction();
        SessionImpl sess = getSess();

        try {

            String sql = " DELETE FROM informex_permiso ip "
                    + " WHERE ip.idpersona_permiso = " + idPersona
                    + "  AND ip.idinforme = " + idInforme;

            sess.createSQLQuery(sql).executeUpdate();

        } catch (Exception e) {
            error.append("FALLA: eliminando usuario del informe: ").append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }

        endTransaction();

    }

    public void eliminarPerfil(Integer idInforme, Integer idPerfil, StringBuilder error) {

        beginTransaction();
        SessionImpl sess = getSess();

        try {

            String sql = " DELETE FROM informex_permiso ip "
                    + " WHERE ip.idperfil = " + idPerfil
                    + "  AND ip.idinforme = " + idInforme;

            sess.createSQLQuery(sql).executeUpdate();

        } catch (Exception e) {
            error.append("FALLA: eliminando perfil del informe: ").append(e.toString());
            e.printStackTrace();
            rollbackTransaction();
        }

        endTransaction();

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
    protected InformexPermiso parseObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(InformexPermiso obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(InformexPermiso obj, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object valueId, StringBuilder err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
