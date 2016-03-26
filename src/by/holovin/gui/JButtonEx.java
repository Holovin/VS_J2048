package by.holovin.gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JButton;

class JButtonEx extends JButton {
    private static final long serialVersionUID = 2374202623312303708L;

    JButtonEx(String displayString) {
        super(displayString);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setPaint(new GradientPaint(new Point(0, 0), ColorManager.getMainColor(), new Point(0, getHeight()),
                (Color) ColorManager.buttons.get(getText())));

        g2.fillRect(0, 0, getWidth(), getHeight());

        int xOffsetModify = 5;
        Font f = new Font(Font.MONOSPACED, Font.BOLD, (int) (getHeight() / (xOffsetModify / 2)));
        g2.setFont(f);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.black);

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
        g2.drawString(this.getText(), x, y);

        g2.setColor(Color.GRAY.darker());
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        g2.dispose();
    }
}
