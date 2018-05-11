package com.kondzio.ships.ui;

import javax.swing.*;

public class ScoreJPanel extends JPanel {

    private final JLabel scoreLabel;

    public ScoreJPanel() {
        scoreLabel = new JLabel(" ");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel("Score: "));
        add(scoreLabel);

    }

    public void setScore(int score) {
        scoreLabel.setText("" + score);
    }
}
