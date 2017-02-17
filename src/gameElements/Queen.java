package gameElements;

/**
 * Created by simon on 17/02/17.
 */
public class Queen implements Square{

    private Player player;


    public Queen(Player p){
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
        return "Q";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
