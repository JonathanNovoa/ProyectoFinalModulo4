 package org.example;

 import org.example.service.AlumnoServicio;
 import org.example.view.Menu;

 public class Main {
    public static void main(String[] args) {

        AlumnoServicio alumnoServicio = new AlumnoServicio();

        Menu menu = new Menu();
        //menu.agregarMateria();
        menu.iniciarMenu();
    }
}