package com.alcode.az.fillingstation.service;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebCamService extends Application {

    private ImageView imageView;
    private Webcam webcam;
    private ScheduledExecutorService executorService;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the webcam
        webcam = Webcam.getDefault();
        if (webcam == null) {
            System.out.println("No webcam detected!");
            return;
        }
        webcam.open();

        // Create UI components
        imageView = new ImageView();
        Button captureButton = new Button("Capture Image");
        captureButton.setOnAction(e -> captureImage());

        // Layout
        VBox root = new VBox(10, imageView, captureButton);
        Scene scene = new Scene(root, 640, 480);
        primaryStage.setTitle("Camera Capture");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the live feed
        startLiveFeed();
    }

    private void startLiveFeed() {
        // Create a scheduled executor service to fetch frames periodically
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            if (webcam.isOpen()) {
                // Capture a frame from the webcam
                BufferedImage bufferedImage = webcam.getImage();

                // Convert BufferedImage to JavaFX Image
                Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);

                // Update the ImageView on the JavaFX Application Thread
                Platform.runLater(() -> imageView.setImage(fxImage));
            }
        }, 0, 80, TimeUnit.MILLISECONDS); // ~30 FPS (1000ms / 30 ≈ 33ms per frame)
    }

    private void captureImage() {
        if (webcam == null || !webcam.isOpen()) {
            System.out.println("Webcam is not available!");
            return;
        }

        // Capture an image from the webcam
        BufferedImage bufferedImage = webcam.getImage();

        // Convert BufferedImage to JavaFX Image
        Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(fxImage);

        // Save the image to a file
        saveImageToFile(bufferedImage, "captured_image.png");

        // Alternatively, prepare the image for storage in a database
        byte[] imageBytes = convertImageToByteArray(bufferedImage, "png");
    }

    private void saveImageToFile(BufferedImage image, String filePath) {
        File file = new File(filePath);
        try {
            ImageIO.write(image, "PNG", file);
            System.out.println("Image saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] convertImageToByteArray(BufferedImage image, String format) {
        try {
            File tempFile = File.createTempFile("temp", "." + format);
            ImageIO.write(image, format, tempFile);
            return java.nio.file.Files.readAllBytes(tempFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void stop() {
        // Close the webcam and shutdown the executor service when the application exits
        if (webcam != null) {
            webcam.close();
        }
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}