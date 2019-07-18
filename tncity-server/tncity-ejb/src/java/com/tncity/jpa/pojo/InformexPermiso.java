/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tncolombia
 */
@Entity
@Table(name = "informex_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformexPermiso.findAll", query = "SELECT i FROM InformexPermiso i")
    , @NamedQuery(name = "InformexPermiso.findByIdpermiso", query = "SELECT i FROM InformexPermiso i WHERE i.idpermiso = :idpermiso")
    , @NamedQuery(name = "InformexPermiso.findByIdinforme", query = "SELECT i FROM InformexPermiso i WHERE i.idinforme = :idinforme")
    , @NamedQuery(name = "InformexPermiso.findByTipo", query = "SELECT i FROM InformexPermiso i WHERE i.tipo = :tipo")})
public class InformexPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpermiso")
    private Long idpermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idinforme")
    private int idinforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne
    private Perfil idperfil;
    @JoinColumn(name = "idpersona_permiso", referencedColumnName = "idpersona")
    @ManyToOne
    private Persona idpersonaPermiso;

    public InformexPermiso() {
    }

    public InformexPermiso(Long idpermiso) {
        this.idpermiso = idpermiso;
    }

    public InformexPermiso(Long idpermiso, int idinforme, String tipo) {
        this.idpermiso = idpermiso;
        this.idinforme = idinforme;
        this.tipo = tipo;
    }

    public Long getIdpermiso() {
        return idpermiso;
    }

    public void setIdpermiso(Long idpermiso) {
        this.idpermiso = idpermiso;
    }

    public int getIdinforme() {
        return idinforme;
    }

    public void setIdinforme(int idinforme) {
        this.idinforme = idinforme;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }

    public Persona getIdpersonaPermiso() {
        return idpersonaPermiso;
    }

    public void setIdpersonaPermiso(Persona idpersonaPermiso) {
        this.idpersonaPermiso = idpersonaPermiso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpermiso != null ? idpermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformexPermiso)) {
            return false;
        }
        InformexPermiso other = (InformexPermiso) object;
        if ((this.idpermiso == null && other.idpermiso != null) || (this.idpermiso != null && !this.idpermiso.equals(other.idpermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tncity.jpa.pojo.InformexPermiso[ idpermiso=" + idpermiso + " ]";
    }
    
}
