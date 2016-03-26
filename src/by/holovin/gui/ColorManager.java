package by.holovin.gui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import by.holovin.logic.Values;

class ColorManager {
    private static int size = by.holovin.logic.Values.getSize();
    private static Color[] colors = new Color[size];

    static Map<String, Color> buttons = new HashMap<String, Color>();

    private static Color mainColor = new Color(0, 0, 0);

    static Color getMainColor() {
        return mainColor;
    }

    static void init() {
        Random r = new Random();

        final int colorOffset = 5;
        final int colorRange = 10;

        final int baseOffset = 199;
        final int baseRange = 55;

        int baseR = r.nextInt(baseRange) + baseOffset;
        int baseG = r.nextInt(baseRange) + baseOffset;
        int baseB = r.nextInt(baseRange) + baseOffset;

        int offsetR = r.nextInt(colorRange) + colorOffset;
        int offsetG = r.nextInt(colorRange) + colorOffset;
        int offsetB = r.nextInt(colorRange) + colorOffset;

        mainColor = new Color(255 - offsetR, 255 - offsetG, 255 - offsetB);

        System.out.println(baseR + " " + baseG + " " + baseB);
        System.out.println(offsetR + " " + offsetG + " " + offsetB);

        for (int i = 0; i < size; i++) {
            colors[i] = new Color(baseR - offsetR * i, baseG - offsetG * i, baseB - offsetB * i);
        }

        buttons.clear();        
        for (int i = 0; i < size; i++) {
            buttons.put(Values.getKeys()[i], colors[i]);
        }
    }
}
