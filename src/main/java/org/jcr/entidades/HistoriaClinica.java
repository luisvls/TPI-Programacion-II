/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisv
 */
public class HistoriaClinica {

    private final String numeroHistoria;
    private final Paciente paciente;
    private final LocalDateTime fechaCreacion;
    private final List<String> diagnosticos = new ArrayList<>();
    private final List<String> tratamientos = new ArrayList<>();
    private final List<String> alergias = new ArrayList<>();

    HistoriaClinica(Paciente paciente) {
        Validaciones.noNulo(paciente, "El paciente es obligatorio");
        this.paciente = paciente;
        this.fechaCreacion = LocalDateTime.now();
        this.numeroHistoria = generarNumeroHistoria();
    }

    private String generarNumeroHistoria() {
        return "HC-" + paciente.getDni() + "-" + LocalDate.now().getYear();
    }

    public void agregarDiagnostico(String diagnostico) {
        agregarSiTieneTexto(diagnosticos, diagnostico);
    }

    public void agregarTratamiento(String tratamiento) {
        agregarSiTieneTexto(tratamientos, tratamiento);
    }

    public void agregarAlergia(String alergia) {
        agregarSiTieneTexto(alergias, alergia);
    }

    private void agregarSiTieneTexto(List<String> lista, String texto) {
        if (texto != null && !texto.trim().isEmpty()) {
            lista.add(texto.trim());
        }
    }

    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public List<String> getDiagnosticos() {
        return Collections.unmodifiableList(diagnosticos);
    }

    public List<String> getTratamientos() {
        return Collections.unmodifiableList(tratamientos);
    }

    public List<String> getAlergias() {
        return Collections.unmodifiableList(alergias);
    }
}
