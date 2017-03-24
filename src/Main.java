import gameElements.*;
import graphics.GameUI;

import java.util.*;

/**
 * Created by simon on 17/02/17.
 */


public class Main {

    public static void test_time(int taille_board) {
        Board board = new Board(taille_board);
        Player p00 = new Player(0);
        Player p01 = new Player(1);

        board.setPiece(0, 0, new Queen(p00));

        long start_time = new Date().getTime();
        board.depthFirstSearch(board);
        long end_time = new Date().getTime();
        System.out.println("--------------------V1 : EXECUTION TIME (sizeof board : " + taille_board + ") -----------------");
        long execution_time = end_time - start_time;

        //System.out.println(start_time);
        //System.out.println(end_time);
        System.out.println(execution_time + " ms (" + execution_time / 1000 + " s)");
        System.out.println("----------------------FIN V1 TEST TIME----------------------------");

        board = new Board(taille_board);
        board.setPiece(0, 0, new Queen(p00));
        start_time = new Date().getTime();
        board.depthFirstSearch2(board);
        end_time = new Date().getTime();
        System.out.println("--------------------V2 : EXECUTION TIME (sizeof board : " + taille_board + ") -----------------");
        execution_time = end_time - start_time;

        //System.out.println(start_time);
        //System.out.println(end_time);
        System.out.println(execution_time + " ms (" + execution_time / 1000 + " s)");
        System.out.println("----------------------FIN V2 TEST TIME----------------------------");

        board = new Board(taille_board);
        board.setPiece(0, 0, new Queen(p00));

        start_time = new Date().getTime();
        board.depthFirstSearchArray();
        end_time = new Date().getTime();
        System.out.println("--------------------V_ARRAY : EXECUTION TIME (sizeof board : " + taille_board + ") -----------------");
        execution_time = end_time - start_time;

        //System.out.println(start_time);
        //System.out.println(end_time);
        System.out.println(execution_time + " ms (" + execution_time / 1000 + " s)");
        System.out.println("----------------------FIN V_ARRAY TEST TIME----------------------------");
    }

    public static void test_diffent_time() {
        test_time(8);
        test_time(9);
        test_time(10);
        test_time(11);
        test_time(12);
    }

    public static void test_different_time_with_three_V() {

    }

    /**
     *
     * @param firstRock
     *
     * Si firstRock = true, alors le premier coup doit etre un Rock
     */

    public static void playWithoutGUI(boolean firstRock) {



        Board board = new Board(2);

        Player p0 = new Player(0);
        Player p1 = new Player(1);



        int tour = 0;
        boolean sonTour = true;



        Player pCourant = p0;
        Player pGagnant = p0;

        while (!board.isFinal()) {

            sonTour = true;

            System.out.println("\n\n==== TOUR " + tour + " ====\n");
            System.out.println("Tour de : Joueur " + pCourant.getNumber());

            System.out.println("\nScore de Joueur " + p0.getNumber() + " : " + board.getScore(p0));
            System.out.println("Score de Joueur " + p1.getNumber() + " : " + board.getScore(p1));


            System.out.println("\n\n" + board.toStringAccess2(pCourant));

            Scanner scanner = new Scanner(System.in);

            while (sonTour) {


                System.out.println("Veuillez saisir un coup :");
                System.out.println("Usage : Q/R line column");
                String str = scanner.nextLine();
                String[] entree = str.split(" ");

                System.out.println(entree[0]);

                if (entree.length == 3) {

                    int line = Integer.parseInt(entree[1]);
                    int col = Integer.parseInt(entree[2]);


                    if (entree[0].equals("Q") && !firstRock) { //joueur courant joue une Queen
                        if (board.isAccessible2(line, col, pCourant) && board.placeQueen2(line, col, pCourant)) {
                            tour++;
                            sonTour = false;
                            firstRock = false;
                            System.out.println("Queen placée en " + line + "," + col);
                        } else {
                            System.out.println("Case inacessible, rejouer\n");
                        }
                    }

                    if (entree[0].equals("R")) { //joueur courant joue un Rock
                        if (board.isEmpty(line, col) && board.placeRock2(line, col, pCourant)) {
                            firstRock = false;
                            tour++;
                            sonTour = false;
                            System.out.println("Rock placé en " + line + "," + col);
                        } else {
                            System.out.println("Case inacessible, rejouer\n");
                        }
                    }

                    if (entree[0].equals("Q") && entree[0].equals("R")) { //joueur courant joue autre chose
                        System.out.println("Coup non valide, rejouer\n");
                    }
                } else { //joueur courant joue mauvais coup
                    System.out.println("Coup non valide, rejouer\n");
                }
            }

            if (pCourant.getNumber() == p0.getNumber()) {
                pCourant = p1;
                pGagnant = p0;
            } else {
                pCourant = p0;
                pGagnant = p1;
            }

        }

        System.out.println("\n\n==== PARTIE TERMINEE ====");

        System.out.println(board.toString());


        System.out.println("Gagnant : Joueur " + pGagnant.getNumber());
        System.out.println("Score : " + board.getScore(pGagnant));
        System.out.println("Perdant : Joueur " + pCourant.getNumber());
        System.out.println("Score : " + board.getScore(pCourant));

    }


