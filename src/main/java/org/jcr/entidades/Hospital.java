/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisv
 */
public class Hospital {

    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final List<Departamento> departamentos = new ArrayList<>();
    private final List<Paciente> pacientes = new ArrayList<>();

    public Hospital(String nombre, String direccion, String telefono) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre es obligatorio.");
        this.direccion = Validaciones.textoObligatorio(direccion, "La dirección es obligatoria.");
        this.telefono = Validaciones.textoObligatorio(telefono, "El teléfono es obligatorio.");
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void agregarDepartamento(Departamento departamento) {
        Validaciones.noNulo(departamento, "El departamento no puede ser nulo.");
        departamento.setHospital(this);
    }

    public void agregarPaciente(Paciente paciente) {
        Validaciones.noNulo(paciente, "El paciente no puede ser nulo.");
        paciente.setHospital(this);
    }

    public List<Departamento> getDepartamentos() {
        return Collections.unmodifiableList(departamentos);
    }

    public List<Paciente> getPacientes() {
        return Collections.unmodifiableList(pacientes);
    }

    List<Departamento> getInternalDepartamentos() {
        return departamentos;
    }

    List<Paciente> getInternalPacientes() {
        return pacientes;
    }
}
