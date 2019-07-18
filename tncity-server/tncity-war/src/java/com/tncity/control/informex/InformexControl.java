/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.informex;

import com.tncity.cache.GeneralCache;
import com.tncity.control.general.FacesUtil;
import com.tncity.informex.InformexAbstract;
import com.tncity.informex.InformexFacade;
import com.tncity.informex.InformexReg;
import com.tncity.properties.Propiedad;
import com.tncity.servlet.ServletGeneralDownload;
import com.tncity.util.EncryptionUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class InformexControl implements Serializable {

    String msgErr = new String();
    String key = "";
    String html = "";
    String link = "";
    boolean successful = false;
    boolean paginar = true;
    InformexReg informexReg;

    @EJB
    InformexFacade informexFacade;

    FacesUtil facesUtil = FacesUtil.currentInstance();
    List<InformexAbstract> lstInfAbs = new ArrayList<>();
    InformexAbstract infAbs;

    public InformexAbstract informexByKey(String key) {
        return informexFacade.getInfo(key);
    }

    public InformexAbstract informexByUser() {
        return informexFacade.getInfo(facesUtil.getInformexKeyUser());
    }

    public void builderInformex(final InformexAbstract inf) {

        Thread h = new Thread() {
            @Override
            public void run() {
                StringBuilder err = new StringBuilder();
                informexFacade.startInfo(key, inf, err);
                msgErr = err.toString();
            }
        };
        h.start();

    }

    public void cancelInfo(String keyI) {
        informexFacade.cancelInfo(keyI);
        successful = true;
        facesUtil.msgOk("", "Informe Cancelado!");
    }

    public void buildHtml(String key, String pathTemplate) {
        StringBuilder err = new StringBuilder();
        successful = false;
        if (pathTemplate == null || pathTemplate.trim().isEmpty()) {
            html = informexFacade.buildHtml(key, err, facesUtil.getProtocolHostPortPath());
        } else {
            html = informexFacade.buildHtml(key, new File(pathTemplate), err, facesUtil.getProtocolHostPortPath());
        }

        if (!err.toString().isEmpty()) {
            msgErr = err.toString();
            successful = false;
        } else {
            successful = true;
        }
    }

    public void buildPdf(String key, String pathTemplate) {
        build(key, pathTemplate, "PDF", "informe.pdf");
    }

    public void buildXlsx(String key, String pathTemplate) {
        build(key, pathTemplate, "XLSX", "informe.xlsx");
    }

    private void build(String key, String pathTemplate, String type, String filenameOut) {
        StringBuilder err = new StringBuilder();
        successful = false;
        ByteArrayOutputStream out = null;
        if (pathTemplate == null || pathTemplate.trim().isEmpty()) {
            if (type.equals("PDF")) {
                out = informexFacade.buildPdfStream(key, err);
            }
            if (type.equals("XLSX")) {
                out = informexFacade.buildXlsxStream(key, err);
            }
        } else {
            if (type.equals("PDF")) {
                out = informexFacade.buildPdfStream(key, new File(pathTemplate), err);
            }
            if (type.equals("XLSX")) {
                out = informexFacade.buildXlsxStream(key, new File(pathTemplate), err);
            }
        }

        informexFacade.saveOutputStream(key, out);

        //TmpKey
        String tmpkey = EncryptionUtil.encryptAES(key + new Date().getTime(), Propiedad.getCurrentInstance().getEncryptAesKey());
        GeneralCache.addTmpKey(tmpkey);

        if (!err.toString().isEmpty()) {
            msgErr = err.toString();
            facesUtil.msgError("ALERTA: ", msgErr);
            successful = false;
        } else {
            facesUtil.msgOk("", "Archivo Creado !");
            successful = true;
        }

        link = facesUtil.getProtocolHostPortPath() + "/download?type=" + ServletGeneralDownload.TYPE_INFO + "&key=" + key + "&filename=" + filenameOut + "&tmpkey=" + tmpkey;
    }

    public void initPaginar(String key) {
        paginar = informexFacade.getInfo(key).getModel().getIsIgnorePagination();
    }

    public void paginarToogle(String key, String pathTemplate) {
        paginar = !paginar;
        informexFacade.setPaginar(paginar, key);
        buildHtml(key, pathTemplate);
    }

    public InformexReg getInformexReg() {
        return informexReg;
    }

    public void setInformexReg(InformexReg informexReg) {
        this.informexReg = informexReg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMsgErr() {
        return msgErr;
    }

    public void setMsgErr(String msgErr) {
        this.msgErr = msgErr;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isPaginar() {
        return paginar;
    }

    public void setPaginar(boolean paginar) {
        this.paginar = paginar;
    }

    public List<InformexAbstract> getLstInfAbs() {
        return lstInfAbs;
    }

    public void setLstInfAbs(List<InformexAbstract> lstInfAbs) {
        this.lstInfAbs = lstInfAbs;
    }

    public void recuperaAllUltimosIformesGenerados() {
        lstInfAbs = informexFacade.listLastInfo();
    }

    public void recuperaMisUltimosIformesGenerados() {
        lstInfAbs = new ArrayList<>();
        infAbs = informexFacade.getInfo(facesUtil.getInformexKeyUser());
        if (infAbs != null) {
            lstInfAbs.add(infAbs);
        }
    }

    public String pathTemplate() {
        if (informexReg.getType() == null || informexReg.getType().equals(InformexReg.TYPE_LIST)) {
            return InformexFacade.PLANTILLA_LIST_GENERIC.getPath();
        }
        if (informexReg.getType().equals(InformexReg.TYPE_STATISTIC)) {
            return InformexFacade.PLANTILLA_ESTADISTICA.getPath();
        }
        if (informexReg.getType().equals(InformexReg.TYPE_FREE)) {
            return informexReg.getPathTemplate();
        }
        return "";
    }

}
