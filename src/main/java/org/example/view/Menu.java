package org.example.view;

import org.example.model.Alumno;
import org.example.model.Materia;
import org.example.model.MateriaEnum;
import org.example.service.AlumnoServicio;
import org.example.service.ArchivoServicio;
import org.example.service.PromedioServicioImp;
import org.example.utility.Utilidad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuTemplate {
    Utilidad utilidad = new Utilidad();
    AlumnoServicio alumnoServicio = new AlumnoServicio();
    Materia materia = new Materia();
    ArchivoServicio archivoServicio = new ArchivoServicio();

    @Override
    public void exportarDatos() {
        try {
            archivoServicio.exportarDatos(alumnoServicio.listaAlumnos().values().stream().toList(), "src/main/java/org/example/Promedios.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void crearAlumno() {
        String rut;
        System.out.println("\n=== Crear Alumno ===");
        do {
            System.out.print("Ingrese el rut del alumno: ");
            rut = sc.next().toUpperCase().trim();
            if (!alumnoServicio.validarRut(rut)) {
                System.out.println("El rut ya ha sido registrado.\n");
            }
        } while (!alumnoServicio.validarRut(rut));
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = sc.next().toLowerCase().trim();
        System.out.print("Ingrese el apellido del alumno: ");
        String apellido = sc.next().toLowerCase().trim();
        sc.nextLine();
        System.out.print("Ingrese la direccion del alumno: ");
        String direccion = sc.nextLine().toLowerCase().trim();
        if(nombre.isEmpty() || apellido.isEmpty() || direccion.isEmpty()){
            System.out.println("Ingrese los datos correctamente.");
        }else {
            Alumno alumno = new Alumno(rut, nombre, apellido, direccion);
            alumnoServicio.crearAlumno(alumno);
            System.out.println();
            System.out.println("--- ¡Alumno agregado! ---");
        }

    }

    @Override
    public void agregarMateria() {
        System.out.println("\n===Agregar materia===\n");
        String rut;
        int opcion;
        do {
            alumnoServicio.mostrarListaAlumnos();
            System.out.println();
            System.out.print("Ingrese el rut del alumno: ");
            rut = sc.next();
            if (alumnoServicio.validarRut(rut)) {
                System.out.println("El rut ingresado no es valido.\n");
            }
        } while (alumnoServicio.validarRut(rut));

        if (alumnoServicio.cantidadMaterias(rut) == 4) {
            if (alumnoServicio.materiasPorAlumnos(rut).size() > 4) {
                System.out.println("El alumno tiene todas sus materias registradas.");
                return;
            }
        }

        if (alumnoServicio.cantidadMaterias(rut) != 4) {

            do {
                System.out.println();
                int cont = 1;
                for (MateriaEnum materiaOpcion : MateriaEnum.values()) {
                    System.out.println((cont++) + ". " + materiaOpcion);
                }
                System.out.println();
                sc.nextLine();
                System.out.print("Selecciona una materia: ");
                opcion = validarEntrada();
                if (alumnoServicio.validarOpcionMateria(rut, opcion)) {
                    System.out.println("La materia ya ha sido registrada.");
                }
            } while (!utilidad.validarOpcionMateria(opcion) || alumnoServicio.validarOpcionMateria(rut, opcion));
            sc.nextLine();
            MateriaEnum materiaEnum = MateriaEnum.values()[opcion - 1];
            List<Double> notas = new ArrayList<>();

            Materia materia = new Materia(materiaEnum, notas);
            alumnoServicio.agregarMateria(rut, materia);
            System.out.println("\n-- ¡Materia agregada! --");

            System.out.println("=====================");
        } else {
            System.out.println("El alumno tiene todas sus materias registradas.\n");
            return;
        }
    }

    @Override
    public void agregarNotaPasoUno() {
        System.out.println("\n===Agregar Nota===\n");
        String rut;
        int opcion, opcion3, opcionNota;
        double opcion2, notaUpdate;

        do {
            alumnoServicio.mostrarListaAlumnos();
            System.out.println();
            System.out.print("Ingrese el rut del alumno: ");
            rut = sc.next();
            if (alumnoServicio.validarRut(rut)) {
                System.out.println("El rut ingresado no es valido.\n");
            }
        } while (alumnoServicio.validarRut(rut));

        if (alumnoServicio.materiasPorAlumnos(rut) != null) {
            List<Materia> materiaSelec = alumnoServicio.materiasPorAlumnos(rut);
            do {
                System.out.println("Alumno tiene las siguientes materias agregadas: ");
                int cont = 1;
                for (Materia materiaSeleccion : alumnoServicio.materiasPorAlumnos(rut)) {
                    System.out.println((cont++) + " = " + materiaSeleccion.getNombre());
                }
                System.out.println();
                System.out.print("Seleccionar una materia: ");
                opcion = validarEntrada();
                sc.nextLine();
                if (opcion < 1 || opcion > materiaSelec.size()) {
                    System.out.println("Ingrese una materia valida.\n");
                }
            } while (opcion < 1 || opcion > materiaSelec.size());

            if (materiaSelec.get(opcion - 1).getNotas().size() < 3) {
                do{
                    do{
                            System.out.print("Ingrese la nota " + (materiaSelec.get(opcion - 1).getNotas().size() + 1) + ": ");
                            opcion2 = validarEntradaDouble();
                            sc.nextLine();
                            if(opcion2 < 1.0 || opcion2 > 7.0){
                                System.out.println("Ingrese una nota válida.");
                            }
                    } while (opcion2 < 1.0 || opcion2 > 7.0);

                    if (materiaSelec.get(opcion - 1).getNotas().size() < 4) {
                            materiaSelec.get(opcion - 1).getNotas().add(opcion2);
                            System.out.println("\n---Nota agregada a " + materiaSelec.get(opcion - 1).getNombre() + "! ---\n");
                    }

                }while(materiaSelec.get(opcion - 1).getNotas().size() < 3);
            } else {
                System.out.println("El Alumno(a) " + alumnoServicio.listaAlumnos().get(rut).getNombre() + ", tiene todas sus notas agregadas en la materia: " + materiaSelec.get(opcion - 1).getNombre() + "\n");
                do {
                    System.out.println("Desea actualizar una nota?: \n1. SI\n2. Salir");
                    System.out.print("Ingrese una opción: ");
                    opcion3 = validarEntrada();
                    sc.nextLine();
                    if (opcion3 < 1 || opcion3 > 2) {
                        System.out.println("Ingrese una opción válida.");
                    }
                } while (opcion3 < 1 || opcion3 > 2);
                if (opcion3 == 1) {
                    for (int i = 0; i < materiaSelec.size(); i++) {
                        if (materiaSelec.get(i).getNombre().equals(materiaSelec.get(opcion - 1).getNombre())) {
                            do {
                                System.out.println("Notas: ");
                                for (int j = 0; j < materiaSelec.get(opcion - 1).getNotas().size(); j++) {
                                    System.out.println((j + 1) + " = " + materiaSelec.get(opcion - 1).getNotas().get(j));
                                }
                                System.out.print("Ingrese una opción: ");
                                opcionNota = validarEntrada();
                                sc.nextLine();
                                if(opcionNota < 1 || opcionNota > 3){
                                    System.out.println("Ingrese una opción válida: (1 al 3) ");
                                }
                            } while (opcionNota < 1 || opcionNota > 3);

                            do {
                                System.out.print("Ingrese la nueva nota: ");
                                notaUpdate = validarEntradaDouble();
                                sc.nextLine();
                                if(notaUpdate < 1.0 || notaUpdate > 7.0){
                                    System.out.println("Ingrese una nota válida.\n");
                                }
                            } while (notaUpdate < 1.0 || notaUpdate > 7.0);

                            materiaSelec.get(opcion - 1).getNotas().set(opcionNota - 1, notaUpdate);
                            System.out.println("La Nota fue actualizada con exito!");
                            System.out.println(alumnoServicio.materiasPorAlumnos(rut).get(opcion - 1).getNotas());
                        }
                    }
                } else if (opcion3 == 2) {
                    return;
                }
            }
        }
    }

    @Override
    public void listaAlumnos() {
        if (alumnoServicio.listaAlumnos().isEmpty() || alumnoServicio.listaAlumnos() == null) {
            System.out.println("\n--- No hay alumnos registrados ---");
            return;
        } else {
            System.out.println("\n=== Listar Alumnos ==");
            int cont = 1;
            System.out.println("==================================");
            for (Alumno a : alumnoServicio.listaAlumnos().values()) {
                System.out.println("Datos Alumno " + (cont++) + "\n");
                System.out.println("RUT: " + a.getRut());
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Apellido: " + a.getApellido());
                System.out.println("Direccion: " + a.getDireccion() + "\n");

                if (alumnoServicio.materiasPorAlumnos(a.getRut()) != null) {
                    System.out.println("Materias\n");
                    for (Materia materia : alumnoServicio.materiasPorAlumnos(a.getRut())) {
                        System.out.println(materia.getNombre());
                        System.out.println("Notas:");
                        System.out.println(materia.getNotas());
                    }
                }
                System.out.println("==================================");
            }
        }
    }

    @Override
    public void terminarPrograma() {
        System.out.println("Adios, Cerrando programa...");
        return;
    }
}
