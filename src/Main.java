import gameElements.*;
import graphics.GameUI;

import java.util.ArrayList;

/**
 * Created by simon on 17/02/17.
 */
public class Main {

    public static void main(String[] args) {
    	
    	Board board = new Board();

    	Player p00 = new Player(0);
    	Player p01 = new Player(1);

		board.setPiece(0, 0, new Queen(p00));

		ArrayList<Board> alb = board.getSuccessors();

		System.out.println(board.solutionSteps(board));

		GameUI gui = new GameUI(board);
        gui.launch();

    }
}
