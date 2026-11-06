# 🏎️ La Super Carrera - Juego Incremental en Java

Proyecto Individual Incremental para el módulo de **Programación** (1.º DAW - Curso 2026/2027).

---

## 📝 Descripción del Proyecto

**La Super Carrera** es un videojuego de carreras simulado por consola donde el jugador se enfrenta a una parrilla de pilotos de Fórmula 1[cite: 2]. El usuario introduce su nombre al iniciar mediante la clase `BufferedReader` y toma decisiones estratégicas antes de competir en carreras configurables de entre 5 y 10 vueltas[cite: 2]. 

---

## 🎮 Mecánica de Juego y Estrategia

El jugador elige entre 6 pilotos reales (como Fernando Alonso, Carlos Sainz o Max Verstappen) y selecciona una estrategia de neumáticos antes del semáforo en verde[cite: 2]. Los rivales controlados por el sistema seleccionan sus compuestos de forma aleatoria mediante la clase `Random`[cite: 2].

El motor de físicas del juego calcula el rendimiento en cada vuelta basándose en:
* **Neumático Blando:** Resta 2.0 segundos al tiempo base de la vuelta, pero sufre una degradación muy alta de 10.0 puntos por iteración[cite: 2].
* **Neumático Medio:** Resta 1.0 segundo al tiempo de vuelta, con una degradación moderada de 5.0 puntos[cite: 2].
* **Neumático Duro:** No ofrece bonificación de tiempo (0.0s), pero garantiza durabilidad con solo 1.0 punto de desgaste por vuelta[cite: 2].
* **Cálculo de Tiempos:** El tiempo de cada iteración se genera con una base aleatoria entre 80 y 90 segundos, se suma la ventaja (negativa) del neumático, y se aplica una penalización sumando el desgaste actual multiplicado por `0.06`[cite: 2]. 
* **Límite de Degradación:** El desgaste del neumático de cualquier coche está controlado matemáticamente para no sobrepasar el tope de 100 puntos[cite: 2].

---

## 📋 Estructura del Menú Principal

El flujo principal de la aplicación se controla mediante un bucle `do-while` interactivo, fuertemente estilizado utilizando códigos de escape ANSI (`\u001B[32m`, etc.) para colorear la terminal[cite: 2]:

1. **Nueva Carrera:** Agrupa de forma secuencial la selección de piloto, elección de neumáticos, número de vueltas y ejecución de la simulación con reportes de líder en vivo[cite: 2].
2. **Proximos circuitos:** Opción en desarrollo para futuras expansiones que actualmente imprime un mensaje de "¡Proximamente!"[cite: 2].
3. **Salir:** Rompe el bucle de ejecución y despide al jugador por su nombre[cite: 2].

---

## ⚙️ Arquitectura de Datos y Variables (Versión 1.0 Real)

El código implementa la lógica procedimental completa en un único bloque dentro del archivo `JuegoCarrera.java`[cite: 2].

### Variables Simples y Constantes
* **Entrada de texto:** Variable `nombreUsuario` tipo `String` para leer y personalizar el menú principal[cite: 2].
* **Control de flujo (tipo `int`):** Variables `opcion`, `pilotoElegido`, `neumaticoElegido`, y `vueltas` utilizadas para gestionar las selecciones numéricas del jugador tras los controles de errores[cite: 2].
* **Constante matemática (`final double`):** `PENALIZACION` fijada en `0.06` para multiplicar la pérdida de tiempo provocada por el desgaste[cite: 2].
* **Constantes de diseño (`final String`):** `VERDE`, `CIAN`, `AMARILLO`, `ROJO`, `RESET` almacenan los códigos de color para enriquecer la experiencia visual[cite: 2].

### Arrays Estáticos (Datos de Configuración)
* `pilotos`: Vector `String[]` que inicializa los nombres de los 6 corredores de la parrilla[cite: 2].
* `neumaticos`: Vector `String[]` que enumera los 3 tipos de compuestos ("Blando", "Medio", "Duro")[cite: 2].
* `ventajaNeumatico`: Vector `double[]` posicionado paralelamente a los neumáticos para aplicar los descuentos de tiempo (`-2.0`, `-1.0`, `0.0`)[cite: 2].
* `degradacionPorVuelta`: Vector `double[]` que guarda el daño por iteración para cada tipo de compuesto (`10.0`, `5.0`, `1.0`)[cite: 2].

### Arrays Dinámicos (Estado en Tiempo Real)
* `tiempos`: Vector `double[]` que acumula el tiempo total de carrera que lleva cada piloto[cite: 2].
* `desgaste`: Vector `double[]` que rastrea la degradación actual acumulada de cada vehículo[cite: 2].
* `neumaticoPiloto`: Vector `int[]` que registra el índice (`0`, `1` o `2`) del neumático que está montando cada corredor[cite: 2].

### Funciones y Algoritmos Implementados
* **Gestión de Hilos de ejecución:** Utilización de `Thread.sleep(700)` al finalizar los cálculos de cada iteración para pausar el programa 700 milisegundos, simulando el paso del tiempo en carrera[cite: 2].
* **Algoritmo de búsqueda de mínimos:** Implementación de un recorrido lineal de arrays (buscando siempre la condición `tiempos[i] < tiempos[lider]`) para evaluar quién va en primera posición en la vuelta actual y quién resulta ganador en la bandera a cuadros[cite: 2].
* **Impresión tabular con formato:** Uso extensivo de `System.out.printf()` con banderas de alineación (ej: `%-18s`) para pintar una tabla final de resultados limpia, mostrando los tiempos totales restringidos a dos decimales y marcando visualmente la posición final del jugador con un "<- TU"[cite: 2].
