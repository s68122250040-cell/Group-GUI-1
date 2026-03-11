package com.example.demo2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.regex.Pattern;

public class EmailValidator extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. สร้าง UI Controls
        Label lblEmailHeader = new Label("Email:");
        lblEmailHeader.setFont(Font.font("Arial", 14));

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Enter your email here");
        txtEmail.setPrefWidth(300);

        Button btnValidate = new Button("Validate");
        Label lblResult = new Label(""); // ป้ายสำหรับแสดงผล
        lblResult.setFont(Font.font("Arial", 16));

        // 2. การวาง Layout (ใช้ GridPane เพื่อจัดเรียงตามภาพ)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // แถวที่ 1: Label Email (อยู่เหนือช่องพิมพ์)
        grid.add(lblEmailHeader, 0, 0, 2, 1);
        GridPane.setHalignment(lblEmailHeader, javafx.geometry.HPos.CENTER);

        // แถวที่ 2: TextField (ยาวเต็มบรรทัด)
        grid.add(txtEmail, 0, 1, 2, 1);

        // แถวที่ 3: ปุ่ม Validate และ Label ผลลัพธ์
        HBox actionBox = new HBox(15, btnValidate, lblResult);
        actionBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(actionBox, 0, 2, 2, 1);

        // 3. Logic การตรวจสอบ Email ด้วย Regular Expression
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);

        btnValidate.setOnAction(e -> {
            String email = txtEmail.getText().trim();
            if (pattern.matcher(email).matches()) {
                lblResult.setText("Valid email address.");
                lblResult.setTextFill(Color.GREEN);
            } else {
                lblResult.setText("Invalid email address.");
                lblResult.setTextFill(Color.RED);
            }
        });

        // 4. ตั้งค่า Scene และ Stage
        Scene scene = new Scene(grid, 450, 180);
        primaryStage.setTitle("Email Validator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
