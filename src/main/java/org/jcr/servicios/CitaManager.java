/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;

/**
 *
 * @author luisv
 */
public class CitaManager implements CitaService {

    private static final int HORAS_ENTRE_CITAS = 2;
    private final List<Cita> citas = new ArrayList<>();

    @Override
    public Cita programarCita(Paciente paciente, Medico medico, Sala sala,
            LocalDateTime fechaHora, double costo) throws CitaException {
        if (paciente == null || medico == null || sala == null) {
            throw new CitaException("El paciente, el médico y la sala son obligatorios");
        }
        validarCita(fechaHora, costo);
        if (sala.getDepartamento().getEspecialidad() != medico.getEspecialidad()) {
            throw new CitaException("La especialidad del médico no coincide con la de la sala");
        }
        if (!esMedicoDisponible(medico, fechaHora)) {
            throw new CitaException("El médico ya tiene una cita en ese horario");
        }
        if (!esSalaDisponible(sala, fechaHora)) {
            throw new CitaException("La sala ya tiene una cita en ese horario");
        }
        Cita cita = new Cita(paciente, medico, sala, fechaHora, costo);
        citas.add(cita);
        paciente.addCita(cita);
        medico.addCita(cita);
        sala.addCita(cita);
        return cita;
    }

    private void validarCita(LocalDateTime fechaHora, double costo) throws CitaException {
        if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now())) {
            throw new CitaException("La fecha es obligatoria y no puede ser anterior a ahora.");
        }
        if (costo <= 0) {
            throw new CitaException("El costo debe ser mayor a cero.");
        }
    }

    private boolean esMedicoDisponible(Medico medico, LocalDateTime fechaHora) {
        for (Cita existente : medico.getCitas()) {
            if (seSuperponen(existente.getFechaHora(), fechaHora)) {
                return false;
            }
        }
        return true;
    }

    private boolean esSalaDisponible(Sala sala, LocalDateTime fechaHora) {
        for (Cita existente : sala.getCitas()) {
            if (seSuperponen(existente.getFechaHora(), fechaHora)) {
                return false;
            }
        }
        return true;
    }

    private boolean seSuperponen(LocalDateTime existente, LocalDateTime nueva) {
        return nueva.isBefore(existente.plusHours(HORAS_ENTRE_CITAS)) && nueva.isAfter(existente.minusHours(HORAS_ENTRE_CITAS));
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getPaciente() == paciente) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getMedico() == medico) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getSala() == sala) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

}
