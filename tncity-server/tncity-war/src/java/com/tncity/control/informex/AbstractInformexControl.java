/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex;


import com.tncity.control.general.FacesUtil;
import com.tncity.informex.InformexAbstract;
import com.tncity.informex.InformexFacade;
import com.tncity.informex.InformexListFacade;
import com.tncity.informex.InformexReg;
import com.tncity.jpa.pojo.Persona;
import com.tncity.util.BeanUtil;

public abstract class AbstractInformexControl {

    InformexFacade informexFacade = BeanUtil.lookupFacadeBean(InformexFacade.class);
    InformexListFacade informeListFacade = BeanUtil.lookupFacadeBean(InformexListFacade.class);

    private boolean successful = false;

    public abstract InformexAbstract buildModel(InformexReg infR, String protocolHostPortPath);

    public abstract boolean validaSuccessful(StringBuilder err);

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void generarInforme(final String key) {
        successful = false;

        StringBuilder err = new StringBuilder();
        if (!validaSuccessful(err)) {
            FacesUtil.currentInstance().msgError("ALERTA: ", err.toString());
            return;
        }

        InformexAbstract inf = informexFacade.getInfo(key);
        if (inf != null && !inf.isIsEndInfo()) {
            FacesUtil.currentInstance().msgError("ALERTA: ", "Solo puede generarse un informe a la vez por usuario, acutalmente se está generando el informe: " + inf.getModel().getId() + ". " + inf.getModel().getTitle());
            return;
        }

        final InformexReg infR = informeListFacade.findInformexByIf(FacesUtil.currentInstance().getFacesParamInteger("idinforme_"));
        final String protocolHostPortPath = FacesUtil.currentInstance().getProtocolHostPortPath();
        final Persona persona = FacesUtil.currentInstance().getCurrentUser().getIdpersona();
        final String navegador = FacesUtil.currentInstance().getUserAgent().getBrowser().getName();
        final String sysOperativo = FacesUtil.currentInstance().getUserAgent().getOperatingSystem().getName();
        final String ip = FacesUtil.currentInstance().getHostClient();

        Thread h = new Thread() {
            @Override
            public void run() {
                InformexAbstract infB = buildModel(infR, protocolHostPortPath);
                infB.setTitle(infR.getTitle());
                infB.setIdinfo(infR.getId());
                infB.setPersona(persona);
                infB.setNavegador(navegador);
                infB.setIp(ip);
                infB.setSysOperativo(sysOperativo);
                infB.setApp("APP. ADMIN WEB");
                informexFacade.startInfo(key, infB, new StringBuilder());
            }
        };
        h.start();

        /*if (!err.toString().isEmpty()) {
            facesUtil.msgError("ALERTA: ", err.toString());
        }*/
        successful = true;
    }
}
