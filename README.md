# Proyecto Tragamonedas

Una aplicación simple de tragamonedas utilizando **JavaFX**. Los usuarios pueden hacer clic en el botón "Girar" para generar una combinación aleatoria de símbolos. Si la combinación es ganadora, se muestra un mensaje de "¡Ganaste!", y si no, un mensaje de "Intenta de nuevo".

## Descripción

Este proyecto consiste en una interfaz gráfica de usuario (GUI) que simula una máquina tragamonedas. Utiliza la biblioteca **JavaFX** para crear la ventana, los botones y los textos, y **ExecutorService** para manejar la lógica de la tragamonedas en un hilo separado. El juego genera una combinación aleatoria de tres símbolos y verifica si son iguales, lo que determina si el jugador ha ganado.

## Características

- Genera una combinación aleatoria de símbolos (como 🍒, 🍋, 🍎).
- Muestra el mensaje "¡Ganaste!" si los tres símbolos son iguales.
- Muestra "Intenta de nuevo" si la combinación no es ganadora.
- Interfaz gráfica simple con un botón para girar los símbolos.

## Instalación

Para ejecutar esta aplicación, necesitas tener **Java 8 o superior** instalado en tu máquina.

1. **Clona el repositorio:**

    ```bash
    git clone https://github.com/roger742/tragamoneda.git
    ```

2. **Navega al directorio del proyecto:**

    ```bash
    cd Tragamoneda
    ```

3. **Compila el proyecto:**

    ```bash
    javac Tragamoneda.java
    ```

4. **Ejecuta la aplicación:**

    ```bash
    java Tragamoneda
    ```

## Uso

- Haz clic en el botón "Girar" para generar una combinación aleatoria de símbolos.
- Si todos los símbolos generados son iguales, se muestra un mensaje de "¡Ganaste!".
- Si no son iguales, se muestra "Intenta de nuevo".


## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.
