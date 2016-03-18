package by.holovin.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import by.holovin.logic.Directions;
import by.holovin.logic.Game;

public class MainFrame {
    private Game game;
    private JButton buttons[][];

    private JFrame frame;

    public MainFrame(Game game, String title) {
        this.game = game;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.pack();

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(Game.FIELD_SIZE, Game.FIELD_SIZE));

        Init();
    }

    private void Init() {
        game.start();

        buttons = new JButton[Game.FIELD_SIZE][Game.FIELD_SIZE];

        for (int i = 0; i < Game.FIELD_SIZE; i++) {
            for (int k = 0; k < Game.FIELD_SIZE; k++) {

                buttons[i][k] = new JButton(game.getField().getDisplayString(i, k));
                buttons[i][k].setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
                buttons[i][k].setFocusable(false);

                frame.add(buttons[i][k]);
            }
        }

        this.frame.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.doMove(Directions.LEFT);
                        break;

                    case KeyEvent.VK_RIGHT:
                        game.doMove(Directions.RIGHT);
                        break;

                    case KeyEvent.VK_UP:
                        game.doMove(Directions.UP);
                        break;

                    case KeyEvent.VK_DOWN:
                        game.doMove(Directions.DOWN);
                        break;
                }

                updateData();

                if (game.isEnd()) {
                    if (game.isWin()) {
                        JOptionPane.showMessageDialog(frame, "GG, WP!", "You win", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Try again!", "You lost", JOptionPane.INFORMATION_MESSAGE);
                    }

                    System.exit(0);
                }
            }
        });
    }

    private void updateData() {
        for (int i = 0; i < Game.FIELD_SIZE; i++) {
            for (int k = 0; k < Game.FIELD_SIZE; k++) {
                buttons[i][k].setText(game.getField().getDisplayString(i, k));
            }
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}
