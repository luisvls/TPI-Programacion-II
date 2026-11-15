/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import org.jcr.enums.TipoSangre;

/**
 *
 * @author luisv
 */
public class Paciente extends Persona {

    private final HistoriaClinica historiaClinica;
    private String telefono;
    private String direccion;

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
            TipoSangre tipoSangre, String telefono, String direccion) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.telefono = Validaciones.textoObligatorio(telefono, "El teléfono es obligatorio");
        this.direccion = Validaciones.textoObligatorio(direccion, "La dirección es obligatoria");
        this.historiaClinica = new HistoriaClinica(this);
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

}
