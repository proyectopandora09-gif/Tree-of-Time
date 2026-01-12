package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class seguimiento {

    // -----------------------
    // ARREGLOS FIJOS (globales)
    // -----------------------
    // Aquí se guardan las tareas y hábitos mientras el programa corre.
    // Son estáticos para que todos los métodos static puedan acceder sin pasarlos como parámetros.
    private static String[] tareas = new String[100];        // hasta 100 tareas
    private static int tareasCount = 0;

    private static String[] tareasCompletadas = new String[100];
    private static int tareasCompletadasCount = 0;

    private static String[] habitos = new String[100];       // hasta 100 hábitos
    private static int habitosCount = 0;

    private static String[] habitosCompletados = new String[100];
    private static int habitosCompletadosCount = 0;

    // Para guardar el tiempo en redes sociales (opcional, usado en recomendaciones)
    private static int tiempoEnRedes = -1;


    // -----------------------
    // VISTA DE SEGUIMIENTO (módulo con opciones 1..11)
    // -----------------------
    private static void vistaDeSeguimiento(Scanner sc, String usuario) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nVISTA DE SEGUIMIENTO");
            System.out.println();
            System.out.println("Bienvenid@ a la vista de seguimiento, a continuación te mostraremos las opciones a usar:");
            System.out.println("1) Observar las actividades registradas hasta el momento");
            System.out.println("2) Observar los hábitos registrados hasta el momento");
            System.out.println("3) Marcar como completadas las actividades");
            System.out.println("4) Marcar como completados los hábitos");
            System.out.println("5) Eliminar actividades registradas");
            System.out.println("6) Eliminar hábitos registrados");
            System.out.println("7) Recomendaciones de tiempo en redes sociales");
            System.out.println("8) Observar las actividades pendientes");
            System.out.println("9) Observar las actividades completadas");
            System.out.println("10) Volver al menú");
            System.out.println("11) Cerrar sesión");

            String op = sc.nextLine().trim();

            switch (op) {
                case "1":
                    mostrarTareasRegistradas();
                    break;
                case "2":
                    mostrarHabitosRegistrados();
                    break;
                case "3":
                    marcarActividadCompletada(sc);
                    break;
                case "4":
                    marcarHabitoCompletado(sc);
                    break;
                case "5":
                    eliminarActividad(sc);
                    break;
                case "6":
                    eliminarHabito(sc);
                    break;
                case "7":
                    mostrarRecomendacionRedes();
                    break;
                case "8":
                    mostrarActividadesPendientes();
                    break;
                case "9":
                    mostrarActividadesCompletadas();
                    break;
                case "10":
                    // volver al menú Tree of Time
                    mostrarArbolYTitulo();
                    return;
                case "11":
                    cierreSesion(sc, usuario);
                    return;
                default:
                    System.out.println("Opción no válida. Ingresa un número entre 1 y 11.");
            }

            System.out.println();
        }
    }

    // -----------------------
    // MÉTODOS AUXILIARES PARA VISTA DE SEGUIMIENTO
    // -----------------------
    private static void mostrarTareasRegistradas() {
        System.out.println("\nActividades registradas hasta el momento:");
        if (tareasCount == 0) {
            System.out.println("No hay actividades registradas.");
            return;
        }
        for (int i = 0; i < tareasCount; i++) {
            System.out.println((i + 1) + ") " + tareas[i]);
        }
    }

    private static void mostrarHabitosRegistrados() {
        System.out.println("\nHábitos registrados hasta el momento:");
        if (habitosCount == 0) {
            System.out.println("No hay hábitos registrados.");
            return;
        }
        for (int i = 0; i < habitosCount; i++) {
            System.out.println((i + 1) + ") " + habitos[i]);
        }
    }

    private static void marcarActividadCompletada(Scanner sc) {
        if (tareasCount == 0) {
            System.out.println("No hay actividades para marcar como completadas.");
            return;
        }
        mostrarTareasRegistradas();
        System.out.println("\nEscriba el número de actividad que desee marcar como completada.");
        int num = pedirNumeroValido(sc, 1, tareasCount);
        // mover a completadas
        String seleccion = tareas[num - 1];
        if (tareasCompletadasCount < tareasCompletadas.length) {
            tareasCompletadas[tareasCompletadasCount] = seleccion;
            tareasCompletadasCount++;
        }
        // eliminar de tareas pendientes (shift)
        eliminarElementoArray(tareas, num - 1, tareasCount);
        tareasCount--;

        System.out.println("La tarea se ha marcado como completada con éxito");

        System.out.println("¿Desea volver a marcar una tarea como completada? (s/n)");
        String r = sc.nextLine().trim().toLowerCase();
        if (r.equals("s")) {
            marcarActividadCompletada(sc);
            return;
        }

        System.out.println("¿Desea visualizar las tareas completadas? (s/n)");
        String r2 = sc.nextLine().trim().toLowerCase();
        if (r2.equals("s")) {
            mostrarActividadesCompletadas();
        }
    }

    private static void marcarHabitoCompletado(Scanner sc) {
        if (habitosCount == 0) {
            System.out.println("No hay hábitos para marcar como completados.");
            return;
        }
        mostrarHabitosRegistrados();
        System.out.println("\nEscriba el número de hábito que desee marcar como completado.");
        int num = pedirNumeroValido(sc, 1, habitosCount);
        String seleccion = habitos[num - 1];
        if (habitosCompletadosCount < habitosCompletados.length) {
            habitosCompletados[habitosCompletadosCount] = seleccion;
            habitosCompletadosCount++;
        }
        eliminarElementoArray(habitos, num - 1, habitosCount);
        habitosCount--;

        System.out.println("El hábito se ha marcado como completado con éxito");

        System.out.println("¿Desea volver a marcar un hábito como completado? (s/n)");
        String r = sc.nextLine().trim().toLowerCase();
        if (r.equals("s")) {
            marcarHabitoCompletado(sc);
            return;
        }

        System.out.println("¿Desea visualizar los hábitos completados? (s/n)");
        String r2 = sc.nextLine().trim().toLowerCase();
        if (r2.equals("s")) {
            mostrarHabitosCompletados();
        }
    }

    private static void eliminarActividad(Scanner sc) {
        if (tareasCount == 0) {
            System.out.println("No hay actividades para eliminar.");
            return;
        }
        mostrarTareasRegistradas();
        System.out.println("\nEscriba el número de actividad que desee eliminar");
        int num = pedirNumeroValido(sc, 1, tareasCount);
        eliminarElementoArray(tareas, num - 1, tareasCount);
        tareasCount--;
        System.out.println("La actividad se ha eliminado con éxito");

        System.out.println("¿Desea eliminar otra actividad? (s/n)");
        String r = sc.nextLine().trim().toLowerCase();
        if (r.equals("s")) {
            eliminarActividad(sc);
        }
    }

    private static void eliminarHabito(Scanner sc) {
        if (habitosCount == 0) {
            System.out.println("No hay hábitos para eliminar.");
            return;
        }
        mostrarHabitosRegistrados();
        System.out.println("\nEscriba el número del hábito que desea eliminar:");
        int num = pedirNumeroValido(sc, 1, habitosCount);
        eliminarElementoArray(habitos, num - 1, habitosCount);
        habitosCount--;
        System.out.println("El hábito se ha eliminado con éxito");

        System.out.println("¿Desea eliminar otro hábito? (s/n)");
        String r = sc.nextLine().trim().toLowerCase();
        if (r.equals("s")) {
            eliminarHabito(sc);
        }
    }

    private static void mostrarRecomendacionRedes() {
        if (tiempoEnRedes < 0) {
            System.out.println("No registraste tu tiempo en redes sociales aún.");
            return;
        }
        int sugerido = Math.max(0, tiempoEnRedes / 2);
        System.out.println("\nRegistraste que pasas aproximadamente " + tiempoEnRedes + " minutos en las redes sociales por día. Sugerimos bajar el tiempo de distractores digitales a " + sugerido + " minutos por día, el tiempo lo puedes aprovechar realizando otras actividades, tales como: leer un libro, practicar un deporte, pasar tiempo con personas queridas, adelantar tareas, etc. ¡Aprovecha tu tiempo!");
    }

    private static void mostrarActividadesPendientes() {
        System.out.println("\nActividades pendientes:");
        if (tareasCount == 0) {
            System.out.println("No hay actividades pendientes.");
            return;
        }
        for (int i = 0; i < tareasCount; i++) {
            System.out.println((i + 1) + ". " + tareas[i]);
        }
    }

    private static void mostrarActividadesCompletadas() {
        System.out.println("\nActividades completadas:");
        if (tareasCompletadasCount == 0) {
            System.out.println("No hay actividades completadas.");
            return;
        }
        for (int i = 0; i < tareasCompletadasCount; i++) {
            System.out.println((i + 1) + ". " + tareasCompletadas[i]);
        }
    }

    private static void mostrarHabitosCompletados() {
        System.out.println("\nHábitos completados:");
        if (habitosCompletadosCount == 0) {
            System.out.println("No hay hábitos completados.");
            return;
        }
        for (int i = 0; i < habitosCompletadosCount; i++) {
            System.out.println((i + 1) + ". " + habitosCompletados[i]);
        }
    }

    // -----------------------
    // UTILIDADES
    // -----------------------
    private static int pedirNumeroValido(Scanner sc, int min, int max) {
        while (true) {
            String linea = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(linea);
                if (n >= min && n <= max) return n;
            } catch (Exception e) {
                // seguir pidiendo
            }
            System.out.println("Entrada no válida. Ingresa un número entre " + min + " y " + max + ":");
        }
    }

    // elimina el elemento en index de array y hace shift a la izquierda
    private static void eliminarElementoArray(String[] array, int index, int currentCount) {
        for (int i = index; i < currentCount - 1; i++) {
            array[i] = array[i + 1];
        }
        array[currentCount - 1] = null;
    }

}
