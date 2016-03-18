package by.holovin.logic;

import java.util.Random;

public class Game {
    final public static int FIELD_SIZE = 4;

    private Random r;
    private Field field;

    public Game() {
        r = new Random();
        start();
    }

    public void start() {
        field = new Field(FIELD_SIZE);
        doInsert();
    }

    public void doMove(Directions direction) {
        switch (direction) {
            case DOWN:
            case UP:
            case LEFT:
            case RIGHT:
                field.doMove(direction);
                doInsert();
                return;

            default:
        }
    }

    public Field getField() {
        return field;
    }

    private void doInsert() {
        if (field.isFilled()) {
            return;
        }

        int row, col;

        do {
            row = r.nextInt(FIELD_SIZE);
            col = r.nextInt(FIELD_SIZE);

            if (field.getCell(row, col).isEmpty()) {
                field.updateCell(row, col);
                break;
            }

        } while (true);
    }

    public boolean isWin() {
        int maxLevel = Values.maxValue();

        return field.findValue(maxLevel);
    }

    public boolean isEnd() {
        return field.isFilled();
    }
}
