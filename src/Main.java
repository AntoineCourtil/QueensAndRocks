import gameElements.*;
import graphics.GameUI;

/**
 * Created by simon on 17/02/17.
 */
public class Main {

    public static void main(String[] args) {
    	
    	Board board = new Board();

    	Player p00 = new Player(0);
    	Player p01 = new Player(1);

    	board.setPiece(2, 2, new Queen(p00));
    	board.setPiece(3, 5, new Queen(p01));

    	board.setPiece(0, 3, new Rock(p00));
    	board.setPiece(1, 3, new Rock(p01));
    	
    	Player p10 = new Player(0);
    	Player p11 = new Player(1);
    	p11.setColorMode("og");
    	p10.setColorMode("og");

    	board.setPiece(4, 4, new Queen(p10));
    	board.setPiece(5, 6, new Queen(p11));

    	board.setPiece(0, 5, new Rock(p10));
    	board.setPiece(1, 6, new Rock(p11));
    	
        GameUI gui = new GameUI(board);
        gui.launch();

    }
}
