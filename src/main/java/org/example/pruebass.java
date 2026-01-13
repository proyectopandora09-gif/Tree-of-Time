package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


public class pruebass {
    private static String[] tareas = new String[100];
    private static int tareasCount = 0;

    static Scanner lector = new Scanner(System.in);

    static String[] habitos = new String[20];
    static int indiceHabito = 0;

    static String[] mental = new String[20];
    static int indiceMental = 0;

    static String[] emocional = new String[20];
    static int indiceEmocional = 0;

    static String[] fisico = new String[20];
    static int indiceFisico = 0;

    static int tiemRed = -1;

    static boolean[] tareasCompletadasFlag = new boolean[100];

    static String[] tareasCompletadas = new String[100];
    static int tareasCompletadasCount = 0;

    static String[] tareasPendientes = new String[100];
    static int tareasPendientesCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        mostrarArbolYTitulo();

        System.out.println();
        System.out.print("Ingresa nombre de usuario: ");
        String usuario = sc.nextLine().trim();


        menuPrincipal(sc, usuario);

    }


    private static void menuPrincipal(Scanner sc, String usuario)  {

        System.out.println();
        System.out.println("Bienvenid@ " + usuario + " al programa gestor de actividades y hábitos, estamos listos para ayudarte");
        System.out.println("Elige la opción con la que deseas trabajar, solo teclea el número de la opción.");
        System.out.println();

        System.out.println("1) Registro de tareas");
        System.out.println("2) Análisis de hábitos");
        System.out.println("3) Vista de seguimiento");
        System.out.println("4) Cierre de sesión e informe");

        boolean salir = false;
        while (!salir) {

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    registroDeTareas(sc, usuario);
                    break;
                case "2":
                    analisisDeHabitos(sc, usuario);
                    break;
                case "3":
                    if (puedeEntrarVistaSeguimiento(sc)) {
                        vistaDeSeguimiento(sc, usuario);
                    }
                    break;
                case "4":
                    cierreSesionEInforme(sc, usuario);
                    mostrarArbolYTitulo();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor ingresa un número entre 1 y 4.");
            }

            if (!salir) {
                System.out.println();
            }

        }
        System.out.println("\nPrograma finalizado. ¡Hasta luego!");
    }



    // Registro de tareas
    private static void registroDeTareas(Scanner sc, String usuario) {
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
            nombre = capitalizar(nombre);


            System.out.println("Ingresa el tipo de tarea de acuerdo a lo siguiente:");
            System.out.println("0) Tarea ligera  1) Tarea regular  2) Entregable  3) Proyecto  4) Estudio para examen");
            int tipo = -1;
            while (true) {
                String t = sc.nextLine().trim();
                try {
                    tipo = Integer.parseInt(t);
                    if (tipo >= 0 && tipo <= 4) break;
                } catch (Exception e) {
                    // para pedir de nuevo
                }
                System.out.println("Opción no válida. Ingresa un número entre 0 y 4:");
            }




            boolean fechasValidas = false;

            while (!fechasValidas) {

                // parsing básico
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
                    System.out.println("Ingresa la fecha de entrega de la tarea registrada, siguiendo la siguiente nomenclatura:\n" +
                            "día/mes/año (dd/MM/yyyy):");
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
                    System.out.println("Valor negativo, verifica las fechas.");
                    // NO fechasValidas el while vuelve a empezar

                } else {
                    fechasValidas = true;

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

                    // resumen
                    System.out.println("\n¡Registro de actividad completado! A continuación, se mostrará el resultado según los datos ingresados...");
                    System.out.println("Materia: " + materia);
                    System.out.println("Tarea: " + nombre);
                    System.out.println("Tipo: " + tipoTexto);

                    System.out.println("Según el modelo de la matríz de Eisenhower, tu tarea será clasificada como " + clasificacion +
                            ", ponte manos a la obra, pues tan solo te faltan " + dias + " días para la entrega.");

                    if (dias <= 7) {
                        System.out.println("¡No permitas que se acumule!");
                    }
                    // arrTAREAS
                    String registro = materia + " | " + nombre + " | " + tipoTexto + " | " + fechaHoy + " -> " + fechaEntrega + " | Días: " + dias;
                    if (tareasCount < tareas.length) {
                        tareas[tareasCount] = registro;
                        tareasCount++;
                    } else {
                        System.out.println("No se pueden guardar más tareas: el arreglo está lleno.");
                    }

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

                // SOCIALES
                asciiSociales();

                System.out.println("Ingresa el tiempo en MINUTOS que pasas en promedio por día en redes sociales o distractores digitales:");



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
                System.out.println();
                System.out.println("¿Necesitas volver al menú? (s/n)");
                String resp2 = sc.nextLine().trim().toLowerCase();
                while (!resp2.equals("s") && !resp2.equals("n")) {
                    System.out.println("Responde con 's' o 'n':");
                    resp2 = sc.nextLine().trim().toLowerCase();
                }

                if (resp2.equals("s")) {
                    mostrarArbolYTitulo();
                    menuPrincipal(sc, usuario);
                    return;
                } else {
                    System.out.println("Se cerrará la sesión de hoy. Gracias por registrar actividades. ¡Éxito en tus entregas!");
                    cierreSesionEInforme(sc, usuario);
                    return;
                }
            }

        }

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

        if (minutos == 0) {
            System.out.println("\nExcelente, no usas redes sociales. Mantén ese hábito. ");
            return;
        }

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



    // Análisis de hábitos
    private static void analisisDeHabitos(Scanner sc, String usuario) {
        boolean seguirMenu = true;

        while (seguirMenu) {

            mostrarAsciiArt();

            System.out.println("\n¡Bienvenido usuario! ¿Qué deseas hacer hoy? \n" +
                    " 1) Registrar hábito \n" +
                    " 2) Recomendación de hábito \n" +
                    " 3) Salir al menú Tree of Time");

            int respuesta = leerEntero("Ingresa la opción (1-3):", 1, 3);

            mostrarAsciiCafe();
            System.out.println("""
                       
                        """);

            switch (respuesta) {
                case 1:
                    registro();
                    break;
                case 2:
                    recomendacion();
                    break;
                case 3:
                    System.out.println("Saliendo de análisis de hábitos... ¡Hasta luego!");
                    seguirMenu = false;
                    mostrarArbolYTitulo();
                    menuPrincipal(sc, usuario);
                    break;
                default:
                    System.out.println("Por favor ingrese un número válido.");
            }
        }
    }

    // leer enteros con validación
    private static int leerEntero (String mensaje,int min, int max){
        while (true) {
            System.out.println(mensaje);
            String linea = lector.nextLine().trim();
            try {
                int val = Integer.parseInt(linea);
                if (min <= max) {
                    if (val < min || val > max) {
                        System.out.println("Por favor ingresa un número entre " + min + " y " + max + ".");
                        continue;
                    }
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingresa un número entero.");
            }
        }
    }

    static void mostrarAsciiArt () {

        final String VERDE = "\u001B[32m";
        final String RESET = "\u001B[0m";

        String dibujo = """
                      ██╗  ██╗ █████╗ ██████╗ ██╗████████╗ ██████╗ ███████╗
                      ██║  ██║██╔══██╗██╔══██╗██║╚══██╔══╝██╔═══██╗██╔════╝
                      ███████║███████║██████╔╝██║   ██║   ██║   ██║███████╗
                      ██╔══██║██╔══██║██╔══██╗██║   ██║   ██║   ██║╚════██║
                      ██║  ██║██║  ██║██████╔╝██║   ██║   ╚██████╔╝███████║
                      ╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝   ╚═╝    ╚═════╝ ╚══════╝
                    
                    
                    
                    """;

        int anchoPantalla = 80;

        for (String linea : dibujo.split("\n")) {
            int espacios = (anchoPantalla - linea.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, espacios)) + VERDE + linea + RESET);
        }
    }

    static void mostrarAsciiRegistro () {

        final String NARANJA = "\u001B[93m"; // naranja (café)
        final String RESET = "\u001B[0m";


        String dibujo = """
                                    ██████╗ ███████╗ ██████╗ ██╗███████╗████████╗██████╗  ██████╗\s
                                    ██╔══██╗██╔════╝██╔════╝ ██║██╔════╝╚══██╔══╝██╔══██╗██╔═══██╗
                                    ██████╔╝█████╗  ██║  ███╗██║███████╗   ██║   ██████╔╝██║   ██║
                                    ██╔══██╗██╔══╝  ██║   ██║██║╚════██║   ██║   ██╔══██╗██║   ██║
                                    ██║  ██║███████╗╚██████╔╝██║███████║   ██║   ██║  ██║╚██████╔╝
                                    ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝\s
                    
                    
                    """;

        int anchoPantalla = 80;

        for (String linea : dibujo.split("\n")) {
            int espacios = (anchoPantalla - linea.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, espacios)) + NARANJA + linea + RESET);
        }
    }

    static void mostrarAsciiCafe () {

        final String CAFE = "\u001B[33m"; // amarillo oscuro (café)
        final String RESET = "\u001B[0m";

        String ascii =
                "                                       _            \n" +
                        " _ __   ___ _ __  ___  __ _ _ __   __| | ___       \n" +
                        "| '_ \\ / _ \\ '_ \\/ __|/ _` | '_ \\ / _` |/ _ \\      \n" +
                        "| |_) |  __/ | | \\__ \\ (_| | | | | (_| | (_) | _ _ \n" +
                        "| .__/ \\___|_| |_|___/\\__,_|_| |_|\\__,_|\\___(_|_|_)\n" +
                        "|_|                                                \n";

        int anchoPantalla = 100;

        for (String linea : ascii.split("\n")) {
            int espacios = (anchoPantalla - linea.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, espacios)) + CAFE + linea + RESET);
        }
    }

    static void mostrarRecomendaciones () {

        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";

        String dibujo = """
                                    ██████╗ ███████╗ ██████╗ ██████╗ ███╗   ███╗███████╗███╗   ██╗██████╗  █████╗  ██████╗██╗ ██████╗ ███╗   ██╗███████╗███████╗██╗██╗
                                    ██╔══██╗██╔════╝██╔════╝██╔═══██╗████╗ ████║██╔════╝████╗  ██║██╔══██╗██╔══██╗██╔════╝██║██╔═══██╗████╗  ██║██╔════╝██╔════╝██║██║
                                    ██████╔╝█████╗  ██║     ██║   ██║██╔████╔██║█████╗  ██╔██╗ ██║██║  ██║███████║██║     ██║██║   ██║██╔██╗ ██║█████╗  ███████╗██║██║
                                    ██╔══██╗██╔══╝  ██║     ██║   ██║██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║  ██║██╔══██║██║     ██║██║   ██║██║╚██╗██║██╔══╝  ╚════██║╚═╝╚═╝
                                    ██║  ██║███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗██║ ╚████║██████╔╝██║  ██║╚██████╗██║╚██████╔╝██║ ╚████║███████╗███████║██╗██╗
                                    ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝╚══════╝╚═╝╚═╝
                    
                    
                    
                    
                    """;

        int anchoPantalla = 80;

        for (String linea : dibujo.split("\n")) {
            int espacios = (anchoPantalla - linea.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, espacios)) + CYAN + linea + RESET);
        }
    }


    // registro
    public static void registro () {

        mostrarAsciiRegistro();
        boolean seguir = true;

        System.out.println("""
                    Se registrarán los hábitos que ya tiene adquiridos previos a esta aplicación para recomendarle actividades adaptadas a su estilo, 
                    ingrese los datos requeridos para comenzar nuestra clasificación.
                    
                    """);

        while (seguir) {

            String nombre = "";
            while (true) {
                System.out.println("¿Cuál es el nombre del hábito?");
                nombre = lector.nextLine();
                if (nombre == null) nombre = "";
                if (nombre.trim().isEmpty()) {
                    System.out.println("El nombre no puede estar vacío. Por favor escribe el nombre del hábito.");
                    continue;
                }
                break;
            }

            // Verificar límite antes de guardar
            if (indiceHabito < habitos.length) {
                habitos[indiceHabito] = nombre;
                indiceHabito++;
            } else {
                System.out.println("Ya alcanzaste el límite de hábitos (20). No se guardará este hábito.");
            }

            System.out.println(
                    "\nClasifica tu hábito de acuerdo a lo siguiente\n" +
                            "1) Mental / Cognitivo\n" +
                            "2) Emocional / Psicológico\n" +
                            "3) Físico / Corporal\n" +
                            "Ingresa el número:");

            int tipo = 0;
            while (true) {
                String linea = lector.nextLine().trim();
                try {
                    tipo = Integer.parseInt(linea);
                    if (tipo >= 1 && tipo <= 3) break;
                    System.out.println("Por favor ingresa 1, 2 o 3:");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Por favor ingresa 1, 2 o 3:");
                }
            }

            if (tipo == 1) {
                if (indiceMental < mental.length) {
                    mental[indiceMental] = nombre;
                    indiceMental++;
                } else {
                    System.out.println("Límite de hábitos mentales alcanzado (20).");
                }
            } else if (tipo == 2) {
                if (indiceEmocional < emocional.length) {
                    emocional[indiceEmocional] = nombre;
                    indiceEmocional++;
                } else {
                    System.out.println("Límite de hábitos emocionales alcanzado (20).");
                }
            } else if (tipo == 3) {
                if (indiceFisico < fisico.length) {
                    fisico[indiceFisico] = nombre;
                    indiceFisico++;
                } else {
                    System.out.println("Límite de hábitos físicos alcanzado (20).");
                }
            } else {
                // No debería ocurrir, idk
                System.out.println("Opción no válida.");
            }

            // Pregunta Sí/No validada para continuar
            String respuesta = " ";
            while (true) {
                System.out.println("¿Deseas registrar otro hábito? (Si/No)");
                respuesta = lector.nextLine().trim().toLowerCase();
                if (respuesta.equals("s") || respuesta.equals("si")) {
                    // volver a iniciar el bucle (seguir = true)
                    break; // sale del while y continúa el while principal (seguir sigue true)
                } else if (respuesta.equals("n") || respuesta.equals("no")) {
                    // el usuario no quiere más registros
                    seguir = false;
                    break;
                } else {
                    System.out.println("Respuesta no válida. Escribe 'Si' o 'No'.");
                    // vuelve a pedir
                }
            }

        }

        System.out.println("Registro finalizado. Regresando al menú...");
    }


    static void mostrarHabitosRegistrados () {

        System.out.println("\nLos hábitos con los que cuentas registrados son:\n");

        // mentales
        System.out.println(" MENTALES:");
        if (indiceMental == 0) {
            System.out.println("- Ninguno registrado");
        } else {
            for (int i = 0; i < indiceMental; i++) {
                System.out.println("- " + mental[i]);
            }
        }

        // emocionales
        System.out.println("\n️ EMOCIONALES:");
        if (indiceEmocional == 0) {
            System.out.println("- Ninguno registrado");
        } else {
            for (int i = 0; i < indiceEmocional; i++) {
                System.out.println("- " + emocional[i]);
            }
        }

        // físicos
        System.out.println("\n FÍSICOS:");
        if (indiceFisico == 0) {
            System.out.println("- Ninguno registrado");
        } else {
            for (int i = 0; i < indiceFisico; i++) {
                System.out.println("- " + fisico[i]);
            }
        }
        System.out.println("""
                    
                    
                    """);
    }

    // recomendaciones
    public static void recomendacion () {

        boolean tieneMental = indiceMental > 0;
        boolean tieneEmocional = indiceEmocional > 0;
        boolean tieneFisico = indiceFisico > 0;

        if (!tieneMental && !tieneEmocional && !tieneFisico) {
            System.out.println("Regista hábitos previos para poder continuar");
            return;
        }

        int categoriasActivas = 0;
        if (tieneMental) categoriasActivas++;
        if (tieneEmocional) categoriasActivas++;
        if (tieneFisico) categoriasActivas++;

        if (categoriasActivas < 2) {
            System.out.println("\nPara recibir recomendaciones útiles, necesitas registrar al menos 2 hábitos en categorías diferentes.");
            return;
        }


        mostrarRecomendaciones();
        mostrarHabitosRegistrados();

        int combinacion = 0;
        if (tieneMental) combinacion += 1;
        if (tieneEmocional) combinacion += 2;
        if (tieneFisico) combinacion += 4;

        switch (combinacion) {

            // Mental + Emocional + Físico
            case 7:
                System.out.println("""
                            Tienes todas las categorías equilibradas. ¡Muchas felicidades!
                            Ahora es momento de subir tu nivel.
                            
                            Reto de hoy:
                            Haz que tu hábito mental sea diferente o dure 15 minutos más que ayer.
                            
                            Ayuno de Dopamina Controlado:
                            Consiste en abstenerse de actividades de placer fácil (celular, redes, comida chatarra)
                            para recalibrar tu sistema de recompensa.
                            
                            Se te redirigirá nuevamente al menú de hábitos, continúa con lo que desees hacer...
                            """);
                break;

            // Mental + Físico (sin emocional)
            case 5:
                System.out.println("""
                            ____RIESGOS____
                            
                            • Dificultad para manejar el estrés emocional
                            • Problemas en relaciones interpersonales
                            • Mayor riesgo de ansiedad y depresión
                            
                            -------------RECOMENDACIÓN----------------|
                            | Selecciona el hábito emocional          |
                            |-----------------------------------------|
                            | 1) Journaling                           |
                            | 2) Práctica de gratitud                 |
                            | 3) Luz solar matutina                   |
                            -------------------------------------------
                            """);
                // Leemos opción con la función auxiliar (rango 1-3)
                int h_emocional = leerEntero("Selecciona 1, 2 o 3:", 1, 3);

                switch (h_emocional) {
                    case 1:
                        System.out.println("""
                                    Journaling (escritura reflexiva)
                                    
                                    ___Beneficios_____
                                    “Permite un rato de paz. Es bueno pararse, reflexionar, pensar, meditar y frenar en esta vida tan acelerada que llevan nuestros adolescentes. 
                                    Facilita tomar conciencia de uno mismo. A veces necesitamos ayuda, o creemos que somos más débiles de lo que realmente somos. 
                                    Estos diarios nos permiten tomar consciencia de nuestras verdaderas necesidades, tanto si somos adolescentes como si somos adultos. 
                                    Reduce el estrés. Tratar de retener todo en nuestra mente provoca estrés. Frenar, escribirlo y saber que lo dejamos ahí inmortalizado nos relaja” (Logo’s International School, s. f.).
                                    
                                    ___Cómo iniciar___
                                    Finalizando tu día anota por dos minutos lo que ocurrio durante el día y lo pensaste durante el transcurso de este.
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Si
                                    2) No
                                    Solo escriba números
                                    """);

                        int emocional_c = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (emocional_c == 1) {
                            if (indiceEmocional < emocional.length) {
                                emocional[indiceEmocional] = "Escritura reflexiva";
                                indiceEmocional++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos emocionales alcanzado (20).");
                            }
                            return;

                        } else if (emocional_c == 2) {
                            System.out.println("Regresando al menú...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }

                        break;

                    case 2:
                        System.out.println("""
                                    Practica de gratitud
                                    
                                    ____Beneficios____
                                    La práctica de la gratitud se asocia con beneficios significativos en la salud mental de niños y adolescentes, ya que puede reducir el riesgo de ideación suicida, 
                                    mejorar la calidad del sueño y fortalecer el bienestar emocional, especialmente cuando es fomentada y modelada por padres y cuidadores.
                                    (Anxiety & Depression Association of America [ADAA], s. f.).
                                    
                                    __Cómo iniciar__
                                    Tres cosas buenas al día para reentrenar el enfoque del cerebro hacia lo positivo.
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Si
                                    2) No
                                    Solo escriba números
                                    """);
                        int emocional_c1 = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (emocional_c1 == 1) {
                            if (indiceEmocional < emocional.length) {
                                emocional[indiceEmocional] = "Practica de gratitud";
                                indiceEmocional++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos emocionales alcanzado (20).");
                            }
                            return;

                        } else if (emocional_c1 == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }

                        break;

                    case 3:
                        System.out.println("""
                                    Luz solar matutina
                                    
                                    ____Beneficios____
                                    "La luz solar matutina ayuda a regular tu ritmo circadiano al indicarle a tu cuerpo cuándo aumentar los niveles de cortisol 
                                    (para darte energía) y cuándo comenzar la producción de melatonina (para ayudarte a dormir más tarde). 
                                    Estar bajo el sol también ayuda a tu cuerpo a producir Vitamina D, esencial para los huesos, las células sanguíneas y el sistema inmunológico." 
                                    — Cleveland Clinic, "The Health Benefits of Sunlight".
                                    
                                    __Cómo iniciar__
                                    Sin pantallas: No revises el celular antes de esto. El objetivo es que los fotones del sol lleguen a tus ojos antes que la luz azul del teléfono.
                                    Si está nublado: ¡Hazlo igual! Incluso en días grises, la intensidad de la luz exterior es mucho mayor que cualquier lámpara de oficina 
                                    y sigue siendo efectiva para ajustar tu reloj interno.
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Si
                                    2) No
                                    Solo escriba números
                                    """);
                        int emocional_c2 = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (emocional_c2 == 1) {
                            if (indiceEmocional < emocional.length) {
                                emocional[indiceEmocional] = "Luz solar matutina";
                                indiceEmocional++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos emocionales alcanzado (20).");
                            }
                            return;

                        } else if (emocional_c2 == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }

                        break;
                }

                // Emocional + Físico (sin mental)
            case 6:
                System.out.println("""
                            ____RIESGOS____
                            
                            • Deterioro cognitivo progresivo
                            • Dificultad para tomar decisiones complejas
                            • Dependencia cognitiva
                            
                            -------------RECOMENDACIÓN----------------|
                            | Selecciona el hábito mental             |
                            |-----------------------------------------|
                            | 1) Lectura diaria                       |
                            | 2) Juegos de lógica                     |
                            | 3) Aprendizaje de algo nuevo            |
                            -------------------------------------------
                            """);

                int h_mental = leerEntero("Selecciona 1, 2 o 3:", 1, 3);

                switch (h_mental) {

                    case 1:
                        System.out.println("""
                                    Lectura diaria
                                    
                                    ___Beneficios___
                                    La lectura frecuente fortalece la memoria, la atención y la comprensión, además de reducir el deterioro cognitivo
                                    y mejorar la toma de decisiones (Harvard Health Publishing; National Institute on Aging).
                                    
                                    ___Cómo iniciar___
                                    Lee 5 a 10 minutos diarios un texto de tu interés (libro, artículo o ensayo), sin distracciones.
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Sí
                                    2) No
                                    Solo escriba números
                                    """);
                        int mental_c = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (mental_c == 1) {
                            if (indiceMental < mental.length) {
                                mental[indiceMental] = "Lectura diaria";
                                indiceMental++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos mentales alcanzado (20).");
                            }
                            return;

                        } else if (mental_c == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }

                        break;

                    case 2:
                        System.out.println("""
                                    Juegos de lógica y pensamiento
                                    
                                    ___Beneficios___
                                    Los ejercicios de lógica estimulan el cerebro, mejoran la resolución de problemas y fortalecen las funciones ejecutivas
                                    (American Psychological Association).
                                    
                                    ___Cómo iniciar___
                                    Resuelve un acertijo, sudoku o problema lógico al día.
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Sí
                                    2) No
                                    Solo escriba números
                                    """);
                        int mental_c1 = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (mental_c1 == 1) {
                            if (indiceMental < mental.length) {
                                mental[indiceMental] = "Juegos de lógica y pensamiento";
                                indiceMental++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos mentales alcanzado (20).");
                            }
                            return;

                        } else if (mental_c1 == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }
                        break;

                    case 3:
                        System.out.println("""
                                    Aprender algo nuevo
                                    
                                    ___Beneficios___
                                    El aprendizaje continuo promueve la neuroplasticidad y previene el estancamiento cognitivo
                                    (National Institutes of Health).
                                    
                                    ___Cómo iniciar___
                                    Dedica 10 minutos diarios a aprender una habilidad nueva (idioma, tema escolar o curiosidad personal).
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Sí
                                    2) No
                                    Solo escriba números
                                    """);
                        int mental_c2 = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (mental_c2 == 1) {
                            if (indiceMental < mental.length) {
                                mental[indiceMental] = "Aprender algo nuevo";
                                indiceMental++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos mentales alcanzado (20).");
                            }
                            return;

                        } else if (mental_c2 == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }
                        break;
                }

                // Mental + Emocional (sin físico)
            case 3:
                System.out.println("""
                            ____RIESGOS____
                            
                            • Mayor riesgo de enfermedades físicas
                            • Fatiga mental prolongada
                            • Alteraciones del sueño
                            
                            -------------RECOMENDACIÓN----------------|
                            | Selecciona el hábito físico             |
                            |-----------------------------------------|
                            | 1) Caminata diaria                      |
                            | 2) Rutina corta de ejercicio            |
                            | 3) Estiramientos                        |
                            -------------------------------------------
                            """);

                int h_fisico = leerEntero("Selecciona 1, 2 o 3:", 1, 3);

                switch (h_fisico) {

                    case 1:
                        System.out.println("""
                                    Caminata diaria
                                    
                                    ___Beneficios___
                                    Caminar regularmente mejora la salud cardiovascular, reduce el estrés y aumenta la energía
                                    (World Health Organization; CDC).
                                    
                                    ___Cómo iniciar___
                                    Camina 10 minutos diarios a ritmo cómodo.
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Sí
                                    2) No
                                    Solo escriba números
                                    """);
                        int fisico_c = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (fisico_c == 1) {
                            if (indiceFisico < fisico.length) {
                                fisico[indiceFisico] = "Caminata diaria";
                                indiceFisico++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos físicos alcanzado (20).");
                            }
                            return;

                        } else if (fisico_c == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }

                        break;

                    case 2:
                        System.out.println("""
                                    Ejercicio corporal ligero
                                    
                                    ___Beneficios___
                                    La actividad física regular mejora el estado de ánimo, la concentración y la salud general
                                    (World Health Organization).
                                    
                                    ___Cómo iniciar___
                                    Realiza una rutina corta de 5 a 10 minutos (sentadillas, lagartijas o jumping jacks).
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Sí
                                    2) No
                                    Solo escriba números
                                    """);
                        int fisico_c2 = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (fisico_c2 == 1) {
                            if (indiceFisico < fisico.length) {
                                fisico[indiceFisico] = "Ejercicio corporal ligero";
                                indiceFisico++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos físicos alcanzado (20).");
                            }
                            return;

                        } else if (fisico_c2 == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }

                        break;

                    case 3:
                        System.out.println("""
                                    Estiramientos
                                    
                                    ___Beneficios___
                                    Los estiramientos mejoran la flexibilidad y reducen la tensión muscular.
                                    
                                    ___Cómo iniciar___
                                    Dedica 5 minutos al día a estiramientos básicos.
                                    
                                    --¿Desea agregar este hábito a su lista?--
                                    1) Sí
                                    2) No
                                    Solo escriba números
                                    """);
                        int fisico_c3 = leerEntero("Ingresa 1 o 2:", 1, 2);

                        if (fisico_c3 == 1) {
                            if (indiceFisico < fisico.length) {
                                fisico[indiceFisico] = "Estiramientos";
                                indiceFisico++;
                                System.out.println("El hábito ha sido registrado");
                            } else {
                                System.out.println("Límite de hábitos físicos alcanzado (20).");
                            }
                            return;

                        } else if (fisico_c3 == 2) {
                            System.out.println("Regresando al menú principal...");
                            return;

                        } else {
                            System.out.println("Opción no válida.");
                        }

                }    break;
        }
    }










    // Vista de seguimiento
    private static void vistaDeSeguimiento(Scanner sc, String usuario) {
        boolean salir = false;
        while (!salir) {
            asciiSeguimiento();
            System.out.println();
            System.out.println("Bienvenid@ a la vista de seguimiento, a continuación te mostraremos las opciones a usar:");
            System.out.println("1) Observar las actividades registradas hasta el momento");
            System.out.println("2) Observar los hábitos registrados hasta el momento");
            System.out.println("3) Marcar como completadas las actividades");
            System.out.println("4) Observar las actividades pendientes");
            System.out.println("5) Observar las actividades completadas");
            System.out.println("6) Eliminar actividades registradas");
            System.out.println("7) Recomendaciones de tiempo en redes sociales");
            System.out.println("8) Volver al menú");
            System.out.println("9) Cerrar sesión");

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
                case "6":
                    eliminarActividad(sc);
                    break;
                case "7":
                    if (tiemRed >= 0) {
                        evaluarTiempoEnRedes(tiemRed);
                    } else {
                        System.out.println("No tenemos registro de tu tiempo en redes sociales.");
                    }
                    break;
                case "4":
                    mostrarActividadesPendientes();
                    break;
                case "5":
                    mostrarActividadesCompletadas();
                    break;
                case "8":
                    mostrarArbolYTitulo();
                    menuPrincipal(sc, usuario);
                    return;
                case "9":
                    cierreSesionEInforme(sc, usuario);
                    return;
                default:
                    System.out.println("Opción no válida. Ingresa un número entre 1 y 11.");
            }

            System.out.println();
        }
    }


    static void asciiSeguimiento() {
        final String AZUL = "\u001B[94m";
        final String RESET = "\u001B[0m";

        String ascii =
                "           _____                                                       _____                                                                                      \n" +
                        "  __|   _ |__  ____  ______   __    ____     _____   ______   __|___  |__  ______  ______  __   _  ____  ____    __  ____  ______  ____   _    __    _____  \n" +
                        " \\  \\  //    ||    ||   ___|_|  |_ |    \\   |     \\ |   ___| |   ___|    ||   ___||   ___||  | | ||    ||    \\  /  ||    ||   ___||    \\ | | _|  |_ /     \\ \n" +
                        " |\\  \\//     ||    | `-.`-.|_    _||     \\  |      \\|   ___|  `-.`-.     ||   ___||   |  ||  |_| ||    ||     \\/   ||    ||   ___||     \\| ||_    _||     | \n" +
                        " |_\\__/    __||____||______| |__|  |__|\\__\\ |______/|______| |______|  __||______||______||______||____||__/\\__/|__||____||______||__/\\____|  |__|  \\_____/ \n" +
                        "    |_____|                                                     |_____|                                                                                     \n" +
                        "                                                                                                                                                            ";

        int anchoPantalla = 100;

        for (String linea : ascii.split("\n")) {
            int espacios = (anchoPantalla - linea.length()) / 2;
            if (espacios < 0) espacios = 0;
            System.out.println(" ".repeat(espacios) + AZUL + linea + RESET);
        }
    }



    // MÉTODOS AUXILIARES PARA VISTA DE SEGUIMIENTO


    private static void mostrarTareasRegistradas() {
        System.out.println("\nActividades registradas hasta el momento:");
        System.out.println("Tareas registradas:");
        for (int i = 0; i < tareasCount; i++)
        { System.out.println((i + 1) + ") " + tareas[i]); }
    }

    private static void marcarActividadCompletada(Scanner sc) {

        mostrarTareasRegistradas();
        System.out.println("\nEscriba el número de actividad que desee marcar");

        int num = pedirNumeroValido(sc, 1, tareasCount);
        int indiceSeleccionado = num - 1;

        tareasCompletadasFlag[indiceSeleccionado] = true;
        actualizarArreglosVistaSeguimiento();

        System.out.println("La tarea se ha marcado como completada con éxito");
    }


    private static void eliminarActividad(Scanner sc) {

        mostrarTareasRegistradas();
        System.out.println("\nEscriba el número de actividad que desee eliminar");
        int num = pedirNumeroValido(sc, 1, tareasCount);
        int indice = num - 1;
        for (int i = indice; i < tareasCount - 1; i++) {
            tareas[i] = tareas[i + 1];
            tareasCompletadasFlag[i] = tareasCompletadasFlag[i + 1];
        }

        tareas[tareasCount - 1] = null;
        tareasCompletadasFlag[tareasCount - 1] = false;
        tareasCount--;

        actualizarArreglosVistaSeguimiento();
        System.out.println("Actividad eliminada correctamente.");
    }

    private static void mostrarActividadesPendientes() {
        System.out.println("\n Actividades pendientes:");
        for (int i = 0; i < tareasPendientesCount; i++) {
            System.out.println((i + 1) + ") " + tareasPendientes[i]);
        }
    }

    private static void mostrarActividadesCompletadas() {
        System.out.println("\nActividades completadas:");
        for (int i = 0; i < tareasCompletadasCount; i++) {
            System.out.println((i + 1) + ") " + tareasCompletadas[i]);
        }
    }

    private static void actualizarArreglosVistaSeguimiento() {

        tareasCompletadasCount = 0;
        tareasPendientesCount = 0;

        for (int i = 0; i < tareasCount; i++) {
            if (tareasCompletadasFlag[i]) {
                tareasCompletadas[tareasCompletadasCount++] = tareas[i];
            } else {
                tareasPendientes[tareasPendientesCount++] = tareas[i];
            }
        }
    }

    private static int pedirNumeroValido(Scanner sc, int min, int max) {
        while (true) {
            System.out.print("Ingresa un número entre " + min + " y " + max + ": ");
            String linea = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(linea);
                if (n >= min && n <= max) {
                    return n;
                } else {
                    System.out.println("Número fuera de rango. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debes escribir un número entero.");
            }
        }
    }
    private static boolean puedeEntrarVistaSeguimiento(Scanner sc) {

        if (tareasCount == 0 ||  indiceHabito == 0) {

            System.out.println("\n Aún no tienes actividades u hábitos registrados.");
            System.out.println("Por favor utiliza los módulos correspondientes para registrar tus datos " +
                    "antes de hacer uso de Vista de Seguimiento.");
            System.out.println("\nSerás redirigido automáticamente al menú principal Tree of Time...");


            mostrarArbolYTitulo();
            return false;
        }

        return true;
    }














    // Cierre de sesión e informe
// Se recibe el nombre de usuario para personalizar el informe si lo deseas.
    private static void cierreSesionEInforme(Scanner sc, String usuario) {
        System.out.println("\n--- MÓDULO: Cierre de sesión e informe ---");
        // TODO: Inserta tu código aquí
        System.out.println("Generando informe para " + usuario + "...");
        System.out.println("(Aquí iría el código para cerrar sesión y crear el informe)");
        final String VERDE = "\u001B[32m";
        final String RESET = "\u001B[0m";
        String ascii = "" +
                " __     ___   __    __    ___     __     ___    __    ___  __      __      \n" +
                "/  ` | |__   |__)  |__)  |__     |  \\   |__    /__`  |__  /__`  | /  \\  |\\ |\n" +
                "\\__, | |___  |  \\  |  \\  |___    |__/   |___   .__/  |___ .__/  | \\__/  | \\| \n";
        int anchoPantalla = 100;
        for (String linea : ascii.split("\n")) {
            int espacios = (anchoPantalla - linea.length()) / 2;
            System.out.println("".repeat(Math.max(0, espacios)) + VERDE + linea + RESET);
        }
        System.out.println("¡Felicidades! Has llegado al final de la sesión de hoy. Éstos son los resultados que obtuviste según tu rendimiento. ");
        System.out.println("Éstas son las tareas que registraste:");
        for (int i = 0; i < tareasCount; i++) {
            System.out.println((i + 1) + ") " + tareas[i]);
        }
        System.out.println("Y éstas son las tareas que fuiste completando:");
        for (int i = 0; i < tareasCompletadasCount; i++) {
            System.out.println((i + 1) + ") " + tareasCompletadas[i]);
        }
        System.out.println("¡Muy bien hecho! ¡Recuerda que cualquier progreso es significativo!");
        System.out.println("A continuación te mostramos la lista de los hábitos que has registrado junto a los que se te recomendaron.");
        for (int i = 0; i < indiceHabito; i++) {
            System.out.println((i + 1) + ") " + habitos[i]);
        }
        System.out.println("Tómalo en cuenta.");
        System.out.println("Recuerda que el tiempo es un recurso muy valioso. De acuerdo a estudios de la Universidad de Harvard,\n" +
                " las personas que establecen metas claras y priorizan su tiempo son más propensas a alcanzar sus objetivos. ");
        System.out.println("También ten en cuenta que no se trata de hacer más, se trata de hacer lo que es importante para lograr tus objetivos. ");
        System.out.println("¡Muchas gracias por usar nuestra aplicación, esperamos que vuelvas pronto!");
        System.out.println("¿Deseas volver a utilizar la aplicación? Por favor sólo escribe números.");
        System.out.println(" 1. Sí");
        System.out.println(" 2. No");
        sc = new Scanner(System.in);
        char opcion = sc.next().charAt(0);

        if (opcion == '1') {
            mostrarArbolYTitulo();
            menuPrincipal(sc, usuario);
            //reinicio del programa

            System.exit(0);
        } else {
            if (opcion == '2') {
                System.out.println("fin");
                //se termina el programa
                System.exit(0);
            }
        }

        while (opcion > '2' || opcion < '1') {
            condicional(usuario);
        }

    }

        private static char condicional(String usuario) {
            System.out.println("¿Deseas volver a utilizar la aplicación? Por favor sólo escribe números.");
            System.out.println(" 1. Sí");
            System.out.println(" 2. No");
            Scanner sc = new Scanner(System.in);
            char opcion = sc.next().charAt(0);
            if (opcion > '2' || opcion < '1') {
                System.out.println("Error, no es una opción válida");
            } else {
                if (opcion == '1') {
                    mostrarArbolYTitulo();
                    menuPrincipal(sc, usuario);

                    //reinicio del programa
                } else {
                    System.out.println("fin");
                    //se termina el programa
                    System.exit(0);
                }
            }
            return 0;


        }

    private static void mostrarArbolYTitulo() {
        final String VERDE = "\u001B[32m"; // verde hoja
        final String RESET = "\u001B[0m";

        String ascii =


                "-----------------------------::::-==----:::*=::*#***+::-*-::::--++--:-::----------------------------\n" +
                        "-----------------------------:::::=#**##*--##=-##+*#*--*#+::+####*-:::::::--------------------------\n" +
                        "---------------------------::-=-:::*#*+#*#-*#=-##+##+:=##+-*#**##-:::--::::-------------------------\n" +
                        "-----------------------::::::-+##*=:+#***#+:+=:=#*#*-:=#*:=*###+:-*#*#*::::-------------------------\n" +
                        "----------------------:-+++=-:-***#+:=***##=*-::+#==-:-+-+*=--=-*#***#=:=*#=::----------------------\n" +
                        "----------------------:-+#**##=--*##-=*+-:-#*-:-**:+#--+#=-**#=+#+*##-=###*::::---------------------\n" +
                        "-----------------------::*##++#+:::+*-*##*-:+#-=#=--*##*--###+-***+-:-**#*-::-----------------------\n" +
                        "------------------+####*=-=##**#---=#=:=*##--*##+:++-#*-:##*--*+:--=:=+--=**####*-------------------\n" +
                        "-------------------+#****#-:--=#*-*=#+:=::-++*#*-:*#=+*=#-:::+#-=#*=-*-:*#***##*-::-----------------\n" +
                        "-------------------:=##*+#+:+*--#+:+#*-=##=:=##+:-*#=+##-:=+:**--==:=+:+#*+*##*::::-----------------\n" +
                        "-------------------=----=+*--##-=*-:=#-:+##*-+#+:*#+:=#+--#+:*#**=:-#***+++=--=====-----------------\n" +
                        "---------------*##*##*#+-:-*=-==-#=:-**:-=*#*+#*+#+::*#+=#*--#*-::=##+-::::-+##*#**##+--------------\n" +
                        "---------------+##***++*#*++#*=::**-:-##=::-+#####::+###*-:-##=-=###########*****##*=---------------\n" +
                        "------------------+**+=----=+*##++##+=+##*+=--*##*-=##*=::-*#####*=:::-==:::--=++=-:::--------------\n" +
                        "----------------+*#+---+##*=-:--+###**#######*-+##*###---#####+--:::+###-:+*#+--=++=----------------\n" +
                        "--------------*#****#+=-:-=--**-:::::-----=####*#####-=############+-:::-+*=--##**#*##+-------------\n" +
                        "-----------------==----=***+++*########**#*=+#######**##*+---::::-----=*#***#******##+--------------\n" +
                        "-----------------+####*+*###+----+###########*###+#####**########**###*-:::::--+++----:-------------\n" +
                        "---------------*#***+*#=::--==+*#+=:--+**#####*#########*####*==+*##+=---+#*+--==+=-----------------\n" +
                        "-------------=#****###=:-+###*#+::=*##+-::-+##*####*####*--::=***=--+#####***###*#*##*--------------\n" +
                        "------------=######*=-:=##**##*-:=#*#*-:::::+#+####*###+::::::=#***-:-*=::--=###*#+*##=-------------\n" +
                        "-----------------:-:::-*#*###=::-*#*+-::::::-*##*##*###=:::::::=*##=::-####=:::=++=-::--------------\n" +
                        "-----------------:::-:-**+=-:::::--:::::::::-*##*##*###-:::::::::-==:::-+###+-:::::::::-------------\n" +
                        "---------------------:::::::::::::::::::::::-*##*##*###=:::::::::::::::::::--:::::::----------------\n" +
                        "---------------------:-:::::::::::::::::::::=######*###+::::::::::::::::::::::::::::--:-------------\n" +
                        "-----------------------::::::::::::::::::::-##*#*#####*#-:::::::::::::::::::::::::-:----------------\n" +
                        "--------------------::::::::::::::::::::::=##*######*####+::::::::::::::::::::-::::-----------------\n" +
                        "--------------------:::::::::::::::::--=+###*########*#**##*=---::::::::::::::::::------------------\n" +
                        "----------------------::-*################++###+*##**#####++####********#+-:::-::-------------------\n" +
                        "--------------------:--*#=-------=*##*=--+##*+#=**+*=#++#####=-*#-:::::--+#*-:::::------------------\n" +
                        "------------------+**#*+---*****##*---:+*-++-+#=*++*-#+=+-*-:+#=-##****#+-:+*#***-:-----------------\n" +
                        "----------------------::-**=::-+#=--**#*-=#+:+#-**+*-*+=*-**=:-*=-**-:::=*+:::::-:------------------\n" +
                        "---------------------:-##=:::+**+:**:::=##-:=#*-*+=*-**:=#+:+*-*+::-*###-:=#*=:::::-----------------\n" +
                        "--------------------------=+*=-**:*=-=#+--+#+--+#+=#-+*-:-*+=#-**:::-*==*-:-=-:---------------------\n" +
                        "---------------------:-:-#+*-:-**-*--*+:+#=-=*+=*+=#=:=**--=-#--+*=::-==-::-:::---------------------\n" +
                        "-------------------------:--::-*+=##-*=-#=:**-=#=:-###-:+#+:-*-::=##*-::::-:------------------------\n" +
                        "------------------------:---+*#-:::-**=:#=:*=-#=-***-=#=:=*--#-::-*==*-:::-:------------------------\n" +
                        "------------------------:---=+=-:-+*#=:-#+-*+:*+-+#=--#=:=*-:**-::-==-:---:-------------------------\n" +
                        "----------------------------:::-::+#*-:#=*-+*-#*-=#--*#+-#*+:-+**::::::----:------------------------\n" +
                        "-------------------------:---:::::::::::---*+-=+-=#=-*+*-:::::=+-::-:-------------------------------\n" +
                        "--------------------------------::::::::::-**-::-***-::::::::-:::::-:--:----------------------------\n" +
                        "---------------------------------:-::::::::::::::+*=:::::::::::::::---------------------------------\n" +
                        "------------------------------:----::::::::-::::::::::::::::::::-::---------------------------------\n" +
                        "---------=*******=+****+=--=*****+-******=:::::::::::::::-+*******=***--+**----+*#+-+*****+---------\n" +
                        "---------+=-+#+-+=-#*-=*#+--#*=--=-=#+-:==:-:-+#*=-=****--=-:##--+-+#+--=##*---###--=#*--=+---------\n" +
                        "------------+#+----#*--+#+--##=----+#+----::=#+--**=#+---::::##----+#+--+###+-####--=##--=----------\n" +
                        "------------+#+----##+*#*---#####--+####+--:=#=--##=###=::-::##----*#+--+*=####=*#--=#####----------\n" +
                        "------------+#*----##=##=---##=----+#+--:---=*+--#+=#+------:##----*#+--**--##+-*#=-=##-------------\n" +
                        "------------+#*----##-=##=--##+==*=+#*==++----=++--=++------:##----*#+--**--++--*#+-+##==+*=--------\n" +
                        "-----------=***=--+**=--+*+=++++*+=+***++=-------------:----+**=---+*+-=**=-----+**=+****++---------\n";

        int anchoPantalla = 100;

        for (String linea : ascii.split("\n")) {
            int espacios = (anchoPantalla - linea.length()) / 2;
            if (espacios < 0) espacios = 0;
            System.out.println(" ".repeat(espacios) + VERDE + linea + RESET);
        }
    }
}

