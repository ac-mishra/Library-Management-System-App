package com.librarymanagement.ui.theme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class Theme {

    private Theme() {
    }

    // Colors
    public static final Color PRIMARY = new Color(25, 118, 210);
    public static final Color SUCCESS = new Color(46, 125, 50);
    public static final Color WARNING = new Color(251, 140, 0);
    public static final Color DANGER = new Color(211, 47, 47);
    public static final Color SECONDARY = new Color(97, 97, 97);

    public static final Color BACKGROUND = new Color(245, 247, 250);
    public static final Color CARD = Color.WHITE;
    public static final Color TABLE_HEADER = new Color(33, 150, 243);

    // Fonts
    public static final Font TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 30);

    public static final Font SUBTITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 20);

    public static final Font NORMAL_FONT =
            new Font("Segoe UI", Font.PLAIN, 14);

    public static final Font BUTTON_FONT =
            new Font("Segoe UI", Font.BOLD, 14);

    public static final Border PANEL_BORDER =
            new EmptyBorder(20,20,20,20);

    public static JButton createButton(
            String text,
            Color color
    ) {

        JButton button = new JButton(text);

        button.setFont(BUTTON_FONT);

        button.setForeground(Color.WHITE);

        button.setBackground(color);

        button.setFocusPainted(false);

        button.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(130,40)
        );

        return button;

    }

    public static JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(NORMAL_FONT);

        field.setPreferredSize(
                new Dimension(250,35)
        );

        return field;

    }

    public static JLabel createTitle(
            String text
    ) {

        JLabel label = new JLabel(
                text,
                SwingConstants.CENTER
        );

        label.setFont(TITLE_FONT);

        label.setForeground(PRIMARY);

        return label;

    }

}