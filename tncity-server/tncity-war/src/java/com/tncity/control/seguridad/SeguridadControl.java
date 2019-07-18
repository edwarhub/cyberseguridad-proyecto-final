package com.tncity.control.seguridad;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.GeneralConfig;
import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.security.SecurityFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Edwar Rojas - edwar.red@gmail.com
 */
@ManagedBean(name = "seguridadControl")
@RequestScoped
public class SeguridadControl {

    @EJB
    SecurityFacade securityFacade;
    @EJB
    ParamFacade paramFacade;
    FacesUtil facesUtil = FacesUtil.currentInstance();

    private boolean isPermiso(int idFunc) {
        Usuario us = facesUtil.getCurrentUser();
        if (us != null && us.getIdusuario() != null) {
            return securityFacade.isFuncAutorizada(us.getIdusuario(), idFunc);
        }
        return false;
    }

    private String getValidaPermiso(int idFunc) {
        if (isPermiso(idFunc)) {
            return null;
        } else {
            return noAccess();
        }

    }

    private String noAccess() {
        facesUtil.cerrarSesionAdminApp();
        return "<script tyle='text/javascript'>document.location.href='" + facesUtil.getProtocolHostPortContext() + "/app-admin/access.xhtml';</script>";
    }

    public boolean isPermisoAdmin() {
        if (isPermisoAdminAdmin()) {
            return true;
        }
        Usuario us = facesUtil.getCurrentUser();
        if (us != null && us.getIdusuario() != null) {
            GeneralConfig gc = paramFacade.getParamFromCache(GeneralConfig.class);
            if (gc != null && gc.getIdusuarioAdministrador() != null) {
                if (gc.getIdusuarioAdministrador().equals(us.getIdusuario())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPermisoAdminAdmin() {
        Usuario us = facesUtil.getCurrentUser();
        if (us != null && us.getIdusuario() != null) {
            if (us.getIdusuario().equals(UsuarioFacade.USUARIO_ADMIN)) {
                return true;
            }
        }
        return false;
    }

    public String getValidaPermisoAdmin() {
        if (isPermisoAdmin()) {
            return null;
        } else {
            return noAccess();
        }
    }

    public String getValidaPermisoAdminAdmin() {
        if (isPermisoAdminAdmin()) {
            return null;
        } else {
            return noAccess();
        }
    }

    //PERMISOS 
        public boolean isPermiso_8070(){return isPermiso(8070);}public String getValidaPermiso_8070(){return getValidaPermiso(8070);}
        public boolean isPermiso_8071(){return isPermiso(8071);}public String getValidaPermiso_8071(){return getValidaPermiso(8071);}
        public boolean isPermiso_8072(){return isPermiso(8072);}public String getValidaPermiso_8072(){return getValidaPermiso(8072);}
        public boolean isPermiso_8073(){return isPermiso(8073);}public String getValidaPermiso_8073(){return getValidaPermiso(8073);}
        public boolean isPermiso_8074(){return isPermiso(8074);}public String getValidaPermiso_8074(){return getValidaPermiso(8074);}
        public boolean isPermiso_8075(){return isPermiso(8075);}public String getValidaPermiso_8075(){return getValidaPermiso(8075);}
        public boolean isPermiso_8076(){return isPermiso(8076);}public String getValidaPermiso_8076(){return getValidaPermiso(8076);}
        public boolean isPermiso_8077(){return isPermiso(8077);}public String getValidaPermiso_8077(){return getValidaPermiso(8077);}
        public boolean isPermiso_8078(){return isPermiso(8078);}public String getValidaPermiso_8078(){return getValidaPermiso(8078);}
        public boolean isPermiso_8079(){return isPermiso(8079);}public String getValidaPermiso_8079(){return getValidaPermiso(8079);}
        public boolean isPermiso_8080(){return isPermiso(8080);}public String getValidaPermiso_8080(){return getValidaPermiso(8080);}
        public boolean isPermiso_8081(){return isPermiso(8081);}public String getValidaPermiso_8081(){return getValidaPermiso(8081);}
        public boolean isPermiso_8082(){return isPermiso(8082);}public String getValidaPermiso_8082(){return getValidaPermiso(8082);}
        public boolean isPermiso_8083(){return isPermiso(8083);}public String getValidaPermiso_8083(){return getValidaPermiso(8083);}
        public boolean isPermiso_8084(){return isPermiso(8084);}public String getValidaPermiso_8084(){return getValidaPermiso(8084);}
        public boolean isPermiso_8085(){return isPermiso(8085);}public String getValidaPermiso_8085(){return getValidaPermiso(8085);}
        public boolean isPermiso_8086(){return isPermiso(8086);}public String getValidaPermiso_8086(){return getValidaPermiso(8086);}
        public boolean isPermiso_8087(){return isPermiso(8087);}public String getValidaPermiso_8087(){return getValidaPermiso(8087);}
        public boolean isPermiso_8088(){return isPermiso(8088);}public String getValidaPermiso_8088(){return getValidaPermiso(8088);}
        public boolean isPermiso_8089(){return isPermiso(8089);}public String getValidaPermiso_8089(){return getValidaPermiso(8089);}
        public boolean isPermiso_8090(){return isPermiso(8090);}public String getValidaPermiso_8090(){return getValidaPermiso(8090);}
        public boolean isPermiso_8091(){return isPermiso(8091);}public String getValidaPermiso_8091(){return getValidaPermiso(8091);}
        public boolean isPermiso_8092(){return isPermiso(8092);}public String getValidaPermiso_8092(){return getValidaPermiso(8092);}
        public boolean isPermiso_8093(){return isPermiso(8093);}public String getValidaPermiso_8093(){return getValidaPermiso(8093);}
        public boolean isPermiso_8101(){return isPermiso(8101);}public String getValidaPermiso_8101(){return getValidaPermiso(8101);}
        public boolean isPermiso_8102(){return isPermiso(8102);}public String getValidaPermiso_8102(){return getValidaPermiso(8102);}
        public boolean isPermiso_8103(){return isPermiso(8103);}public String getValidaPermiso_8103(){return getValidaPermiso(8103);}
        public boolean isPermiso_8104(){return isPermiso(8104);}public String getValidaPermiso_8104(){return getValidaPermiso(8104);}
        public boolean isPermiso_8105(){return isPermiso(8105);}public String getValidaPermiso_8105(){return getValidaPermiso(8105);}
        public boolean isPermiso_8106(){return isPermiso(8106);}public String getValidaPermiso_8106(){return getValidaPermiso(8106);}
        public boolean isPermiso_8107(){return isPermiso(8107);}public String getValidaPermiso_8107(){return getValidaPermiso(8107);}
        public boolean isPermiso_8108(){return isPermiso(8108);}public String getValidaPermiso_8108(){return getValidaPermiso(8108);}
        public boolean isPermiso_8109(){return isPermiso(8109);}public String getValidaPermiso_8109(){return getValidaPermiso(8109);}
        public boolean isPermiso_8110(){return isPermiso(8110);}public String getValidaPermiso_8110(){return getValidaPermiso(8110);}
        public boolean isPermiso_8111(){return isPermiso(8111);}public String getValidaPermiso_8111(){return getValidaPermiso(8111);}
        public boolean isPermiso_8112(){return isPermiso(8112);}public String getValidaPermiso_8112(){return getValidaPermiso(8112);}
        public boolean isPermiso_8113(){return isPermiso(8113);}public String getValidaPermiso_8113(){return getValidaPermiso(8113);}
        public boolean isPermiso_8114(){return isPermiso(8114);}public String getValidaPermiso_8114(){return getValidaPermiso(8114);}
        public boolean isPermiso_8115(){return isPermiso(8115);}public String getValidaPermiso_8115(){return getValidaPermiso(8115);}
        public boolean isPermiso_8116(){return isPermiso(8116);}public String getValidaPermiso_8116(){return getValidaPermiso(8116);}
        public boolean isPermiso_8117(){return isPermiso(8117);}public String getValidaPermiso_8117(){return getValidaPermiso(8117);}
        public boolean isPermiso_8118(){return isPermiso(8118);}public String getValidaPermiso_8118(){return getValidaPermiso(8118);}
        public boolean isPermiso_8119(){return isPermiso(8119);}public String getValidaPermiso_8119(){return getValidaPermiso(8119);}
        public boolean isPermiso_8120(){return isPermiso(8120);}public String getValidaPermiso_8120(){return getValidaPermiso(8120);}
        public boolean isPermiso_8121(){return isPermiso(8121);}public String getValidaPermiso_8121(){return getValidaPermiso(8121);}
        public boolean isPermiso_8122(){return isPermiso(8122);}public String getValidaPermiso_8122(){return getValidaPermiso(8122);}
        public boolean isPermiso_8123(){return isPermiso(8123);}public String getValidaPermiso_8123(){return getValidaPermiso(8123);}
        public boolean isPermiso_8124(){return isPermiso(8124);}public String getValidaPermiso_8124(){return getValidaPermiso(8124);}
        public boolean isPermiso_8125(){return isPermiso(8125);}public String getValidaPermiso_8125(){return getValidaPermiso(8125);}
        public boolean isPermiso_8126(){return isPermiso(8126);}public String getValidaPermiso_8126(){return getValidaPermiso(8126);}
        public boolean isPermiso_8127(){return isPermiso(8127);}public String getValidaPermiso_8127(){return getValidaPermiso(8127);}
        public boolean isPermiso_8128(){return isPermiso(8128);}public String getValidaPermiso_8128(){return getValidaPermiso(8128);}
        public boolean isPermiso_8129(){return isPermiso(8129);}public String getValidaPermiso_8129(){return getValidaPermiso(8129);}
        public boolean isPermiso_8130(){return isPermiso(8130);}public String getValidaPermiso_8130(){return getValidaPermiso(8130);}
        public boolean isPermiso_8131(){return isPermiso(8131);}public String getValidaPermiso_8131(){return getValidaPermiso(8131);}
        public boolean isPermiso_8132(){return isPermiso(8132);}public String getValidaPermiso_8132(){return getValidaPermiso(8132);}
        public boolean isPermiso_8133(){return isPermiso(8133);}public String getValidaPermiso_8133(){return getValidaPermiso(8133);}
        public boolean isPermiso_8134(){return isPermiso(8134);}public String getValidaPermiso_8134(){return getValidaPermiso(8134);}
        public boolean isPermiso_8135(){return isPermiso(8135);}public String getValidaPermiso_8135(){return getValidaPermiso(8135);}
        public boolean isPermiso_8136(){return isPermiso(8136);}public String getValidaPermiso_8136(){return getValidaPermiso(8136);}
        public boolean isPermiso_8137(){return isPermiso(8137);}public String getValidaPermiso_8137(){return getValidaPermiso(8137);}
        public boolean isPermiso_8138(){return isPermiso(8138);}public String getValidaPermiso_8138(){return getValidaPermiso(8138);}
        public boolean isPermiso_8139(){return isPermiso(8139);}public String getValidaPermiso_8139(){return getValidaPermiso(8139);}
    }

                                    