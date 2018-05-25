package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.ChoiceTranslator;
import com.kondzio.ships.GameSpecs;
import com.kondzio.ships.Point;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {

    public static final int CELL_HEIGHT = 80;
    public static final int CELL_WIDTH = 80;


    public BoardPanel(int sizeX, int sizeY, BoardJTableModel boardJTableModel, String[] rowHeaders, OnSelectAction onSelectAction) {

        ListModel rowHeadersModel = new AbstractListModel() {

            public int getSize() {
                return rowHeaders.length;
            }

            public Object getElementAt(int index) {
                return rowHeaders[index];
            }
        };

        DefaultTableModel dm = new DefaultTableModel(rowHeadersModel.getSize(), sizeX);
        JTable table = new JTable(dm);
        table.setRowHeight(CELL_HEIGHT);
        int columnCount = table.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            table.getColumnModel().getColumn(i).setMaxWidth(CELL_WIDTH);
            table.getColumnModel().getColumn(i).setMinWidth(CELL_WIDTH);
            ;
            table.getColumnModel().getColumn(i).setPreferredWidth(CELL_WIDTH);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JList rowHeader = new JList(rowHeadersModel);
        rowHeader.setFixedCellWidth(30);
        rowHeader.setFixedCellHeight(CELL_HEIGHT);

        rowHeader.setCellRenderer(new RowHeaderRenderer(table));


        table.setModel(boardJTableModel);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(CELL_WIDTH * (sizeX + 1), CELL_HEIGHT * (sizeY + 1)));
        scroll.setRowHeaderView(rowHeader);
        add(scroll, BorderLayout.CENTER);

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    onSelectAction.perform(row, col);
                }
                table.repaint();
            }

        });
    }


}
