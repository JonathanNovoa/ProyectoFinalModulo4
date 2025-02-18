package org.example.view;

import java.util.Scanner;

public abstract class MenuTemplate {

    Scanner sc = new Scanner(System.in);
    int opcion;

    public abstract void exportarDatos();

    public abstract void crearAlumno();

    public abstract void agregarMateria();

    public abstract void agregarNotaPasoUno();

    public abstract void listaAlumnos();

    public abstract void terminarPrograma();

    public int validarEntrada() {
        boolean validar = false;
        int opcion = 0;
        while (!validar) {
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                validar = true;
            } else {
                System.out.println("Por favor, ingresa un valor númerico");
                System.out.print("Ingrese una opcion: ");
                sc.nextLine();
            }
        }
        return opcion;
    }

    public double validarEntradaDouble() {
        boolean validar = false;
        double opcion = 0;
        while (!validar) {
            if (sc.hasNextDouble()) {
                opcion = sc.nextDouble();
                validar = true;
            } else {
                System.out.println("Por favor, ingresa un valor númerico double");
                System.out.print("Ingrese una opcion: ");
                sc.nextLine();
            }
        }
        return opcion;
    }

    public void iniciarMenu() {

        int opcion = 0;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Crear Alumno");
            System.out.println("2. Listar Alumnos");
            System.out.println("3. Agregar Materia");
            System.out.println("4. Agregar Nota");
            System.out.println("5. Exportar Datos");
            System.out.println("6. Salir del Programa");
            System.out.println("======================");
            System.out.print("Ingrese una opcion: ");
            opcion = validarEntrada();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    listaAlumnos();
                    break;
                case 3:
                    agregarMateria();
                    break;
                case 4:
                    agregarNotaPasoUno();
                    break;
                case 5:
                    exportarDatos();
                    break;
                case 6:
                    terminarPrograma();
                    break;
                default:
                    System.out.println("Ingrese una opción válida.\n");
            }
        } while (opcion != 6);
    }
}
