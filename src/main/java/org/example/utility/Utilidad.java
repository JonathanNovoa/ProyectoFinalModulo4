package org.example.utility;

import java.util.Scanner;

public class Utilidad {

    public int validarEntrada() {
        Scanner sc = new Scanner(System.in);
        boolean validar = false;
        int opcion = 0;
        while (!validar) {
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                validar = true;
            } else {
                System.out.print("Por favor, ingresa un valor númerico");
                System.out.print("Ingrese una opcion: ");
                sc.nextLine();
                System.out.println();
            }
        }
        return opcion;
    }

    public boolean validarOpcionMateria(int opcion) {
        if (opcion >= 1 && opcion <= 4) {
            return true;
        } else {
            System.out.println("Por favor, ingrese un valor entre 1 y 4.");
        }
        return false;
    }
}
