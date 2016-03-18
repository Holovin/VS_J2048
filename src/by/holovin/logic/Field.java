package by.holovin.logic;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Cell[][] field;

    private int emptyCells;
    private int size;

    Field(int size) {
        this.size = size;

        emptyCells = size * size;
        field = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                field[i][k] = new Cell();
            }
        }
    }

    Cell getCell(int row, int col) {
        return field[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        if (field[row][col].isEmpty() && !cell.isEmpty()) {
            emptyCells--;
        }

        if (!field[row][col].isEmpty() && cell.isEmpty()) {
            emptyCells++;
        }

        field[row][col] = cell;
    }

    void updateCell(int row, int col) {
        field[row][col].next();
    }

    void doMove(Directions direction) {
        int offset = 0;

        switch (direction) {
            case LEFT:
                offset = 0;
                break;

            case RIGHT:
                offset = size - 1;
                break;

            case UP:
                offset = 0;
                transpose();
                break;

            case DOWN:
                offset = size - 1;
                transpose();
                break;
        }

        List<Cell> temp = new ArrayList<Cell>(4);

        for (int row = 0; row < size; row++) {
            temp.clear();

            // Save
            for (int col = 0; col < size; col++) {
                if (field[row][Math.abs(offset - col)].isEmpty()) {
                    continue;
                }

                temp.add(new Cell(field[row][Math.abs(offset - col)].getValue()));
            }

            // Count
            countLine(temp);

            // Load
            for (int col = 0; col < size; col++) {
                if (col < temp.size()) {
                    field[row][Math.abs(offset - col)] = new Cell(temp.get(col).getValue());
                    continue;
                }

                field[row][Math.abs(offset - col)] = new Cell();
            }
        }

        if (direction == Directions.DOWN || direction == Directions.UP) {
            transpose();
        }
    }

    boolean isFilled() {
        // TODO: fix?
        updateEmptyCount();

        return emptyCells == 0;
    }

    private boolean isRangeCorrect(int coordinate) {
        return !(coordinate >= size || coordinate < 0);
    }

    public String getDisplayString(int row, int col) {
        if (!isRangeCorrect(row) || !isRangeCorrect(col)) {
            return "ERR";
        }

        return field[row][col].toString();
    }

    boolean findValue(int value) {
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (getCell(i, k).getValue() == value) {
                    return true;
                }
            }
        }

        return false;
    }

    private void countLine(List<Cell> line) {
        for (int index = 0; index < line.size() - 1; index++) {
            if (Cell.isCanAdd(line.get(index), line.get(index + 1))) {
                line.get(index).next();
                line.remove(index + 1);
            }
        }
    }

    private void updateEmptyCount() {
        emptyCells = 0;

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (field[i][k].isEmpty()) {
                    emptyCells++;
                }
            }
        }
    }

    private void transpose() {
        Cell[][] newField = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                newField[i][k] = field[k][i];
            }
        }

        field = newField;
    }
}