    public static void joueurVSordi(boolean firstRock){

        System.out.println("Un joueur vs Ordinateur");

        Board board = new Board(3);

        Player p0 = new Player(0);
        Player pMachine = new Player(1);


        int tour = 0;
        boolean sonTour = true;

        Player pCourant = p0;
        Player pGagnant = p0;

        while (!board.isFinal()) {

            sonTour = true;

            System.out.println("\n\n==== TOUR " + tour + " ====\n");
            System.out.println("Tour de : Joueur " + pCourant.getNumber());

            System.out.println("\nScore de Joueur " + p0.getNumber() + " : " + board.getScore(p0));
            System.out.println("Score de Joueur " + pMachine.getNumber() + " : " + board.getScore(pMachine));


            System.out.println("\n\n" + board.toStringAccess2(pCourant));

            Scanner scanner = new Scanner(System.in);

            if(pCourant.getNumber() == 1){
                board = board.minimax(board, pMachine, 2, new Eval0(), firstRock);
                firstRock = false;
            }else {
                while (sonTour) {

                    System.out.println("Veuillez saisir un coup :");
                    System.out.println("Usage : Q/R line column");
                    String str = scanner.nextLine();
                    String[] entree = str.split(" ");

                    System.out.println(entree[0]);

                    if (entree.length == 3) {

                        int line = Integer.parseInt(entree[1]);
                        int col = Integer.parseInt(entree[2]);


                        if (entree[0].equals("Q") && !firstRock ) { //joueur courant joue une Queen
                            if (board.isAccessible2(line, col, pCourant) && board.placeQueen2(line, col, pCourant)) {
                                tour++;
                                sonTour = false;
                                System.out.println("Queen placée en " + line + "," + col);
                                firstRock = false;
                            } else {
                                System.out.println("Case inacessible, rejouer\n");
                            }
                        }

                        if (entree[0].equals("R")) { //joueur courant joue un Rock
                            if (board.isEmpty(line, col) && board.placeRock2(line, col, pCourant)) {
                                firstRock = false;
                                tour++;
                                sonTour = false;
                                System.out.println("Rock placé en " + line + "," + col);
                            } else {
                                System.out.println("Case inacessible, rejouer\n");
                            }
                        }

                        if (entree[0].equals("Q") && entree[0].equals("R")) { //joueur courant joue autre chose
                            System.out.println("Coup non valide, rejouer\n");
                        }
                    } else { //joueur courant joue mauvais coup
                        System.out.println("Coup non valide, rejouer\n");
                    }
                }
            }

            if (pCourant.getNumber() == p0.getNumber()) {
                pCourant = pMachine;
                pGagnant = p0;
            } else {
                pCourant = p0;
                pGagnant = pMachine;
            }

        }

        System.out.println("\n\n==== PARTIE TERMINEE ====");

        System.out.println(board.toString());


        System.out.println("Gagnant : Joueur " + pGagnant.getNumber());
        System.out.println("Score : " + board.getScore(pGagnant));
        System.out.println("Perdant : Joueur " + pCourant.getNumber());
        System.out.println("Score : " + board.getScore(pCourant));

    }


    public static void MachineVSMachine(boolean firstRock){

        System.out.println("Un joueur vs Ordinateur");

        Board board = new Board(3);

        Player p0 = new Player(0);
        Player pMachine = new Player(1);


        int tour = 0;
        boolean sonTour = true;

        Player pCourant = p0;
        Player pGagnant = p0;

        while (!board.isFinal()) {

            sonTour = true;

            tour++;

            System.out.println("\n\n==== TOUR " + tour + " ====\n");
            System.out.println("Tour de : Joueur " + pCourant.getNumber());

            System.out.println("\nScore de Joueur " + p0.getNumber() + " : " + board.getScore(p0));
            System.out.println("Score de Joueur " + pMachine.getNumber() + " : " + board.getScore(pMachine));


            System.out.println("\n\n" + board.toStringAccess2(pCourant));


            if(pCourant.getNumber() == 1){
                board = board.minimax(board, pMachine, 2, new Eval0(), firstRock);
            }else {
                board = board.minimax(board, p0, 2, new Eval0(), firstRock);
            }

            firstRock = false;

            if (pCourant.getNumber() == p0.getNumber()) {
                pCourant = pMachine;
                pGagnant = p0;
            } else {
                pCourant = p0;
                pGagnant = pMachine;
            }



        }

        System.out.println("\n\n==== PARTIE TERMINEE ====");

        System.out.println(board.toString());


        System.out.println("Gagnant : Joueur " + pGagnant.getNumber());
        System.out.println("Score : " + board.getScore(pGagnant));
        System.out.println("Perdant : Joueur " + pCourant.getNumber());
        System.out.println("Score : " + board.getScore(pCourant));
    }


    public static void main(String[] args) {


        Board board = new Board(4);

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

        //test_diffent_time();

        //MachineVSMachine(true);

        GameUI gui = new GameUI(board);
        gui.launch();

    }
}
