package dif_e_Integracion;

import java.util.ArrayList;
import java.util.List;

/**
 * Regla de Simpson 1/3 Compuesta con duplicación de subintervalos hasta
 * alcanzar una tolerancia. Reutiliza la función y el cálculo de h de
 * TrapecioCompuesto.
 */
public class SimpsonCompuesto {

    // Datos de la última ejecución (para el reporte comparativo)
    public static int nFinal = 0;
    public static int iteraciones = 0;
    public static List<double[]> historial = new ArrayList<>(); // {n, integral, error}

    /** Simpson 1/3 compuesto con n subintervalos (n debe ser PAR). */
    private static double calcularSimpson(double a, double b, int n) {
        double h = TrapecioCompuesto.calcularH(a, b, n);
        double sumaImpares = 0.0;
        double sumaPares = 0.0;

        for (int k = 1; k < n; k++) {
            double xk = a + k * h;
            if (k % 2 == 1) {
                // índice impar: punto medio de una parábola -> coeficiente 4
                sumaImpares += TrapecioCompuesto.funcion(xk);
            } else {
                // índice par interior: punto compartido por dos parábolas -> coeficiente 2
                sumaPares += TrapecioCompuesto.funcion(xk);
            }
        }

        double sumaTotal = TrapecioCompuesto.funcion(a) + 4 * sumaImpares
                + 2 * sumaPares + TrapecioCompuesto.funcion(b);
        return (h / 3) * sumaTotal;
    }

    public static double integrarConTolerancia(double a, double b,
                                               double tolerancia) {
        int n = 2;
        iteraciones = 0;
        historial.clear();
        double integralAnterior = calcularSimpson(a, b, n);
        historial.add(new double[] { n, integralAnterior, Double.NaN });
        double errorAbsoluto = 999.0;

        while (errorAbsoluto > tolerancia) {
            n *= 2;
            iteraciones++;
            double integralNueva = calcularSimpson(a, b, n);
            errorAbsoluto = Math.abs(integralNueva - integralAnterior);
            historial.add(new double[] { n, integralNueva, errorAbsoluto });
            integralAnterior = integralNueva;
        }
        nFinal = n;
        return integralAnterior;
    }
}