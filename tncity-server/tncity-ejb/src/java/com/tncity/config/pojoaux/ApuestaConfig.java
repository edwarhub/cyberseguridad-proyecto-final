/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApuestaConfig extends AbstractConfig {

    public final static String TIPO_RECARGA_MENOR = "Recarga Menor";
    public final static String TIPO_RECARGA_MAYOR = "Recarga Mayor";
    public final static String TIPO_APUESTA_MENOR = "Apuesta Menor";
    public final static String TIPO_APUESTA_MAYOR = "Apuesta Mayor";

    ProgresivoXml progresivoRecargaMenor = new ProgresivoXml();
    ProgresivoXml progresivoRecargaMayor = new ProgresivoXml();
    ProgresivoXml progresivoApuestaMenor = new ProgresivoXml();
    ProgresivoXml progresivoApuestaMayor = new ProgresivoXml();

    long tiempoAnimacionGanador = 3 * 60;
    
    String urlVideoFondo;
    String urlVideoFondoGanador;
    
    int probabilidadWhenMax=3;

    public ProgresivoXml getProgresivoRecargaMenor() {
        return progresivoRecargaMenor;
    }

    public void setProgresivoRecargaMenor(ProgresivoXml progresivoRecargaMenor) {
        this.progresivoRecargaMenor = progresivoRecargaMenor;
    }

    public ProgresivoXml getProgresivoRecargaMayor() {
        return progresivoRecargaMayor;
    }

    public void setProgresivoRecargaMayor(ProgresivoXml progresivoRecargaMayor) {
        this.progresivoRecargaMayor = progresivoRecargaMayor;
    }

    public ProgresivoXml getProgresivoApuestaMenor() {
        return progresivoApuestaMenor;
    }

    public void setProgresivoApuestaMenor(ProgresivoXml progresivoApuestaMenor) {
        this.progresivoApuestaMenor = progresivoApuestaMenor;
    }

    public ProgresivoXml getProgresivoApuestaMayor() {
        return progresivoApuestaMayor;
    }

    public void setProgresivoApuestaMayor(ProgresivoXml progresivoApuestaMayor) {
        this.progresivoApuestaMayor = progresivoApuestaMayor;
    }

    public long getTiempoAnimacionGanador() {
        return tiempoAnimacionGanador;
    }

    public void setTiempoAnimacionGanador(long tiempoAnimacionGanador) {
        this.tiempoAnimacionGanador = tiempoAnimacionGanador;
    }

    public String getUrlVideoFondo() {
        return urlVideoFondo;
    }

    public void setUrlVideoFondo(String urlVideoFondo) {
        this.urlVideoFondo = urlVideoFondo;
    }

    public String getUrlVideoFondoGanador() {
        return urlVideoFondoGanador;
    }

    public void setUrlVideoFondoGanador(String urlVideoFondoGanador) {
        this.urlVideoFondoGanador = urlVideoFondoGanador;
    }

    public int getProbabilidadWhenMax() {
        return probabilidadWhenMax;
    }

    public void setProbabilidadWhenMax(int probabilidadWhenMax) {
        this.probabilidadWhenMax = probabilidadWhenMax;
    }
    
}
