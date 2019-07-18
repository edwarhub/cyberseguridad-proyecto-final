/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PerfilFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.jpa.pojo.PerfilHasUsuario;
import com.tncity.jpa.pojo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "perfilControl")
@RequestScoped
public class PerfilControl extends AbstractControl<Perfil> {

    @EJB
    PerfilFacade facade;

    List<PerfilHasUsuario> lstUser = new ArrayList<>();
    Usuario objUs = new Usuario();

    public PerfilControl() {
        super(Perfil.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Perfil obj) {
        return obj.getNombre();
    }

    public List<PerfilHasUsuario> getLstUser() {
        return lstUser;
    }

    public void setLstUser(List<PerfilHasUsuario> lstUser) {
        this.lstUser = lstUser;
    }

    public Usuario getObjUs() {
        return objUs;
    }

    public void setObjUs(Usuario objUs) {
        this.objUs = objUs;
    }

    public void nuevo() {
        createDefault();
    }

    public void editar() {
        editDefault();
    }

    public void recuperaUsuarios() {
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");
        lstUser = facade.listUsuariosLight(idperfil);
    }

    public void addUsuario() {
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");
        StringBuilder err = new StringBuilder();

    }

    public List<SelectItem> getAllSelectListByPerfil() {
        List<SelectItem> l = new ArrayList<>();
        l.add(new SelectItem(null, "---"));
        List<Perfil> lstC = facade.listAllLightByPerfil("idperfil", "ASC");
        for (Perfil g : lstC) {
            l.add(new SelectItem(g, g.getNombre()));
        }
        return l;
    }

}
