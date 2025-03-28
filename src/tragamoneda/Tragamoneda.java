package tragamoneda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Esta clase implementa una aplicación de tragamonedas simple utilizando JavaFX.
 * Los usuarios pueden hacer clic en el botón "Girar" para generar una combinación aleatoria de símbolos.
 * Si la combinación es ganadora, se muestra un mensaje de "¡Ganaste!".
 * Si no lo es, se muestra "Intenta de nuevo".
 */
public class Tragamoneda extends Application {

    // Instancia del modelo de tragamonedas
    private final SlotMachine model = new SlotMachine();
    // Executor para manejar la lógica en un hilo separado
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Modelo de la tragamonedas, define los símbolos y las reglas del juego.
     * Genera combinaciones aleatorias de símbolos y verifica si hay una combinación ganadora.
     */
    class SlotMachine {

        private final String[] symbols = {"🍒", "🔔", "🍋", "⭐", "💎"}; // Símbolos posibles de la tragamonedas
        private final Random random = new Random(); // Generador de números aleatorios

        /**
         * Genera una combinación aleatoria de tres símbolos.
         * @return una matriz de tres símbolos aleatorios.
         */
        public String[] spin() {
            return new String[]{symbols[random.nextInt(symbols.length)],
                symbols[random.nextInt(symbols.length)],
                symbols[random.nextInt(symbols.length)]};
        }

        /**
         * Verifica si la combinación generada es ganadora (los tres símbolos son iguales).
         * @param result la combinación de símbolos a verificar.
         * @return true si la combinación es ganadora, false en caso contrario.
         */
        public boolean isWinningCombination(String[] result) {
            return result[0].equals(result[1]) && result[1].equals(result[2]);
        }
    }

    /**
     * Método principal que inicializa la interfaz gráfica de usuario.
     * @param primaryStage el escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        // Crear la etiqueta para mostrar el resultado
        Label resultLabel = new Label("Presiona 'Girar' para jugar");
        resultLabel.setFont(new Font("Arial", 20)); // Establecer la fuente y el tamaño del texto
        resultLabel.setTextFill(Color.DODGERBLUE); // Establecer el color inicial del texto

        // Crear el botón "Girar"
        Button spinButton = new Button("Girar");
        // Estilos del botón (color de fondo, texto, tamaño y bordes)
        spinButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10;");
        spinButton.setMinSize(100, 40); // Tamaño mínimo del botón
        spinButton.setMaxSize(150, 60); // Tamaño máximo del botón

        // Acción que ocurre cuando se hace clic en el botón "Girar"
        spinButton.setOnAction(e -> executor.execute(() -> {
            // Generar una combinación aleatoria
            String[] result = model.spin();
            String displayResult = String.join(" | ", result); // Mostrar los símbolos generados

            // Verificar si la combinación es ganadora
            boolean isWin = model.isWinningCombination(result);
            String message = isWin ? "¡Ganaste!" : "Intenta de nuevo"; // Mensaje dependiendo del resultado

            // Actualizar la interfaz gráfica en el hilo de JavaFX
            javafx.application.Platform.runLater(() -> {
                resultLabel.setText(displayResult + "\n" + message); // Actualizar el texto de la etiqueta

                // Cambiar el color del mensaje según el resultado
                if (isWin) {
                    resultLabel.setTextFill(Color.GREEN); // Color verde para ganar
                } else {
                    resultLabel.setTextFill(Color.RED); // Color rojo para no ganar
                }

                // Establecer estilos adicionales para la etiqueta de resultado
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            });
        }));

        // Crear el contenedor principal y agregar los elementos (etiqueta y botón)
        VBox root = new VBox(20, resultLabel, spinButton);
        root.setStyle("-fx-background-color: #F0F8FF; -fx-padding: 30; -fx-alignment: center; -fx-border-color: #ADD8E6; -fx-border-width: 3px;");

        // Crear la escena y asignar el contenedor al escenario
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene); // Establecer la escena en el escenario principal
        primaryStage.setTitle("Tragamonedas"); // Título de la ventana
        primaryStage.show(); // Mostrar el escenario
    }

    /**
     * Método para detener el executor cuando se cierre la aplicación.
     */
    @Override
    public void stop() {
        executor.shutdown(); // Apagar el servicio de ejecución
    }

    /**
     * Método principal para iniciar la aplicación JavaFX.
     * @param args los argumentos de línea de comandos (no se usan en este caso).
     */
    public static void main(String[] args) {
        launch(args); // Lanzar la aplicación JavaFX
    }
}
