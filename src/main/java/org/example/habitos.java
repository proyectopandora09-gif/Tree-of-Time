package org.example;
import java.util.Scanner;

public class habitos {

    static Scanner lector = new Scanner(System.in);

    static String[] habitos = new String[20];
    static int indiceHabito = 0;

    static String[] mental = new String[20];
    static int indiceMental = 0;

    static String[] emocional = new String[20];
    static int indiceEmocional = 0;

    static String[] fisico = new String[20];
    static int indiceFisico = 0;

    public static void main(String[] args) {

        boolean seguirMenu = true;

        while (seguirMenu) {

            mostrarAsciiArt();

            System.out.println("\n¡Bienvenido usuario! ¿Qué deseas hacer hoy? \n" +
                    " 1) Registrar hábito \n" +
                    " 2) Recomendación de hábito \n" +
                    " 3) Salir al menú Tree of Time");

            // Usamos la función auxiliar para leer la opción (evita nextInt/nextLine problemas)
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
                    System.out.println("Saliendo del menú... ¡Hasta luego!");
                    seguirMenu = false;
                    break;
                default:
                    System.out.println("Por favor ingrese un número válido.");
            }
        }
    }

    // ---------- Helper simple para leer enteros con validación ----------
    // Lee una línea, intenta parsear a entero y valida rango (min..max).
    // Si min>max, solo valida que sea entero.
    private static int leerEntero(String mensaje, int min, int max) {
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

    static void mostrarAsciiArt() {

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

    static void mostrarAsciiRegistro() {

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

    static void mostrarAsciiCafe() {

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

    static void mostrarRecomendaciones() {

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
    public static void registro() {

        mostrarAsciiRegistro();
        boolean seguir = true;

        System.out.println("""
            Se registrarán los hábitos que ya tiene adquiridos previos a esta aplicación para recomendarle actividades adaptadas a su estilo, 
            ingrese los datos requeridos para comenzar nuestra clasificación.
            
            """);

        while (seguir) {

            // Pedir nombre y validar que no esté vacío ni solo espacios
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

            // Leer y validar la opción 1-3 usando la función auxiliar leerEntero si la tienes,
            // si no, este bloque valida con parseInt seguro.
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
                // No debería ocurrir por la validación anterior, pero queda por seguridad.
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



    static void mostrarHabitosRegistrados() {

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
    public static void recomendacion() {

        boolean tieneMental = indiceMental > 0;
        boolean tieneEmocional = indiceEmocional > 0;
        boolean tieneFisico = indiceFisico > 0;

        if (!tieneMental && !tieneEmocional && !tieneFisico) {
            System.out.println("Regista hábitos previos para poder continuar");
            return; }

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

                        break;
                }
        }
    }
}
