/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex.impl;

import com.tncity.config.ParamFacade;
import com.tncity.informex.InformexAbstract;
import com.tncity.informex.InformexReg;
import com.tncity.util.BeanUtil;
import com.tncity.util.Tiempo;
import com.tncity.util.dynreport.ColumnDynreport;
import com.tncity.util.dynreport.ModelDynreport;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InformexDs1 extends InformexAbstract {

    ParamFacade paramFacade = BeanUtil.lookupFacadeBean(ParamFacade.class);
   
    private Date desde;
    private Date hasta;
    private String attrOrd;
    private String ascDesc;
    private InformexReg infL;
    private String protocolHostPortPath;

    public InformexDs1(Date desde, Date hasta, String attrOrd, String ascDesc, InformexReg infL, String protocolHostPortPath) {
        this.desde = desde;
        this.hasta = hasta;
        this.attrOrd = attrOrd;
        this.ascDesc = ascDesc;
        this.infL = infL;
        this.protocolHostPortPath = protocolHostPortPath;
    }

    @Override
    protected long totalReg() {
        return 0;
    }

    @Override
    protected int numRegByPage() {
        return 40;
    }

    @Override
    protected ModelDynreport buildModel() {
        ModelDynreport m = new ModelDynreport();
        m.setId(infL.getId());

        m.setTitle(infL.getTitle());
        m.centerTitle();

        m.centerTable();

        m.setIsIgnorePagination(false);
        m.setPageWidthAutoSize(true);
        m.setShowColumnsHeaders(true);
        m.addParam("path_logo", paramFacade.getUrlImgLogoFromCache(protocolHostPortPath));
        m.addParam("impl_name", "tncity");
        m.addParam("params_text0", "Desde: ");
        m.addParam("params_text1", Tiempo.getFecha2("dd-MMM-yyyy", desde));
        m.addParam("params_text2", "Hasta: ");
        m.addParam("params_text3", Tiempo.getFecha2("dd-MMM-yyyy", hasta));
        m.addParam("params_text4", "");
        m.addParam("params_text5", "");
        m.addParam("params_text6", "");
        m.addParam("params_text7", "");

        m.setObservaciones("");
        m.locateObservaciones(10, 50);

        ColumnDynreport c = new ColumnDynreport("Fecha Apuesta", ColumnDynreport.DATATYPE_DATE, 80);
        c.setPattern("dd-MMM-yyyy HH:mm");
        m.addColumn(c);

        c = new ColumnDynreport("Cliente", ColumnDynreport.DATATYPE_TEXT, 200);
        m.addColumn(c);

        c = new ColumnDynreport("No. Documento", ColumnDynreport.DATATYPE_NUMERIC, 80);
        m.addColumn(c);

        c = new ColumnDynreport("Tel.", ColumnDynreport.DATATYPE_NUMERIC, 80);
        m.addColumn(c);

        c = new ColumnDynreport("E-mail", ColumnDynreport.DATATYPE_TEXT, 200);
        m.addColumn(c);

        c = new ColumnDynreport("Casino", ColumnDynreport.DATATYPE_TEXT, 150);
        m.addColumn(c);

        c = new ColumnDynreport("Tipo", ColumnDynreport.DATATYPE_TEXT, 80);
        m.addColumn(c);

        c = new ColumnDynreport("Registró", ColumnDynreport.DATATYPE_TEXT, 200);
        m.addColumn(c);

        c = new ColumnDynreport("Id. Apuesta", ColumnDynreport.DATATYPE_NUMERIC, 80);
        m.addColumn(c);

        c = new ColumnDynreport("Id. progresivo", ColumnDynreport.DATATYPE_NUMERIC, 80);
        m.addColumn(c);

        c = new ColumnDynreport("Progresivo", ColumnDynreport.DATATYPE_TEXT, 100);
        m.addColumn(c);

        c = new ColumnDynreport("Premio", ColumnDynreport.DATATYPE_MONEY, 80);
        c.setTotalAutoSumEnabled(true);
        m.addColumn(c);

        return m;
    }

    @Override
    protected List<Object[]> buildDataPage(long firstReg) {
        List<Object[]> data = new ArrayList<>();

        
        return data;
    }

    @Override
    protected void postProcess(ModelDynreport modelPostProcess) {

    }

}
