/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProgresivoXml {

    String tipo;
    
    Long idprogresivo;
    double tasaAcumulado = 0;
    double tasaOcultaInicio = 0;
    double acumuladoOcultoInicio = 0;

    double minimo = 0;
    double maximo = 0;

    double apuestaMinima = 0;
    int probabilidad = 0;
    int ganador = 0;

    boolean activo = false;

    public Long getIdprogresivo() {
        return idprogresivo;
    }

    public void setIdprogresivo(Long idprogresivo) {
        this.idprogresivo = idprogresivo;
    }

    public double getTasaAcumulado() {
        return tasaAcumulado;
    }

    public void setTasaAcumulado(double tasaAcumulado) {
        this.tasaAcumulado = tasaAcumulado;
    }

    public double getTasaOcultaInicio() {
        return tasaOcultaInicio;
    }

    public void setTasaOcultaInicio(double tasaOcultaInicio) {
        this.tasaOcultaInicio = tasaOcultaInicio;
    }

    public double getAcumuladoOcultoInicio() {
        return acumuladoOcultoInicio;
    }

    public void setAcumuladoOcultoInicio(double acumuladoOcultoInicio) {
        this.acumuladoOcultoInicio = acumuladoOcultoInicio;
    }

    public double getApuestaMinima() {
        return apuestaMinima;
    }

    public void setApuestaMinima(double apuestaMinima) {
        this.apuestaMinima = apuestaMinima;
    }

    public int getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
