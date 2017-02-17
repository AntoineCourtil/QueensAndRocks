import gameElements.Board;
import graphics.GameUI;

/**
 * Created by simon on 17/02/17.
 */
public class Main {

    public static void main(String[] args) {
        GameUI gui = new GameUI(new Board());
        gui.launch();

    }
}
