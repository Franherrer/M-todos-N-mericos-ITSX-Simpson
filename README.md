# Práctica: Simpson 1/3 Compuesto vs. Trapecio Compuesto

**Materia:**  Métodos Numéricos 

##  **Unidad 4**
**Instituto:** Instituto Tecnológico Superior de Xalapa
## Ing. en Sistemas Computacionales

Programa en **Java** que calcula una integral definida con la Regla del Trapecio
Compuesta y con la Regla de Simpson 1/3 Compuesta, duplicando el número de
subintervalos `n` hasta alcanzar una tolerancia, y compara cuántas iteraciones y
subintervalos necesita cada método.

## Datos de la prueba

- Función: `f(x) = sen(x)`
- Intervalo: `[0, π]` (valor exacto de la integral: 2)
- Tolerancia: `0.0001`
- Criterio de paro: `|I_nueva − I_anterior| ≤ tolerancia`, empezando en `n = 2` y duplicando `n` en cada iteración.

Para usar otra función, cambia el método `funcion` de `TrapecioCompuesto.java`.
Para cambiar el intervalo o la tolerancia, edita las variables `a`, `b` y `tolerancia` en `Lanzador.java`.

## Estructura del proyecto

Todas las clases pertenecen al paquete `dif_e_Integracion`.

| Archivo | Responsabilidad |
|---|---|
| `TrapecioCompuesto.java` | Función a integrar, cálculo de `h`, Trapecio compuesto e integración con tolerancia. |
| `SimpsonCompuesto.java` | Simpson 1/3 compuesto e integración con tolerancia. Reutiliza `funcion` y `calcularH` de `TrapecioCompuesto`. |
| `Lanzador.java` | Clase principal: ejecuta ambos métodos, imprime las iteraciones y compara. |

### Sección completada en `SimpsonCompuesto`

Dentro del ciclo `for (int k = 1; k < n; k++)` se separan los puntos por su índice:

```java
if (k % 2 == 1) {
    sumaImpares += TrapecioCompuesto.funcion(xk);   // coeficiente 4
} else {
    sumaPares += TrapecioCompuesto.funcion(xk);     // coeficiente 2
}
```

## Lenguaje

Java (JDK 11 o superior).

## Compilar y ejecutar

Desde la carpeta que **contiene** a `dif_e_Integracion/`:

```bash
# Compilar
javac dif_e_Integracion/*.java

# Ejecutar
java dif_e_Integracion.Lanzador
```

También se puede abrir en un IDE (IntelliJ, NetBeans, Eclipse) y ejecutar la clase `Lanzador`.

## Compilación

```
=== TRAPECIO COMPUESTO ===
n =    2 | I = 1.57079633 | error = ---
n =    4 | I = 1.89611890 | error = 0.32532257
n =    8 | I = 1.97423160 | error = 0.07811270
n =   16 | I = 1.99357034 | error = 0.01933874
n =   32 | I = 1.99839336 | error = 0.00482302
n =   64 | I = 1.99959839 | error = 0.00120503
n =  128 | I = 1.99989960 | error = 0.00030121
n =  256 | I = 1.99997490 | error = 0.00007530

=== SIMPSON 1/3 COMPUESTO ===
n =    2 | I = 2.09439510 | error = ---
n =    4 | I = 2.00455975 | error = 0.08983535
n =    8 | I = 2.00026917 | error = 0.00429059
n =   16 | I = 2.00001659 | error = 0.00025258
n =   32 | I = 2.00000103 | error = 0.00001556

COMPARACION
Trapecio: I = 1.99997490 | n = 256 | iteraciones = 7 | error real = 0.00002510
Simpson:  I = 2.00000103 | n = 32 | iteraciones = 4 | error real = 0.00000103
Valor exacto: 2.0

Process finished with exit code 0

## Conclusión

Con la misma función y tolerancia, el **Trapecio** necesitó **n = 256** subintervalos
y **7 iteraciones**, mientras que **Simpson** necesitó **n = 32** y **4 iteraciones**.
Simpson converge con muchos menos puntos porque aproxima con parábolas (error de
orden h⁴) en lugar de rectas (error de orden h²).
