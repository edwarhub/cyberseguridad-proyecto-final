/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.InformexPermisoFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.InformexPermiso;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.jpa.pojo.Persona;
import com.tncity.jpa.pojo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author desarrollo02
 */
@ManagedBean
@RequestScoped
public class InformexPermisoControl extends AbstractControl<InformexPermiso> {

    @EJB
    InformexPermisoFacade facade;
    InformexPermiso obj = new InformexPermiso();
    //
    List<Persona> lstPersonAu = new ArrayList<>();
    List<Perfil> lstPerfilAu = new ArrayList<>();
    Usuario usSel;
    Perfil perSel;

    public InformexPermisoControl() {
        super(InformexPermiso.class);
        attrOrd = "idpermiso";
        ascDesc = "ASC";
    }

    @Override
    public InformexPermiso getObj() {
        return obj;
    }

    @Override
    public void setObj(Object obj) {
        this.obj = (InformexPermiso) obj;
    }

    public List<Persona> getLstPersonAu() {
        return lstPersonAu;
    }

    public void setLstPersonAu(List<Persona> lstPersonAu) {
        this.lstPersonAu = lstPersonAu;
    }

    public List<Perfil> getLstPerfilAu() {
        return lstPerfilAu;
    }

    public void setLstPerfilAu(List<Perfil> lstPerfilAu) {
        this.lstPerfilAu = lstPerfilAu;
    }

    public Usuario getUsSel() {
        return usSel;
    }

    public void setUsSel(Usuario usSel) {
        this.usSel = usSel;
    }

    public Perfil getPerSel() {
        return perSel;
    }

    public void setPerSel(Perfil perSel) {
        this.perSel = perSel;
    }

    public String getRecuperaAllPersonasAutorizadasObj() {
        Long idinforme = facesUtil.getFacesParamLong("idinforme_");
        lstPersonAu = facade.listPersonaAutorizadaByInforme(idinforme);
        return "";
    }

    public String getRecuperaAllPerfilesAutorizadasObj() {
        Long idinforme = facesUtil.getFacesParamLong("idinforme_");
        lstPerfilAu = facade.listPerfilAutorizadaByInforme(idinforme);
        return "";
    }

    public String agregarUsuario() {
        Integer idinforme = Integer.parseInt(facesUtil.getFacesParamValue("idinforme_"));

        setSuccessful(false);

        try {

            StringBuilder error = new StringBuilder();
            facade.agregarUsuario(idinforme, usSel.getIdusuario(), error);

            if (error.toString().isEmpty()) {
                facesUtil.msgOk("", "¡Usuario agregado éxitosamente!");
                setSuccessful(true);
            } else {
                facesUtil.msgError("IMPORTANTE: ", error.toString());
            }

        } catch (Exception e) {
            facesUtil.msgError("FALLA: ", "Agregando usuario... " + e.toString());
        }

        return null;
    }

    public String agregarPerfil() {
        Integer idinforme = Integer.parseInt(facesUtil.getFacesParamValue("idinforme_"));

        setSuccessful(false);

        try {

            StringBuilder error = new StringBuilder();
            facade.agregarPerfil(idinforme, perSel.getIdperfil(), error);

            if (error.toString().isEmpty()) {
                facesUtil.msgOk("", "¡Perfil agregado éxitosamente!");
                setSuccessful(true);
            } else {
                facesUtil.msgError("IMPORTANTE: ", error.toString());
            }

        } catch (Exception e) {
            facesUtil.msgError("FALLA: ", "Agregando perfil... " + e.toString());
        }

        return null;
    }

    public String eliminarUsuario() {

        Integer idinforme = Integer.parseInt(facesUtil.getFacesParamValue("idinforme_"));
        Integer idpersona = Integer.parseInt(facesUtil.getFacesParamValue("idpersona_"));

        setSuccessful(false);

        try {

            StringBuilder error = new StringBuilder();
            facade.eliminarUsuario(idinforme, idpersona, error);

            if (error.toString().isEmpty()) {
                facesUtil.msgOk("", "Se ha removido el usuario exitosamente.");
                setSuccessful(true);
            } else {
                facesUtil.msgError("IMPORTANTE: ", error.toString());
            }

        } catch (Exception e) {
            facesUtil.msgError("FALLA: ", "removiendo el usuario, " + e.toString());
        }

        return "";
    }

    public String eliminarPerfil() {

        Integer idinforme = Integer.parseInt(facesUtil.getFacesParamValue("idinforme_"));
        Integer idperfil = Integer.parseInt(facesUtil.getFacesParamValue("idperfil_"));

        setSuccessful(false);

        try {

            StringBuilder error = new StringBuilder();
            facade.eliminarPerfil(idinforme, idperfil, error);

            if (error.toString().isEmpty()) {
                facesUtil.msgOk("", "Se ha removido el perfil exitosamente.");
                setSuccessful(true);
            } else {
                facesUtil.msgError("IMPORTANTE: ", error.toString());
            }

        } catch (Exception e) {
            facesUtil.msgError("FALLA: ", "removiendo el perfil, " + e.toString());
        }

        return "";
    }

    @Override
    protected AbstractFacade getFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String displayObj(InformexPermiso obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
