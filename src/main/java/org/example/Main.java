package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;

        asciiAct();


        while (seguir) {


            System.out.println("A continuación, ingresa la materia de la tarea a registrar");
            String materia = sc.nextLine().trim();
            while (materia.equals("")) {
                System.out.println("La materia no puede estar vacía. Intenta de nuevo:");
                materia = sc.nextLine().trim();
            }
            materia = capitalizar(materia);


            System.out.println("Ingresa el nombre de tu tarea a registrar");
            String nombre = sc.nextLine().trim();
            while (nombre.equals("")) {
                System.out.println("El nombre no puede estar vacío. Intenta de nuevo:");
                nombre = sc.nextLine().trim();
            }



            System.out.println("Ingresa el tipo de tarea de acuerdo a lo siguiente:");
            System.out.println("0) Tarea ligera  1) Tarea regular  2) Entregable  3) Proyecto  4) Estudio para examen");
            int tipo = -1;
            while (true) {
                String t = sc.nextLine().trim();
                try {
                    tipo = Integer.parseInt(t);
                    if (tipo >= 0 && tipo <= 4) break;
                } catch (Exception e) {
                    // no hacer nada, pedir de nuevo
                }
                System.out.println("Opción no válida. Ingresa un número entre 0 y 4:");
            }

            //parsing básico
            LocalDate fechaHoy = null;
            while (fechaHoy == null) {
                System.out.println("Ingresa la fecha de hoy siguiendo la siguiente nomenclatura:\n" +
                        "día/mes/año (dd/MM/yyyy):");
                String f = sc.nextLine().trim();
                fechaHoy = parseFechaSimple(f);
                if (fechaHoy == null) {
                    System.out.println("Formato inválido. Usa dd/MM/yyyy (ej: 26/10/2025).");
                }
            }


            LocalDate fechaEntrega = null;
            while (fechaEntrega == null) {
                System.out.println("Ingresa la fecha de entrega de la tarea registrada, siguiendo la siguiente nomenclatura:\n" + "día/mes/año (dd/MM/yyyy):");
                String f = sc.nextLine().trim();
                fechaEntrega = parseFechaSimple(f);
                if (fechaEntrega == null) {
                    System.out.println("Formato inválido. Usa dd/MM/yyyy (ej: 26/10/2025).");
                }
            }

            // días restantes
            long dias = ChronoUnit.DAYS.between(fechaHoy, fechaEntrega);
            System.out.println("Días restantes hasta la entrega: " + dias);

            if (dias < 0) {
                System.out.println("Valor negativo, verifica las fechas. Se te regresará al menú.");
            } else {


                String tipoTexto = "Desconocido";
                if (tipo == 0) tipoTexto = "Tarea ligera";
                if (tipo == 1) tipoTexto = "Tarea regular";
                if (tipo == 2) tipoTexto = "Entregable";
                if (tipo == 3) tipoTexto = "Proyecto";
                if (tipo == 4) tipoTexto = "Estudio para examen";


                String clasificacion;
                if (dias <= 3) clasificacion = "MUY URGENTE";
                else if (dias <= 7) clasificacion = "NECESARIA";
                else if (dias <= 10) clasificacion = "ADELANTARLO";
                else if (dias <= 15) clasificacion = "TOMAR EN CUENTA";
                else clasificacion = "PLANIFICAR SIN PRISA";


                asciiAct();


                // Mostrar resumen
                System.out.println("\n¡Registro de actividad completado! A continuación, se mostrará el resultado según los datos ingresados...");
                System.out.println("Materia: " + materia);
                System.out.println("Tarea: " + nombre);
                System.out.println("Tipo: " + tipoTexto);

                System.out.println("Según el modelo de la matríz de Eisenhower, tu tarea será clasificada como " + clasificacion + ", ponte manos a la obra, pues tan solo te faltan " + dias + " días para la entrega.");

                if (dias <= 7) {
                    System.out.println("¡No permitas que se acumule!");
                }
            }




            System.out.println("\n¿Deseas registrar otra tarea? (s/n)");
            String resp = sc.nextLine().trim().toLowerCase();
            while (!resp.equals("s") && !resp.equals("n")) {
                System.out.println("Responde con 's' para sí o 'n' para no:");
                resp = sc.nextLine().trim().toLowerCase();
            }
            if (resp.equals("s")) {
                seguir = true;
            } else {

                // REDES SOCIALES
                asciiSociales();

                        System.out.println("Ingresa el tiempo en MINUTOS que pasas en promedio por día en redes sociales o distractores digitales:");

                        int tiemRed = -1;
                        while (tiemRed < 0) {
                            String linea = sc.nextLine().trim();
                            try {
                                tiemRed = Integer.parseInt(linea);
                                if (tiemRed < 0) {
                                    System.out.println("No se aceptan números negativos. Ingresa los minutos (ej: 45):");
                                }
                            } catch (Exception e) {
                                System.out.println("Entrada no válida. Ingresa un número entero de minutos (ej: 45):");
                            }
                        }

                        evaluarTiempoEnRedes(tiemRed);




                // volver al menú
                System.out.println(  );
                System.out.println("¿Necesitas volver al menú? (s/n)");
                String resp2 = sc.nextLine().trim().toLowerCase();
                while (!resp2.equals("s") && !resp2.equals("n")) {
                    System.out.println("Responde con 's' o 'n':");
                    resp2 = sc.nextLine().trim().toLowerCase();
                }
                if (resp2.equals("s")) {
                    seguir = true;
                    // DEBE DE VOLVER A TREE OF TIME



                } else {
                    seguir = false;
                }
            }
            System.out.println();
        }

        System.out.println("Se cerrará la sesión de hoy. Gracias por registrar actividades. ¡Éxito en tus entregas!");
        sc.close(); //SE DEBE DE MANDAR A LLAMAR AL CIERRE DE SESIÓN
    }

    //convertir "dd/MM/yyyy" a LocalDate
    private static LocalDate parseFechaSimple(String texto) {
        try {
            String[] partes = texto.split("/");
            if (partes.length != 3) return null;
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);
            return LocalDate.of(anio, mes, dia);
        } catch (Exception e) {
            return null;
        }
    }

    // Capitalizar la primera letra
    private static String capitalizar(String s) {
        if (s == null || s.length() == 0) return s;
        String primera = s.substring(0, 1).toUpperCase();
        String resto = "";
        if (s.length() > 1) resto = s.substring(1).toLowerCase();
        return primera + resto;
    }

        static void asciiAct() {
            final String VERDE = "\u001B[32m"; // verde hoja
            final String RESET = "\u001B[0m";

            String ascii =
                    "        _______      __   __       __    __          __             \n" +
                            " |   _   .----|  |_|__.--.--|__.--|  .---.-.--|  .-----.-----.\n" +
                            " |.  1   |  __|   _|  |  |  |  |  _  |  _  |  _  |  -__|__ --|\n" +
                            " |.  _   |____|____|__|\\___/|__|_____|___._|_____|_____|_____|\n" +
                            " |:  |   |                                                    \n" +
                            " |::.|:. |                                                    \n" +
                            " `--- ---'                                                    \n" +
                            "                                                              ";

            int anchoPantalla = 100;

            for (String linea : ascii.split("\n")) {
                int espacios = (anchoPantalla - linea.length()) / 2;
                if (espacios < 0) espacios = 0;
                System.out.println(" ".repeat(espacios) + VERDE + linea + RESET);
            }
        }

        static void asciiSociales() {
            final String AZUL = "\u001B[94m"; // azul eléctrico (bright blue)
            final String RESET = "\u001B[0m";

            String ascii =
                    "        _______          __                 _______            __       __             \n" +
                            " |   _   .-----.--|  .-----.-----.   |   _   .-----.----|__.---.-|  .-----.-----.\n" +
                            " |.  l   |  -__|  _  |  -__|__ --|   |   1___|  _  |  __|  |  _  |  |  -__|__ --|\n" +
                            " |.  _   |_____|_____|_____|_____|   |____   |_____|____|__|___._|__|_____|_____|\n" +
                            " |:  |   |                           |:  1   |                                   \n" +
                            " |::.|:. |                           |::.. . |                                   \n" +
                            " `--- ---'                           `-------'                                   \n" +
                            "                                                                                 ";

            int anchoPantalla = 100;

            for (String linea : ascii.split("\n")) {
                int espacios = (anchoPantalla - linea.length()) / 2;
                if (espacios < 0) espacios = 0;
                System.out.println(" ".repeat(espacios) + AZUL + linea + RESET);
            }
        }

    public static void evaluarTiempoEnRedes(int minutos) {
    int mitad = minutos / 2;

    if (minutos <= 30) {
        System.out.println("\nBien hecho, tu tiempo en pantalla es mínimo al promedio.");
        System.out.println("Si deseas explotar al máximo tu tiempo intenta reducir tu consumo a " + mitad + " minutos.");
    } else if (minutos <= 60) {
        System.out.println("\n¡Estás en el promedio de tiempo! Nos gusta ver que te superas.");
        System.out.println("Como reto podrías intentar " + mitad + " minutos diarios.");
    } else if (minutos <= 180) {
        System.out.println("\n¡Vaya! Necesitas reducir tu tiempo en pantalla.");
        System.out.println("Recomendamos que a partir de ahora intentes pasar " + mitad + " minutos.");
    } else if (minutos <= 360) {
        System.out.println("\n¡Nos parece bastante tiempo en redes sociales! No te preocupes, puedes cambiarlo.");
        System.out.println("Si buscas mejorar, reduce a " + mitad + " minutos.");
    } else if (minutos <= 720) {
        System.out.println("\n¡Es preocupante el tiempo empleado en redes sociales diariamente!");
        System.out.println("Debes hacer un cambio definitivo. Los primeros días será difícil, intenta " + mitad + " minutos diarios.");
        System.out.println("Como recomendación intenta reemplazar estos 'shots' de dopamina con algún hobby o hábitos recomendados.");
    } else {
        System.out.println("\nTiempo extremadamente alto en redes sociales. Es urgente hacer cambios.");
        System.out.println("Empieza por reducir a " + mitad + " minutos y busca apoyo si lo necesitas.");
    }
    }
}

