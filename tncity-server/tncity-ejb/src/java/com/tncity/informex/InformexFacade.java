/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex;


import com.tncity.properties.Propiedad;
import com.tncity.util.Cadena;
import com.tncity.util.dynreport.DynreportUtil;
import com.tncity.util.dynreport.ModelDynreport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class InformexFacade {

    public static final File PLANTILLA_LIST_GENERIC = new File(Propiedad.getCurrentInstance().getPathInformes() + "/informex/common/listas/listas.vsl");
    public static final File PLANTILLA_LIST_GENERIC_SUBREPORT = new File(Propiedad.getCurrentInstance().getPathInformes() + "/informex/common/listas/listas_subreport.vsl");
    public static final File PLANTILLA_ESTADISTICA = new File(Propiedad.getCurrentInstance().getPathInformes() + "/informex/common/estadisticas/estadisticas.vsl");
    public static final File PLANTILLA_ESTADISTICA_SUBREPORT = new File(Propiedad.getCurrentInstance().getPathInformes() + "/informex/common/estadisticas/estadisticas_subreport.vsl");

    private static HashMap<String, InformexAbstract> mapLastInfo = new HashMap<>();
    private static HashMap<String, ByteArrayOutputStream> mapOutputream = new HashMap<>();

    public void startInfo(String key, final InformexAbstract informex, StringBuilder err) {

        InformexAbstract inf = getInfo(key);
        if (inf != null && inf.getModel() != null) {
            if (!inf.isIsEndInfo()) {
                err.append("Sólo puede generarse un informe por usuario a la vez, por favor cancele o espere que finalize el informe: ").append(inf.getModel().getId()).append(" ").append(inf.getModel().getTitle()).append(" !");
                return;
            }
        }

        //save
        mapLastInfo.put(key, informex);
        informex.startInfo();
        /* Thread h = new Thread() {
            @Override
            public void run() {
                informex.startInfo();
            }
        };
        h.start();*/
    }

    public void saveOutputStream(String key, ByteArrayOutputStream out) {
        mapOutputream.put(key, out);
    }

    public static ByteArrayOutputStream getOuputStream(String key) {
        return mapOutputream.get(key);
    }

    public void cancelInfo(String key) {
        InformexAbstract inf = getInfo(key);
        if (inf != null) {
            inf.cancelInfo();
        }

    }

    public InformexAbstract getInfo(String key) {
        return mapLastInfo.get(key);
    }

    private boolean validaInfoBuild(String key, StringBuilder err) {
        InformexAbstract inf = getInfo(key);
        if (inf == null) {
            err.append("Informe no encontrado !");
            return false;
        }
        if (!inf.isIsEndInfo()) {
            err.append("El informe aún se está generando !");
            return false;
        }
        if (inf.isIsCancel()) {
            err.append("No pueden mostrarse los resultados pues el informe fué cancelado !");
            return false;
        }
        return true;
    }

    public String buildHtml(String key, StringBuilder err, String protocolHostPortPath) {
        return buildHtml(key, PLANTILLA_LIST_GENERIC, err, protocolHostPortPath);
    }

    public String buildHtml(String key, File templateJrxml, StringBuilder err, String protocolHostPortPath) {
        String html = "";
        if (!validaInfoBuild(key, err)) {
            return html;
        }

        ModelDynreport model = getInfo(key).getModel();

        if (model != null) {
            model.setFileJrxmlTemplate(templateJrxml);
            DynreportUtil du = new DynreportUtil(model);
            html = du.buildHtmlContent(protocolHostPortPath);
        }

        html = Cadena.reemplazarPatronAll("src=\"[a-z|0-9|-]*\\.html_files/img_0_0_0\"", html, "src='" + model.getParams().get("path_logo").toString() + "' ");
        return html;
    }

    public ByteArrayOutputStream buildPdfStream(String key, StringBuilder err) {
        return buildPdfStream(key, PLANTILLA_LIST_GENERIC, err);
    }

    public ByteArrayOutputStream buildPdfStream(String key, File templateJrxml, StringBuilder err) {
        ByteArrayOutputStream out = null;
        if (!validaInfoBuild(key, err)) {
            return out;
        }

        ModelDynreport model = getInfo(key).getModel();

        if (model != null) {
            model.setFileJrxmlTemplate(templateJrxml);
            DynreportUtil du = new DynreportUtil(model);
            out = new ByteArrayOutputStream();
            du.buildJasperReportPdfStream(out);
        }

        return out;
    }

    public ByteArrayOutputStream buildXlsxStream(String key, StringBuilder err) {
        return buildXlsxStream(key, PLANTILLA_LIST_GENERIC, err);

    }

    public ByteArrayOutputStream buildXlsxStream(String key, File templateJrxml, StringBuilder err) {
        ByteArrayOutputStream out = null;
        if (!validaInfoBuild(key, err)) {
            return out;
        }

        ModelDynreport model = getInfo(key).getModel();

        if (model != null) {
            model.setFileJrxmlTemplate(templateJrxml);
            DynreportUtil du = new DynreportUtil(model);
            out = new ByteArrayOutputStream();
            du.buildJasperReportXlsxStream(out);
        }

        return out;
    }

    public void setPaginar(boolean paginar, String key) {
        InformexAbstract inf = mapLastInfo.get(key);
        inf.getModel().setIsIgnorePagination(!paginar);
    }

    public List<InformexAbstract> listLastInfo() {
        List<InformexAbstract> lstInf = new ArrayList<>();
        for (InformexAbstract inf : mapLastInfo.values()) {
            lstInf.add(inf);
        }
        return lstInf;
    }

}
