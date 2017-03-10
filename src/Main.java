import gameElements.*;
import graphics.GameUI;

import java.util.ArrayList;

/**
 * Created by simon on 17/02/17.
 */
import java.util.Date;





public class Main {

    public static void test_time(int taille_board){
        Board board = new Board(taille_board);
        Player p00 = new Player(0);
        Player p01 = new Player(1);

        board.setPiece(0, 0, new Queen(p00));

        long start_time = new Date().getTime();
        board.depthFirstSearch(board);
        long end_time = new Date().getTime();
        System.out.println("--------------------EXECUTION TIME (sizeof board : "+taille_board+") -----------------");
        long execution_time = end_time - start_time;

        //System.out.println(start_time);
        //System.out.println(end_time);
        System.out.println(execution_time + " ms ("+execution_time/1000+" s)");
        System.out.println("----------------------FIN TEST TIME----------------------------");
    }

    public static void test_diffent_time(){
        test_time(8);
        test_time(9);
        test_time(10);
        test_time(11);
        test_time(12);
    }

    public static void main(String[] args) {


        Board board = new Board();

        Player p00 = new Player(0);
        Player p01 = new Player(1);

        board.setPiece(0, 0, new Queen(p00));
        System.out.println(board.solutionSteps(board));

        //ArrayList<Board> alb = board.getSuccessors();

        //test_time(9);

        test_diffent_time();

        GameUI gui = new GameUI(board);
        gui.launch();

    }
}
