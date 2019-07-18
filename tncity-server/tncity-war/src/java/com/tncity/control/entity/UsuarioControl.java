/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.util.HashUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "usuarioControl")
@RequestScoped
public class UsuarioControl extends AbstractControl<Usuario> {

    @EJB
    UsuarioFacade facade;

    String pass1;
    String pass2;


    public UsuarioControl() {
        super(Usuario.class);
        attrOrd = "username";
        ascDesc = "ASC";
    }


    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Usuario obj) {
        return obj.getIdpersona().getNombres() + " " + obj.getIdpersona().getApellidos() + " - " + obj.getUsername();
    }

    public List<SelectItem> selectId() {
        List<Usuario> lstObjs = facade.listAllLight("idpersona.nombres", "ASC");
        List<SelectItem> lstS = new ArrayList<>();
        lstS.add(new SelectItem(null, "---"));

        for (Usuario obj : lstObjs) {
            SelectItem it = new SelectItem(obj.getIdusuario(), displayObj(obj));
            lstS.add(it);
        }
        return lstS;
    }

    public void recuperaByIdInteger(Integer idusuario) {
        if (idusuario != null) {
            obj = facade.find(idusuario);
        }
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public void nuevo() {
        obj.setUpdatedAt(new Date());
        obj.setIsActivo(true);
        if (!pass1.equals(pass2)) {
            facesUtil.msgError("ALERTA:", "Las contraseñas deben ser iguales!");
            return;
        }
        obj.setUsername(obj.getIdpersona().getNumdocumento().toString());
        obj.setContrasena(HashUtil.md5(pass1));
        createDefault();
    }

    public void editar() {
        obj.setUpdatedAt(new Date());
        editDefault();
    }

    public void cambiarPass() {
        Integer idusuario = facesUtil.getFacesParamInteger("idusuario_");
        StringBuilder err = new StringBuilder();
        facade.cambiarPass(idusuario, pass1, pass2, err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("", "Contraseña Cambiada!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
            successful = false;
        }

    }

    public void cambiarPassCurrentUser() {
        StringBuilder err = new StringBuilder();
        facade.cambiarPass(facesUtil.getCurrentUser().getIdusuario(), pass1, pass2, err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("", "Contraseña Cambiada!");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
            successful = false;
        }
    }

    public List<Usuario> listAutocomplete(String query) {
        return facade.listFullText(query, 0, 20);
    }


}
