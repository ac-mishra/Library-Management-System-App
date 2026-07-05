package com.librarymanagement.ui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StyledTable extends JTable {

    public StyledTable(DefaultTableModel model) {

        super(model);

        setRowHeight(34);

        setFont(UIConstants.NORMAL_FONT);

        setGridColor(new Color(230,230,230));

        setShowGrid(true);

        setSelectionBackground(new Color(220,235,252));

        setSelectionForeground(Color.BLACK);

        getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        getTableHeader().setBackground(
                UIConstants.PRIMARY
        );

        getTableHeader().setForeground(
                Color.WHITE
        );

        getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0; i < getColumnCount(); i++) {

            getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);

        }

    }

}