/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

/**
 *
 * @author luisv
 */
public class Matricula {

    private final String numero;

    public Matricula(String numero) {
        this.numero = validarMatricula(numero);
    }

    public String getNumero() {
        return numero;
    }

    private String validarMatricula(String numero) {
        String limpio = Validaciones.textoObligatorio(numero, "La matrícula es obligatoria");
        if (!limpio.startsWith("MP-")) {
            throw new IllegalArgumentException("La matrícula debe comenzar con MP-");
        }
        String digitos = limpio.substring(3);
        if (!Validaciones.sonDigitos(digitos) || digitos.length() < 4 || digitos.length() > 6) {
            throw new IllegalArgumentException("La matrícula debe tener entre 4 y 6 dígitos");
        }
        return limpio;
    }
}
