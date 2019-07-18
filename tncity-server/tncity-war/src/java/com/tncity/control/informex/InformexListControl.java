/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex;

import com.tncity.control.general.FacesUtil;
import com.tncity.facade.entity.InformexPermisoFacade;
import com.tncity.informex.InformexListFacade;
import com.tncity.informex.InformexListXml;
import com.tncity.informex.InformexReg;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class InformexListControl {

    @EJB
    InformexListFacade informexListFacade;
    @EJB
    InformexPermisoFacade informexPermisoFacade;

    FacesUtil facesUtil = FacesUtil.currentInstance();

    InformexListXml informexList = new InformexListXml();
    InformexReg informexReg = new InformexReg();

    boolean successful = false;

    public void recuperaInformexList() {
        InformexListXml infLst = informexListFacade.getInformexBuild();

        //Permisos
        HashMap<Integer, Boolean> mapPermisos = informexPermisoFacade.mapPermisos(facesUtil.getCurrentUser().getIdpersona().getIdpersona(), null);
        boolean isAdmin = facesUtil.isCurrentUserAdmin();
        for (InformexReg inf : infLst.getLsfInformex()) {
            if (isAdmin || mapPermisos.get(inf.getId()) != null) {
                inf.setIsActivo(true);
            } else {
                inf.setIsActivo(false);
            }
        }

        informexList = new InformexListXml();

        //Rendered según implementación
        for (InformexReg inf : infLst.getLsfInformex()) {
            informexList.getLsfInformex().add(inf);
        }
    }

    public boolean isInformexAutorizado(Integer idinforme) {
        HashMap<Integer, Boolean> mapPermisos = informexPermisoFacade.mapPermisos(facesUtil.getCurrentUser().getIdpersona().getIdpersona(), idinforme);
        if (facesUtil.isCurrentUserAdmin() || mapPermisos.get(idinforme) != null) {
            return true;
        } else {
            return false;
        }
    }

    public String validaInformexAutorizado() {
        Integer idinforme = facesUtil.getFacesParamInteger("idinforme_");
        if (isInformexAutorizado(idinforme)) {
            return null;
        } else {
            String redir = facesUtil.getProtocolHostPortContext() + "/app-admin/access.xhtml";
            facesUtil.cerrarSesionAdminApp();
            facesUtil.msgError("ALERTA: ", "Acceso no autorizado a un informe !");
            return "<script type='text/javascript'>document.location.href='" + redir + "';</script>";
        }

    }

    public void recuperaInforme() {
        Integer idinforma = facesUtil.getFacesParamInteger("idinforme_");
        informexReg = informexListFacade.findInformexByIf(idinforma);
    }

    public String urlFormString(String idinf) {
        return urlForm(new Integer(idinf));
    }

    public String urlForm(Integer idinf) {
        return facesUtil.getProtocolHostPortContext() + "/app-admin/informex/form/infor-list/informe" + idinf + "/index.xhtml?idinforme_=" + idinf;
    }

    public InformexListXml getInformexList() {
        return informexList;
    }

    public void setInformexList(InformexListXml informexList) {
        this.informexList = informexList;
    }

    public InformexReg getInformexReg() {
        return informexReg;
    }

    public void setInformexReg(InformexReg informexReg) {
        this.informexReg = informexReg;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}
