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
        doInsert(false);
    }

    public void doMove(Directions direction) {
        switch (direction) {
        case DOWN:
        case UP:
        case LEFT:
        case RIGHT:
            field.doMove(direction);
            doInsert(true);
            break;

        default:
            break;
        }
    }
    
    public boolean doRevert() {
        return field.doRevert();
    }

    public Field getField() {
        return field;
    }

    public boolean isWin() {
        int maxLevel = Values.maxValue();

        return field.findValue(maxLevel);
    }

    public boolean isLost() {
        return field.isFilled() && field.isLost();
    }

    public void doFill(Cell cell) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int k = 0; k < FIELD_SIZE; k++) {
                field.setCell(i, k, cell);
            }
        }
    }

    private void doInsert(boolean isNeedCheck) {
        if (field.isFilled() || (isNeedCheck && !field.isChanged())) {
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
}
