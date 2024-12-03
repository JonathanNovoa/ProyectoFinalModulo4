package org.example.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromedioServicioImpTest {

    private final PromedioServicioImp promedioServicio = new PromedioServicioImp();

    @Test
    void calcularPromedioTest() {
        // Caso 1: Lista con varios elementos
        List<Double> notas = Arrays.asList(8.5, 9.0, 7.5);
        Double resultado = promedioServicio.calcularPromedio(notas);
        assertEquals(8.33, resultado, 0.01, "El promedio no se calculó correctamente.");

        // Caso 2: Lista con un solo elemento
        notas = Collections.singletonList(10.0);
        resultado = promedioServicio.calcularPromedio(notas);
        assertEquals(10.0, resultado, "El promedio de una lista con un único elemento debería ser el elemento mismo.");

        // Caso 3: Lista vacía
        notas = Collections.emptyList();
        List<Double> finalNotas = new ArrayList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> promedioServicio.calcularPromedio(finalNotas));
        assertEquals("La lista de notas esta vacia", exception.getMessage());
    }

}