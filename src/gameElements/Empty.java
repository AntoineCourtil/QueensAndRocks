package gameElements;

/**
 * Created by simon on 17/02/17.
 */
public class Empty implements Square{
    private Player player;


    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public void setPlayer(Player player) {

    }


    public String toString(){
        //TODO TO STRING
        return "";
    }
}
