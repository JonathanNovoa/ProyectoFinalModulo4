package org.example.service;

import org.example.model.Alumno;
import org.example.model.Materia;
import org.example.model.MateriaEnum;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ArchivoServicio {
    AlumnoServicio alumnoServicio = new AlumnoServicio();
    PromedioServicioImp promedioServicio = new PromedioServicioImp();

public void exportarDatos(List<Alumno> alumnos, String rutaArchivo) throws IOException {
    alumnos = alumnoServicio.listaAlumnos().values().stream().toList();
    rutaArchivo = "src/main/java/org/example/Promedios.txt";
    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
        if (alumnos == null || alumnos.isEmpty()) {
            escritor.write("No hay alumnos para exportar");
            return;
        }

        escritor.write("Reporte de Calificaciones Cólegio Latinoamericano");
        escritor.newLine();
        escritor.write("====================================");
        escritor.newLine();
        escritor.newLine();

        int totalAlumnos = 0;
        int totalMaterias = 0;
        double promedioGeneral = 0;
        int totalAlmunosConNotas = 0;
        int alumnosReprobados = 0;
        int alumnosAprobados = 0;

        for (Alumno alumno : alumnos) {

            escritor.write(String.format("Alumno: %s - %s", alumno.getRut(), alumno.getNombre()));
            escritor.newLine();

            double promedioAlumno = 0;
            int materiasPorAlumno = 0;

            for (Materia materia : alumno.getMaterias()) {
                double promedioMateria = promedioServicio.calcularPromedio(materia.getNotas());
                escritor.write(String.format("    Materia: %-20s", materia.getNombre()));
                escritor.newLine();

                if (!materia.getNotas().isEmpty()) {
                    for (Double notas : materia.getNotas()) {
                        escritor.write(String.format("        Nota: %.2f", notas));
                        escritor.newLine();
                    }
                    totalAlmunosConNotas++;
                }


                escritor.write(String.format("    Promedio: %.2f", promedioMateria));
                escritor.newLine();
                    /*escritor.write(String.format("    Materia: %-20s Promedio: %.2f",
                            materia.getNombre(), promedioMateria));
                    escritor.newLine();*/

                promedioAlumno += promedioMateria;
                materiasPorAlumno++;
                totalMaterias++;
            }

            if (materiasPorAlumno > 0) {
                promedioAlumno /= materiasPorAlumno;
                escritor.write(String.format("    Promedio General: %.2f", promedioAlumno));
                escritor.newLine();
            }

            escritor.write("---------------------------------");
            escritor.newLine();

            if (promedioAlumno > 0.0 && promedioAlumno < 4.0) {
                alumnosReprobados++;
            } else if (promedioAlumno >= 4.0 && promedioAlumno <= 7.0) {
                alumnosAprobados++;
            }

            totalAlumnos++;
            promedioGeneral += promedioAlumno;
        }
        //Instant fechaActual = Instant.now();
        //LocalDateTime fechaActual = LocalDateTime.now();
        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fechaActual = formato.format(calendario.getTime());
        //Date fechaActual = new Date();

        escritor.newLine();
        escritor.write("Resumen del Reporte");
        escritor.newLine();
        escritor.write("==================");
        escritor.newLine();
        escritor.write(String.format("Total de Alumnos: %d", totalAlumnos));
        escritor.newLine();
        escritor.write(String.format("Total alumnos Reprobados: %d", alumnosReprobados));
        escritor.newLine();
        escritor.write(String.format("Total alumnos Aprobados: %d", alumnosAprobados));
        escritor.newLine();
        escritor.write(String.format("Promedio General del Curso: %.2f",
                totalAlumnos > 0 ? promedioGeneral / totalAlumnos : 0));
        escritor.newLine();
        escritor.write(String.format("Fecha de Generación: %s", fechaActual));

        System.out.println("Archivo exportado exitosamente a: " + rutaArchivo);

    } catch (IOException e) {
        System.err.println("Error al exportar los datos: " + e.getMessage());
        e.printStackTrace();
    }
}
}
