package gameElements;

/**
 * Created by simon on 17/02/17.
 */
public class Empty implements Square{
    private Player player;


    public Empty(Player p){
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
        return "E";
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
