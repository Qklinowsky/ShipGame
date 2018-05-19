package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.ChoiceTranslator;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class GameWindow extends JFrame {

    private final Board board;
    private ChoiceTranslator translator;

    public GameWindow(Board board) {
        translator = new ChoiceTranslator();
        int gridSizeX = board.getxSize() + 1;
        int gridSizeY = board.getySize() + 1;
        this.board = board;
        setTitle("Ships");
        setSize(1600, 1400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        ScoreJPanel scoreJPanel = new ScoreJPanel();
        getContentPane().add(scoreJPanel);
//        getContentPane().add(new BoardPanel(translator.createRowHeaders(board.getySize()),
//                translator.createColumnHeaders(board.getxSize()),
//                board,
//                scoreJPanel));
        setVisible(true);
    }

}
