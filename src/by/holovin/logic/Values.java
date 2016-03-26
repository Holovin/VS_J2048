package by.holovin.logic;

public class Values {
    // You can define any words for this values
    private static String[] KEYS = {" ", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"};

    public static String[] getKeys() {
        return KEYS;
    }

    static String getValue(int index) {
        if (index > KEYS.length || index < 0) {
            return "";
        }

        return KEYS[index];
    }

    public static int maxValue() {
        return KEYS.length - 1;
    }
    
    public static int getSize() {
        return KEYS.length;
    }
}
