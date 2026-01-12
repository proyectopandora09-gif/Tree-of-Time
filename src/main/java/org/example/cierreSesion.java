package org.example;

import java.util.Scanner;

public class cierreSesion {
    public static void main(String[] args) {

        String[] actreg = new String[4];
        actreg[0] = "Diagrama de flujo";
        actreg[1] = "Fichas de referencia";
        actreg[2] = "Lámina de dibujo";
        actreg[3] = "Codificación";

        String[] actcomp = new String[4];
        actcomp[0] = "Diagrama de flujo";
        actcomp[1] = "Fichas de referencia";
        actcomp[2] = "Lámina de dibujo";
        actcomp[3] = "Codificación";

        String[] habitos = new String[3];
        habitos[0] = "Natación";
        habitos[1] = "Sudoku";
        habitos[2] = "Problemas matemáticos";

        final String VERDE = "\u001B[32m";
        final String RESET = "\u001B[0m";
        String ascii = "" +
                " __     ___   __    __    ___     __     ___    __    ___  __      __      \n" +
                "/  ` | |__   |__)  |__)  |__     |  \\   |__    /__`  |__  /__`  | /  \\  |\\ |\n" +
                "\\__, | |___  |  \\  |  \\  |___    |__/   |___   .__/  |___ .__/  | \\__/  | \\| \n";
        int anchoPantalla = 100;
        for (String linea : ascii.split("\n"))
        {
            int espacios = (anchoPantalla - linea.length())/2;
            System.out.println("".repeat(Math.max(0, espacios))+ VERDE + linea + RESET);
        }
        System.out.println("¡Felicidades! Has llegado al final de la sesión de hoy. Éstos son los resultados que obtuviste según tu rendimiento. ");
        System.out.println("Éstas son las tareas que registraste:");
        for (int i = 0; i < actreg.length; i++) {
            System.out.println(actreg[i]);
        }
        System.out.println("Y éstas son las tareas que fuiste completando:");
        for (int i = 0; i < actcomp.length; i++) {
            System.out.println(actcomp[i]);
        }
        System.out.println("¡Muy bien hecho! ¡Recuerda que cualquier progreso es significativo!");
        System.out.println("A continuación te mostramos la lista de los hábitos que has registrado junto a los que se te recomendaron.");
        for (int i = 0; i < habitos.length; i++) {
            System.out.println(habitos[i]);
        }

        System.out.println("Tómalo en cuenta.");
        System.out.println("Recuerda que el tiempo es un recurso muy valioso. De acuerdo a estudios de la Universidad de Harvard,\n" +
                " las personas que establecen metas claras y priorizan su tiempo son más propensas a alcanzar sus objetivos. ");
        System.out.println("También ten en cuenta no se trata de hacer más, se trata de hacer lo que es importante para lograr tus objetivos. ");
        System.out.println("¡Muchas gracias por usar nuestra aplicación, esperamos que vuelvas pronto!");
        condicional();
        System.out.println("¿Deseas volver a utilizar la aplicación? Por favor sólo escribe números.");
        System.out.println(" 1. Sí");
        System.out.println(" 2. No");
        Scanner sc = new Scanner(System.in);
        char opcion = sc.next().charAt(0);
        if (opcion > '2' || opcion < '1') {
            System.out.println("Error, no es una opción válida");
        } else {
            if (opcion == '1') {
                System.out.println("regreso");
                //reinicio del programa
                System.exit(0);
            } else {
                System.out.println("fin");
                //se termina el programa
                System.exit(0);
            }
        }
        while (opcion > '2' || opcion < '1') {
            System.out.println(condicional());
        }
    }

    private static char condicional() {
        System.out.println("¿Deseas volver a utilizar la aplicación? Por favor sólo escribe números.");
        System.out.println(" 1. Sí");
        System.out.println(" 2. No");
        Scanner sc = new Scanner(System.in);
        char opcion = sc.next().charAt(0);
        if (opcion > '2' || opcion < '1') {
            System.out.println("Error, no es una opción válida");
        } else {
            if (opcion == '1') {
                System.out.println("regreso");
                //reinicio del programa
            } else {
                System.out.println("fin");
                //se termina el programa
                System.exit(0);
            }
        }
        return 0;
    }
}