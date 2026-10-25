package dif_e_Integracion;

import java.util.ArrayList;
import java.util.List;

/**
 * Regla del Trapecio Compuesto con duplicación de subintervalos hasta
 * alcanzar una tolerancia. Además aporta la función a integrar y el cálculo
 * del ancho h, que también reutiliza SimpsonCompuesto.
 */
public class TrapecioCompuesto {

    // Datos de la última ejecución (para el reporte comparativo)
    public static int nFinal = 0;                 // subintervalos finales
    public static int iteraciones = 0;            // veces que se duplicó n
    public static List<double[]> historial = new ArrayList<>(); // {n, integral, error}

    /** Función a integrar: f(x) = sen(x). */
    public static double funcion(double x) {
        return Math.sin(x);
    }

    /** Ancho de cada subintervalo: h = (b - a) / n. */
    public static double calcularH(double a, double b, int n) {
        return (b - a) / n;
    }

    /** Trapecio compuesto con n subintervalos: (h/2)[f0 + 2(f1+...+f(n-1)) + fn]. */
    private static double calcularTrapecio(double a, double b, int n) {
        double h = calcularH(a, b, n);
        double suma = 0.0;
        for (int k = 1; k < n; k++) {
            double xk = a + k * h;
            suma += funcion(xk);                  // todos los puntos interiores llevan coeficiente 2
        }
        return (h / 2) * (funcion(a) + 2 * suma + funcion(b));
    }

    /** Duplica n hasta que |I_nueva - I_anterior| <= tolerancia. */
    public static double integrarConTolerancia(double a, double b, double tolerancia) {
        int n = 2;
        iteraciones = 0;
        historial.clear();
        double integralAnterior = calcularTrapecio(a, b, n);
        historial.add(new double[] { n, integralAnterior, Double.NaN });
        double errorAbsoluto = 999.0;

        while (errorAbsoluto > tolerancia) {
            n *= 2;
            iteraciones++;
            double integralNueva = calcularTrapecio(a, b, n);
            errorAbsoluto = Math.abs(integralNueva - integralAnterior);
            historial.add(new double[] { n, integralNueva, errorAbsoluto });
            integralAnterior = integralNueva;
        }
        nFinal = n;
        return integralAnterior;
    }
}