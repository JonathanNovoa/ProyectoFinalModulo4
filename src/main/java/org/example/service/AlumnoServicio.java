package org.example.service;

import org.example.model.Alumno;
import org.example.model.Materia;
import org.example.model.MateriaEnum;

import java.io.File;
import java.util.*;

public class AlumnoServicio {
    Scanner sc = new Scanner(System.in);
    private static Map<String, Alumno> listaAlumnos = new HashMap<>();

    static {
        // Crear materias para Juan
        Materia matematicasJuan = new Materia(
                MateriaEnum.MATEMATICAS,
                new ArrayList<>(Arrays.asList(5.5, 6.0, 5.8))
        );
        Materia lenguajeJuan = new Materia(
                MateriaEnum.LENGUAJE,
                new ArrayList<>(Arrays.asList(6.2, 5.9, 6.1))
        );

        // Crear a Juan
        Alumno juan = new Alumno(
                "17.423.112-4",
                "Juan",
                "Pérez",
                "Av. Principal 123",
                new ArrayList<>(Arrays.asList(matematicasJuan, lenguajeJuan))
        );

        // Crear materias para María
        Materia matematicasMaria = new Materia(
                MateriaEnum.MATEMATICAS,
                new ArrayList<>(Arrays.asList(6.5, 6.8, 7.0))
        );
        Materia historiaMaria = new Materia(
                MateriaEnum.HISTORIA,
                new ArrayList<>(Arrays.asList(6.3, 6.0, 6.4))
        );

        // Crear a María
        Alumno maria = new Alumno(
                "18.546.232-1",
                "María",
                "González",
                "Calle Central 456",
                new ArrayList<>(Arrays.asList(matematicasMaria, historiaMaria))
        );

        // Crear materias para Pedro
        Materia lenguajePedro = new Materia(
                MateriaEnum.LENGUAJE,
                new ArrayList<>(Arrays.asList(5.8, 5.5))
        );

        // Crear a Pedro (solo con una materia inicialmente)
        Alumno pedro = new Alumno(
                "19.334.567-8",
                "Pedro",
                "Sánchez",
                "Pasaje Los Pinos 789",
                new ArrayList<>(Arrays.asList(lenguajePedro))
        );

        // Agregar todos los alumnos al Map
        listaAlumnos.put(juan.getRut(), juan);
        listaAlumnos.put(maria.getRut(), maria);
        listaAlumnos.put(pedro.getRut(), pedro);
    }

    public Map<String, Alumno> listaAlumnos() {
        return new HashMap<>(listaAlumnos);
    }

    public void crearAlumno(Alumno alumno) {
        if (alumno != null) {
            listaAlumnos.put(alumno.getRut(), alumno);
            //System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellido() + " ha sido creado correctamente");
        }
    }

    public void agregarMateria(String rut, Materia materia) {
        if (rut != null && materia != null) {

            Alumno alumno = listaAlumnos.get(rut);
            if (alumno != null) {
                alumno.getMaterias().add(materia);
            } else {
                System.out.println("El alumno no existe");
            }
        }
    }

    public List<Materia> materiasPorAlumnos(String rut) {
        if (rut != null) {
            Alumno alumn = listaAlumnos.get(rut);
            if (alumn != null && !alumn.getMaterias().isEmpty()) {
                return alumn.getMaterias();
            }
        }
        System.out.println("El alumno no tiene materias registradas.");
        return null;
    }

    public int cantidadMaterias(String rut) {
        if (rut != null) {
            Alumno alumn = listaAlumnos.get(rut);
            if (alumn != null) {
                return alumn.getMaterias().size();
            }
        }
        return 0;
    }

    public List<Materia> validarMaterias(String rut) {
        if (rut != null) {
            Alumno alumn = listaAlumnos.get(rut);
            if (alumn != null) {
                if(alumn.getMaterias().isEmpty()){
                    System.out.println("El alumno no tiene materias registradas.");
                }
                if(alumn.getMaterias().size() == 4){
                    System.out.println("El alumno tiene todas las materias registradas.");
                }
                return alumn.getMaterias();
            }
        }
        return null;
    }

    public boolean validarRut(String rut) {
        if (rut != null || !rut.isEmpty()) {
            for (Alumno alumno : listaAlumnos().values()) {
                if (alumno.getRut().equals(rut)) {
                    //System.out.println("El alumno con rut " + alumno.getRut() + " es valido.");
                    return false;
                }
            }

        }/*else{
            //System.out.println("El rut ingresado no es valido.");
        }*/
        //System.out.println("El rut ingresado no es valido.");
        return true;
    }

    public boolean validarOpcionMateria(String rut, int opcion) {
        if (opcion < 1 || opcion > 4) return false;
        MateriaEnum materiaEnum1 = MateriaEnum.values()[opcion - 1];
        for (Alumno a : listaAlumnos().values()) {
            if (a.getRut().equals(rut)) {
                for (Materia m : a.getMaterias()) {
                    if (m.getNombre().equals(materiaEnum1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void mostrarListaAlumnos() {
        int cont = 1;
        System.out.println("=== Lista de Alumnos ===");
        for (Alumno alumno : listaAlumnos().values()) {
            System.out.println("Alumno " + (cont++) + ": " + "Rut: " + alumno.getRut() + " - Nombre: " + alumno.getNombre() + " - Apellido: " + alumno.getApellido());
        }
    }

}
