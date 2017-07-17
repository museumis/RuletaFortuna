package ejer22_t8_ruletadelafortuna;

import java.util.Scanner;

/**
 *
 * @author Ismael Martín Ramírez
 * [imartinr01@informatica.iesvalledeljerteplasencia.es]
 */
public class Test {

    public static String pedirNombre(int jugador) {

        Scanner entrada = new Scanner(System.in);

        if (jugador == 1) {
            System.out.print("¿Nombre del primer jugador?: ");
        } else {
            System.out.print("¿Nombre del segundo jugador?");
        }
        return entrada.nextLine();

    }

    public static String menu() {

        int eleccion;
        String palabra = "";
        char letra;
        Scanner entrada = new Scanner(System.in);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Pulsa 1 para pedir letra ");
        System.out.print("Pulsa 2 para resolver ");
        eleccion = entrada.nextInt();

        if (eleccion == 1) {
            letra = pedirLetra();
            palabra = Character.toString(letra);
        }

        if (eleccion == 2) {
            palabra = pedirPalabraSecre();
        }

       return palabra;
    }

    public static char pedirLetra() {

        String respuesta;

        Scanner entrada = new Scanner(System.in);

        System.out.print("¿Letra? ");
        respuesta = entrada.nextLine();
        

        return respuesta.charAt(0);
    }

    public static String pedirPalabraSecre() {

        String palabra;
        Scanner entrada = new Scanner(System.in);

        System.out.print("¿Cúal es la respuesta? ");
        palabra = entrada.nextLine();

        return palabra;

    }
    

    public static void main(String[] args) {

        Juego juego = new Juego();

        System.out.println("º-º-º-º-º--º-º-º-º-º-º-º-º-º-");
        System.out.println("   La Ruleta de la fortuna  ");
        System.out.println("º-º-º-º-º--º-º-º-º-º-º-º-º-º-");

        juego.comienzo();

        System.out.println("º-º-º-º-º--º-º-º-º-º-º-º-º-º-");
        System.out.println("Intenta adivinar la fruta que se esconde tras la palabra secreta.");
        System.out.println("º-º-º-º-º--º-º-º-º-º-º-º-º-º-");

        juego.partida();
        
        System.out.println("¡Saludoos!");
    }

}
