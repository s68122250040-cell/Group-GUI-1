package com.example.demo2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AreaCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. สร้าง UI Controls
        ComboBox<String> shapeCombo = new ComboBox<>();
        shapeCombo.getItems().addAll("Circle", "Rectangle", "Triangle");
        shapeCombo.setValue("Circle");

        Label lblInput1 = new Label("Enter radius (for Circle) or length (for Rectangle/Triangle):");
        TextField txtInput1 = new TextField();

        Label lblInput2 = new Label("Enter height (for Triangle):");
        TextField txtInput2 = new TextField();

        Button btnCalculate = new Button("Calculate");
        Label lblResult = new Label("Area: ");
        TextField txtResult = new TextField();
        txtResult.setEditable(false);

        // 2. จัดวาง Layout ด้วย GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        // แถวที่ 1: เลือกรูปทรง และช่องกรอกค่าที่ 1
        grid.add(shapeCombo, 0, 0);
        grid.add(lblInput1, 1, 0);

        // แถวที่ 2: ช่องกรอกค่าที่ 1 และ 2
        grid.add(txtInput1, 0, 1);
        grid.add(lblInput2, 1, 1);
        grid.add(txtInput2, 2, 1);

        // แถวที่ 3: ปุ่มคำนวณและผลลัพธ์
        HBox resultBox = new HBox(10, btnCalculate, lblResult, txtResult);
        resultBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(resultBox, 0, 2, 3, 1);

        // 3. Logic การคำนวณ
        btnCalculate.setOnAction(e -> {
            try {
                String shape = shapeCombo.getValue();
                double val1 = Double.parseDouble(txtInput1.getText());
                double area = 0;

                if (shape.equals("Circle")) {
                    // สูตรพื้นที่วงกลม: π * r²
                    area = Math.PI * Math.pow(val1, 2);
                } else if (shape.equals("Rectangle")) {
                    // สำหรับ Rectangle ในภาพใช้ช่องเดียว (อาจหมายถึง Square)
                    // แต่ถ้ามีช่อง height ให้ใช้ val1 * val2
                    double val2 = txtInput2.getText().isEmpty() ? val1 : Double.parseDouble(txtInput2.getText());
                    area = val1 * val2;
                } else if (shape.equals("Triangle")) {
                    // สูตรพื้นที่สามเหลี่ยม: 0.5 * base * height
                    double height = Double.parseDouble(txtInput2.getText());
                    area = 0.5 * val1 * height;
                }

                txtResult.setText(String.valueOf(area));
            } catch (NumberFormatException ex) {
                txtResult.setText("Invalid Input");
            }
        });

        // 4. ตั้งค่า Scene และ Stage
        Scene scene = new Scene(grid, 700, 200);
        primaryStage.setTitle("Area Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
