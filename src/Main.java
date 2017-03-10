import gameElements.*;
import graphics.GameUI;

import java.util.ArrayList;

/**
 * Created by simon on 17/02/17.
 */
import java.util.Date;

public class Main {

    public static void main(String[] args) {
    	
    	Board board = new Board();

    	Player p00 = new Player(0);
    	Player p01 = new Player(1);

		board.setPiece(0, 0, new Queen(p00));

		ArrayList<Board> alb = board.getSuccessors();
		System.out.println(board.solutionSteps(board));

		Date d = new Date();
		long start_time  = d.getTime();
		alb = board.depthFirstSearch(board);
		long end_time = d.getTime();
		System.out.println("--------------------EXECUTION TIME-----------------");
		long execution_time = end_time-start_time;

		//System.out.println(start_time);
		//System.out.println(end_time);
		System.out.println(execution_time+ " ms");



		GameUI gui = new GameUI(board);
        gui.launch();

    }
}
