package com.kondzio.ships.ui;

import com.kondzio.ships.Board;
import com.kondzio.ships.ChoiceTranslator;
import com.kondzio.ships.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final Logger log = LoggerFactory.getLogger(GameWindow.class);

    private boolean isDone;
    private ChoiceTranslator translator = new ChoiceTranslator();

    public GameWindow(Board enemyBoard) {
        setTitle("Ships Setup");
        setSize(1600, 1400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        String[] rowHeaders = translator.createRowHeaders(enemyBoard.getySize());
        String[] columnHeaders = translator.createColumnHeaders(enemyBoard.getxSize());
        BoardJTableModel boardJTableModel = new BoardJTableModel(enemyBoard, columnHeaders);

        getContentPane().add(new BoardPanel(enemyBoard.getxSize(),
                enemyBoard.getySize(),
                boardJTableModel,
                rowHeaders,
                (x, y) -> {
                    if (!isDone) {
                        Point point = Point.point(x, y);
                        log.info("Player choose field {}", point);
                        enemyBoard.pickField(point);
                        isDone = true;
                    }
                }));
        setVisible(true);
    }

    public boolean isDone() {
        return isDone;
    }

    public void yourTurn() {
        isDone = false;
    }
}
