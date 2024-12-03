package org.example.model;

import java.util.List;

public class Materia {

    private MateriaEnum nombre;
    private List<Double> notas;

    public Materia() {
    }

    public Materia(MateriaEnum nombre, List<Double> notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public MateriaEnum getNombre() {
        return nombre;
    }

    public void setNombre(MateriaEnum nombre) {
        this.nombre = nombre;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "nombre=" + getNombre() +
                ", notas=" + notas +
                '}';
    }
}
