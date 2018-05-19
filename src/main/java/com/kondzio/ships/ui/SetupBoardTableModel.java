package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.Point;
import com.kondzio.ships.ShipStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SetupBoardTableModel extends BoardJTableModel {


    public SetupBoardTableModel(Board board, String[] columnHeaders) {
        super(board, columnHeaders);
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (board.getFieldStatus(Point.point(row, column)) == ShipStatus.HEALTHY) {
            return SHIP_ICON;
        }
        return EMPTY_ICON;
    }
}
