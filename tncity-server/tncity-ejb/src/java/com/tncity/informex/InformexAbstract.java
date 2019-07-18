/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex;


import com.tncity.facade.entity.InformexHistoFacade;
import com.tncity.jpa.pojo.InformexHisto;
import com.tncity.jpa.pojo.Persona;
import com.tncity.util.BeanUtil;
import com.tncity.util.Tiempo;
import com.tncity.util.dynreport.ModelDynreport;
import java.util.Date;
import java.util.List;

/**
 *
 * @author edwar.red@gmail.com
 */
public abstract class InformexAbstract {

    final private InformexHistoFacade informexHistoFacade = BeanUtil.lookupFacadeBean(InformexHistoFacade.class);

    private String state = "";
    private int progressInfo = 0;
    private boolean isEndInfo = false;
    private boolean isCancel = false;
    private ModelDynreport model;
    private Date startAt;
    private Date endAt;
    private String title;
    private Integer idinfo;
    private Persona persona;
    private String ip;
    private String navegador;
    private String sysOperativo;
    private String app;
    private StringBuilder log = new StringBuilder();

    protected abstract long totalReg();

    protected abstract int numRegByPage();

    protected abstract ModelDynreport buildModel();

    protected abstract List<Object[]> buildDataPage(long firstReg);

    protected abstract void postProcess(ModelDynreport modelPostProcess);

    protected void startInfo() {
        isEndInfo = false;
        long totalReg = totalReg();
        model = buildModel();
        long regByPage = numRegByPage();
        int i = 0;
        state = "Iniciando reporte...";
        addLog("Informe " + model.getId() + ": " + model.getTitle() + " generado INICIADO ");
        progressInfo = 0;
        startAt = new Date();

        if (regByPage <= 0) {
            regByPage = totalReg;
        }

        int tasa = 0;
        while (i < totalReg && !isCancel) {
            List<Object[]> data = buildDataPage(i);
            addDataModel(model, data);
            i += regByPage;
            tasa = (int) ((i * 1.0 / totalReg * 1.0) * 100.0);
            if (tasa >= 100) {
                progressInfo = 99;
            } else {
                progressInfo = tasa;
            }
            state = "Generando informe ... " + i + " de " + totalReg;
        }

        if (isCancel) {
            state = "Informe Cancelado!";
        } else {
            postProcess(model);
            state = "Datos Calculados!";
        }

        addLog(state);
        isEndInfo = true;
        progressInfo = 100;
        endAt = new Date();
        saveHisto();

    }

    protected void cancelInfo() {
        isCancel = true;
    }

    private void addDataModel(ModelDynreport model, List<Object[]> data) {
        for (Object[] pojo : data) {
            model.addData(pojo);
        }

    }

    public ModelDynreport getModel() {
        return model;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getProgressInfo() {
        return progressInfo;
    }

    public boolean isIsEndInfo() {
        return isEndInfo;
    }

    public void setIsEndInfo(boolean isEndInfo) {
        this.isEndInfo = isEndInfo;
    }

    public boolean isIsCancel() {
        return isCancel;
    }

    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public Date getStartAt() {
        return startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIdinfo() {
        return idinfo;
    }

    public void setIdinfo(Integer idinfo) {
        this.idinfo = idinfo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public String getSysOperativo() {
        return sysOperativo;
    }

    public void setSysOperativo(String sysOperativo) {
        this.sysOperativo = sysOperativo;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getLog() {
        return log.toString();
    }

    private void saveHisto() {
        InformexHisto h = new InformexHisto();
        h.setIdpersona(persona);
        h.setIdinforme(idinfo);
        h.setTitulo(title);
        h.setIp(ip);
        h.setNavegador(navegador);
        h.setSysOperativo(sysOperativo);
        h.setApp(app);
        h.setIsFinalizado(isEndInfo);
        h.setIsCancelado(isCancel);
        h.setFechaIni(startAt);
        h.setFechaFin(endAt);
        h.setLog(getLog());

        informexHistoFacade.nuevo(h);
    }

    public void addLog(String msg) {
        log.append(Tiempo.getFecha2("yyyy-MMM-dd HH:mm:ss", new Date())).append(" : ").append(msg).append("\n");
    }

}
