package gameElements;

import java.util.Random;

/**
 * Created by simon on 30/03/17.
 */
public class Eval2 implements Eval {
    @Override
    public float getEval(Player player, Board board) {
        System.out.println("EVAL2 : \n"+board.toString());

        int n = 0;
        Player pAdversaire;

        if (player == board.getGame().getPlayer0()) {
            pAdversaire = board.getGame().getPlayer1();
        } else {
            pAdversaire = board.getGame().getPlayer0();
        }

        Random random = new Random();

        for (int i = 0; i < 5; i++) {

            System.out.println(i);

            Board board1 = board.clone();



            while(!board1.isSolution()) {

                Player pCourant = player;

                boolean coupOK = false;

                System.out.println(board1.toStringAccess2(pCourant));

                while (!coupOK) {

                    int piece = (int) (Math.random() * (1));
                    int x = (int) (Math.random() * board.getSize()) ;
                    int y = (int) (Math.random() * board.getSize()) ;

                    /*System.out.println("piece : "+piece);
                    System.out.println("x : "+x);
                    System.out.println("y: "+y);*/


                    if (piece == 0) { //Queen
                        if (board1.isAccessible2(x, y, pCourant)) {
                            coupOK = true;
                            board1.placeQueen2(x, y, pCourant);
                            System.out.println("QUEEN");
                            break;
                        }
                    } else {
                        if (board1.isEmpty(x, y)) {
                            coupOK = true;
                            board1.placeRock2(x, y, pCourant);
                            System.out.println("ROCK");
                            break;
                        }
                    }

                }

                if (pCourant == board.getGame().getPlayer0()) {
                    pCourant = board.getGame().getPlayer1();
                } else {
                    pCourant = board.getGame().getPlayer0();
                }
            }

            if(board1.getScore(player) > board1.getScore(pAdversaire)){
                n++;
            }
        }

        return 2 * n / 5 - 1;
    }
}
