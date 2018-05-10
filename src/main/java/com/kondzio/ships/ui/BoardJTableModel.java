package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.Point;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BoardJTableModel extends DefaultTableModel {


    private static final Icon SHIP_ICON = new ImageIcon(BoardJTableModel.class.getResource("/full.png"));
    private static final Icon EMPTY_ICON = new ImageIcon(BoardJTableModel.class.getResource("/empty.png"));
    private static final Icon MISS_ICON = new ImageIcon(BoardJTableModel.class.getResource("/miss.png"));
    private Board board;

    public BoardJTableModel(Board board, String[] columnHeaders) {
        this.board = board;
        setDataVector(new Object[board.getySize()][board.getxSize()], columnHeaders);

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (board.getFieldStatus(Point.point(row, column))) {

            case HIT:
                return SHIP_ICON;
            case MISS:
                return MISS_ICON;
            case HEALTHY:
            case EMPTY:
                return EMPTY_ICON;
            default:
                throw new IllegalArgumentException("no.");
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
