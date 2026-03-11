package com.example.demo2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends Application {

    // กำหนดอัตราแลกเปลี่ยนจำลอง (เทียบกับ 1 USD)
    private final Map<String, Double> rates = new HashMap<>() {{
        put("USD", 1.0);
        put("EUR", 0.92);
        put("THB", 35.50);
    }};

    @Override
    public void start(Stage primaryStage) {
        // 1. สร้าง UI Controls
        Label lblAmount = new Label("Amount:");
        TextField txtAmount = new TextField();
        txtAmount.setPrefWidth(150);

        Label lblFrom = new Label("From Currency:");
        ComboBox<String> comboFrom = new ComboBox<>();
        comboFrom.getItems().addAll("USD", "EUR", "THB");
        comboFrom.setValue("USD");

        Label lblTo = new Label("To Currency:");
        ComboBox<String> comboTo = new ComboBox<>();
        comboTo.getItems().addAll("USD", "EUR", "THB");
        comboTo.setValue("THB");

        Button btnConvert = new Button("Convert");
        Label lblResult = new Label("Result: 0.00");
        lblResult.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // 2. การวาง Layout (GridPane)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        // แถวที่ 1
        grid.add(lblAmount, 0, 0);
        grid.add(txtAmount, 1, 0, 2, 1); // กินพื้นที่ 2 คอลัมน์
        grid.add(lblFrom, 3, 0);

        // แถวที่ 2
        HBox midBox = new HBox(10, comboFrom, lblTo, comboTo, btnConvert);
        midBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(midBox, 0, 1, 5, 1);

        // แถวที่ 3
        grid.add(lblResult, 1, 2, 3, 1);

        // 3. Logic การคำนวณ
        btnConvert.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText());
                String from = comboFrom.getValue();
                String to = comboTo.getValue();

                // คำนวณ: แปลงจากหน่วยต้นทางเป็น USD ก่อน แล้วค่อยแปลงเป็นหน่วยปลายทาง
                double amountInUSD = amount / rates.get(from);
                double convertedAmount = amountInUSD * rates.get(to);

                lblResult.setText(String.format("Result: %.2f", convertedAmount));
            } catch (NumberFormatException ex) {
                lblResult.setText("Invalid Input");
            }
        });

        // 4. ตั้งค่า Scene และ Stage
        Scene scene = new Scene(grid, 550, 200);
        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
