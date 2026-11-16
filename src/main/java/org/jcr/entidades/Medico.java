/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

/**
 *
 * @author luisv
 */
public class Medico extends Persona {

    private final Matricula matricula;
    private final EspecialidadMedica especialidad;
    private Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();

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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        if (departamento != null && departamento.getEspecialidad() != especialidad) {
            throw new IllegalArgumentException("El médico solo puede estar en un departamento de su misma especialidad.");
        }
        if (this.departamento != departamento) {
            if (this.departamento != null) {
                this.departamento.getInternalMedicos().remove(this);
            }
            this.departamento = departamento;
            if (departamento != null) {
                departamento.getInternalMedicos().add(this);
            }
        }
    }

    public void addCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("Cita es obligatoria.");
        } else {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
}
