package com.alcode.az.fillingstation.service;


import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.InputStream;

public class DigitalClock {

    public static Label getTimeLabel(Label timeLabel) {
        // Load Digital-7 font from resources/fonts
        String fontPath = "/com/alcode/az/fillingstation/fonts/digital-7.ttf";  // Ensure this matches the actual path
        InputStream fontStream = DigitalClock.class.getResourceAsStream(fontPath);
        if (fontStream != null) {
            Font digitalFont = Font.loadFont(fontStream, 100);  // Set size to 40px
            timeLabel.setFont(digitalFont);
            System.out.println("Font file found in resources.");
            return timeLabel;
        } else
            System.out.println("Font file not found in resources!");
        return timeLabel;
    }

}
