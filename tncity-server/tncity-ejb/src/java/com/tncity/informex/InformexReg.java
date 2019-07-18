/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "informex")
public class InformexReg {

    public final static String TYPE_LIST = "list";
    public final static String TYPE_STATISTIC = "statistic";
    public final static String TYPE_FREE = "free";

    int id;
    String title;
    String icon;
    String descripcion;
    List<String> lstCampos = new ArrayList<>();
    boolean isActivo = false;
    String pathTemplate;
    String type = TYPE_LIST;
    boolean implTech = true;
    boolean implPro = true;
    boolean implSchool = true;
    boolean implQuantum = true;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @XmlElement
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlElement(name = "campo")
    public List<String> getLstCampos() {
        return lstCampos;
    }

    public void setLstCampos(List<String> lstCampos) {
        this.lstCampos = lstCampos;
    }

    @XmlAttribute
    public boolean getIsActivo() {
        return isActivo;
    }

    public boolean isIsActivo() {
        return isActivo;
    }

    public void setIsActivo(boolean isActivo) {
        this.isActivo = isActivo;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getPathTemplate() {
        return pathTemplate;
    }

    public void setPathTemplate(String pathTemplate) {
        this.pathTemplate = pathTemplate;
    }

    @XmlAttribute
    public boolean isImplTech() {
        return implTech;
    }

    public void setImplTech(boolean implTech) {
        this.implTech = implTech;
    }

    @XmlAttribute
    public boolean isImplPro() {
        return implPro;
    }

    public void setImplPro(boolean implPro) {
        this.implPro = implPro;
    }

    @XmlAttribute
    public boolean isImplSchool() {
        return implSchool;
    }

    public void setImplSchool(boolean implSchool) {
        this.implSchool = implSchool;
    }

    @XmlAttribute
    public boolean isImplQuantum() {
        return implQuantum;
    }

    public void setImplQuantum(boolean implQuantum) {
        this.implQuantum = implQuantum;
    }

}
