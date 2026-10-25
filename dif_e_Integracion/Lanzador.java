package dif_e_Integracion;

import java.util.List;

/**
 * Clase principal: ejecuta el Trapecio Compuesto y Simpson 1/3 Compuesto
 * con la misma función, intervalo y tolerancia, y compara los resultados.
 */
public class Lanzador {

    public static void main(String[] args) {
        double a = 0.0;                 // límite inferior
        double b = Math.PI;             // límite superior
        double tolerancia = 0.0001;     // tolerancia de error
        double exacto = 2.0;            // integral exacta de sen(x) en [0, pi]

        double trapecio = TrapecioCompuesto.integrarConTolerancia(a, b, tolerancia);
        double simpson = SimpsonCompuesto.integrarConTolerancia(a, b, tolerancia);

        System.out.println("Funcion: f(x) = sen(x)   Intervalo: [0, pi]   Tolerancia: " + tolerancia);

        imprimirTabla("TRAPECIO COMPUESTO", TrapecioCompuesto.historial);
        imprimirTabla("SIMPSON 1/3 COMPUESTO", SimpsonCompuesto.historial);

        System.out.println();
        System.out.println("COMPARACION");
        System.out.printf("Trapecio: I = %.8f | n = %d | iteraciones = %d | error real = %.8f%n",
                trapecio, TrapecioCompuesto.nFinal, TrapecioCompuesto.iteraciones, Math.abs(exacto - trapecio));
        System.out.printf("Simpson:  I = %.8f | n = %d | iteraciones = %d | error real = %.8f%n",
                simpson, SimpsonCompuesto.nFinal, SimpsonCompuesto.iteraciones, Math.abs(exacto - simpson));
        System.out.println("Valor exacto: " + exacto);
    }

    /** Imprime n, la integral y el error de cada iteración. */
    private static void imprimirTabla(String titulo, List<double[]> historial) {
        System.out.println();
        System.out.println("=== " + titulo + " ===");
        for (double[] fila : historial) {
            if (Double.isNaN(fila[2])) {
                System.out.printf("n = %4d | I = %.8f | error = ---%n", (int) fila[0], fila[1]);
            } else {
                System.out.printf("n = %4d | I = %.8f | error = %.8f%n", (int) fila[0], fila[1], fila[2]);
            }
        }
    }
}