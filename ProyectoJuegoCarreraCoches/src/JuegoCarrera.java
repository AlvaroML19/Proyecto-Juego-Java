import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class JuegoCarrera {
    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        String nombreUsuario;
        int opcion;
        int pilotoElegido;
        int neumaticoElegido;
        int vueltas;
        final String VERDE = "\u001B[32m";
        final String RESET = "\u001B[0m";
        final String CIAN = "\u001B[36m";
        final String AMARILLO = "\u001B[33m";
        final String ROJO = "\u001B[31m";

        Random random = new Random();

        String[] pilotos = {
            "Fernando Alonso", "Carlos Sainz",
            "Max Verstappen", "Lewis Hamilton",
            "Lando Norris", "George Russell"
        };

        String[] neumaticos = {
            "Blando", "Medio", "Duro"
        };

        double[] ventajaNeumatico = {-2.0, -1.0, 0.0};
        double[] degradacionPorVuelta = {10.0, 5.0, 1.0};
        final double PENALIZACION = 0.06;

        double[] tiempos = new double[pilotos.length];
        double[] desgaste = new double[pilotos.length];
        int[] neumaticoPiloto = new int[pilotos.length];

        System.out.println("¿Cual es tu nombre? ");
        nombreUsuario = lector.readLine();

        System.out.println(VERDE + "Bienvenido al Juego: " + CIAN + nombreUsuario + " A CORRER" + RESET);

        do {
            System.out.println("\n=== JUEGO DE CARRERAS ===");
            System.out.println("1. Nueva Carrera");
            System.out.println("2. Proximos circuitos");
            System.out.println("3. Salir");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(lector.readLine());

            if (opcion == 1) {
                System.out.println("\n--- PILOTOS ---");

                for (int i = 0; i < pilotos.length; i++) {
                    System.out.println((i + 1) + ". " + pilotos[i]);
                }

                System.out.print("Elige tu piloto: ");
                pilotoElegido = Integer.parseInt(lector.readLine());

                if (pilotoElegido < 1 || pilotoElegido > pilotos.length) {
                    System.out.println(ROJO + "Piloto no valido" + RESET);
                    continue;
                }

                System.out.println("\n--- NEUMATICOS ---");

                for (int i = 0; i < neumaticos.length; i++) {
                    System.out.println((i + 1) + ". " + neumaticos[i]);
                }

                System.out.print("Elige tus neumaticos: ");
                neumaticoElegido = Integer.parseInt(lector.readLine());

                if (neumaticoElegido < 1 || neumaticoElegido > neumaticos.length) {
                    System.out.println(ROJO + "Neumatico no valido" + RESET);
                    continue;
                }

                System.out.print("Numero de vueltas (5-10): ");
                vueltas = Integer.parseInt(lector.readLine());

                if (vueltas < 5 || vueltas > 10) {
                    System.out.println(ROJO + "Numero de vueltas no válido" + RESET);
                    continue;
                }

                for (int i = 0; i < pilotos.length; i++) {
                    tiempos[i] = 0;
                    desgaste[i] = 0;
                    neumaticoPiloto[i] = random.nextInt(neumaticos.length);
                }
                neumaticoPiloto[pilotoElegido - 1] = neumaticoElegido - 1;

                System.out.println(AMARILLO + "\n¡COMIENZA LA CARRERA!" + RESET);

                // Carrera vuelta a vuelta
                for (int v = 1; v <= vueltas; v++) {

                    for (int i = 0; i < pilotos.length; i++) {
                        int n = neumaticoPiloto[i];

                        double tiempoVuelta = 80 + random.nextDouble() * 10;
                        tiempoVuelta += ventajaNeumatico[n];
                        tiempoVuelta += desgaste[i] * PENALIZACION;

                        tiempos[i] += tiempoVuelta;

                        desgaste[i] += degradacionPorVuelta[n];
                        if (desgaste[i] > 100) {
                            desgaste[i] = 100;
                        }
                    }

                    int lider = 0;
                    for (int i = 1; i < pilotos.length; i++) {
                        if (tiempos[i] < tiempos[lider]) {
                            lider = i;
                        }
                    }

                    int mio = pilotoElegido - 1;
                    System.out.printf("Vuelta %d/%d | Lider: %s | " + CIAN + "%s" + RESET
                            + " (%s)%n",
                            v, vueltas, pilotos[lider], pilotos[mio],
                            neumaticos[neumaticoPiloto[mio]]);

                    Thread.sleep(700);      //Hace que el programa se detenga durante 700 milisegundos
                }

            
                int ganador = 0;
                for (int i = 1; i < tiempos.length; i++) {
                    if (tiempos[i] < tiempos[ganador]) {
                        ganador = i;
                    }
                }

                System.out.println(VERDE + "\n--- RESULTADOS ---" + RESET);
                System.out.printf("%-18s %-9s %s%n", "Piloto", "Neumatico", "Tiempo total");

                for (int i = 0; i < pilotos.length; i++) {
                    String marca = (i == pilotoElegido - 1) ? "  <- TU" : "";
                    System.out.printf("%-18s %-9s %.2f s%s%n",
                            pilotos[i],
                            neumaticos[neumaticoPiloto[i]],
                            tiempos[i],
                            marca);
                }

                System.out.println("\nGanador: " + pilotos[ganador]);

            } else if (opcion == 2) {
                System.out.println(AMARILLO + "\n--- PROXIMOS CIRCUITOS ---" + RESET);
                System.out.println("¡Proximamente!");

            } else if (opcion != 3) {
                System.out.println("Opcion no valida.");
            }

        } while (opcion != 3);

        System.out.println(ROJO + "Gracias " + AMARILLO + "por " + ROJO + "jugar." + RESET);
    }
}