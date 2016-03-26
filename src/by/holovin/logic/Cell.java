package by.holovin.logic;

public class Cell {
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
    
    public Cell(int value) {
        this.value = value;
    }
    
    Cell(Cell cell) {
        this(cell.getValue());
    }

    void next() {
        value++;
    }

    static boolean isCanAdd(Cell one, Cell two) {
        return one.getValue() == two.getValue();
    }

    @Override
    public String toString() {
        return Values.getValue(value);
    }

}
