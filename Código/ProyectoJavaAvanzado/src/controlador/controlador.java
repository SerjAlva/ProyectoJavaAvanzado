/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.Vista;
import modelo.modelo;

public class controlador {
    Vista vista;
    modelo modelo;
    public controlador(Vista vista, modelo modelo){
        this.vista = vista;
        this.modelo = modelo;
    }
    
}
