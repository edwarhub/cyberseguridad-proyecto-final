/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneralConfig extends AbstractConfig {

    Integer idusuarioAdministrador;
    String nombreSistema;

    public Integer getIdusuarioAdministrador() {
        return idusuarioAdministrador;
    }

    public void setIdusuarioAdministrador(Integer idusuarioAdministrador) {
        this.idusuarioAdministrador = idusuarioAdministrador;
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

}
