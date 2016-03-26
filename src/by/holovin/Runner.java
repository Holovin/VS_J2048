package by.holovin;

import by.holovin.gui.MainFrame;
import by.holovin.logic.Game;

public class Runner {

    public static void main(String[] args) {
        Game game = new Game();
        MainFrame frame = new MainFrame(game, "< 2048 >");
        frame.show();
    }

}
