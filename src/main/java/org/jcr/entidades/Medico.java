/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

/**
 *
 * @author luisv
 */
public class Medico extends Persona {

    private final Matricula matricula;
    private final EspecialidadMedica especialidad;

    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
            TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.matricula = new Matricula(numeroMatricula);
        Validaciones.noNulo(especialidad, "La especialidad es obligatoria");
        this.especialidad = especialidad;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }
}
