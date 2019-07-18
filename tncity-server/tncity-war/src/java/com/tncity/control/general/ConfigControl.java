/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.config.AbstractConfig;
import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.RestConfig;
import com.tncity.config.pojoaux.ApuestaConfig;
import com.tncity.config.pojoaux.GeneralConfig;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ConfigControl {

    @EJB
    ParamFacade paramFacade;

    ApuestaConfig objApuestaConfig = new ApuestaConfig();
    GeneralConfig objGeneralConfig = new GeneralConfig();
    RestConfig objRestConfig=new RestConfig();

    FacesUtil facesUtil = FacesUtil.currentInstance();
    private boolean successful = false;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void guardar(AbstractConfig objConfig) {
        paramFacade.saveParam(objConfig, facesUtil.getCurrentUser());
        successful = true;
        facesUtil.msgOk("", "Cambios Guardados!");
    }

    public AbstractConfig config(AbstractConfig objConfig) {
        return paramFacade.getParamFromCache(objConfig.getClass());
    }

    public ApuestaConfig getObjApuestaConfig() {
        return objApuestaConfig;
    }

    public void setObjApuestaConfig(ApuestaConfig objApuestaConfig) {
        this.objApuestaConfig = objApuestaConfig;
    }

    public GeneralConfig getObjGeneralConfig() {
        return objGeneralConfig;
    }

    public void setObjGeneralConfig(GeneralConfig objGeneralConfig) {
        this.objGeneralConfig = objGeneralConfig;
    }

    public RestConfig getObjRestConfig() {
        return objRestConfig;
    }

    public void setObjRestConfig(RestConfig objRestConfig) {
        this.objRestConfig = objRestConfig;
    }

}
