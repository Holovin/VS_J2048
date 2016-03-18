package by.holovin.logic;

class Values {
    // You can define any words for this values
    private static String[] KEYS = {" ", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"};

    static String getValue(int index) {
        if (index > KEYS.length || index < 0) {
            return "";
        }

        return KEYS[index];
    }

    static int maxValue() {
        return KEYS.length - 1;
    }
}
