/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDateTime;
import org.jcr.enums.EstadoCita;

/**
 *
 * @author luisv
 */
public class Cita {

    private final Paciente paciente;
    private final Medico medico;
    private final Sala sala;
    private final LocalDateTime fechaHora;
    private final double costo;
    private EstadoCita estado;
    private String observaciones;

    public Cita(Paciente paciente, Medico medico, Sala sala,
            LocalDateTime fechaHora, double costo) {
        Validaciones.noNulo(paciente, "El paciente es obligatorio.");
        Validaciones.noNulo(medico, "El médico es obligatorio.");
        Validaciones.noNulo(sala, "La sala es obligatoria.");
        Validaciones.noNulo(fechaHora, "La fecha es obligatoria.");
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.costo = costo;
        this.estado = EstadoCita.PROGRAMADA;
        this.observaciones = "";
    }

    public Paciente getPaciente() {
        return paciente;

    }

    public Medico getMedico() {
        return medico;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getCosto() {
        return costo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setEstado(EstadoCita estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }
        this.estado = estado;
    }

    public void setObservaciones(String observaciones) {
        if (observaciones == null) {
            this.observaciones = "";
        } else {
            this.observaciones = observaciones;
        }
    }
}
