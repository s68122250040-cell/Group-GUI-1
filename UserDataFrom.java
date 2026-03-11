package com.example.demo2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserDataFrom extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. ส่วนรับข้อมูล (Inputs)
        Label lblName = new Label("Name:");
        TextField txtName = new TextField();

        Label lblAddress = new Label("Address:");
        TextField txtAddress = new TextField();

        Label lblPhone = new Label("Phone Number:");
        TextField txtPhone = new TextField();

        // 2. ส่วนปุ่มและพื้นที่แสดงผล (Submit & Result)
        Button btnSubmit = new Button("Submit");
        Label lblDisplay = new Label("User Data:");
        TextArea txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefSize(300, 100);

        // 3. การวาง Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.TOP_LEFT);

        // แถวที่ 1: ส่วนข้อมูล Input
        grid.add(lblName, 0, 0);
        grid.add(txtName, 1, 0);
        grid.add(lblAddress, 2, 0);
        grid.add(txtAddress, 3, 0);
        grid.add(lblPhone, 4, 0);
        grid.add(txtPhone, 5, 0);

        // แถวที่ 2: ส่วนปุ่มและแสดงผล
        HBox submitBox = new HBox(btnSubmit);
        submitBox.setAlignment(Pos.CENTER_RIGHT);

        HBox resultBox = new HBox(10, lblDisplay, txtResult);
        resultBox.setAlignment(Pos.TOP_LEFT);

        grid.add(submitBox, 1, 1);
        grid.add(resultBox, 3, 1, 3, 1); // กินพื้นที่ 3 คอลัมน์

        // 4. Logic การทำงานของปุ่ม
        btnSubmit.setOnAction(e -> {
            String name = txtName.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();

            String output = String.format("Name: %s\nAddress: %s\nPhone: %s",
                    name, address, phone);

            txtResult.setText(output);
        });

        // 5. ตั้งค่า Stage
        Scene scene = new Scene(grid, 950, 200);
        primaryStage.setTitle("User Data Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
