package com.librarymanagement.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CardPanel extends JPanel {

    public CardPanel(String title) {

        setLayout(new BorderLayout(15,15));

        setBackground(Color.WHITE);

        setBorder(BorderFactory.createCompoundBorder(

                BorderFactory.createLineBorder(

                        new Color(220,220,220),

                        1,

                        true

                ),

                new EmptyBorder(20,20,20,20)

        ));

        JLabel lblTitle = new JLabel(title);

        lblTitle.setFont(

                new Font(

                        "Segoe UI",

                        Font.BOLD,

                        20

                )

        );

        lblTitle.setForeground(

                new Color(25,118,210)

        );

        add(lblTitle,BorderLayout.NORTH);

    }

}