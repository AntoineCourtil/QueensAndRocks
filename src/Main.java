import gameElements.*;
import graphics.GameUI;

import java.util.ArrayList;

/**
 * Created by simon on 17/02/17.
 */
import java.util.Arrays;
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
        System.out.println("--------------------V1 : EXECUTION TIME (sizeof board : "+taille_board+") -----------------");
        long execution_time = end_time - start_time;

        //System.out.println(start_time);
        //System.out.println(end_time);
        System.out.println(execution_time + " ms ("+execution_time/1000+" s)");
        System.out.println("----------------------FIN V1 TEST TIME----------------------------");

        board = new Board(taille_board);
        board.setPiece(0, 0, new Queen(p00));
        start_time = new Date().getTime();
        board.depthFirstSearch2(board);
        end_time = new Date().getTime();
        System.out.println("--------------------V2 : EXECUTION TIME (sizeof board : "+taille_board+") -----------------");
        execution_time = end_time - start_time;

        //System.out.println(start_time);
        //System.out.println(end_time);
        System.out.println(execution_time + " ms ("+execution_time/1000+" s)");
        System.out.println("----------------------FIN V2 TEST TIME----------------------------");

        board = new Board(taille_board);
        board.setPiece(0, 0, new Queen(p00));

        start_time = new Date().getTime();
        board.depthFirstSearchArray();
        end_time = new Date().getTime();
        System.out.println("--------------------V_ARRAY : EXECUTION TIME (sizeof board : "+taille_board+") -----------------");
        execution_time = end_time - start_time;

        //System.out.println(start_time);
        //System.out.println(end_time);
        System.out.println(execution_time + " ms ("+execution_time/1000+" s)");
        System.out.println("----------------------FIN V_ARRAY TEST TIME----------------------------");
    }

    public static void test_diffent_time(){
        test_time(8);
        test_time(9);
        test_time(10);
        test_time(11);
        test_time(12);
    }

    public static void test_different_time_with_three_V(){

    }

    public static void main(String[] args) {


        Board board = new Board();

        Player p00 = new Player(0);
        Player p01 = new Player(1);

        //board.setPiece(0, 0, new Queen(p00));
        //System.out.println(board.solutionSteps(board));



        //TEST BOARD TO ARRAY OK
        //int[] a = board.boardToArray();
        //System.out.println(Arrays.toString(a));

        //TEST ARRAY TO BOARD OK
        //Board res = board.arrayToBoard(a);
        //System.out.println(res.toString());


        //ArrayList<Board> alb = board.getSuccessors();

        //test_time(9);

        test_diffent_time();

        GameUI gui = new GameUI(board);
        gui.launch();

    }
}
