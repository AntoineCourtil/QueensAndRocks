package gameElements;

/**
 * Created by simon on 30/03/17.
 */
public class Eval1 implements Eval{
    @Override
    public float getEval(Player player, Board board) {
        Player adversaire;
        if(player == board.getGame().getPlayer1()){
            adversaire = board.getGame().getPlayer0();
        }else{
            adversaire = board.getGame().getPlayer1();
        }
        int accessibles =  board.numberOfAccessible2(player);
        int adv_accessibles = board.numberOfAccessible2(adversaire);

        int numberIrrecoverable = board.numberOfIrrecoverableEnemy(player);//Recup du nb cases irréc (reines adverses)
        int adv_numberIrrecoverable = board.numberOfIrrecoverableEnemy(adversaire);

        int totalPlayer = accessibles - adv_accessibles + adv_numberIrrecoverable - numberIrrecoverable;
        int adv_totalPlayer = accessibles - adv_accessibles + adv_numberIrrecoverable - numberIrrecoverable;

        return totalPlayer - adv_totalPlayer;
    }
}
