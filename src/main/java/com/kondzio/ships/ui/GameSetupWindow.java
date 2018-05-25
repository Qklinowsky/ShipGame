package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.ChoiceTranslator;
import com.kondzio.ships.GameSpecs;
import com.kondzio.ships.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameSetupWindow extends JFrame {

    private ChoiceTranslator translator = new ChoiceTranslator();

    public GameSetupWindow(GameSpecs gameSpecs, Board ownBoard) {
        setTitle("Ships Setup");
        setSize(1600, 1400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        String[] rowHeaders = translator.createRowHeaders(gameSpecs.getySize());
        String[] columnHeaders = translator.createColumnHeaders(gameSpecs.getxSize());
        BoardJTableModel boardJTableModel = new SetupBoardTableModel(ownBoard, columnHeaders);

        getContentPane().add(new BoardPanel(ownBoard.getxSize(),
                ownBoard.getySize(),
                boardJTableModel,
                rowHeaders,
                (x, y) -> ownBoard.addNewPoint(Point.point(x, y))));
        setVisible(true);
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}

