package ejer22_t8_ruletadelafortuna;

/**
 *
 * @author Ismael Martín Ramírez
 * [imartinr01@informatica.iesvalledeljerteplasencia.es]
 */
public class Panel {

    private String palabraSecreta;

    public Panel() {
    }

    public Panel(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public void setPalabraSecreta(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
    }

    public void mostrarPanel(char conjuntoRespuesta[]) {

        for (int i = 0; i < conjuntoRespuesta.length; i++) {
            System.out.print(conjuntoRespuesta[i] + " ");
        }
        System.out.println("");
    }

}
