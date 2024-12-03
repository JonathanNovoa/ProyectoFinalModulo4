package org.example.service;

import java.util.List;

public class PromedioServicioImp implements PromedioServicio{
    @Override
    public Double calcularPromedio(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            throw new IllegalArgumentException("La lista de notas esta vacia");
        }
        double sumaDeNotas = 0;
        int cont = 0;
        for (Double nota : notas) {
            sumaDeNotas += nota;
            cont++;
        }
        return sumaDeNotas / cont;
    }
}
