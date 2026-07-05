package com.librarymanagement.ui.theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DashboardCard extends JPanel {

    private final JButton button;

    public DashboardCard(
            String icon,
            String title,
            String subtitle,
            Color color
    ) {

        setLayout(new BorderLayout(10,10));

        setBackground(Color.WHITE);

        setBorder(new LineBorder(
                new Color(220,220,220),
                1,
                true
        ));

        setPreferredSize(
                new Dimension(180,180)
        );

        JLabel lblIcon =
                new JLabel(
                        icon,
                        SwingConstants.CENTER
                );

        lblIcon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        42
                )
        );

        JLabel lblTitle =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        JLabel lblSubtitle =
                new JLabel(
                        "<html><center>"+subtitle+"</center></html>",
                        SwingConstants.CENTER
                );

        lblSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        button =
                new JButton("Open");

        button.setBackground(color);

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setFont(
                Theme.BUTTON_FONT
        );

        add(lblIcon,BorderLayout.NORTH);

        add(lblTitle,BorderLayout.CENTER);

        add(lblSubtitle,BorderLayout.SOUTH);

        JPanel bottom =
                new JPanel();

        bottom.setBackground(Color.WHITE);

        bottom.add(button);

        add(bottom,BorderLayout.PAGE_END);

    }

    public JButton getButton(){

        return button;

    }

}