/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baruCorpModel;

/**
 *
 * @author sebas
 */
public class Mensaje {
    
    public int code;
    public String fechaHora;
    public String artista;
    public String mensaje;

    public Mensaje(int code, String fechaHora, String artista, String mensaje) {
        this.code = code;
        this.fechaHora = fechaHora;
        this.artista = artista;
        this.mensaje = mensaje;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
