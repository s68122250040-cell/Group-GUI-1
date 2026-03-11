package com.example.demo2;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.time.LocalTime;

public class Greeting extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. ดึงเวลาปัจจุบันจากระบบ
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        String greeting;

        // 2. ตรวจสอบเงื่อนไขช่วงเวลา
        if (hour >= 5 && hour < 12) {
            greeting = "Good Morning";
        } else if (hour >= 12 && hour < 17) {
            greeting = "Good Afternoon";
        } else if (hour >= 17 && hour < 21) {
            greeting = "Good Evening";
        } else {
            greeting = "Good Night";
        }

        // 3. สร้าง UI Label
        Label lblGreeting = new Label(greeting);
        lblGreeting.setFont(new Font("Arial", 40));

        // 4. จัดวาง Layout (ใช้ StackPane เพื่อให้ข้อความอยู่กลางหน้าจอ)
        StackPane root = new StackPane(lblGreeting);
        root.setAlignment(Pos.CENTER);

        // 5. ตั้งค่า Scene และ Stage
        Scene scene = new Scene(root, 500, 250);
        primaryStage.setTitle("Current Time Greeting");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
