package by.holovin.gui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import by.holovin.logic.Cell;
import by.holovin.logic.Directions;
import by.holovin.logic.Game;
import by.holovin.logic.Values;

public class MainFrame {
    private Game game;
    private JButtonEx buttons[][];
    private JFrame frame;

    public MainFrame(Game game, String title) {
        this.game = game;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.pack();

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(Game.FIELD_SIZE, Game.FIELD_SIZE));

        Init();
    }

    private void Init() {
        final int fontSizeMod = 30;
        ColorManager.init();
        game.start();

        buttons = new JButtonEx[Game.FIELD_SIZE][Game.FIELD_SIZE];

        for (int i = 0; i < Game.FIELD_SIZE; i++) {
            for (int k = 0; k < Game.FIELD_SIZE; k++) {

                buttons[i][k] = new JButtonEx(game.getField().getDisplayString(i, k));
                buttons[i][k].setFont(new Font(Font.MONOSPACED, Font.BOLD, frame.getSize().width / fontSizeMod));
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

                case KeyEvent.VK_Q:
                    JOptionPane.showMessageDialog(frame, "CHEATS ENABLED", null, JOptionPane.INFORMATION_MESSAGE);
                    game.doFill(new Cell(Values.maxValue()));
                    break;

                case KeyEvent.VK_W:
                    JOptionPane.showMessageDialog(frame, "CHEATS ENABLED", null, JOptionPane.INFORMATION_MESSAGE);
                    game.doFill(new Cell(Values.maxValue() - 4));
                    break;

                case KeyEvent.VK_R:
                    game.start();
                    JOptionPane.showMessageDialog(frame, "Game restarted", "Restarted",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

                case KeyEvent.VK_C:
                    ColorManager.init();
                    break;

                case KeyEvent.VK_Z:
                    if (!game.doRevert()) {
                        JOptionPane.showMessageDialog(frame, "Can't revert more 1 times", "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                case KeyEvent.VK_BACK_SLASH:
                    System.exit(0);

                case KeyEvent.VK_H:
                    
                    break;

                case KeyEvent.VK_ENTER:
                case KeyEvent.VK_CONTROL:
                case KeyEvent.VK_ALT:

                    break;

                default:
                    frame.setState(Frame.ICONIFIED);
                    break;
                }

                updateData();

                if (game.isWin()) {
                    JOptionPane.showMessageDialog(frame, "GG, WP!", "You win", JOptionPane.INFORMATION_MESSAGE);
                    goNext();
                }

                if (game.isLost()) {
                    JOptionPane.showMessageDialog(frame, "Try again!", "You lost", JOptionPane.INFORMATION_MESSAGE);
                    goNext();
                }
            }
        });
    }

    private void goNext() {
        int reply = JOptionPane.showConfirmDialog(null, "Go again?", "Question", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (reply == JOptionPane.YES_OPTION) {
            game.start();
            updateData();
        } else {
            System.exit(0);
        }
    }

    private void updateData() {
        for (int i = 0; i < Game.FIELD_SIZE; i++) {
            for (int k = 0; k < Game.FIELD_SIZE; k++) {
                buttons[i][k].setText(game.getField().getDisplayString(i, k));
                buttons[i][k].repaint();
            }
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}
