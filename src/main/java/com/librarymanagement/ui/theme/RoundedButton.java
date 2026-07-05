package com.librarymanagement.ui.theme;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private final Color backgroundColor;

    public RoundedButton(String text, Color color) {

        super(text);

        this.backgroundColor = color;

        setContentAreaFilled(false);

        setFocusPainted(false);

        setBorderPainted(false);

        setForeground(Color.WHITE);

        setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                14
        ));

        setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

    }

    @Override
    protected void paintComponent(Graphics graphics) {

        Graphics2D g2 =
                (Graphics2D) graphics.create();

        g2.setRenderingHint(

                RenderingHints.KEY_ANTIALIASING,

                RenderingHints.VALUE_ANTIALIAS_ON

        );

        g2.setColor(backgroundColor);

        g2.fillRoundRect(

                0,

                0,

                getWidth(),

                getHeight(),

                18,

                18

        );

        super.paintComponent(g2);

        g2.dispose();

    }

}