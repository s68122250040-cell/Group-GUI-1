package com.example.demo2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DigitalClock extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. สร้าง Label สำหรับแสดงเวลา
        Label lblClock = new Label();
        lblClock.setFont(new Font("Arial", 80)); // ตั้งค่าขนาดตัวอักษรให้ใหญ่ตามภาพ
        lblClock.setAlignment(Pos.CENTER);

        // 2. สร้างรูปแบบของเวลา (ชั่วโมง:นาที:วินาที)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // 3. ใช้ Timeline เพื่ออัปเดตเวลาทุก 1 วินาที
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    // ดึงเวลาปัจจุบันมาจัดรูปแบบและแสดงผลบน Label
                    lblClock.setText(LocalTime.now().format(formatter));
                })
        );

        // ตั้งค่าให้ Timeline ทำงานไปเรื่อยๆ (Infinite)
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // เริ่มทำงาน

        // แสดงเวลาทันทีเมื่อเปิดโปรแกรม (ไม่ต้องรอ 1 วินาทีแรก)
        lblClock.setText(LocalTime.now().format(formatter));

        // 4. การวาง Layout
        StackPane root = new StackPane(lblClock);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Digital Clock");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
