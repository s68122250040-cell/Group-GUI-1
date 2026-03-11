package com.example.demo2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StopWatch extends Application {

    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    private Label lblTimeLeft = new Label("00:00:00");
    private Label lblTimeRight = new Label("00:00:00");
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        // 1. ตั้งค่าตัวเลขเวลา (Labels)
        lblTimeLeft.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        lblTimeLeft.setTextFill(Color.BLUE);

        lblTimeRight.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        lblTimeRight.setTextFill(Color.RED);

        // 2. สร้างปุ่มต่างๆ
        Button btnStart = new Button("Start");
        Button btnReset = new Button("Reset");
        Button btnExit = new Button("Exit");

        // ปรับขนาดปุ่มให้ใหญ่ใกล้เคียงกับรูป
        btnStart.setMaxWidth(Double.MAX_VALUE);
        btnReset.setMaxWidth(Double.MAX_VALUE);
        btnExit.setMaxWidth(Double.MAX_VALUE);
        btnStart.setPrefHeight(40);
        btnReset.setPrefHeight(40);
        btnExit.setPrefHeight(40);

        // 3. จัดวาง Layout ด้วย GridPane
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(30));
        grid.setAlignment(Pos.CENTER);

        // แถวที่ 1: ตัวเลขเวลา
        grid.add(lblTimeLeft, 0, 0);
        grid.add(lblTimeRight, 1, 0);

        // แถวที่ 2: ปุ่ม Start และ Reset
        grid.add(btnStart, 0, 1);
        grid.add(btnReset, 1, 1);

        // แถวที่ 3: ปุ่ม Exit (อยู่ใต้ปุ่ม Start)
        grid.add(btnExit, 0, 2);

        // 4. Logic การทำงานของ Stopwatch
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            seconds++;
            if (seconds == 60) { seconds = 0; minutes++; }
            if (minutes == 60) { minutes = 0; hours++; }
            updateDisplay();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        btnStart.setOnAction(e -> {
            if (btnStart.getText().equals("Start")) {
                timeline.play();
                btnStart.setText("Stop");
            } else {
                timeline.pause();
                btnStart.setText("Start");
            }
        });

        btnReset.setOnAction(e -> {
            timeline.stop();
            seconds = 0; minutes = 0; hours = 0;
            updateDisplay();
            btnStart.setText("Start");
        });

        btnExit.setOnAction(e -> primaryStage.close());

        // 5. แสดงผล Scene
        Scene scene = new Scene(grid, 500, 250);
        primaryStage.setTitle("Stopwatch");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateDisplay() {
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        lblTimeLeft.setText(time);
        lblTimeRight.setText(time); // ในรูปมี 2 ฝั่ง จึงอัปเดตทั้งคู่
    }

    public static void main(String[] args) {
        launch(args);
    }
}
