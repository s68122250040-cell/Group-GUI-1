package com.example.demo2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UnitConverter extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. สร้าง UI Controls
        Label lblValue = new Label("Enter value:");
        TextField txtInput = new TextField();
        txtInput.setPrefWidth(100);

        Label lblFrom = new Label("From:");
        ComboBox<String> comboFrom = new ComboBox<>();
        comboFrom.getItems().addAll("Celsius", "Fahrenheit");
        comboFrom.setValue("Celsius");

        Label lblTo = new Label("To:");
        ComboBox<String> comboTo = new ComboBox<>();
        comboTo.getItems().addAll("Celsius", "Fahrenheit");
        comboTo.setValue("Celsius");

        Button btnConvert = new Button("Convert");
        Label lblResultText = new Label("Converted Value: Result:");
        TextField txtResult = new TextField();
        txtResult.setEditable(false); // อ่านได้อย่างเดียว
        txtResult.setPrefWidth(100);

        // 2. การวาง Layout (ใช้ GridPane เพื่อความเป๊ะตามภาพ)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        // แถวที่ 1
        grid.add(lblValue, 0, 0);
        grid.add(txtInput, 1, 0);
        grid.add(lblFrom, 2, 0);
        grid.add(comboFrom, 3, 0);
        grid.add(lblTo, 4, 0);
        grid.add(comboTo, 5, 0);

        // แถวที่ 2
        HBox bottomBox = new HBox(10, btnConvert, lblResultText, txtResult);
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(bottomBox, 0, 1, 6, 1); // กินพื้นที่ 6 คอลัมน์

        // 3. Logic การคำนวณ
        btnConvert.setOnAction(e -> {
            try {
                double input = Double.parseDouble(txtInput.getText());
                String from = comboFrom.getValue();
                String to = comboTo.getValue();
                double result = 0;

                if (from.equals(to)) {
                    result = input;
                } else if (from.equals("Celsius") && to.equals("Fahrenheit")) {
                    // สูตร: (C * 9/5) + 32
                    result = (input * 9 / 5) + 32;
                } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
                    // สูตร: (F - 32) * 5/9
                    result = (input - 32) * 5 / 9;
                }

                txtResult.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                txtResult.setText("Invalid Input");
            }
        });

        // 4. ตั้งค่า Stage
        Scene scene = new Scene(grid, 600, 150);
        primaryStage.setTitle("Unit Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
