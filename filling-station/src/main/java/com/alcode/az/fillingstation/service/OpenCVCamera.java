package com.alcode.az.fillingstation.service;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.opencv.core.Mat; // Example: OpenCV
import org.opencv.videoio.VideoCapture; // Example: OpenCV
import org.opencv.imgcodecs.Imgcodecs; // Example: OpenCV

import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;

import java.io.File;
import javax.imageio.ImageIO;

public class OpenCVCamera extends Application {

    private ImageView cameraView = new ImageView();
    private ImageView previewView = new ImageView();
    private Button captureButton = new Button("Capture");
    private Button saveButton = new Button("Save");
    private Button sendButton = new Button("Send");
    private VideoCapture capture;
    private Mat frame;
    private BufferedImage capturedImage;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(cameraView); // Initially show camera
        root.setBottom(captureButton);

        saveButton.setOnAction(e -> {
            try {
                File outputFile = new File("captured.png"); // Example - use FileChooser
                ImageIO.write(capturedImage, "png", outputFile);
                System.out.println("Saved to " + outputFile.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        captureButton.setOnAction(e -> {
            // Grab the current frame
            frame = new Mat();
            capture.read(frame);
            capturedImage = matToBufferedImage(frame);

            // Display in preview
            previewView.setImage(SwingFXUtils.toFXImage(capturedImage, null));

            // Swap views (hide camera, show preview)
            root.setCenter(previewView);
            root.setBottom(saveButton);  // Replace capture button
        });

        // ...  Similar action for sendButton (requires server API interaction) ...

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Camera Application");
        primaryStage.show();

        // Initialize OpenCV
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME); // Load OpenCV library

        capture = new VideoCapture(0); // Open default camera
        if (!capture.isOpened()) {
            System.err.println("Cannot open camera!");
            System.exit(1);
        }

        // Start a thread to continuously grab frames
        new Thread(() -> {
            while (true) {
                frame = new Mat();
                capture.read(frame);

                if (!frame.empty()) {
                    BufferedImage image = matToBufferedImage(frame); // Convert Mat to BufferedImage
                    javafx.scene.image.Image fxImage = SwingFXUtils.toFXImage(image, null); // Convert BufferedImage to JavaFX Image
                    cameraView.setImage(fxImage); // Update the ImageView
                }
            }
        }).start();
    }


    // Helper function to convert OpenCV Mat to BufferedImage
    private BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] b = new byte[bufferSize];
        mat.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((java.awt.image.DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

    @Override
    public void stop() {
        if (capture != null && capture.isOpened()) {
            capture.release(); // Release camera resources
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}