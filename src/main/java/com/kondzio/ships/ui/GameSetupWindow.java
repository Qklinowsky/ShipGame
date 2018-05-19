package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.GameSpecs;

import javax.swing.*;
import java.awt.*;

public class GameSetupWindow extends JFrame {


    public GameSetupWindow(GameSpecs gameSpecs, Board board) {
        setTitle("Ships Setup");
        setSize(1600, 1400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        getContentPane().add(new BoardPanel(gameSpecs, board));
        setVisible(true);
    }
}

