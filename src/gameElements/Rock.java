package gameElements;

/**
 * Created by simon on 17/02/17.
 */
public class Rock implements Square{
    private Player player;


    public Rock(Player p){
        player = p;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {

    }


    public String toString(){
        //TODO TO STRING
        return "R";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isEnemyQueen(Player p) {
        return false;
    }
}
