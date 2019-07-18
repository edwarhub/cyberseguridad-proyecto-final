/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tncolombia
 */
@Entity
@Table(name = "informex_histo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformexHisto.findAll", query = "SELECT i FROM InformexHisto i")
    , @NamedQuery(name = "InformexHisto.findByIdinformexhisto", query = "SELECT i FROM InformexHisto i WHERE i.idinformexhisto = :idinformexhisto")
    , @NamedQuery(name = "InformexHisto.findByIdinforme", query = "SELECT i FROM InformexHisto i WHERE i.idinforme = :idinforme")
    , @NamedQuery(name = "InformexHisto.findByTitulo", query = "SELECT i FROM InformexHisto i WHERE i.titulo = :titulo")
    , @NamedQuery(name = "InformexHisto.findByIp", query = "SELECT i FROM InformexHisto i WHERE i.ip = :ip")
    , @NamedQuery(name = "InformexHisto.findByNavegador", query = "SELECT i FROM InformexHisto i WHERE i.navegador = :navegador")
    , @NamedQuery(name = "InformexHisto.findBySysOperativo", query = "SELECT i FROM InformexHisto i WHERE i.sysOperativo = :sysOperativo")
    , @NamedQuery(name = "InformexHisto.findByApp", query = "SELECT i FROM InformexHisto i WHERE i.app = :app")
    , @NamedQuery(name = "InformexHisto.findByIsFinalizado", query = "SELECT i FROM InformexHisto i WHERE i.isFinalizado = :isFinalizado")
    , @NamedQuery(name = "InformexHisto.findByIsCancelado", query = "SELECT i FROM InformexHisto i WHERE i.isCancelado = :isCancelado")
    , @NamedQuery(name = "InformexHisto.findByFechaIni", query = "SELECT i FROM InformexHisto i WHERE i.fechaIni = :fechaIni")
    , @NamedQuery(name = "InformexHisto.findByFechaFin", query = "SELECT i FROM InformexHisto i WHERE i.fechaFin = :fechaFin")
    , @NamedQuery(name = "InformexHisto.findByLog", query = "SELECT i FROM InformexHisto i WHERE i.log = :log")})
public class InformexHisto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinformexhisto")
    private Long idinformexhisto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idinforme")
    private int idinforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "navegador")
    private String navegador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sys_operativo")
    private String sysOperativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "app")
    private String app;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_finalizado")
    private boolean isFinalizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_cancelado")
    private boolean isCancelado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ini")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 2147483647)
    @Column(name = "log")
    private String log;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona idpersona;

    public InformexHisto() {
    }

    public InformexHisto(Long idinformexhisto) {
        this.idinformexhisto = idinformexhisto;
    }

    public InformexHisto(Long idinformexhisto, int idinforme, String titulo, String ip, String navegador, String sysOperativo, String app, boolean isFinalizado, boolean isCancelado, Date fechaIni, Date fechaFin) {
        this.idinformexhisto = idinformexhisto;
        this.idinforme = idinforme;
        this.titulo = titulo;
        this.ip = ip;
        this.navegador = navegador;
        this.sysOperativo = sysOperativo;
        this.app = app;
        this.isFinalizado = isFinalizado;
        this.isCancelado = isCancelado;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public Long getIdinformexhisto() {
        return idinformexhisto;
    }

    public void setIdinformexhisto(Long idinformexhisto) {
        this.idinformexhisto = idinformexhisto;
    }

    public int getIdinforme() {
        return idinforme;
    }

    public void setIdinforme(int idinforme) {
        this.idinforme = idinforme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public boolean getIsFinalizado() {
        return isFinalizado;
    }

    public void setIsFinalizado(boolean isFinalizado) {
        this.isFinalizado = isFinalizado;
    }

    public boolean getIsCancelado() {
        return isCancelado;
    }

    public void setIsCancelado(boolean isCancelado) {
        this.isCancelado = isCancelado;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinformexhisto != null ? idinformexhisto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformexHisto)) {
            return false;
        }
        InformexHisto other = (InformexHisto) object;
        if ((this.idinformexhisto == null && other.idinformexhisto != null) || (this.idinformexhisto != null && !this.idinformexhisto.equals(other.idinformexhisto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.InformexHisto[ idinformexhisto=" + idinformexhisto + " ]";
    }
    
}
