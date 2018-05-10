package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.ui.RowHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RowHeaderExample extends JFrame {

    public RowHeaderExample() {
        super("Row Header Example");
        setSize(1000, 1000);
        getContentPane().add(new BoardPanel(new String[]{"a", "b", "c", "d"}, new String[]{"1", "2", "3", "4"}, new Board(4,4)));
    }



    public static void main(String[] args) {
        RowHeaderExample frame = new RowHeaderExample();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}
