/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer22_t8_ruletadelafortuna;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Ismael Martín Ramírez
 * [imartinr01@informatica.iesvalledeljerteplasencia.es]
 */
public class Juego {

    private Jugador jugador1;
    private Jugador jugador2;
    private Panel panel;
    private Ruleta ruleta;

    public Juego() {
    }

    public Juego(Jugador jugador1, Jugador jugador2, Panel panel, Ruleta ruleta) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.panel = panel;
        this.ruleta = ruleta;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public Panel getPanel() {
        return panel;
    }

    public Ruleta getRuleta() {
        return ruleta;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public void setRuleta(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public void comienzo() {

        String[] palabras = {"melocoton", "sandia", "manzana", "platano", "frambuesa", "nectarina", "granada", "cereza"};
        String palabra = palabras[(int) Math.floor((Math.random()) * 8)];
        int[] ruletaJuego = {100, 200, 300, 400, 500, 0, 400, 300, 200, 100};

        setJugador1(this.jugador1 = new Jugador(Test.pedirNombre(1), 0));

        setJugador2(this.jugador2 = new Jugador(Test.pedirNombre(2), 0));

        setPanel(this.panel = new Panel(palabra));

        setRuleta(this.ruleta = new Ruleta(ruletaJuego));

    }

    public void partida() {

        boolean ganador = false;
        char[] conjuntoRespuesta = new char[panel.getPalabraSecreta().length()];

        Arrays.fill(conjuntoRespuesta, '_');

        System.out.print("Palabra secreta: ");
        panel.mostrarPanel(conjuntoRespuesta);

        //bucle principal.
        while (ganador == false) {
            ganador = juegoJugador1(conjuntoRespuesta);

            if (ganador == true) {
                System.out.println("¡El " + jugador1.getNombre() + " ganó " + jugador1.getPuntuacion() + " €! Con " + panel.getPalabraSecreta());
            } else {
                System.out.print("Palabra secreta: ");
                panel.mostrarPanel(conjuntoRespuesta);
                System.out.println("");
            }

            if (ganador != true) {
                ganador = juegoJugador2(conjuntoRespuesta);

                if (ganador == true) {
                    System.out.println("¡El " + jugador2.getNombre() + " ganó " + jugador2.getPuntuacion() + "€! Con " + panel.getPalabraSecreta());
                } else {
                    System.out.print("Palabra secreta: ");
                    panel.mostrarPanel(conjuntoRespuesta);
                    System.out.println("");
                }
            }
        }

    }

    public boolean juegoJugador1(char conjuntoRespuesta[]) {

        String lanzamiento, respuesta;
        int aleatorio;
        char letra;

        boolean ganador = false, validadorHayLetra = false;
        Scanner entrada = new Scanner(System.in);

        System.out.println("");
        System.out.println("¡Juega " + jugador1.getNombre());
        System.out.println("-------------------------------");
        System.out.print("¡Pulsa cualquier letra e intro para lanzar la ruleta!");
        lanzamiento = entrada.nextLine();

        //Comprobación que pulsó alguna letra.
        if (lanzamiento.length() > 0) {

            aleatorio = (int) Math.floor(Math.random() * 10);
            //ruleta.mostrarRule(0);

            System.out.println("¡Salio la casilla de " + this.ruleta.getRuleta()[aleatorio] + " €!");

            if (aleatorio == 5) {
                System.out.println("Vaya... salió quiebra... tu marcado queda a 0.");
                this.jugador1.setPuntuacion(0);
                ganador = false;
            } else {
                //Preguntamos si quiere letra o número.En ambos casos nos devuelve una cadena.
                respuesta = Test.menu();

                //Si la respuesta tiene una letra entraremos a comprobarla en la palabra secreta.En el método pedirLetra sólo se captura una letra.
                if (respuesta.length() == 1) {
                    //Transformamos la respuesta a un caracter. 
                    letra = respuesta.charAt(0);

                    for (int i = 0; i < panel.getPalabraSecreta().length(); i++) {

                        if (letra == panel.getPalabraSecreta().charAt(i)) {
                            conjuntoRespuesta[i] = letra;
                            validadorHayLetra = true;
                        }

                    }
                    //Si se encontró letra en la palabra.
                    if (validadorHayLetra == true) {
                        System.out.println("¡La letra está en la palabra secreta!");
                        panel.mostrarPanel(conjuntoRespuesta);
                        //Sumamos la cantidad de la posicion aleatoria de la ruleta en la puntuación del jugador.
                        this.jugador1.setPuntuacion(jugador1.getPuntuacion() + this.ruleta.getRuleta()[aleatorio]);
                    }

                    if (validadorHayLetra != true) {
                        System.out.println("La letra no estaba en la frase...");
                    }
                }

                //Si la respuesta tiene más de una letra y es la misma que la palabraSecreta habrá ganado
                if (respuesta.length() > 1) {

                    if (respuesta.equalsIgnoreCase(panel.getPalabraSecreta())) {

                        System.out.println("¡Si!¡Acertaste!");
                        this.jugador1.setPuntuacion(jugador1.getPuntuacion() + this.ruleta.getRuleta()[aleatorio]);
                        System.out.println("¡ " + this.jugador1.getNombre() + " ha ganado " + getJugador1().getPuntuacion() + " €!");
                        ganador = true;

                    } else {

                        System.out.println("!No!¡Esa no era la palabra secreta!");
                        ganador = false;
                    }
                }

            }

        }
        System.out.println("º-º-º-º-º--º-º-º-º-º-º-º-º-º-");
        return ganador;
    }

    public boolean juegoJugador2(char conjuntoRespuesta[]) {

        String lanzamiento, respuesta;
        int aleatorio;
        char letra;

        boolean ganador = false, validadorHayLetra = false;
        Scanner entrada = new Scanner(System.in);

        System.out.println("¡Juega " + jugador2.getNombre());
        System.out.println("-------------------------------");
        System.out.print("¡Pulsa cualquier letra e intro para lanzar la ruleta!");
        lanzamiento = entrada.nextLine();

        //Comprobación que pulsó alguna letra.
        if (lanzamiento.length() > 0) {

            aleatorio = (int) Math.floor(Math.random() * 10);
            //ruleta.mostrarRule(0);

            System.out.println("¡Salio la casilla de " + this.ruleta.getRuleta()[aleatorio] + " €!");

            if (aleatorio == 5) {
                System.out.println("Vaya... salió quiebra... tu marcado queda a 0.");
                this.jugador2.setPuntuacion(0);
                ganador = false;
            } else {
                //Preguntamos si quiere letra o número.En ambos casos nos devuelve una cadena.
                respuesta = Test.menu();

                //Si la respuesta tiene una letra entraremos a comprobarla en la palabra secreta.En el método pedirLetra sólo se captura una letra.
                if (respuesta.length() == 1) {
                    //Transformamos la respuesta a un caracter. 
                    letra = respuesta.charAt(0);

                    for (int i = 0; i < panel.getPalabraSecreta().length(); i++) {

                        if (letra == panel.getPalabraSecreta().charAt(i)) {
                            conjuntoRespuesta[i] = letra;
                            validadorHayLetra = true;
                        }

                    }
                    //Si se encontró letra en la palabra.
                    if (validadorHayLetra == true) {
                        System.out.println("¡La letra está en la palabra secreta!");
                        panel.mostrarPanel(conjuntoRespuesta);
                        //Sumamos la cantidad de la posicion aleatoria de la ruleta en la puntuación del jugador.
                        this.jugador2.setPuntuacion(jugador2.getPuntuacion() + this.ruleta.getRuleta()[aleatorio]);
                    }

                    if (validadorHayLetra != true) {
                        System.out.println("La letra no estaba en la frase...");
                    }

                }

                //Si la respuesta tiene más de una letra y es la misma que la palabraSecreta habrá ganado
                if (respuesta.length() > 1) {

                    if (respuesta.equalsIgnoreCase(panel.getPalabraSecreta())) {

                        System.out.println("¡Si!¡Acertaste!");
                        this.jugador2.setPuntuacion(jugador2.getPuntuacion() + this.ruleta.getRuleta()[aleatorio]);
                        System.out.println("¡ " + this.jugador2.getNombre() + " ha ganado " + getJugador2().getPuntuacion() + " €!");
                        ganador = true;

                    } else {

                        System.out.println("!No!¡Esa no era la palabra secreta!");
                        ganador = false;
                    }
                }

            }

        }
        System.out.println("º-º-º-º-º--º-º-º-º-º-º-º-º-º-");
        return ganador;
    }

}
