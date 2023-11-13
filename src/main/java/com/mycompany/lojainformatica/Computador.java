/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lojainformatica;

public class Computador {
    
    static String marca = "CarlosEduardoMartinsDosSantos";
    private String processador;
    private String HD;
    
    
    public Computador(){
        
    }
    
    public Computador(String processador, String HD){
        this.processador = processador;
        this.HD = HD;
    }

    public static String getMarca() {
        return marca;
    }

    public static void setMarca(String marca) {
        Computador.marca = marca;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }
}
