package by.holovin.logic;

class Cell {
    private int value;

    int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    boolean isEmpty() {
        return value == 0;
    }

    Cell() {
        value = 0;
    }

    Cell(int value) {
        this.value = value;
    }

    private void reset() {
        value = 0;
    }

    void next() {
        value++;
    }

    public boolean doSum(Cell cell) {
        if (isEmpty() || isCanAdd(this, cell)) {
            return false;
        }

        this.next();
        cell.reset();

        return true;
    }

    public static boolean isCanAdd(Cell one, Cell two) {
        return one.getValue() == two.getValue();
    }

    @Override
    public String toString() {
        return Values.getValue(value);
    }
}
