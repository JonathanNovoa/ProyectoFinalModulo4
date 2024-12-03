package org.example.service;

import org.example.model.Alumno;
import org.example.model.Materia;
import org.example.model.MateriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlumnoServicioTest {
    AlumnoServicio alumnoServicio;
    @Mock
    AlumnoServicio alumnoServicioMock;

    Materia matematicas;
    Materia lenguaje;
    Alumno mapu;
    @BeforeEach
    public void setup() {
        alumnoServicio = new AlumnoServicio();
        MockitoAnnotations.openMocks(this);

        matematicas = new Materia(MateriaEnum.MATEMATICAS, Arrays.asList(5.0, 6.0, 7.0));
        lenguaje = new Materia(MateriaEnum.LENGUAJE, Arrays.asList(6.0, 6.5, 7.0));
        mapu = new Alumno("12.345.678-9", "Mapu", "Apellido", "Dirección");
    }

    @Test
    void listaAlumnos() {
        alumnoServicio.crearAlumno(mapu);
        assertEquals(4, alumnoServicio.listaAlumnos().size(), "Debería haber un alumno registrado.");
        assertEquals(mapu, alumnoServicio.listaAlumnos().get(mapu.getRut()), "El alumno debería coincidir con los datos registrados.");
    }

    @Test
    void crearAlumno() {
        alumnoServicio.crearAlumno(mapu);
        assertTrue(alumnoServicio.listaAlumnos().containsKey(mapu.getRut()), "El alumno debería estar registrado en la lista.");
    }

    @Test
    void agregarMateria() {
        alumnoServicio.crearAlumno(mapu);
        alumnoServicio.agregarMateria(mapu.getRut(), matematicas);

        List<Materia> materias = alumnoServicio.materiasPorAlumnos(mapu.getRut());
        assertTrue(materias.contains(matematicas), "La materia debería haber sido agregada correctamente.");
    }

    @Test
    void materiasPorAlumnos() {
        when(alumnoServicioMock.materiasPorAlumnos(mapu.getRut())).thenReturn(Arrays.asList(matematicas, lenguaje));

        List<Materia> materias = alumnoServicioMock.materiasPorAlumnos(mapu.getRut());
        assertEquals(2, materias.size(), "Debería haber dos materias.");
        verify(alumnoServicioMock, times(1)).materiasPorAlumnos(mapu.getRut());
    }
}