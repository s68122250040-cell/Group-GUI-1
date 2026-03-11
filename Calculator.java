package com.example.demo2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    private TextField display = new TextField();
    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    @Override
    public void start(Stage primaryStage) {
        // 1. Configure the Display
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setMinHeight(50);
        display.setStyle("-fx-font-size: 20px;");

        // 2. Create the Button Grid
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int index = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                String text = buttons[index++];
                Button btn = createButton(text);
                grid.add(btn, col, row);
            }
        }

        // 3. Main Layout
        VBox root = new VBox(10, display, grid);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setPrefSize(60, 60);
        btn.setStyle("-fx-font-size: 18px;");
        btn.setOnAction(e -> processKeyEvent(text));
        return btn;
    }

    private void processKeyEvent(String value) {
        if (value.matches("[0-9.]")) {
            if (start) {
                display.setText("");
                start = false;
            }
            display.appendText(value);
        } else if (value.equals("=")) {
            if (operator.isEmpty()) return;
            double num2 = Double.parseDouble(display.getText());
            display.setText(String.valueOf(calculate(num1, num2, operator)));
            operator = "";
            start = true;
        } else {
            // Operator clicked
            if (!operator.isEmpty()) return;
            operator = value;
            num1 = Double.parseDouble(display.getText());
            start = true;
        }
    }

    private double calculate(double n1, double n2, String op) {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/": return (n2 == 0) ? 0 : n1 / n2;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}



